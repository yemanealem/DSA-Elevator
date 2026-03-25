import java.util.*;

/**
 * LeetCode Problem 691: Stickers to Spell Word
 *
 * Problem:
 * Given an array of stickers (each a string of lowercase letters) and a target word,
 * find the minimum number of stickers needed to spell out the target.
 * Each sticker can be used multiple times and letters can be cut from stickers.
 *
 * How It Works:
 * 1. Precompute each sticker as a letter-frequency array (int[26]).
 * 2. Use DFS with memoization on the remaining letters of the target.
 * 3. At each recursion:
 *      - Skip stickers that don't contain the first needed letter (major pruning).
 *      - Apply a sticker: reduce letters from the remaining target.
 *      - Recursively compute the minimum stickers needed for the new remaining target.
 * 4. Memoize results for each remaining target string to avoid recomputation.
 *
 * Running Time:
 * - Worst-case exponential, but memoization + pruning makes it fast for target length ≤ 15.
 * - For typical inputs on LeetCode, it runs in milliseconds and passes all tests.
 */

public class StickersToSpellWord {

    // Main method to run the program
    public static void main(String[] args) {
        String[] stickers = {"with", "example", "science"};
        String target = "thehat";

        StickersToSpellWord solver = new StickersToSpellWord();
        int result = solver.minStickers(stickers, target);
        System.out.println("Minimum stickers needed: " + result);
    }

    // Public method to solve the problem
    public int minStickers(String[] stickers, String target) {
        int n = stickers.length;
        int[][] stickerCount = new int[n][26];
        for (int i = 0; i < n; i++) {
            for (char c : stickers[i].toCharArray()) {
                stickerCount[i][c - 'a']++;
            }
        }

        Map<String, Integer> memo = new HashMap<>();
        memo.put("", 0); // base case: empty target requires 0 stickers

        int res = helper(target, stickerCount, memo);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    // Helper method with memoization
    private int helper(String target, int[][] stickerCount, Map<String, Integer> memo) {
        if (memo.containsKey(target)) return memo.get(target);

        int[] targetCount = new int[26];
        for (char c : target.toCharArray()) targetCount[c - 'a']++;

        int min = Integer.MAX_VALUE;

        for (int[] sticker : stickerCount) {
            // Skip stickers that don't contain the first needed letter
            if (sticker[target.charAt(0) - 'a'] == 0) continue;

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (targetCount[i] > 0) {
                    int remaining = targetCount[i] - sticker[i];
                    for (int j = 0; j < Math.max(0, remaining); j++) sb.append((char) ('a' + i));
                }
            }

            String newTarget = sb.toString();
            int tmp = helper(newTarget, stickerCount, memo);
            if (tmp != Integer.MAX_VALUE) min = Math.min(min, 1 + tmp);
        }

        memo.put(target, min);
        return min;
    }
}
