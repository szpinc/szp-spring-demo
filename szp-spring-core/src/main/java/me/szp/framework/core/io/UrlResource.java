package me.szp.framework.core.io;

import me.szp.framework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * 根据xml配置文件的URL得到InputStream
 *
 * @author GhostDog
 */
public class UrlResource implements Resource {

    /**
     * xml配置文件的URL
     */
    private URL url;

    public UrlResource(String url) throws IOException {
        this.url = new URL(url);
    }

    public UrlResource(URL url) {
        super();
        this.url = url;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        if (this.url != null) {
            return this.url.openStream();
        }
        return null;
    }

    @Override
    public boolean exists() {
        return this.url != null;
    }

    @Override
    public boolean isReadable() {
        return exists();
    }

    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    public File getFile() {
        return null;
    }

}
