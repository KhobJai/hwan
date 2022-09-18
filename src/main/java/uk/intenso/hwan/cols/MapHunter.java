package uk.intenso.hwan.cols;

import java.util.List;
import java.util.Map;

public abstract class MapHunter {

    public static String findString(Map map,String... keys) {
        var result = findDeep(map,keys);
        if (result instanceof String) {
            return (String) result;
        }else {
            throw new RuntimeException("Found Unexpected Type " + result.getClass().getSimpleName());
        }
    }

    public static List findList(Map map,String... keys) {
        var result = findDeep(map,keys);
        if (result instanceof List) {
            return (List) result;
        }else {
            throw new RuntimeException("Found Unexpected Type " + result.getClass().getSimpleName());
        }
    }

    public static Object findDeep(Map map ,String... keys) {
        if (keys ==null || keys.length==0) {
            System.out.println("No more keys -  returning existing value");
            return map;
        }
        var results =  map.get(keys[0]);
            if (results == null) {
                System.out.printf("Nothing found with key %s, returning map%n",keys[0]);
                System.out.println("Returning Map");
                return map;
            }else if (results instanceof Map) {
                System.out.printf("Found Child Map with Key %s%n",keys[0]);
                return findDeep((Map) results,ColUtils.removeFirst(keys));
            }else if (results instanceof List) {
                System.out.printf("Found list with Key %s - returning %n",keys[0]);
                return results;

            }else if (results instanceof Integer) {
                System.out.printf("Found int with Key %s - returning %n",keys[0]);
                return results;

            }else if (results instanceof Double) {
                System.out.printf("Found double with Key %s - returning %n",keys[0]);
                return results;
            }else if (results instanceof String) {
                System.out.printf("Found String with Key %s - returning %n",keys[0]);
                return results;
            }else {
                throw new RuntimeException("Found value of class "+results.getClass().getSimpleName() + " Don't know what to do yet...");
        }
    }
}
