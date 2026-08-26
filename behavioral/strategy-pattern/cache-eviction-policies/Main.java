import cache.Cache;
import factory.CacheFactory;

public class Main {

    public static void main(String[] args) {

        Cache<Integer, String> cache = CacheFactory.createMRUCache(3);

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");

        String value1 = cache.get(1);
        System.err.println("Accessed key 1: " + value1);
        String value2 = cache.get(2);
        System.err.println("Accessed key 2: " + value2);
        String value3 = cache.get(3);
        System.err.println("Accessed key 3: " + value3);

        cache.put(4, "D");
         value1 = cache.get(1);
        System.err.println("Accessed key 1: " + value1);
        value2 = cache.get(2);
        System.err.println("Accessed key 2: " + value2);
         value3 = cache.get(3);
        System.err.println("Accessed key 3: " + value3);
        String value4 = cache.get(4);
        System.err.println("Accessed key 4: " + value4);

        System.out.println(cache.get(2));
    }
}