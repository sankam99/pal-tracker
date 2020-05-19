package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {
    String port;
    String memoryLimit;
    String cfInstanceIndex;
    String cfInstanceAddr;

    public EnvController(@Value("${port: NOT SET}") String PORT, @Value("${memory.limit}") String MEMORY_LIMIT, @Value("${cf.instance.index: NOT SET}") String CF_INSTANCE_INDEX, @Value("${cf.instance.addr}") String CF_INSTANCE_ADDR) {
        this.port = PORT;
        this.memoryLimit = MEMORY_LIMIT;
        this.cfInstanceIndex = CF_INSTANCE_INDEX;
        this.cfInstanceAddr = CF_INSTANCE_ADDR;
    }

    @GetMapping("/env")
    public Map<String, String> getEnv() throws Exception {
        Map<String, String> env = new HashMap<>();

        env.put("PORT", port);
        env.put("MEMORY_LIMIT",memoryLimit);
        env.put("CF_INSTANCE_INDEX",cfInstanceIndex);
        env.put("CF_INSTANCE_ADDR",cfInstanceAddr);


        return env;
    }
}
