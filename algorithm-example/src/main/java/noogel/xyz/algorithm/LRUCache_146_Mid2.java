package noogel.xyz.algorithm;

import java.util.*;

public class LRUCache_146_Mid2 extends LinkedHashMap<Integer, Integer> {
    private int capacity;
    public static void main(String[] args) {
        LRUCache_146_Mid2 ss = new LRUCache_146_Mid2(2);
        ss.put(2,1);
        ss.put(1,1);
        ss.put(2,3);
        ss.put(4,1);
        System.out.println(ss.get(1));
        System.out.println(ss.get(2));
    }

    public LRUCache_146_Mid2(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> entry) {
        return super.size() > capacity;
    }
}
