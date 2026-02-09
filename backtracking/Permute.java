import java.util.ArrayList;
import java.util.List;

public class Permute {

    public static void main(String[] args) {

        int[] nums = {1, 2, 3};

        Solution solution = new Solution();
        List<List<Integer>> result = solution.permute(nums);

        // Print permutations
        for (List<Integer> perm : result) {
            System.out.println(perm);
        }
    }
}