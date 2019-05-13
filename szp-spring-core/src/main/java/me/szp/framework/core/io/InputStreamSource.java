package me.szp.framework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 加载不同的xml配置文件得到最终产出流InputStream的借口
 * 在Resource接口里面实现
 *
 * @author GhostDog
 */
public interface InputStreamSource {
    /**
     * 根据指定的xml配置文件的类路径加载xml得到InputStream
     *
     * @return
     * @throws IOException
     */
    InputStream getInputStream() throws IOException;
}
