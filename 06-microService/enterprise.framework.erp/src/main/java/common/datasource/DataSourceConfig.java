package common.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author XZH
 * @Date 2019/12/5
 */
@Configuration
public class DataSourceConfig {


    // -----------------------------------------CbsBase config-------------------------------------

    @Value("${spring.datasource.cbsBase.url}")
    private String dbUrl;

    @Value("${spring.datasource.cbsBase.username}")
    private String username;

    @Value("${spring.datasource.cbsBase.password}")
    private String password;

    @Value("${spring.datasource.cbsBase.driverClassName}")
    private String driverClassName;

    @Value("${spring.datasource.cbsBase.validationQuery}")
    private String validationQuery;

    @Bean(name="dataSourceMysql")
    public DataSource dataSourceCbsBase(){
        System.out.println("----------------cbsBase:" + dbUrl);
        return getDataSource(dbUrl, username, password, driverClassName, validationQuery);
    }

    private DataSource getDataSource(String dbUrl, String username, String password, String driverClassName, String validationQuery) {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(dbUrl);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);
        datasource.setValidationQuery(validationQuery);
        setDruidOptions(datasource); // 设置druid数据源的属性
        return datasource;
    }

    // -----------------------------------------mysql2 config-------------------------------------

    @Value("${spring.datasource.mysql2.url}")
    private String mysql2Url;

    @Value("${spring.datasource.mysql2.username}")
    private String mysql2Username;

    @Value("${spring.datasource.mysql2.password}")
    private String mysql2Password;

    @Value("${spring.datasource.mysql2.driverClassName}")
    private String mysql2DriverClassName;

    @Value("${spring.datasource.mysql2.validationQuery}")
    private String mysql2ValidationQuery;

    @Bean(name="dataSourceMysql2")
    public DataSource dataSourceMysql2(){
        System.out.println("----------------mysql2:" + mysql2Url);

        return getDataSource(mysql2Url, mysql2Username, mysql2Password, mysql2DriverClassName, mysql2ValidationQuery);
    }

    // -----------------------------------------mysql3 config-------------------------------------

    @Value("${spring.datasource.mysql3.url}")
    private String mysql3Url;

    @Value("${spring.datasource.mysql3.username}")
    private String mysql3Username;

    @Value("${spring.datasource.mysql3.password}")
    private String mysql3Password;

    @Value("${spring.datasource.mysql3.driverClassName}")
    private String mysql3DriverClassName;

    @Value("${spring.datasource.mysql3.validationQuery}")
    private String mysql3ValidationQuery;

    @Bean(name="dataSourceMysql3")
    public DataSource dataSourceMysql3(){
        System.out.println("----------------mysql3:" + mysql3Url);

        return getDataSource(
                mysql3Url,
                mysql3Username,
                mysql3Password,
                mysql3DriverClassName,
                mysql3ValidationQuery
        );
    }
    // -----------------------------------------druid config-------------------------------------

    @Value("${spring.datasource.druid.initialSize}")
    private int initialSize;

    @Value("${spring.datasource.druid.minIdle}")
    private int minIdle;

    @Value("${spring.datasource.druid.maxActive}")
    private int maxActive;

    @Value("${spring.datasource.druid.maxWait}")
    private int maxWait;

    @Value("${spring.datasource.druid.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.druid.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${spring.datasource.druid.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${spring.datasource.druid.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${spring.datasource.druid.testOnReturn}")
    private boolean testOnReturn;

    @Value("${spring.datasource.druid.poolPreparedStatements}")
    private boolean poolPreparedStatements;

    @Value("${spring.datasource.druid.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;

    @Value("${spring.datasource.druid.filters}")
    private String filters;

    @Value("{spring.datasource.druid.connectionProperties}")
    private String connectionProperties;

    private void setDruidOptions(DruidDataSource datasource){
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
        }
        datasource.setConnectionProperties(connectionProperties);
    }

    @Bean(name = "dynamicDataSource")
    @Primary  // 优先使用，多数据源
    public DataSource dataSource(){

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        DataSource cbsBase = dataSourceCbsBase();
        DataSource mysql2 = dataSourceMysql2();
        DataSource mysql3 = dataSourceMysql3();

        //设置默认数据源
        dynamicDataSource.setDefaultTargetDataSource(cbsBase);

        //配置多个数据源
        Map<Object,Object> map = new HashMap<>();
        map.put(DataSourceType.CbsBase.getName(),cbsBase);
        map.put(DataSourceType.Mysql2.getName(),mysql2);
        map.put(DataSourceType.Mysql3.getName(),mysql3);
        dynamicDataSource.setTargetDataSources(map);

        return dynamicDataSource;
    }

//    @Bean // 事务管理
//    public PlatformTransactionManager txManager() {
//        return new DataSourceTransactionManager(dataSource());
//    }

//
//    @Bean(name="druidServlet")
//    public ServletRegistrationBean druidServlet() {
//        ServletRegistrationBean reg = new ServletRegistrationBean();
//        reg.setServlet(new StatViewServlet());
//        reg.addUrlMappings("/druid/*");
//        reg.addInitParameter("allow", ""); // 白名单
//        return reg;
//    }
//
//    @Bean(name = "filterRegistrationBean")
//    public FilterRegistrationBean filterRegistrationBean() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(new WebStatFilter());
//        filterRegistrationBean.addUrlPatterns("/*");
//        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
//        filterRegistrationBean.addInitParameter("profileEnable", "true");
//        filterRegistrationBean.addInitParameter("principalCookieName","USER_COOKIE");
//        filterRegistrationBean.addInitParameter("principalSessionName","USER_SESSION");
//        filterRegistrationBean.addInitParameter("DruidWebStatFilter","/*");
//        return filterRegistrationBean;
//    }
}
