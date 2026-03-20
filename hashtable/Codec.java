import java.util.HashMap;

// Optimized high-performance TinyURL Codec
public class Codec {

    private HashMap<String, String> map = new HashMap<>();
    private int counter = 0;
    private final String baseUrl = "https://tinyurl.com/";
    private final char[] chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        counter++;
        String key = encodeBase62(counter);
        key = padKey(key); // make it always 6 chars
        map.put(key, longUrl);
        return baseUrl + key;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return map.get(shortUrl.substring(baseUrl.length()));
    }

    // Convert integer to base62 string
    private String encodeBase62(int num) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append(chars[num % 62]);
            num /= 62;
        }
        return sb.reverse().toString();
    }

    // Pad key to always 6 characters
    private String padKey(String key) {
        StringBuilder sb = new StringBuilder();
        int padLength = 6 - key.length();
        for (int i = 0; i < padLength; i++) sb.append('0'); // pad with '0'
        sb.append(key);
        return sb.toString();
    }

    // Example usage
    public static void main(String[] args) {
        Codec codec = new Codec();
        String url1 = "https://leetcode.com/problems/design-tinyurl";
        String shortUrl1 = codec.encode(url1);
        System.out.println(shortUrl1 + " -> " + codec.decode(shortUrl1));

        String url2 = "https://google.com";
        String shortUrl2 = codec.encode(url2);
        System.out.println(shortUrl2 + " -> " + codec.decode(shortUrl2));
    }
}
