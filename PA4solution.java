
import java.util.Scanner;

public class PA4solution {
    /*
     * 
     * At first, to me, this looks like a sliding window problem. However, on second
     * inspection,
     * it seems to be a Kadane's algorithim problem borrowing from solution to the
     * Maximum Subarray Sum problem
     * which I solved in 2023:
     * https://drive.google.com/file/d/1I3uP0MD2pY6kpmCo0NsjwkOTScndegu3/view?usp=
     * share_link
     * 
     * I initially thought this was a sliding window approach similar to my video:
     * Longest Consecutive Subsequence: https://www.youtube.com/watch?v=GmlMx5IMEqE
     * However, this problem accumlates (or should accumlate) an ongoing score (or
     * sum) based on encountered letters. And, find the goal seems to be to find the
     * window where that score difference is maximized.
     * 
     * 
     * I'm pasting my from 2023 to Kadane's algorithim here for reference
     * 
     * * class Solution {
     * public int maxSubArray(int[] nums) {
     * // Initialize our variables using the first element.
     * int currentSubarray = nums[0];
     * int maxSubarray = nums[0];
     * 
     * // Start with the 2nd element since we already used the first one.
     * for (int i = 1; i < nums.length; i++) {
     * int num = nums[i];
     * // If current_subarray is negative, throw it away. Otherwise, keep adding to
     * it.
     * currentSubarray = Math.max(num, currentSubarray + num);
     * maxSubarray = Math.max(maxSubarray, currentSubarray);
     * }
     * 
     * return maxSubarray;
     * }
     * 
     * 
     * Plan is to have a running score for a particular team -
     * +1 if the team's letter is encountered, -1 if the other team's letter is
     * encountered.
     * and from that point - use Kadane's algorithim to find the maximum score
     * difference.
     * 
     * 
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the input: ");

        /*
         * input will always be non-empty
         * will only be B and m.
         * so no invalid chars / mixed case.
         * no sanitization or anything like that.
         * 
         */

        String input = sc.nextLine();

        /*
         * I'll have to "funnel" the input into a helper array that maps the string to
         * an array of (+1/-1).
         * However, one array can't handle both. We need to assume the side of one team,
         * one at a time.
         * So there will be an array where when we see B (blue) we get a +1 and m is -1.
         * And there will be an array where when we see B, we get a -1 and m is +1.
         * (flipped)
         * Then we can pass each of those numerical arrays to Kadane's to generate two
         * results and start / end indicies of their best performance.
         * 
         */

        int[] uva_array = uva_array(input);
        int[] vt_array = vt_array(input);
        int[] uva_kadane = kadane(uva_array);
        int[] vt_kadane = kadane(vt_array);
        who_wins(uva_kadane, vt_kadane);

    }

    public static int[] uva_array(String input) {

        int[] arr = new int[input.length()];

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 'B') {
                arr[i] = 1;
            } else {
                arr[i] = -1;
            }
        }
        return arr;
    }

    public static int[] vt_array(String input) {
        int[] arr = new int[input.length()];

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 'm') {
                arr[i] = 1;
            } else {
                arr[i] = -1;
            }
        }
        return arr;
    }

    public static void who_wins(int[] team1, int[] team2) {

        if (team1[0] > team2[0]) {
            System.out.println("UVA");
            // getting the result.
            // need to offset by 1 to get the correct index of the first element of the
            // original array.
            System.out.println(team1[1] + 1 + " " + (team1[2] + 1));

        } else if (team2[0] > team1[0]) {
            System.out.println("VT");
            System.out.println(team2[1] + 1 + " " + (team2[2] + 1));
        } else {
            System.out.println("TIE");
            System.out.println(team1[1] + 1 + " " + (team1[2] + 1));
            System.out.println(team2[1] + 1 + " " + (team2[2] + 1));
        }
    }

    /*
     * when we pass both results from uva and vt (that were filtered through
     * Kadane's)
     * we need to determine who wins.
     * if the first elem of that result array from one is greater than the other,
     * that team won.
     * So check for both uva and vt that condition. Print that.
     * 
     * 
     * 
     */

    /*
     * My solution to the Maximum Subarray Sum problem in 2023 adapted for this
     * problem.
     * 
     * class Solution {
     * public int maxSubArray(int[] nums) {
     * // Initialize our variables using the first element.
     * int currentSubarray = nums[0];
     * int maxSubarray = nums[0];
     * 
     * // Start with the 2nd element since we already used the first one.
     * for (int i = 1; i < nums.length; i++) {
     * int num = nums[i];
     * // If current_subarray is negative, throw it away. Otherwise, keep adding to
     * it.
     * currentSubarray = Math.max(num, currentSubarray + num);
     * maxSubarray = Math.max(maxSubarray, currentSubarray);
     * }
     * 
     * return maxSubarray;
     * }
     * }
     * 
     */

    public static int[] kadane(int[] nums) {

        // if only "B" , output should say UVA but I'm seeing TIE.

        /*
         * main test cases pass - edge cases?
         * BmBm (tie works).
         */
        int[] result = new int[3];
        int currentSum = nums[0];
        int maxSum = nums[0];

        int currentStart = 0;
        int bestStart = 0;
        int bestEnd = 0;

        for (int i = 1; i < nums.length; i++) {

            int num = nums[i];
            if (num > currentSum + num) {
                currentSum = num;
                currentStart = i;
            } else {
                currentSum += num;
            }

            if (currentSum > maxSum) {
                maxSum = currentSum;
                bestStart = currentStart;
                bestEnd = i;
            }
            result[0] = maxSum;
            result[1] = bestStart;
            result[2] = bestEnd;
        }
        return result;
    }

}
