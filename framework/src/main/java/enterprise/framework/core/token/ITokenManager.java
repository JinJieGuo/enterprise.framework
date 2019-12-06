package enterprise.framework.core.token;

public interface ITokenManager {

    public String createToken(String username);

    public String getToken(String token);

    public void removeToken(String token);

}
