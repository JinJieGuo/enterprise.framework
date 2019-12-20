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

public class JwtHeader {

    private String alg;

    private String typ;

    public void setAlg(String alg) {
        this.alg = alg;
    }

    public String getAlg() {
        return alg;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getTyp() {
        return typ;
    }
}
