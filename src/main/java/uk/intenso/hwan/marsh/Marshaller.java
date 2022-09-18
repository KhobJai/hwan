package uk.intenso.hwan.marsh;

public interface Marshaller {

    <T> String toYaml(T t);

    <T> T fromYaml(Class<T> clazz, String data);

    <T> T fromYamlClassPath(Class<T> clazz, String path);

    <T> T fromYamlFilePath(Class<T> clazz, String path);


    <T> String toJson(T t);

    <T> T fromJson(Class<T> clazz, String data);

    <T> T fromJsonClassPath(Class<T> clazz, String path);

    <T> T fromJsonFilePath(Class<T> clazz, String path);


}
