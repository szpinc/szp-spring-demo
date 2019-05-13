package me.szp.framework.core.io;

import java.io.IOException;

/**
 * 加载xml配置文件的行为ResourceLoader接口 在ApplicationContext里面实现
 * 工厂模式:根据不同的字符串创建不同的Resource对象
 *
 * @author GhostDog
 */
public interface ResourceLoader {

    /**
     * 根据字符串创建Resource对象
     *
     * @param location
     * @return
     * @throws IOException
     */
    Resource getResource(String location) throws IOException;
}
