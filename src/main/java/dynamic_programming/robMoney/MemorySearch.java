package dynamic_programming.robMoney;

/**
 * 记忆搜索，用一个数组保存已经计算过的值
 */
public class MemorySearch {

    //倒序搜索
    //复杂度 o(N)
    public int robReverse(int[] nums) {
        int[] memo = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            memo[i] = -1;
        }
        return searchReverse(nums.length - 1, nums, memo);
    }

    private int searchReverse(int i, int[] nums, int[] memo) {
        if (i < 0) {
            return 0;
        }
        if (memo[i] != -1) {
            return memo[0];
        }
        return memo[i] = Math.max(searchReverse(i - 1, nums, memo), nums[i] + searchReverse(i - 2, nums, memo));
    }

    //正序搜索
    public int rob(int[] nums) {
        int[] memo = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            memo[i] = -1;
        }
        return searchHead2Tail(0, nums, memo);
    }

    private int searchHead2Tail(int i, int[] nums, int[] memo) {
        if (i >= nums.length) {
            return 0;
        }
        if (memo[i] != -1) {
            return memo[i];
        }
        return memo[i] = Math.max(searchHead2Tail(i + 1, nums, memo), nums[i] + searchHead2Tail(i + 2, nums, memo));
    }
}
