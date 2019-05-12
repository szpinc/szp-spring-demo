package advisor;

import pointcut.Pointcut;

public interface PointcutAdvisor extends Advisor {

	Pointcut getPointcut();
}
