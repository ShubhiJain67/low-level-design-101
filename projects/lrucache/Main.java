package projects.lrucache;

import projects.lrucache.repository.InMemoryNodeRepository;
import projects.lrucache.service.CacheService;

public class Main {
    private static CacheService<String, String> cacheService;
    private final static int CACHE_SIZE = 3;

    public static void main(String[] args) {
        start();
        cacheService.put("A", "aa");
        cacheService.print();
        cacheService.put("B", "bb");
        cacheService.print();
        cacheService.put("B", "bbb");
        cacheService.print();
        cacheService.put("C", "cc");
        cacheService.print();
        cacheService.put("A", "aaa");
        cacheService.print();
        cacheService.put("D", "dd");
        cacheService.print();
    }
    
    private static void start(){
        InMemoryNodeRepository<String, String> repository = new InMemoryNodeRepository<>();
        cacheService = new CacheService<>(repository, CACHE_SIZE);
    }
}
