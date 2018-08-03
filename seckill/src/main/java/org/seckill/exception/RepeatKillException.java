package org.seckill.exception;

/**
 * @author Devin13 on 2018/8/2.
 * @version 1.0
 */
//重复秒杀异常
public class RepeatKillException extends SeckillException{

    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
