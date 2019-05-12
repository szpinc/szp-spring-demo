package me.szp.framework.spring.beans.samples;

public class ABeanFactory {

	//静态工厂方式
	public static ABean getABean() {
		return new ABean();
	}

	//工厂bean的方式
	public ABean getABean2() {
		return new ABean();
	}
}
