package org.ootb.espresso.facilities;

import com.google.common.collect.Maps;

import java.util.*;


public class MapUtils {

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValueAsc( Map<K, V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValueDesc( Map<K, V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return -o1.getValue().compareTo(o2.getValue());
            }
        });

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public static void main(String[] args) {
        Map<String,Integer> testMap = Maps.newHashMap();
        testMap.put("w1",388);
        testMap.put("w2",-92);
        testMap.put("g1",11);
        testMap.put("g4",111);
        testMap.put("g2",22);
        Map<String,Integer> testMapResult =  sortByValueAsc(testMap);
        for(String key :testMapResult.keySet()){
            System.out.println(key+"="+testMapResult.get(key));
        }
    }

}
