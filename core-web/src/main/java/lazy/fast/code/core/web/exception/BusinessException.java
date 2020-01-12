package lazy.fast.code.core.web.exception;

import lazy.fast.code.core.web.result.MsgEnumable;
import lazy.fast.code.core.web.result.ResultMsg;
import org.springframework.http.HttpStatus;

/**
 * 自定义业务异常类 - HTTP Status 400
 *
 * @author wendell
 */
public class BusinessException extends BaseUnCheckException {

    private static final HttpStatus HTTP_STATUS = HttpStatus.BAD_REQUEST;

    /**
     * {"code":400,"msg":"操作失败"}
     */
    public BusinessException() {
        super(HTTP_STATUS, ResultMsg.fail());
    }

    /**
     * {"code":400,"msg":"${message}"}
     *
     * @param message
     *            异常简要信息
     */
    public BusinessException(String message) {
        super(HTTP_STATUS, ResultMsg.fail(message));
    }

    /**
     * {"code":400,"msg":"${message}","detailMsg":"${detailMsg}"}
     *
     * @param message
     *            异常简要信息
     * @param detailMsg
     *            异常详细信息
     */
    public BusinessException(String message, String detailMsg) {
        super(HTTP_STATUS, ResultMsg.fail(message, detailMsg));
    }

    /**
     * HTTP状态码为400的业务异常类，如果需要自定义返回的code和msg，那必须通过实现MsgEnumable的枚举来定义操作
     *
     * {"code":${msgEnum.code},"msg":"${msgEnum.msg}"}
     *
     * @param msgEnum
     *            消息枚举
     */
    public BusinessException(MsgEnumable msgEnum) {
        super(HTTP_STATUS, ResultMsg.of(msgEnum));
    }

}
