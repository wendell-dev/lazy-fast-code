package lazy.fast.code.core.result;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 偏操作提示类的消息返回 - 只针对返回JSON数据交互格式
 * 
 * <pre>
 * 适用于操作提示类，如：操作成功/操作失败 前端可直接提示msg给用户.
 * 还有类似返回一个字段值的情况，如：返回一个url地址，返回一个操作id值等，前端可直接使用msg.
 * </pre>
 *
 * @author wendell
 */
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    public static ResultMsg ok() {
        return ResultMsg.of(MsgEnum.SUCCESS);
    }

    public static ResultMsg ok(Serializable msg) {
        return ResultMsg.of(MsgEnum.SUCCESS.code(), msg.toString());
    }

    public static ResultMsg fail() {
        return ResultMsg.of(MsgEnum.FAIL);
    }

    public static ResultMsg fail(String msg) {
        return ResultMsg.of(MsgEnum.FAIL.code(), msg);
    }

    public static ResultMsg fail(String msg, String detailMsg) {
        return ResultMsg.of(MsgEnum.FAIL.code(), msg, detailMsg);
    }

    public static ResultMsg error() {
        return ResultMsg.of(MsgEnum.ERROR);
    }

    public static ResultMsg error(String msg) {
        return ResultMsg.of(MsgEnum.ERROR.code(), msg);
    }

    public static ResultMsg error(String msg, String detailMsg) {
        return ResultMsg.of(MsgEnum.ERROR.code(), msg, detailMsg);
    }

    public static ResultMsg of(MsgEnumable msgEnum) {
        return ResultMsg.of(msgEnum.code(), msgEnum.msg());
    }

    public static ResultMsg of(int code, String msg) {
        return ResultMsg.of(code, msg, null);
    }

    private static ResultMsg of(int code, String msg, String detailMsg) {
        return new ResultMsg(code, msg, detailMsg);
    }

    /**
     * 组装成JSON字符串返回
     */
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
