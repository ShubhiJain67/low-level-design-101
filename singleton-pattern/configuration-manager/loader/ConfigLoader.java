package loader;

import java.util.HashMap;
import java.util.Map;

public class ConfigLoader {

    public Map<String, String> load() {

        Map<String, String> configs = new HashMap<>();
        configs.put(
            "DB_URL",
            "localhost"
        );
        configs.put(
            "DB_PASSWORD",
            "password"
        );

        configs.put(
            "DB_PORT",
            "5432"
        );

        return configs;
    }
}