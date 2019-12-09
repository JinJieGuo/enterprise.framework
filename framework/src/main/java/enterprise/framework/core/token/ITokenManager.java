package enterprise.framework.core.token;

import java.io.UnsupportedEncodingException;

public interface ITokenManager {

    public TokenInfo createToken(String user_id) throws Exception;

    public String getToken(String token);

    public void removeToken(String token);

}
