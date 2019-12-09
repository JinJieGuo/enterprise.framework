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

package enterprise.framework.utility.generaltools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeStampHandler {

    /**
     * 时间戳转为时间
     *
     * @param timeStamp 时间戳
     * @return
     */
    public String timeStampToDatetime(long timeStamp) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(timeStamp);
//        return simpleDateFormat.format(calendar.getTimeInMillis());
        Date date = new Date(timeStamp);
        return simpleDateFormat.format(date);
    }

    /**
     * 时间转时间戳
     *
     * @param time         时间
     * @param timeTypeEnum 时间类型枚举
     * @return
     */
    public long dateTimeToTimeStamp(int time, TimeTypeEnum timeTypeEnum) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar nowTime = Calendar.getInstance();
        switch (timeTypeEnum) {
            case SECOND:
                nowTime.add(Calendar.SECOND, time);
                break;
            case MINUTE:
                nowTime.add(Calendar.MINUTE, time);
                break;
            case HOUR:
                nowTime.add(Calendar.HOUR, time);
                break;
            case DAY:
                nowTime.add(Calendar.DATE, time);
                break;
        }
        return nowTime.getTimeInMillis();
    }
}
