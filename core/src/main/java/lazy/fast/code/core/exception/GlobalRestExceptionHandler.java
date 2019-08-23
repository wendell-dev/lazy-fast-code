package lazy.fast.code.core.exception;

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
 * 全局异常处理器 - 只针对返回JSON数据交互格式
 *
 * @author wendell
 */
@Slf4j
@RestControllerAdvice
public class GlobalRestExceptionHandler {

    @ExceptionHandler(value = ServiceException.class)
    public ResponseEntity<String> serviceExceptionHandler(ServiceException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(value = AuthException.class)
    public ResponseEntity<String> authExceptionHandler(AuthException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    @ExceptionHandler(value = ForbiddenException.class)
    public ResponseEntity<String> forbiddenExceptionHandler(ForbiddenException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Void> notFoundExceptionHandler() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(value = NoContentNotException.class)
    public ResponseEntity<Void> notContentNotExceptionHandler() {
        return ResponseEntity.noContent().build();
    }





    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<String> illegalArgumentExceptionHandler(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(ErrorMsg.fail(e.getMessage()));
    }

    @ExceptionHandler(value = IllegalStateException.class)
    public ResponseEntity<String> illegalStateExceptionHandler(IllegalStateException e) {
        return ResponseEntity.badRequest().body(ErrorMsg.fail(e.getMessage()));
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity<String>
        missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException e) {
        return ResponseEntity.badRequest().body(ErrorMsg.fail(e.getParameterName() + "参数缺失"));
    }

    @ExceptionHandler(value = TypeMismatchException.class)
    public ResponseEntity<String> typeMismatchExceptionHandler(TypeMismatchException e) {
        return ResponseEntity.badRequest()
            .body(ErrorMsg.fail("参数类型不匹配,参数" + e.getPropertyName() + "类型应该为" + e.getRequiredType()));
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> methodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(ErrorMsg.fail(e.getMethod() + "请求方法不支持"));
    }

    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<String> httpMediaTypeNotSupportedExceptionHandler(HttpMediaTypeNotSupportedException e) {
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
            .body(ErrorMsg.fail(e.getContentType() + "媒体类型不支持"));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        StringBuilder sb = new StringBuilder(10);
        allErrors.stream().forEach(oe -> sb.append(oe.getDefaultMessage()).append(";"));
        String body = sb.toString();
        if (body.endsWith(";")) {
            body = body.substring(0, body.length() - 1);
        }
        return ResponseEntity.badRequest().body(ErrorMsg.fail(body));
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<String> handleBindException(BindException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        assert fieldError != null;
        return ResponseEntity.badRequest().body(ErrorMsg.fail(fieldError.getDefaultMessage()));
    }

    @ExceptionHandler(value = RestClientException.class)
    public ResponseEntity<String> restClientExceptionHandler(RestClientException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(ErrorMsg.of(MsgEnum.RPC_ERROR));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorMsg.error());
    }

}
