import java.util.ArrayList;

/*
LeetCode 535: Encode and Decode TinyURL
Problem: Design a TinyURL system with two methods:
  1. encode(longUrl) -> returns a shortened URL
  2. decode(shortUrl) -> returns the original long URL

Constraints:
- Each URL is unique and encode should produce a unique short URL.
- decode should restore the original long URL.
- All characters are ASCII, and URLs are valid.
- Goal: minimize runtime and memory overhead.

Approach:
- Use a counter to generate unique IDs for each URL.
- Convert the counter to a base62 string (0-9, a-z, A-Z) to form the short key.
- Store original URLs in an ArrayList at index = counter-1.
- encode: add URL to ArrayList, convert counter to base62 key, return baseUrl+key.
- decode: convert key back to counter, retrieve URL from ArrayList.
- This avoids HashMap overhead and random collision checking.

Trace Example:
1. Encode "https://leetcode.com/problems/design-tinyurl"
   - counter = 1
   - base62(1) = "1"
   - store at urls[0] = original URL
   - return "https://tinyurl.com/1"

2. Decode "https://tinyurl.com/1"
   - extract key = "1"
   - base62 decode = 1
   - urls[0] -> "https://leetcode.com/problems/design-tinyurl"

3. Encode "https://google.com"
   - counter = 2
   - base62(2) = "2"
   - store at urls[1] = original URL
   - return "https://tinyurl.com/2"

4. Decode "https://tinyurl.com/2"
   - extract key = "2"
   - base62 decode = 2
   - urls[1] -> "https://google.com"
*/

public class Codec {

    private ArrayList<String> urls = new ArrayList<>(); // store original URLs
    private int counter = 0; // unique ID for each URL
    private final String baseUrl = "https://tinyurl.com/";
    private final char[] chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        urls.add(longUrl); // store URL at index = counter
        counter++;
        String key = encodeBase62(counter); // convert counter to base62 key
        return baseUrl + key;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        String key = shortUrl.substring(baseUrl.length());
        int index = decodeBase62(key); // convert base62 key back to index
        return urls.get(index - 1); // array index = counter-1
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

    // Convert base62 string back to integer
    private int decodeBase62(String key) {
        int num = 0;
        for (char c : key.toCharArray()) {
            num *= 62;
            if (c >= '0' && c <= '9') num += c - '0';
            else if (c >= 'a' && c <= 'z') num += c - 'a' + 10;
            else num += c - 'A' && c <= 'Z' ? c - 'A' + 36 : 0;
        }
        return num;
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
