package noogel.xyz.algorithm;

import java.util.*;

public class LRUCache_146_Mid {
    List<String> que = new ArrayList<>();
    Map<String, Integer> data = new HashMap<>();
    int capacity;

    public static void main(String[] args) {
        LRUCache_146_Mid ss = new LRUCache_146_Mid(2);
        ss.put(2,1);
        ss.put(1,1);
        ss.put(2,3);
        ss.put(4,1);
        System.out.println(ss.get(1));
        System.out.println(ss.get(2));
    }

    public LRUCache_146_Mid(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        Integer resp = data.get(key + "");
        if (Objects.nonNull(resp)) {
            que.remove(key + "");
            que.add(0, key + "");
        }
        return resp == null ? -1: resp;
    }

    public void put(int key, int value) {
        data.put(key+"", value);
        que.remove(key + "");
        que.add(0, key+"");
        if (que.size() > capacity) {
            String removeKey = que.remove(que.size() - 1);
            data.remove(removeKey);
        }
    }
}
