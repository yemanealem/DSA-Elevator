public class MinimumTimeToTypeWord {

    /*
     * LeetCode 1974: Minimum Time to Type Word Using Special Typewriter
     *
     * There is a special typewriter with lowercase English letters 'a' to 'z'
     * arranged in a circular manner.
     *
     * - The typewriter initially points to 'a'
     * - Typing the current character takes 1 second
     * - Moving the pointer one step clockwise or counterclockwise takes 1 second
     *
     * You can move in either direction.
     *
     * Return the minimum time required to type the given word.
     *
     * Greedy Insight:
     * - At each character, choose the shorter rotation (clockwise or counterclockwise)
     * - Add 1 second for typing the character
     * - Repeat for all characters
     */

    public static int minTimeToType(String word) {
        int time = 0;
        char current = 'a'; // initial pointer position

        for (char c : word.toCharArray()) {
            int diff = Math.abs(c - current);
            
            // choose the shorter direction in circular alphabet
            time += Math.min(diff, 26 - diff);
            
            // 1 second to type the character
            time += 1;

            // move pointer to current character
            current = c;
        }

        return time;
    }

    // ---------------- MAIN METHOD ----------------
    public static void main(String[] args) {

        String word = "abc";

        int result = minTimeToType(word);

        System.out.println("Minimum time to type the word: " + result);
    }
}
