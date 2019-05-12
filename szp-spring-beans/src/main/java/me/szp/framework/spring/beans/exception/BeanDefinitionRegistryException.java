package me.szp.framework.spring.beans.exception;

/**
 * @author Sun zhi peng
 */
public class BeanDefinitionRegistryException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6056374114834139330L;

	public BeanDefinitionRegistryException(String mess) {
		super(mess);
	}

	public BeanDefinitionRegistryException(String mess, Throwable e) {
		super(mess, e);
	}
}
