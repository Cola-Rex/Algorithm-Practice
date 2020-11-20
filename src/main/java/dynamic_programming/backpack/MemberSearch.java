package dynamic_programming.backpack;

public class MemberSearch {

    /**
     * @param w 物品重量
     * @param v 物品价值
     * @param W 背包的最大容量
     * @return 最大价值
     */
    public int solve(int[] w, int[] v, int W) {
        int n = w.length;
        final int[][] memo = new int[n][W + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= W; j++) {
                memo[i][j] = -1;
            }
        }
        return search(0, w, v, n, 0, W, memo);
    }

    /**
     * @param s 已装重量
     */
    private int search(int index, int[] w, int[] v, int n, int s, int W, int[][] memo) {
        //已经没有物品搜索了
        if (index >= n) {
            return 0;
        }
        if (memo[index][s] != -1) {
            return memo[index][s];
        }
        //如果装不下这件物品
        if (s + w[index] > W) {
            return memo[index][s] = search(index + 1, w, v, n, s, W, memo);
        }
        //否则直接返回拿index这件物品和不拿这件物品的最大价值就行了
        return memo[index][s] = Math.max(search(index + 1, w, v, n, s + w[index], W, memo) + v[index], search(index + 1, w, v, n, s, W, memo));
    }
}
