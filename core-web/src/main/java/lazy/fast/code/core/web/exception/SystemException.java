package lazy.fast.code.core.web.exception;

import lazy.fast.code.core.web.result.MsgEnumable;
import lazy.fast.code.core.web.result.ResultMsg;

/**
 * 自定义系统异常类 - HTTP Status 500
 *
 * @author wendell
 */
public class SystemException extends BaseUnCheckException {

    /**
     * {"code":500,"msg":"服务器异常,请稍后再试"}
     */
    public SystemException() {
        super(ResultMsg.error());
    }

    /**
     * {"code":500,"msg":"${message}"}
     *
     * @param message
     *            异常简要信息
     */
    public SystemException(String message) {
        super(ResultMsg.error(message));
    }

    /**
     * {"code":500,"msg":"${message}","detailMsg":"${detailMsg}"}
     *
     * @param message
     *            异常简要信息
     * @param detailMsg
     *            异常详细信息
     */
    public SystemException(String message, String detailMsg) {
        super(ResultMsg.error(message, detailMsg));
    }

    /**
     * HTTP状态码为500的业务异常类，如果需要自定义返回的code和msg，那必须通过实现MsgEnumable的枚举来定义操作
     *
     * {"code":${msgEnum.code},"msg":"${msgEnum.msg}"}
     *
     * @param msgEnum
     *            消息枚举
     */
    public SystemException(MsgEnumable msgEnum) {
        super(ResultMsg.of(msgEnum));
    }

}
