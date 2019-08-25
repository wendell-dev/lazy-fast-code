package lazy.fast.code.core.result;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 提示性消息返回
 *
 * @author wendell
 */
@Setter
@Getter
public class ResultMsg implements Serializable {

    /**
     * code
     */
    private int code;

    /**
     * 简要信息
     */
    private String msg;

    /**
     * 详细信息
     */
    private String detailMsg;

    private ResultMsg(int code, String msg, String detailMsg) {
        this.code = code;
        this.msg = msg;
        this.detailMsg = detailMsg;
    }

    public static String ok() {
        return ResultMsg.of(MsgEnum.SUCCESS);
    }

    public static String fail() {
        return ResultMsg.of(MsgEnum.FAIL);
    }

    public static String fail(String msg) {
        return ResultMsg.of(MsgEnum.FAIL.code(), msg);
    }

    public static String fail(String msg, String detailMsg) {
        return ResultMsg.of(MsgEnum.FAIL.code(), msg, detailMsg);
    }

    public static String error() {
        return ResultMsg.of(MsgEnum.ERROR);
    }

    public static String of(MsgEnumable msgEnum) {
        return ResultMsg.of(msgEnum.code(), msgEnum.msg());
    }

    public static String of(int code, String msg) {
        return ResultMsg.of(code, msg, null);
    }

    private static String of(int code, String msg, String detailMsg) {
        return new ResultMsg(code, msg, detailMsg).toString();
    }

    /**
     * 组装成JSON字符串返回
     */
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
