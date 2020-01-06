package common.datasource;


import java.lang.annotation.*;

/**
 * @Author XZH
 * @Date 2019/12/6
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TargetDataSource {

    DataSourceType value() default DataSourceType.ERPDATABASE;
}
