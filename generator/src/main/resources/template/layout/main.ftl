package ${basePackageName};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring4all.swagger.EnableSwagger2Doc;

/**
 * 启动类
 *
 * @author ${author}
 * @date ${.now?string("yyyy-MM-dd HH:mm")}
 */
@EnableSwagger2Doc
@SpringBootApplication(scanBasePackages = "lazy.fast.code")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
