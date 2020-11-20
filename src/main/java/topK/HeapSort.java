package topK;

/**
 * 使用堆排序
 * 求最大K个采用最小堆，而求最小K个采用最大堆。
 * 时间复杂度O(nlogK)(n:数据的长度),特别适用于大数据的求Top K
 * 数据量比较大（特别是大到内存不可以容纳）时，偏向于采用堆
 * <p>
 * 以下求最大K个元素的步骤：
 * 1.根据数据前K个建立K个节点的小根堆。
 * 2.在后面的N-K的数据中进行扫描
 * 3.如果数据大于小根堆的根节点，则根节点的值覆为该数据，并调节节点至小根堆。
 * 4.如果数据小于或等于小根堆的根节点，小根堆无变化。
 */
public class HeapSort {

    /**
     * 创建K个节点的最小堆
     */
    int[] createHeap(int[] a, int k) {
        int[] result = new int[k];
        //先将前K个索引的数赋值到目标数组
        for (int i = 0; i < k; i++) {
            result[i] = a[i];
        }

        //一个排序过程，将不满足条件的父节点与子节点交换
        //这里数组在堆中的映射是用的层序遍历，知道这个才看得懂代码
        for (int i = 1; i < k; i++) {
            int child = i;
            int parent = (i - 1) / 2;
            int temp = a[i];
            while (parent >= 0 && child != 0 && result[parent] > temp) {
                result[child] = result[parent];
                child = parent;
                parent = (parent - 1) / 2;
            }
            result[child] = temp;
        }
        return result;
    }

    //插入 value 元素，并调整树
    void insert(int[] a, int value) {
        a[0] = value;
        int parent = 0;

        while (parent < a.length) {
            int lchild = 2 * parent + 1;
            int rchild = 2 * parent + 2;
            int minIndex = parent;
            if (lchild < a.length && a[minIndex] > a[lchild]) {
                minIndex = lchild;
            }
            if (rchild < a.length && a[minIndex] > a[rchild]) {
                minIndex = rchild;
            }
            if (minIndex == parent) {
                break;
            } else {
                int temp = a[parent];
                a[parent] = a[minIndex];
                a[minIndex] = temp;
                parent = minIndex;
            }
        }
    }

    int[] getTopKByHeap(int input[], int k) {
        int heap[] = this.createHeap(input, k);
        for (int i = k; i < input.length; i++) {
            if (input[i] > heap[0]) {
                this.insert(heap, input[i]);
            }
        }
        return heap;
    }

    public static void main(String[] args) {
        int a[] = {2, 20, 3, 7, 9, 1, 17, 18, 0, 4};
        int result[] = new HeapSort().getTopKByHeap(a, 3);
        for (int temp : result) {
            System.out.println(temp);
        }
    }
}
