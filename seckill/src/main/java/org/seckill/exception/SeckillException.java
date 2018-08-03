package org.seckill.exception;

/**
 * @author Devin13 on 2018/8/2.
 * @version 1.0
 */
public class SeckillException extends RuntimeException {

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
