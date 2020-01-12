package lazy.fast.code.demo.core;

import lazy.fast.code.core.web.exception.AbstractRestExceptionHandler;
import lazy.fast.code.core.web.exception.NoContentNotException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author wendell
 */
@RestControllerAdvice
public class GlobalRestExceptionHandler extends AbstractRestExceptionHandler {

    @Override
    @ExceptionHandler(value = NoContentNotException.class)
    public ResponseEntity<Void> notContentNotExceptionHandler() {
        return ResponseEntity.noContent().build();
    }

}
