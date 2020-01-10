package lazy.fast.code.core.web.result;

/**
 * 消息枚举顶层接口, 自定义消息枚举类应该实现此接口
 *
 * @author wendell
 */
public interface MsgEnumable {

    /**
     * 编码
     */
    int code();

    /**
     * 消息
     */
    String msg();

}
