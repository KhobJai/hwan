package uk.intenso.hwan.serialisation;

import com.google.gson.Gson;
import org.yaml.snakeyaml.Yaml;
import uk.intenso.hwan.cols.HwColUtils;
import uk.intenso.hwan.io.HwIoUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class HWSerialiser implements JsonYamlSerialiser {

    private final Yaml yaml;

    private final Gson gson;

    public static HWSerialiser create() {
        return create(new Yaml(), new Gson());
    }

    public static HWSerialiser create(Yaml yaml, Gson gson) {
        return new HWSerialiser(yaml, gson);
    }

    public HWSerialiser(Yaml yaml, Gson gson) {
        this.yaml = yaml;
        this.gson = gson;
    }

    @Override
    public <T> String toYaml(T t) {
        return yaml.dump(t);
    }

    @Override
    public <T> T fromYaml(Class<T> clazz, String data) {
        return yaml.loadAs(data, clazz);

    }

    @Override
    public <T> T fromYamlClassPath(Class<T> clazz, String path) {
        return fromYaml(clazz, HwIoUtils.readFromClasspath(path));
    }

    @Override
    public <T> T fromYamlFilePath(Class<T> clazz, String path) {
        return fromYaml(clazz, HwIoUtils.readFromFilePath(path));
    }

    @Override
    public <T> String toJson(T t) {
        return gson.toJson(t);
    }

    @Override
    public <T> T fromJson(Class<T> clazz, String data) {
        return gson.fromJson(data, clazz);
    }

    @Override
    public <T> T fromJsonClassPath(Class<T> clazz, String path) {
        return fromJson(clazz, HwIoUtils.readFromClasspath(path));
    }

    @Override
    public <T> T fromJsonFilePath(Class<T> clazz, String path) {
        return fromJson(clazz, HwIoUtils.readFromFilePath(path));
    }

}
