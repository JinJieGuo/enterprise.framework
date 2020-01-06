package common.datasource;

/**
 * @Author XZH
 * @Date 2019/12/5
 */

public class JdbcContextHolder {

    private final static ThreadLocal<Object> local = new ThreadLocal<>();

    public static void putDataSource(String name) {
        System.out.println("putDataSource:" + name);
        local.set(name);
    }

    public static Object getDataSource() {
        return local.get();
    }

    public static void removeDataSource() {
        local.remove();
    }
}
