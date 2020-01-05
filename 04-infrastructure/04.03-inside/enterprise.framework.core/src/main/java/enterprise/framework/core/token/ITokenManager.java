package enterprise.framework.core.token;

import enterprise.framework.utility.generaltools.TimeTypeEnum;

import java.util.Map;

public interface ITokenManager {

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
    TokenInfo createToken(String userId, Map<String, Object> keyMap, int time, TimeTypeEnum timeTypeEnum) throws Exception;

    /**
     * token是否失效
     *
     * @param tokenInfo
     * @return
     */
    boolean tokenInfoIsInvalid(TokenInfo tokenInfo);

    /**
     * 延长token有效时长
     *
     * @param tokenInfo
     * @param time
     * @param timeTypeEnum
     * @return
     */
    boolean extendTokenTime(TokenInfo tokenInfo, int time, TimeTypeEnum timeTypeEnum);

}
