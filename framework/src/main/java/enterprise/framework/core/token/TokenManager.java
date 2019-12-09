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
import enterprise.framework.utility.generaltools.TimeStampHandler;
import enterprise.framework.utility.generaltools.TimeTypeEnum;
import enterprise.framework.utility.security.Base64Utils;
import enterprise.framework.utility.security.RSAUtils;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class TokenManager implements ITokenManager {
    private Long tokenExpiration;
    private String tokenSignKey;

    TimeStampHandler timeStampHandler = new TimeStampHandler();


    @Override
    public TokenInfo createToken(String user_id) throws Exception {

        JwtHeader jwtHeader = new JwtHeader();
        jwtHeader.setAlg("SHA1");
        jwtHeader.setTyp("JWT");

        JwtPayload jwtPayload = new JwtPayload();
        jwtPayload.setJti(UUID.randomUUID().toString());
        jwtPayload.setSub(HttpSourceEnum.COMPUTERTERMINAL);
        jwtPayload.setAud(user_id);
        jwtPayload.setIss(HttpSourceEnum.SERVER);
        jwtPayload.setIat(System.currentTimeMillis());
        jwtPayload.setExp(timeStampHandler.dateTimeToTimeStamp(30, TimeTypeEnum.MINUTE));

        String jwtHeaderStr = Base64Utils.encode(JSON.toJSONString(jwtHeader).getBytes("utf-8"));
        String jwtPayloadStr = Base64Utils.encode(JSON.toJSONString(jwtPayload).getBytes("utf-8"));

        JwtInfo jwtInfo = new JwtInfo();
        jwtInfo.setJwtHeader(jwtHeader);
        jwtInfo.setJwtPayload(jwtPayload);

        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setJwtInfo(jwtInfo);
        tokenInfo.setEncrypt_str(jwtHeaderStr + "." + jwtPayloadStr);
        Map<String, Object> keyMap = RSAUtils.genKeyPair(1024);
        tokenInfo.setPublic_key(RSAUtils.getPublicKey(keyMap));
        tokenInfo.setPrivate_key(RSAUtils.getPrivateKey(keyMap));
        tokenInfo.setSignature(RSAUtils.sign(tokenInfo.getEncrypt_str().getBytes("utf-8"), tokenInfo.getPrivate_key()));
        String signature = Base64Utils.encode(tokenInfo.getSignature().getBytes("utf-8"));
        tokenInfo.setToken_str(tokenInfo.getEncrypt_str() + "." + signature);
        return tokenInfo;
    }

    @Override
    public String getToken(String token) {
        String user = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token).getBody().getSubject();
        return user;
    }

    @Override
    public void removeToken(String token) {
        //jwttoken无需删除，客户端扔掉即可。
    }
}
