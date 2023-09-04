package uk.intenso.hwan.str;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Class to enable python style string interpolation, as an easier alternative to standard formatting.
 */
public class Str {

    private Str() {}
    public static List<String> getPlaceholders(String preFormatted) {
        var placeholders = new ArrayList<String>();
        var arr = preFormatted.split("\\{");
        arr = Arrays.copyOfRange(arr, 1, arr.length);

        for (String str : arr) {
            placeholders.add(str.split("\\}")[0]);
        }
        return placeholders;
    }

    public static String f(String preFormatted, List<Object> values) {
        List<String> placeHolders = getPlaceholders(preFormatted);
        Map<String, String> placeholderValueMap = new HashMap<>();
        createPlaceholderMap(values, placeHolders, placeholderValueMap);

        return formatOutput(preFormatted, placeholderValueMap);
    }

    public static String f(String preFormatted, Object... values) {
        return f(preFormatted,List.of(values));
    }

    private static String formatOutput(String preFormatted, Map<String, String> placeholderValueMap) {
        String output = preFormatted;
        for (Map.Entry e : placeholderValueMap.entrySet()) {
            output = output.replace("{" + e.getKey() + "}", e.getValue().toString());
        }
        return output;
    }

    private static void createPlaceholderMap(List<Object> values, List<String> placeHolders, Map<String, String> placeholderValueMap) {
        if (placeHolders.size() != values.size()) {
            throw new MissingFormatArgumentException("Placeholder count and value count don't match");
        }

        IntStream.range(0, values.size()).forEach(i -> {
            placeholderValueMap.put(placeHolders.get(i), values.get(i).toString());
        });

    }
}
