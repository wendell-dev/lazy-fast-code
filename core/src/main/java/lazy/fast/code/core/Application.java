package lazy.fast.code.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * test
 *
 * @author wendell
 */
@SpringBootApplication
@RestController
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("/")
    public ResponseEntity hello() {
        String s = null;
        //throw new ServiceException("wwww");
        Assert.notNull(s, "参数不能为null");
        return ResponseEntity.noContent().build();
    }

}
