package me.szp.framework.core.io;

import me.szp.framework.core.io.Resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 从文件系统里面加载xml配置文件得到InputStream
 *
 * @author GhostDog
 */
public class FileSystemResource implements Resource {

    private File file;

    public FileSystemResource(String fileName) {
        this.file = new File(fileName);
    }

    public FileSystemResource(File file) {
        super();
        this.file = file;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }

    @Override
    public boolean exists() {
        return this.file != null && file.exists();
    }

    @Override
    public boolean isReadable() {
        return this.file != null && file.canRead();
    }

    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    public File getFile() {
        return file;
    }

}
