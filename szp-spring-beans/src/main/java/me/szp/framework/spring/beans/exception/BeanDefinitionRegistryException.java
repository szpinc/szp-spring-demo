package me.szp.framework.spring.beans.exception;

/**
 * @author GhostDog
 */
public class BeanDefinitionRegistryException extends Exception {

    public BeanDefinitionRegistryException(String mess) {
        super(mess);
    }

    public BeanDefinitionRegistryException(String mess, Throwable e) {
        super(mess, e);
    }
}
