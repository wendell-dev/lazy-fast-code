package lazy.fast.code.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring4all.swagger.EnableSwagger2Doc;

import lazy.fast.code.core.result.ResultMsg;

/**
 * 启动类
 *
 * @author wendell
 */
@RestController
@EnableSwagger2Doc
@SpringBootApplication(scanBasePackages = "lazy.fast.code")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * 返回一个提示操作成功的情况, 等同于：
     * 
     * <pre>
     * &#64;GetMapping("/ok")
     * public ResultMsg ok() {
     *     return ResultMsg.ok();
     * }
     * </pre>
     */
    @GetMapping("/ok")
    public ResponseEntity<ResultMsg> ok() {
        return ResponseEntity.ok(ResultMsg.ok());
    }

    /**
     * 返回一个提示操作成功的情况
     * 
     * <pre>
     *     {"code":200,"msg":"操作成功"}
     * </pre>
     */
    @GetMapping("/ok1")
    public ResultMsg ok1() {
        return ResultMsg.ok();
    }

    /**
     * 返回一个可用值的情况, 如一个操作ID之类
     *
     * <pre>
     *     {"code":200,"msg":"${msg}"}
     * </pre>
     */
    @GetMapping("/ok2")
    public ResultMsg ok2() {
        return ResultMsg.ok(1L);
    }

    /**
     * 无返回值
     */
    @GetMapping("/no-content")
    public ResponseEntity hello() {
        return ResponseEntity.noContent().build();
    }

}
