public class MinimumTimeToTypeWord  {
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
