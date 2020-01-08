package enterprise.framework.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.session.data.redis.RedisFlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;

@EnableDiscoveryClient// => 网关
@SpringBootApplication
@ComponentScan(basePackages = {"auth", "cors"})
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

//    @Bean
//    public LettuceConnectionFactory connectionFactory() {
//
//        return new LettuceConnectionFactory();
//    }
//
//    @Bean
//    public HttpSessionIdResolver httpSessionStrategy() {
//        return HeaderHttpSessionIdResolver.authenticationInfo();
//    }

    /**
     * 用于spring session，防止每次创建一个线程
     * @return
     */
    @Bean
    public ThreadPoolTaskExecutor springSessionRedisTaskExecutor(){
        ThreadPoolTaskExecutor springSessionRedisTaskExecutor = new ThreadPoolTaskExecutor();
        springSessionRedisTaskExecutor.setCorePoolSize(8);
        springSessionRedisTaskExecutor.setMaxPoolSize(16);
        springSessionRedisTaskExecutor.setKeepAliveSeconds(10);
        springSessionRedisTaskExecutor.setQueueCapacity(1000);
        springSessionRedisTaskExecutor.setThreadNamePrefix("Spring session redis executor thread: ");
        return springSessionRedisTaskExecutor;
    }

//    @Bean
//    public CookieSerializer httpSessionIdResolver()
//    {
//        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
//
//        cookieSerializer.setCookieName("SESSION");
//
//        cookieSerializer.setUseHttpOnlyCookie(false);
//
////        cookieSerializer.setSameSite(null);
//
//        return cookieSerializer;
//    }

}
