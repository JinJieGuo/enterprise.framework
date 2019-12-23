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
 *		gl				 2019-12-21		        1.00					新建
 *******************************************************************************/

package enterprise.framework.utility.database;

import enterprise.framework.utility.transform.StrHandler;

import java.lang.reflect.*;

public class ReflectProvider<T> {

    /**
     * 动态生成新增sql
     *
     * @param modelDO
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public String generateSaveSql(T modelDO) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        try {
            Field[] fields = modelDO.getClass().getDeclaredFields();
            Type type = this.getClass().getGenericSuperclass();
            String tableName = getTableName(modelDO, type);

            StrHandler strHandler = new StrHandler();
            StringBuilder paramFields = new StringBuilder(" (");
            StringBuilder paramFieldsValue = new StringBuilder("(");
            boolean flag = true;
            for (int index = 0; index < fields.length; index++) {
                if (fields[index].isAnnotationPresent(PrimaryKey.class)) {
                    continue;
                }

                String propertyName = fields[index].getName();
                String tempName = strHandler.strCapitals(propertyName, "_");
                Method m = modelDO.getClass().getMethod("get" + tempName); // 构造get方法
                Object propertyValue = m.invoke(modelDO); // 调用get方法获取属性值

                if (propertyValue != null) {
                    if (flag) {
                        paramFields.append(propertyName);
                        paramFieldsValue.append("#{" + propertyName + "}");
                        flag = false;
                    } else {
                        paramFields.append(", " + propertyName);
                        paramFieldsValue.append(", #{" + propertyName + "}");
                    }

                }
            }

            paramFields.append(")");
            paramFieldsValue.append(")");
            StringBuilder sqlStr = new StringBuilder("INSERT INTO " + tableName);

            sqlStr.append(paramFields + " VALUES ");
            sqlStr.append(paramFieldsValue);

            return sqlStr.toString();
        } catch (Exception error) {
            return "";
        }
    }

    /**
     * 动态生成更新sql
     *
     * @param modelDO
     * @return
     */
    public String generateUpdateSql(T modelDO) {
        try {
            Field[] fields = modelDO.getClass().getDeclaredFields();
            Type type = this.getClass().getGenericSuperclass();
            String tableName = getTableName(modelDO, type);

            StrHandler strHandler = new StrHandler();
            StringBuilder paramFields = new StringBuilder("");
            StringBuilder conditions = new StringBuilder();
            boolean flag = true;
            for (int index = 0; index < fields.length; index++) {
                String propertyName = fields[index].getName();
                String tempName = strHandler.strCapitals(propertyName, "_");
                Method m = modelDO.getClass().getMethod("get" + tempName); // 构造get方法
                Object propertyValue = m.invoke(modelDO); // 调用get方法获取属性值

                if (fields[index].isAnnotationPresent(PrimaryKey.class)) {
                    PrimaryKey bananaAnnotation = fields[index].getAnnotation(PrimaryKey.class);
                    String primaryKey = bananaAnnotation.value();
                    conditions.append(" WHERE " + primaryKey + " = " + "#{" + primaryKey + "} ");
                }

                if (propertyValue != null) {

                    if (flag) {
                        paramFields.append(propertyName + " = #{" + propertyName + "}");
                        flag = false;
                    } else {
                        paramFields.append(", " + propertyName + " = #{" + propertyName + "}");
                    }
                }
            }

            StringBuilder strSql = new StringBuilder("UPDATE " + tableName + " SET " + paramFields + conditions);

            return strSql.toString();
        } catch (Exception error) {
            return "";

        }
    }

    public String generateSelectOneSql() {
        return "";
    }

    /**
     * 根据注解获取表名
     *
     * @param modelDO
     * @param type
     * @return
     */
    private String getTableName(T modelDO, Type type) {
        String tableName = "";
        Type[] typeArr = ((ParameterizedType) type).getActualTypeArguments();
        Class<T> mtClass = (Class<T>) (typeArr[0]);
        if (mtClass.isAnnotationPresent(Table.class)) {
            Table getAnnotation = mtClass.getAnnotation(Table.class);
            tableName = getAnnotation.value();
        } else {
            tableName = modelDO.getClass().getSimpleName();
        }
        return tableName;
    }

    /**
     * 根据注解获取主键名称
     *
     * @param modelDO
     * @param type
     * @return
     */
    private String getPrimaryKey(T modelDO, Type type) {
        String primaryKey = "";
        Type[] typeArr = ((ParameterizedType) type).getActualTypeArguments();
        Class<T> mtClass = (Class<T>) (typeArr[0]);
        Field[] fields = mtClass.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(PrimaryKey.class)) {
                PrimaryKey bananaAnnotation = field.getAnnotation(PrimaryKey.class);
                primaryKey = bananaAnnotation.value();
            } else {
                primaryKey = field.getName();
            }
        }
        return primaryKey;
    }
}
