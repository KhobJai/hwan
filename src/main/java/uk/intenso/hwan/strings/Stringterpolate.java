package uk.intenso.hwan.strings;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Stringterpolate {

    Object object;

    public static Stringterpolate create(Object object) {
        Stringterpolate stringterpolate = new Stringterpolate();
        stringterpolate.object = object;
        return stringterpolate;
    }

    private Stringterpolate() {
    }

    List<String> findFields(String preFormatted) {
        var fields = new LinkedList<String>();
        for (String field : preFormatted.split("\\{")) {
            String[] next = field.split("}");
            if (next.length < 2) {
                continue;
            }
            fields.add(next[0]);
        }
        return fields;
    }


    void printerpolate(String preFormatted, String... fields) {
        System.out.println(forEachReplacer(preFormatted, fields));
    }

    String forEachReplacer(String preFormatted) {
        return forEachReplacer(preFormatted, object, findFields(preFormatted));
    }


    String forEachReplacer(String preFormatted, Object o, List<String> fields) {
        return forEachReplacer(preFormatted, o, fields.toArray(new String[]{}));
    }

    String replaceSequence(String message, String fieldName, Object value) {
        return message.replace("{" + message + "}", value.toString());
    }

    String forLoopReplacer(String preFormatted, Object o, List<String> fields) {

        for (Field field : o.getClass().getDeclaredFields()) {
            if (fields.contains(field.getName())) {
                preFormatted = replaceSequence(preFormatted, field.getName(), o);
            }
        }
        return preFormatted;
    }

    String forEachReplacer(String preFormatted, Object o, String... fields) {
        AtomicReference<String> atomRef = new AtomicReference<>(preFormatted);
        List<String> fieldList = Arrays.asList(fields);

        Arrays.asList(o.getClass().getDeclaredFields()).forEach((f) -> {
            if (fieldList.contains(f.getName())) {
                try {
                    atomRef.set(atomRef.get().replace("{" + f.getName() + "}", f.get(o).toString()));
                } catch (IllegalAccessException e) {
                    System.err.println(e);
                }
            }
        });
        return atomRef.get();
    }
}