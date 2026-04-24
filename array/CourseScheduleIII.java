import java.util.*;

public class CourseScheduleIII {

    /*
     * LeetCode 630 - Course Schedule III
     *
     * How it works:
     * 1. Sort courses by lastDay (deadline).
     * 2. Use a max heap to store durations of selected courses.
     * 3. Add courses greedily.
     * 4. If total time exceeds deadline, remove longest course.
     *
     * Running Time: O(n log n)
     */

    public static int scheduleCourse(int[][] courses) {

        Arrays.sort(courses, (a, b) -> a[1] - b[1]);

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        int time = 0;

        for (int[] c : courses) {

            time += c[0];
            maxHeap.add(c[0]);

            if (time > c[1]) {
                time -= maxHeap.poll();
            }
        }

        return maxHeap.size();
    }

    public static void main(String[] args) {

        int[][] courses = {
                {100, 200},
                {200, 1300},
                {1000, 1250},
                {2000, 3200}
        };

        int result = scheduleCourse(courses);

        System.out.println("Max courses: " + result);
    }
}
