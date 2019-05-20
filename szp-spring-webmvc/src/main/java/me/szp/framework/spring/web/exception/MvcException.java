package me.szp.framework.spring.web.exception;

/**
 * @author Sun Zhi Peng
 * @version 1.0.0
 * @date 2019/5/10 11:03
 */
public class MvcException extends RuntimeException {
    public MvcException(String msg) {
        super(msg);
    }
}
