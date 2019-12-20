package enterprise.framework.core.token;

import java.util.Map;

public interface ITokenManager {

    TokenInfo createToken(String user_id, Map<String, Object> keyMap) throws Exception;

//    public String getToken(String token);
//
//    public void removeToken(String token);

}
