package common.datasource;

/**
 * @Author XZH
 * @Date 2019/12/5
 */

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * AOP根据注解给上下文赋值
 */
@Aspect
@Component
public class DataSourceAspect {


    // 切入点：service类的方法上(这个包的子包及所有包的里面的以Service结尾的类的任意方法名任意参数的方法，都将被切到)
    //  @Pointcut("execution(* service..*(..))")

    @Pointcut("@annotation(common.datasource.TargetDataSource)")
    public void dataSourcePointCut() {
    }

    @Before("dataSourcePointCut()")
    private void before(JoinPoint joinPoint) {

        Object target = joinPoint.getTarget();
        String method = joinPoint.getSignature().getName();
        Class<?> classZ = target.getClass();
        Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();
        try {
            Method m = classZ.getMethod(method, parameterTypes);

            System.out.println("dataSourcePointCut before:" + method);
            // 如果 m 上存在切换数据源的注解，则根据注解内容进行数据源切换
            if (m != null && m.isAnnotationPresent(TargetDataSource.class)) {
                TargetDataSource data = m.getAnnotation(TargetDataSource.class);
                JdbcContextHolder.putDataSource(data.value().getName());
            } else { // 如果不存在，则使用默认数据源
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 执行完切面后，将线程共享中的数据源名称清空
    @After("dataSourcePointCut()")
    public void after(JoinPoint joinPoint) {
        JdbcContextHolder.removeDataSource();
    }

}
