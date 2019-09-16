package lazy.fast.code.core.exception;

import lazy.fast.code.core.result.MsgEnum;
import lazy.fast.code.core.result.ResultMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
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
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;

import java.util.List;

/**
 * 全局异常处理器 - 只针对返回JSON数据交互格式(标记为@RestController类)
 *
 * @author wendell
 */
@Slf4j
@RestControllerAdvice
public class GlobalRestExceptionHandler {

    @ExceptionHandler(value = NoContentNotException.class)
    public ResponseEntity<Void> notContentNotExceptionHandler() {
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<ResultMsg> businessExceptionHandler(BusinessException e) {
        return ResponseEntity.badRequest().body(e.getResultMsg());
    }

    @ExceptionHandler(value = AuthException.class)
    public ResponseEntity<ResultMsg> authExceptionHandler(AuthException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getResultMsg());
    }

    @ExceptionHandler(value = ForbiddenException.class)
    public ResponseEntity<ResultMsg> forbiddenExceptionHandler(ForbiddenException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getResultMsg());
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ResultMsg> notFoundExceptionHandler(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getResultMsg());
    }

    @ExceptionHandler(value = SystemException.class)
    public ResponseEntity<ResultMsg> systemExceptionHandler(SystemException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultMsg.error());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ResultMsg> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        StringBuilder sb = new StringBuilder(10);
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

    @ExceptionHandler(value = TypeMismatchException.class)
    public ResponseEntity<ResultMsg> typeMismatchExceptionHandler(TypeMismatchException e) {
        return ResponseEntity.badRequest()
            .body(ResultMsg.fail("参数类型不匹配,参数" + e.getPropertyName() + "类型应该为" + e.getRequiredType()));
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

    @ExceptionHandler(value = BaseUnCheckException.class)
    public ResponseEntity<ResultMsg> baseUnCheckHandler(BaseUnCheckException e) {
        // 其它继承BaseUnCheckException的自定义非受检异常的处理方式
        return ResponseEntity.status(e.getResultMsg().getCode()).body(e.getResultMsg());
    }

}
