package uk.intenso.hwan.serialisation;

public interface YamlSerialiser {

    <T> String toYaml(T t);

    <T> T fromYaml(Class<T> clazz, String data);

    <T> T fromYamlClassPath(Class<T> clazz, String path);

    <T> T fromYamlFilePath(Class<T> clazz, String path);
}
