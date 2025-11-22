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
     * 
     */

}
