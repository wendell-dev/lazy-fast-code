package lazy.fast.code.core;

import lazy.fast.code.core.result.ResultMsg;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
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

    /**
     * 等同于：
     * <pre>
     * @GetMapping("/ok")
     * public String ok() {
     *     return MsgInfo.ok();
     * }
     * </pre>
     */
    @GetMapping("/ok")
    public ResponseEntity<String> ok() {
        return ResponseEntity.ok(ResultMsg.ok());
    }

    @GetMapping("/no-content")
    public ResponseEntity hello() {
        return ResponseEntity.noContent().build();
    }

}
