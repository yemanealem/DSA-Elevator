class Solution {
    List<String> result = new ArrayList<>();

    int[] hours = {8, 4, 2, 1};
    int[] minutes = {32, 16, 8, 4, 2, 1};

    public List<String> readBinaryWatch(int turnedOn) {
        backtrack(0, 0, 0, 0, turnedOn);
        return result;
    }

    private void backtrack(int index, int hour, int minute, int count, int target) {
        // Stop if invalid
        if (hour > 11 || minute > 59) return;

        // Found valid time
        if (count == target) {
            result.add(hour + ":" + (minute < 10 ? "0" : "") + minute);
            return;
        }

        // Stop if all LEDs checked
        if (index == 10) return;

        // Choose current LED
        if (index < 4) {
            backtrack(index + 1, hour + hours[index], minute, count + 1, target);
        } else {
            backtrack(index + 1, hour, minute + minutes[index - 4], count + 1, target);
        }

        // Skip current LED
        backtrack(index + 1, hour, minute, count, target);
    }
}
