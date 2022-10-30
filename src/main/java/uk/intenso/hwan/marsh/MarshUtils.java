package uk.intenso.hwan.marsh;

import com.google.gson.Gson;
import org.yaml.snakeyaml.Yaml;
import uk.intenso.hwan.res.ReadUtils;

public class MarshUtils implements Marshaller {


    private Yaml yaml;

    private Gson gson;

    public static MarshUtils create() {
        return new MarshUtils(new Yaml(),new Gson());
    }


    public MarshUtils(Yaml yaml, Gson gson) {
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
       return fromYaml(clazz,ReadUtils.readFromClasspath(path));
    }

    @Override
    public <T> T fromYamlFilePath(Class<T> clazz, String path) {
        return fromYaml(clazz,ReadUtils.readFromFilePath(path));
    }

    @Override
    public <T> String toJson(T t) {
        return gson.toJson(t);
    }

    @Override
    public <T> T fromJson(Class<T> clazz, String data) {
        return gson.fromJson(data,clazz);
    }

    @Override
    public <T> T fromJsonClassPath(Class<T> clazz, String path) {
        return fromJson(clazz,ReadUtils.readFromClasspath(path));
    }

    @Override
    public <T> T fromJsonFilePath(Class<T> clazz, String path) {
        return fromJson(clazz,ReadUtils.readFromFilePath(path));
    }

}
