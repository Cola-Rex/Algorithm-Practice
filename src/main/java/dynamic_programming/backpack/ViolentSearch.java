package dynamic_programming.backpack;

public class ViolentSearch {

	/**
	 * @param index 索引
	 * @param w		物品重量
	 * @param v		物品价值
	 * @param n		w数组的长度
	 * @param s		已装重量
	 * @param W		背包的最大容量
	 * @return		最大价值
	 */
	private int search(int index, int[] w, int[] v, int n, int s, int W) {
		// 已经没有物品搜索了
		if (index >= n) {
			return 0;
		}
		//如果装不下这件物品，直接返回不拿这件物品的重量即可
		if (s + w[index] > W) {
			return search(index + 1, w, v, n, s, W);
		}
		//否则我们直接返回拿index这件物品和不拿这件物品的最大价值就行了
		return Math.max(search(index + 1, w, v, n, s + w[index], W), search(index + 1, w, v, n, s, W));
	}
	
	public int solve(int[] w, int[] v, int W) {
		int n = w.length;
		return search(0, w, v, n, 0, W);
	}
}
