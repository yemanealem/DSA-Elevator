import java.util.*;

/*
LeetCode: Search Suggestions System

QUESTION:
Given an array of strings products and a string searchWord.

After each character of searchWord is typed, return at most 3 lexicographically smallest products
that start with the current prefix.

------------------------------------------------------

OPTIMIZED APPROACH (SORT + BINARY SEARCH):

WHY PREVIOUS SOLUTION WAS SLOW:
- For every prefix → we scanned ALL products → O(n * m)
- This is inefficient when n is large

------------------------------------------------------

BETTER IDEA:

1. Sort the products array.
2. Use Binary Search to find the FIRST product that matches the prefix.
3. From that index, just check the next 3 products.

This avoids scanning the whole array every time.

------------------------------------------------------

HOW IT WORKS:

For each prefix:
- Use binary search to find the leftmost index where prefix can fit
- Check at most 3 elements from that index
- Only add those that start with prefix

------------------------------------------------------

EXAMPLE:

products = ["mobile","mouse","moneypot","monitor","mousepad"]
searchWord = "mouse"

Sorted:
["mobile","moneypot","monitor","mouse","mousepad"]

Prefix = "mo"
Binary search jumps directly near:
["mobile","moneypot","monitor"]

No full scan needed ✅

------------------------------------------------------

TIME COMPLEXITY:
- Sorting → O(n log n)
- Each prefix:
    Binary search → O(log n)
    Checking 3 items → O(1)
- Total → O(n log n + m log n)

SPACE COMPLEXITY:
- O(1) extra (excluding output)

------------------------------------------------------

KEY IDEA:
- Use binary search to jump directly to matches
- Never scan entire list again
*/

public class SearchSuggestionsSystem {

    public static List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);
        List<List<String>> result = new ArrayList<>();

        String prefix = "";

        for (char c : searchWord.toCharArray()) {
            prefix += c;

            int index = lowerBound(products, prefix);
            List<String> suggestions = new ArrayList<>();

            // Only check next 3 items
            for (int i = index; i < Math.min(index + 3, products.length); i++) {
                if (products[i].startsWith(prefix)) {
                    suggestions.add(products[i]);
                }
            }

            result.add(suggestions);
        }

        return result;
    }

    // Binary search to find first position >= prefix
    private static int lowerBound(String[] products, String prefix) {
        int left = 0, right = products.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (products[mid].compareTo(prefix) < 0) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        String[] products = {"mobile","mouse","moneypot","monitor","mousepad"};
        String searchWord = "mouse";

        List<List<String>> result = suggestedProducts(products, searchWord);

        for (List<String> list : result) {
            System.out.println(list);
        }
    }
}
