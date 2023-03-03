package uk.intenso.hwan.serialisation;

import org.jetbrains.annotations.NotNull;
import uk.intenso.hwan.io.HwIoUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class HwPropsSerialiser {


    public Properties loadProperties(String path) {
        Properties properties = new Properties();
        try {
            properties.load(HwIoUtils.classpathToStream(path));
        } catch (IOException e) {
            throw new SerialisationException("Unable to load properties file",e);
        }
        return properties;
    }

    public Map<String,String> propsToStringMap(Properties properties) {
        Map<String,String> map = new HashMap<>();
        /// V2
//        return propsToMapV3(properties, map);


        // V2
//        return propsToMapV2(properties, map);

        // V1
        return propsToMapV1(properties);

    }

    private static Map<String, String> propsToMapV3(Properties properties, Map<String, String> map) {
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            map.put((String) entry.getKey(), (String) entry.getValue());
        }
        return map;
    }

    private static Map<String, String> propsToMapV2(Properties properties, Map<String, String> map) {
        properties.entrySet().forEach(entry -> {
            map.put((String) entry.getKey(), (String) entry.getValue());
        });
        return map;
    }

    private static Map<String, String> propsToMapV1(Properties properties) {
        return properties.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey().toString(),
                        e -> e.getValue().toString()));
    }
}
