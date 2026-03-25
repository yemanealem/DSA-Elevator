class Solution {
    public int minStickers(String[] stickers, String target) {
        int n = target.length();
        int m = stickers.length;
        
        int[][] stickerCount = new int[m][26];
        for (int i = 0; i < m; i++) {
            for (char c : stickers[i].toCharArray()) {
                stickerCount[i][c - 'a']++;
            }
        }

        int[] dp = new int[1 << n];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        return dfs(dp, target, stickerCount, (1 << n) - 1);
    }

    private int dfs(int[] dp, String target, int[][] stickerCount, int state) {
        if (dp[state] != -1) return dp[state];
        int n = target.length();
        int ans = Integer.MAX_VALUE;

        for (int[] sticker : stickerCount) {
            int newState = state;
            int[] count = Arrays.copyOf(sticker, 26);

            boolean used = false;
            for (int i = 0; i < n; i++) {
                if (((state >> i) & 1) == 1) {
                    char c = target.charAt(i);
                    if (count[c - 'a'] > 0) {
                        count[c - 'a']--;
                        newState ^= (1 << i);
                        used = true;
                    }
                }
            }

            if (!used) continue; // skip stickers that don't help

            int temp = dfs(dp, target, stickerCount, newState);
            if (temp != -1) ans = Math.min(ans, 1 + temp);
        }

        dp[state] = (ans == Integer.MAX_VALUE) ? -1 : ans;
        return dp[state];
    }
}
