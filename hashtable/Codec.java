import java.util.HashMap;
import java.util.Random;

// Java solution for LeetCode 535: Encode and Decode TinyURL
// Problem: Implement encode() to shorten a URL and decode() to restore the original URL
// Approach: Use HashMap to store mapping from a unique key to the original URL. Generate random 6-character key for each URL.
// Complexity: O(1) average time for encode and decode, O(n) space for storing mappings.

public class Codec {

    private HashMap<String, String> map = new HashMap<>();
    private String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private Random random = new Random();
    private String baseUrl = "https://tinyurl.com/";

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        String key = generateKey();
        map.put(key, longUrl); // store mapping
        return baseUrl + key;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        String key = shortUrl.replace(baseUrl, ""); // extract key
        return map.get(key); // retrieve original URL
    }

    // Helper method to generate a unique 6-character key
    private String generateKey() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(chars.length());
            sb.append(chars.charAt(index));
        }
        String key = sb.toString();
        // Ensure key is unique (rare collision handling)
        while (map.containsKey(key)) {
            sb.setLength(0);
            for (int i = 0; i < 6; i++) {
                int index = random.nextInt(chars.length());
                sb.append(chars.charAt(index));
            }
            key = sb.toString();
        }
        return key;
    }

    // Example usage
    public static void main(String[] args) {
        Codec codec = new Codec();
        String url = "https://leetcode.com/problems/design-tinyurl";
        String shortUrl = codec.encode(url);
        System.out.println("Short URL: " + shortUrl);
        System.out.println("Decoded URL: " + codec.decode(shortUrl));
    }
}
