package lazy.fast.code.core.web.exception;

import lazy.fast.code.core.web.result.ResultMsg;
import lombok.Getter;

/**
 * 基础异常类 - 自定义的非受检异常应该继承此类
 *
 * @author wendell
 */
@Getter
public abstract class BaseUnCheckException extends RuntimeException {

    private ResultMsg resultMsg;

    public BaseUnCheckException(ResultMsg resultMsg) {
        super(resultMsg.getMsg());
        this.resultMsg = resultMsg;
    }

}
