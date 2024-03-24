

import java.util.HashMap;
import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Simple example showing connection with Redis (REmote DIctionary Server).
 * 
 */

public class RedisExample1 {
    public static void main(String[] args) {
    	//choose a port in your assigned range for the project
        JedisPool pool = new JedisPool("localhost", 6379); 

        try (Jedis jedis = pool.getResource()) {
            // Store & Retrieve a simple string
            jedis.set("goo goo", "gaa gaa");
            System.out.println(jedis.get("goo goo")); // prints "gaa gaa"
            
            // Store & Retrieve a HashMap
            Map<String, String> hash = new HashMap<>();;
            hash.put("name", "Jane");
            hash.put("surname", "Smith");
            hash.put("company", "Redis");
            hash.put("age", "21");
            jedis.hset("user-session:123", hash);
            System.out.println(jedis.hgetAll("user-session:123"));
            // Prints: {name=Jane, surname=Smith, company=Redis, age=21}
        }
        pool.close();
    }
}