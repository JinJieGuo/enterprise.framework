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
 *		gl				 2019-12-07		      1.00					新建
 *******************************************************************************/

package enterprise.framework.core.token;

public class TokenInfo {

    private JwtInfo jwtInfo;

    private String encrypt_str;

    private String signature;

    private String token_str;

    private String public_key;

    private String private_key;

    public void setJwtInfo(JwtInfo jwtInfo) {
        this.jwtInfo = jwtInfo;
    }

    public JwtInfo getJwtInfo() {
        return jwtInfo;
    }

    public void setEncrypt_str(String encrypt_str) {
        this.encrypt_str = encrypt_str;
    }

    public String getEncrypt_str() {
        return encrypt_str;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSignature() {
        return signature;
    }

    public void setToken_str(String token_str) {
        this.token_str = token_str;
    }

    public String getToken_str() {
        return token_str;
    }

    public void setPublic_key(String public_key) {
        this.public_key = public_key;
    }

    public String getPublic_key() {
        return public_key;
    }

    public void setPrivate_key(String private_key) {
        this.private_key = private_key;
    }

    public String getPrivate_key() {
        return private_key;
    }
}
