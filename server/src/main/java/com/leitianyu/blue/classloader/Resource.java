package com.leitianyu.blue.classloader;

import java.nio.file.Path;
import java.util.Objects;

/**
 * 扫描的bean类
 *
 * @author leitianyu
 * @date 2024/1/7
 */
public class Resource {

    //文件绝对路径xxx/xxx.xxx
    private final Path path;
    //basePackage/xxx/name.xxx
    private final String name;
    public Resource(Path path, String name) {
        this.path = path;
        this.name = name;
    }

    public Path getPath() {
        return this.path;    }

    public String getName() {
        return this.name;    }

    @Override
    public String toString() {
        return "Resource{" +
                "path='" + path + '\'' +
                ", name='" + name + '\'' +
                '}';    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Resource resource = (Resource) o;
        return Objects.equals(path, resource.path) && Objects.equals(name, resource.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, name);
    }

}
