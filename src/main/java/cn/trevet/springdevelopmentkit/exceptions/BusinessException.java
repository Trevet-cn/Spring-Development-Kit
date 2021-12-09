package cn.trevet.springdevelopmentkit.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 异常基础消息类
 *
 * @author Eugene
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class BusinessException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 6626905912600321447L;

    /**
     * 异常错误编码
     */
    private long errorCode;

    /**
     * 异常错误信息
     */
    private String message;

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
        this.message = message;
    }

    public BusinessException(long errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public BusinessException(long errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.message = message;
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }
}
