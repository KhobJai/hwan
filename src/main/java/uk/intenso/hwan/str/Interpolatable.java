package uk.intenso.hwan.str;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public interface Interpolatable {

    private List<String> getPlaceholders(String preFormatted) {
        var placeholders = new ArrayList<String>();
        var arr = preFormatted.split("\\{");
        arr = Arrays.copyOfRange(arr, 1, arr.length);

        for (String str : arr) {
            placeholders.add(str.split("\\}")[0]);
        }
        return placeholders;
    }

    default String format(String preFormatted) {
        var placeHolders = getPlaceholders(preFormatted);
        var fieldNames = getAllVarNames();
        if (fieldNames.containsAll(placeHolders) == false) {
            throw new RuntimeException("Can't interpolate as not all place holders found..");
        }
        List<Object> values = getValuesForPlaceholders(placeHolders);
        var postFormatted = f(preFormatted, values);
        return postFormatted;
    }

    default void print(String preFormatted) {
        System.out.println(format(preFormatted));
    }

    @NotNull
    private List<Object> getValuesForPlaceholders(List<String> placeHolders) {
        var values = placeHolders.stream().map(m -> {
            try {
                var field =  this.getClass().getDeclaredField(m);
                field.setAccessible(true);
                return field.get(this);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).toList();
        return values;
    }

    private String f(String preFormatted, List<Object> values) {
        List<String> placeHolders = getPlaceholders(preFormatted);

        var placeholderValueMap = createPlaceholderMap(values, placeHolders);

        return formatOutput(preFormatted, placeholderValueMap);
    }

    default String f(String preFormatted, Object... values) {
        return f(preFormatted, List.of(values));
    }

    private String formatOutput(String preFormatted, Map<String, String> placeholderValueMap) {
        String output = preFormatted;
        for (Map.Entry e : placeholderValueMap.entrySet()) {
            output = output.replace("{" + e.getKey() + "}", e.getValue().toString());
        }
        return output;
    }

    private Map<String, String> createPlaceholderMap(List<Object> values, List<String> placeHolders) {
        Map<String, String> placeholderValueMap = new HashMap<>();
        if (placeHolders.size() != values.size()) {
            throw new MissingFormatArgumentException("Placeholder count and value count don't match");
        }

        IntStream.range(0, values.size()).forEach(i -> {
            placeholderValueMap.put(placeHolders.get(i), values.get(i).toString());
        });

        return placeholderValueMap;
    }

    default List<String> getAllVarNames() {
        return Stream.of(this.getClass().getDeclaredFields()).map(f -> f.getName())
                .filter(p -> !p.contains("this"))
                .toList();
    }
}
