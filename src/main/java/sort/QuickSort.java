package sort;

import util.StdRandom;

/**
 * 快速排序
 */
@SuppressWarnings("rawtypes")
public class QuickSort {

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);    //切分
        sort(a, lo, j - 1);                //继续将左半部分 a[lo..j-1] 排序
        sort(a, j + 1, hi);                //继续将右半部分 a[j+1..hi] 排序
    }

    /*
     * 切分方法。
     * 每次递归，将 比基准值小的元素放在基准值左边，比基准值大的元素放在右边
     */
    private static int partition(Comparable[] a, int lo, int hi) {
        //将数组切分为 a[lo..i-1], a[i], a[i+1..hi]
        int i = lo, j = hi + 1; //左右扫描指针
        Comparable v = a[lo];    //切分元素

        while (true) {
            //扫描左右，检查扫描是否结束并交换元素
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);    //将 v = a[j] 放入正确位置
        return j;        //a[lo...j-1] <= a[j] <= a[j+1..hi] 达成
    }

    @SuppressWarnings("unchecked")
    private static boolean less(Comparable v, Comparable w) {
        if (v == w) return false;

        return v.compareTo(w) < 0;
    }

    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}
