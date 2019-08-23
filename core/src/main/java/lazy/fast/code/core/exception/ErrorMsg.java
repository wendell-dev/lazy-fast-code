package lazy.fast.code.core.exception;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 异常消息返回
 *
 * @author wendell
 */
@Setter
@Getter
public class ErrorMsg implements Serializable {

    /** 异常code */
    private int code;

    /** 异常简要信息 */
    private String msg;

    /** 异常详细信息 */
    private String detailMsg;

    private ErrorMsg(int code, String msg, String detailMsg) {
        this.code = code;
        this.msg = msg;
        this.detailMsg = detailMsg;
    }

    public static String fail() {
        return ErrorMsg.of(MsgEnum.FAIL);
    }

    public static String fail(String msg) {
        return ErrorMsg.of(MsgEnum.FAIL.code(), msg);
    }

    public static String fail(String msg, String detailMsg) {
        return ErrorMsg.of(MsgEnum.FAIL.code(), msg, detailMsg);
    }

    public static String error() {
        return ErrorMsg.of(MsgEnum.ERROR);
    }

    public static String of(MsgEnumable msgEnum) {
        return ErrorMsg.of(msgEnum.code(), msgEnum.msg());
    }

    public static String of(int code, String msg) {
        return ErrorMsg.of(code, msg, null);
    }

    private static String of(int code, String msg, String detailMsg) {
        return new ErrorMsg(code, msg, detailMsg).toString();
    }

    /**
     * 组装成JSON字符串返回
     */
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
