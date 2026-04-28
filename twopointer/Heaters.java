import java.util.Arrays;

public class Heaters {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);

        int i = 0; // heater pointer
        int radius = 0;

        for (int house : houses) {
            // Move heater pointer to the closest heater
            while (i < heaters.length - 1 &&
                   Math.abs(heaters[i + 1] - house) <= Math.abs(heaters[i] - house)) {
                i++;
            }

            // Update max minimum distance
            radius = Math.max(radius, Math.abs(heaters[i] - house));
        }

        return radius;
    }
}
