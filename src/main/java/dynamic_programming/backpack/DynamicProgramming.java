package dynamic_programming.backpack;

public class DynamicProgramming {

    /**
     * @param w 物品重量
     * @param v 物品价值
     * @param W 背包的最大容量
     * @return 总价值
     */
    public int solve(int[] w, int[] v, int W) {
        int n = w.length;
        int[][] dp = new int[n + 1][W + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= W; j++) {
                if (j < w[i]) {
                    dp[i + 1][j] = dp[i][j];
                } else {
                    dp[i + 1][j] = Math.max(dp[i][j], dp[i][j - w[i]] + v[i]);
                }
            }
        }
        return dp[n][W];
    }
}
