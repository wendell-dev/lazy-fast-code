package lazy.fast.code.core.web.exception;

/**
 * 无内容 - 这不是异常 - HTTP Status 204
 * 
 * <pre>
 * 在业务处理层中，如果查询到一个列表为null，可直接抛此异常
 * 
 * if (list == null) {
 *     throw new NoContentNotException();
 * }
 * 
 * 全局异常中会特殊处理，适用于返回状态码为【204】的情况，而不用controller自己去判断返回列表是否为null
 * </pre>
 *
 * @author wendell
 */
public class NoContentNotException extends RuntimeException {

    public NoContentNotException() {}

}
