/*******************************************************************************
 * Copyright(c) 2019 Enterprise.Framework All rights reserved. / Confidential
 * ClassInformation:
 *		1.ProgramName:Enterprise.Framework.Core
 *		2.ClassName:HttpManager.cs
 *		3.FunctionDescription:核心组件 — 模拟请求处理器
 *		4.Call:
 *		5.CalledBy:
 *		6.TableAccessed:
 *		7.TableUpdated:
 *		8.Input:
 *		9.Output:
 *	    10.Return:
 *       11.Others:
 * EditResume:
 *	   Author				Date			  version			   ChangeContent 
 *		gl				 2019-12-06		      1.00					新建
 *******************************************************************************/

package enterprise.framework.core.token;

import com.alibaba.fastjson.JSON;
import enterprise.framework.core.http.HttpResponse;
import enterprise.framework.core.redis.RedisHandler;
import enterprise.framework.utility.generaltools.TimeStampHandler;
import enterprise.framework.utility.generaltools.TimeTypeEnum;
import enterprise.framework.utility.security.Base64Utils;
import enterprise.framework.utility.security.RSAUtils;
import enterprise.framework.utility.transform.StrHandler;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Component
public class TokenManager implements ITokenManager {

    TimeStampHandler timeStampHandler = new TimeStampHandler();

    @Autowired
    private static RedisTemplate redisTemplate;

    @Autowired(required = false)
    private void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }

    /**
     * 创建令牌
     *
     * @param userId       令牌绑定的用户
     * @param keyMap       密钥对
     * @param time         令牌有效时长
     * @param timeTypeEnum 时长类型 秒|分|时|日
     * @return
     * @throws Exception
     */
    @Override
    public TokenInfo createToken(String userId, Map<String, Object> keyMap, int time, TimeTypeEnum timeTypeEnum) throws Exception {

        JwtHeader jwtHeader = new JwtHeader();
        jwtHeader.setAlg("SHA1");
        jwtHeader.setTyp("JWT");

        JwtPayload jwtPayload = new JwtPayload();
        jwtPayload.setJti(UUID.randomUUID().toString());
        jwtPayload.setSub(HttpSourceEnum.COMPUTERTERMINAL);
        jwtPayload.setAud(userId);
        jwtPayload.setIss(HttpSourceEnum.SERVER);
        jwtPayload.setIat(System.currentTimeMillis());
        jwtPayload.setExp(timeStampHandler.dateTimeToTimeStamp(time, timeTypeEnum));

        String jwtHeaderStr = Base64Utils.encode(JSON.toJSONString(jwtHeader).getBytes("utf-8"));
        String jwtPayloadStr = Base64Utils.encode(JSON.toJSONString(jwtPayload).getBytes("utf-8"));

        JwtInfo jwtInfo = new JwtInfo();
        jwtInfo.setJwtHeader(jwtHeader);
        jwtInfo.setJwtPayload(jwtPayload);

        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setJwtInfo(jwtInfo);
        tokenInfo.setEncrypt_str(jwtHeaderStr + "." + jwtPayloadStr);
        tokenInfo.setPublic_key(RSAUtils.getPublicKey(keyMap));
        tokenInfo.setPrivate_key(RSAUtils.getPrivateKey(keyMap));
        tokenInfo.setSignature(RSAUtils.sign(tokenInfo.getEncrypt_str().getBytes("utf-8"), tokenInfo.getPrivate_key()));
        String signature = Base64Utils.encode(tokenInfo.getSignature().getBytes("utf-8"));
        tokenInfo.setToken_str(tokenInfo.getEncrypt_str() + "." + signature);
        return tokenInfo;
    }

    /**
     * token是否失效
     *
     * @param tokenInfo
     * @return
     */
    public boolean tokenInfoIsInvalid(TokenInfo tokenInfo) {
        try {
            //https://blog.csdn.net/lz199719/article/details/81261336
            //日期比较
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = timeStampHandler.timeStampToDatetime(tokenInfo.getJwtInfo().getJwtPayload().getExp());
            Date invalidDate = simpleDateFormat.parse(date);
            Date currentDate = new Date();
            int compare = invalidDate.compareTo(currentDate);
            boolean response = true;
            switch (compare) {
                case -1://invalidDate小于currentDate
                    response = true;
                    break;
                case 0://相等
                    response = true;
                    break;
                case 1://invalidDate大于currentDate
                    response = false;
                    break;
            }
            return response;
        } catch (Exception error) {
            //如果发生异常则认为令牌已失效
            return true;
        }
    }

    /**
     * 延长token有效时长
     *
     * @param tokenInfo
     * @param time
     * @param timeTypeEnum
     * @return
     */
    public boolean extendTokenTime(TokenInfo tokenInfo, int time, TimeTypeEnum timeTypeEnum) {
        try {
            tokenInfo.getJwtInfo().getJwtPayload().setExp(timeStampHandler.dateTimeToTimeStamp(time, timeTypeEnum));
            RedisHandler redisHandler = new RedisHandler(redisTemplate);
            StrHandler strHandler = new StrHandler();
            HttpResponse httpResponse = redisHandler.set("token_info:" + tokenInfo.getJwtInfo().getJwtPayload().getAud(), strHandler.toBinary(JSON.toJSONString(tokenInfo)));
            return true;
        } catch (Exception error) {
            return false;
        }
    }
}
