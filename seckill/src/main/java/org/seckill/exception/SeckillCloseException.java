package org.seckill.exception;

/**
 * @author Devin13 on 2018/8/2.
 * @version 1.0
 */
public class SeckillCloseException  extends SeckillException {
    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
