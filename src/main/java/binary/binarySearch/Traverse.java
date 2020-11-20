package binary.binarySearch;

/**
 * 使用遍历方法，找到目标值的下标，否则为-1
 */
public class Traverse {

    static int binarySearch(int[] array, int key) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {    //使用 <= 是为了判断目标值在边界的情况
            int mid = left + (right - left) / 2;
            if (array[mid] == key) {
                return mid;
            } else if (array[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
