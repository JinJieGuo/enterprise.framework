package common.datasource;

/**
 * @Author XZH
 * @Date 2019/12/5
 */
public enum DataSourceType {
    ERPDATABASE("enterprise-erp"),
    Mysql2("mysql2"),
    Mysql3("mysql3");
    private String name;

    DataSourceType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
