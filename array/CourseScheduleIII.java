import java.util.*;

public class CourseScheduleIII {

    /*
     * LeetCode 630 - Course Schedule III
     *
     * Idea:
     * Sort by end day, use greedy selection.
     * Maintain a max heap manually (int array) to store durations.
     * If total time exceeds deadline, remove largest duration.
     *
     * Running Time: O(n log n)
     */

    static int[] heap = new int[10001];
    static int size = 0;

    // insert into max heap
    static void add(int val) {
        heap[size] = val;
        int i = size++;
        
        while (i > 0 && heap[i] > heap[(i - 1) / 2]) {
            int temp = heap[i];
            heap[i] = heap[(i - 1) / 2];
            heap[(i - 1) / 2] = temp;
            i = (i - 1) / 2;
        }
    }

    // remove max
    static int poll() {
        int max = heap[0];
        heap[0] = heap[--size];

        int i = 0;
        while (2 * i + 1 < size) {
            int j = 2 * i + 1;

            if (j + 1 < size && heap[j + 1] > heap[j]) {
                j++;
            }

            if (heap[i] >= heap[j]) break;

            int temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;

            i = j;
        }

        return max;
    }

    public static int scheduleCourse(int[][] courses) {

        Arrays.sort(courses, (a, b) -> a[1] - b[1]);

        int time = 0;
        size = 0;

        for (int[] c : courses) {

            time += c[0];
            add(c[0]);

            if (time > c[1]) {
                time -= poll();
            }
        }

        return size;
    }

    public static void main(String[] args) {

        int[][] courses = {
                {100, 200},
                {200, 1300},
                {1000, 1250},
                {2000, 3200}
        };

        System.out.println(scheduleCourse(courses));
    }
}
