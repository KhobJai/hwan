package uk.intenso.hwan.serialisation;

public interface JsonSerialiser {

    <T> String toJson(T t);

    <T> T fromJson(Class<T> clazz, String data);

    <T> T fromJsonClassPath(Class<T> clazz, String path);

    <T> T fromJsonFilePath(Class<T> clazz, String path);
}
