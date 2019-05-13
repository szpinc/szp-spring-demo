package me.szp.framework.spring.beans.factory;

import me.szp.framework.spring.beans.factory.Aware;
import me.szp.framework.spring.beans.factory.BeanFactory;

/**
 *
 *
 * @author GhostDog
 */
public interface BeanFactoryAware extends Aware {

	void setBeanFactory(BeanFactory bf);
}
