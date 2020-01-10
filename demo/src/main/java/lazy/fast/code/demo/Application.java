package lazy.fast.code.demo;

import lazy.fast.code.core.web.exception.NoContentNotException;
import lazy.fast.code.core.web.exception.NotFoundException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring4all.swagger.EnableSwagger2Doc;

import lazy.fast.code.core.web.result.ResultMsg;

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
     * 无返回值, 这是正确的请求，只是没有内容返回，http状态为204
     */
    @GetMapping("/no-content")
    public ResponseEntity<Void> noContent() {
        return ResponseEntity.noContent().build();
    }

    /**
     * 无返回值, 等同于：
     *
     * <pre>
     * &#64;GetMapping("/no-content")
     * public ResponseEntity noContent() {
     *     return ResponseEntity.noContent().build();
     * }
     * </pre>
     */
    @GetMapping("/no-content1")
    public void noContent1() {
        throw new NoContentNotException();
    }

    /**
     * http状态为404, 无返回内容
     */
    @GetMapping("/not-found")
    public ResponseEntity<Void> notFound() {
        return ResponseEntity.notFound().build();
    }

    /**
     * http状态为404,
     * 
     * @return {"code":404,"msg":"资源不存在"}
     */
    @GetMapping("/not-found")
    public ResponseEntity<ResultMsg> notFound1() {
        throw new NotFoundException();
    }

}
