package lazy.fast.code.core.web.exception;

import lazy.fast.code.core.web.result.MsgEnum;
import lazy.fast.code.core.web.result.ResultMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

/**
 * 全局异常处理器 - 只针对返回JSON数据交互格式(标记为@RestController类) <br />
 * 应用应该创建一个自定义全局异常处理器,并继承此类,让此类定义的ExceptionHandler生效，如：
 * 
 * <pre>
 * &#64;RestControllerAdvice
 * public class GlobalRestExceptionHandler extends AbstractRestExceptionHandler {
 * 
 * }
 * </pre>
 *
 * @author wendell
 */
@Slf4j
public abstract class AbstractRestExceptionHandler {

    @ExceptionHandler(value = NoContentNotException.class)
    public ResponseEntity<Void> notContentNotExceptionHandler() {
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(value = SystemException.class)
    public ResponseEntity<ResultMsg> systemExceptionHandler(SystemException e) {
        log.error(e.getMessage(), e);
        return baseUnCheckHandler(e);
    }

    @ExceptionHandler(value = BaseUnCheckException.class)
    public ResponseEntity<ResultMsg> baseUnCheckHandler(BaseUnCheckException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(e.getResultMsg());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ResultMsg> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        StringBuilder sb = new StringBuilder(32);
        allErrors.forEach(oe -> sb.append(oe.getDefaultMessage()).append(";"));
        String body = sb.toString();
        if (body.endsWith(";")) {
            body = body.substring(0, body.length() - 1);
        }
        return ResponseEntity.badRequest().body(ResultMsg.fail(body));
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ResultMsg> handleBindException(BindException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        assert fieldError != null;
        return ResponseEntity.badRequest().body(ResultMsg.fail(fieldError.getDefaultMessage()));
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ResultMsg> illegalArgumentExceptionHandler(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(ResultMsg.fail(e.getMessage()));
    }

    @ExceptionHandler(value = IllegalStateException.class)
    public ResponseEntity<ResultMsg> illegalStateExceptionHandler(IllegalStateException e) {
        return ResponseEntity.badRequest().body(ResultMsg.fail(e.getMessage()));
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity<ResultMsg>
        missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException e) {
        return ResponseEntity.badRequest().body(ResultMsg.fail(e.getParameterName() + "参数缺失"));
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResultMsg> typeMismatchExceptionHandler(MethodArgumentTypeMismatchException e) {
        assert e.getRequiredType() != null;
        return ResponseEntity.badRequest()
            .body(ResultMsg.fail("参数类型不匹配,参数" + e.getName() + "类型应该为" + e.getRequiredType().getSimpleName()));
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ResultMsg> methodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(ResultMsg.fail(e.getMethod() + "请求方法不支持"));
    }

    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ResultMsg> httpMediaTypeNotSupportedExceptionHandler(HttpMediaTypeNotSupportedException e) {
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
            .body(ResultMsg.fail(e.getContentType() + "媒体类型不支持"));
    }

    @ExceptionHandler(value = RestClientException.class)
    public ResponseEntity<ResultMsg> restClientExceptionHandler(RestClientException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(ResultMsg.of(MsgEnum.REST_ERROR));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ResultMsg> exceptionHandler(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultMsg.error());
    }

}
