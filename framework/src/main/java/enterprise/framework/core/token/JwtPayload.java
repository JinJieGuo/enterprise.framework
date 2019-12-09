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

public class JwtPayload {

    private String jti;

    private HttpSourceEnum sub;

    private String aud;

    private HttpSourceEnum iss;

    private long iat;

    private long exp;

    private long nbf;

    public void setJti(String jti) {
        this.jti = jti;
    }

    public String getJti() {
        return jti;
    }

    public void setSub(HttpSourceEnum sub) {
        this.sub = sub;
    }

    public HttpSourceEnum getSub() {
        return sub;
    }

    public void setAud(String aud) {
        this.aud = aud;
    }

    public String getAud() {
        return aud;
    }

    public void setIss(HttpSourceEnum iss) {
        this.iss = iss;
    }

    public HttpSourceEnum getIss() {
        return iss;
    }

    public void setIat(long iat) {
        this.iat = iat;
    }

    public long getIat() {
        return iat;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public long getExp() {
        return exp;
    }

    public void setNbf(long nbf) {
        this.nbf = nbf;
    }

    public long getNbf() {
        return nbf;
    }
}
