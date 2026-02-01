class Solution {
    List<String> res = new ArrayList<>();

    int[] hourLED = {8, 4, 2, 1};
    int[] minLED  = {32, 16, 8, 4, 2, 1};

    public List<String> readBinaryWatch(int turnedOn) {
        dfs(0, 0, 0, turnedOn);
        return res;
    }

    private void dfs(int idx, int hour, int min, int left) {
        // prune invalid time
        if (hour > 11 || min > 59) return;

        // prune impossible selections
        if (10 - idx < left) return;

        // success
        if (left == 0) {
            res.add(hour + ":" + (min < 10 ? "0" : "") + min);
            return;
        }

        if (idx == 10) return;

        // choose current LED
        if (idx < 4)
            dfs(idx + 1, hour + hourLED[idx], min, left - 1);
        else
            dfs(idx + 1, hour, min + minLED[idx - 4], left - 1);

        // skip current LED
        dfs(idx + 1, hour, min, left);
    }
}
