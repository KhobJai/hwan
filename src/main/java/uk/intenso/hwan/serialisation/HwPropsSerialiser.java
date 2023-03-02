package uk.intenso.hwan.serialisation;

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
        /// V1
//        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
//            map.put((String) entry.getKey(), (String) entry.getValue());
//        }
//        return map;
        // V2
//        map
//                .putAll(properties.entrySet()
//                        .stream()
//                        .collect(Collectors.toMap(e -> e.getKey().toString(),
//                                e -> e.getValue().toString())));

        // V3
//        properties.entrySet().forEach(entry -> {
//            map.put((String) entry.getKey(), (String) entry.getValue());
//        });

        return properties.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey().toString(),
                        e -> e.getValue().toString()));

//                return map;
    }
}
