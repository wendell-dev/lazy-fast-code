package lazy.fast.code.core.exception;

import lazy.fast.code.core.result.MsgEnum;
import lazy.fast.code.core.result.MsgEnumable;
import lazy.fast.code.core.result.ResultMsg;

/**
 * 访问被禁止异常 - HTTP Status 403
 *
 * @author wendell
 */
public class ForbiddenException extends RuntimeException {

    /**
     * {"code":403,"msg":"无访问权限"}
     */
    public ForbiddenException() {
        super(ResultMsg.of(MsgEnum.FORBIDDEN));
    }

    /**
     * {"code":403,"msg":"${message}"}
     *
     * @param message
     *            异常简要信息
     */
    public ForbiddenException(String message) {
        super(ResultMsg.of(MsgEnum.FORBIDDEN.code(), message));
    }

    /**
     * HTTP状态码为403的业务异常类，如果需要自定义返回的code和msg，那必须通过实现MsgEnumable的枚举来定义操作
     *
     * {"code":${msgEnum.code},"msg":"${msgEnum.msg}"}
     *
     * @param msgEnum
     *            消息枚举
     */
    public ForbiddenException(MsgEnumable msgEnum) {
        super(ResultMsg.of(msgEnum));
    }

}
