package noogel.xyz.grammar;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class BiMapDemo {
    private static final BiMap<String, Integer> BI_MAP = HashBiMap.create();
    static {
        BI_MAP.put("a", 1);
        BI_MAP.put("b", 2);
        BI_MAP.putIfAbsent("b", 3);
        BI_MAP.putIfAbsent("c", 3);
        BI_MAP.forcePut("d", 3);
        // a = 1 b=2 d=3
    }

    public static void main(String[] args) {
        System.out.println(BI_MAP);
    }
}
