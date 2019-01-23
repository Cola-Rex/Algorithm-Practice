package topK;

import java.util.List;

/**
 * 已知m个递减有序的数组，求所有数组的数据并集的前k大的数
 * 适合采用Merge的方法,时间复杂度(O(k*m);
 * 
 * 思路：一共遍历k次，每次遍历，找出所有数组的头元素中最大的，
 * 这个元素即第k大，将下标后移1位。
 * 下一次遍历即从元素后面开始，继续找最大的元素
 */
public class Merge {

	public int[] getTopK(List<List<Integer>> input, int k) {
		int[] index = new int[input.size()];	//保存每个数组下标扫描的位置，默认 m个0
		int[] result = new int[k];
		for (int i = 0; i < k; i++) {	//循环k次
			int max = Integer.MIN_VALUE;
			int maxIndex = 0;
			for (int j = 0; j < input.size(); j++) {	//循环m次
				if (index[j] < input.get(j).size()) {
					if (max < input.get(j).get(index[j])) {
						max = input.get(j).get((index[j]));
						maxIndex = j;
					}
				}
			}
			if (max == Integer.MIN_VALUE) {
				return result;
			}
			result[i] = max;
			index[maxIndex] += 1;
		}
		return result;
	}
}
