package dynamic_programming.robMoney;

/**
 * You are a professional robber planning to rob houses along a street. 
 * Each house has a certain amount of money stashed, 
 * the only constraint stopping you from robbing each of them is that 
 * adjacent houses have security system connected and it will automatically contact the police 
 * if two adjacent houses were broken into on the same night.
 * Given a list of non-negative integers representing the amount of money of each house, 
 * determine the maximum amount of money you can rob tonight without alerting the police.
 */
public class ViolentSearch {

	//复杂度 o(2^n),指数级走不远
	public int search(int i, int[] nums) {
		if (i < 0) {
			return 0;
		}
		
		return Math.max(search(i - 1, nums), nums[i] + search(i - 2, nums));
	}
}
