/*******************************************************************************
 * Copyright(c) 2019 Enterprise.Framework All rights reserved. / Confidential
 * ClassInformation:
 *		1.ProgramName:Enterprise.Framework.Utility
 *		2.ClassName:StrHandler.cs
 *		3.FunctionDescription:字符串转换 — 包括Json、及所有字符串的处理
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
 *		gl				 2019-11-29		      1.00					新建
 *******************************************************************************/

package enterprise.framework.utility.transform;

public class StrHandler {

    /**
     * 字符串转为二进制字符串
     * @param str
     * @return
     */
    public String toBinary(String str){
        char[] strChar = str.toCharArray();
        String result = "";
        for (int i = 0; i < strChar.length; i++) {
            result += Integer.toBinaryString(strChar[i]) + "-";
        }

        return result.substring(0, result.length() - 1);
    }

    /**
     * 二进制字符串转为字符
     * @param str
     * @return
     */
    public String binaryToStr(String str){
        String[] tempStr = StrToStrArray(str.replace("-", " "));
        char[] tempChar = new char[tempStr.length];
        for (int i = 0; i < tempStr.length; i++) {
            tempChar[i] = BinstrToChar(tempStr[i]);
        }
        return String.valueOf(tempChar);
    }

    // 将初始二进制字符串转换成字符串数组，以空格相隔
    private String[] StrToStrArray(String str) {
        return str.split(" ");
    }

    // 将二进制字符串转换为char
    private char BinstrToChar(String binStr) {
        int[] temp = BinstrToIntArray(binStr);
        int sum = 0;
        for (int i = 0; i < temp.length; i++) {
            sum += temp[temp.length - 1 - i] << i;
        }
        return (char) sum;
    }

    // 将二进制字符串转换成int数组
    private int[] BinstrToIntArray(String binStr) {
        char[] temp = binStr.toCharArray();
        int[] result = new int[temp.length];
        for (int i = 0; i < temp.length; i++) {
            result[i] = temp[i] - 48;
        }
        return result;
    }
}
