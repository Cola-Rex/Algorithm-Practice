package binary.binarySearch;

public class BinarySearch {
    public int searchInsert(int[] nums, int target) {
        return binarySearch(nums, target, 0, nums.length-1);
    }
    
    public int binarySearch(int[] nums, int target, int left, int right) {
        int mid = (left + right) / 2;
        
        if(left >= right) {
            if(nums[mid] < target) return mid + 1;
            else return mid;
        }
       
        if(nums[mid] < target) {
            binarySearch(nums, target, mid, right);
        } else if(nums[mid] > target) {
            binarySearch(nums, target, left, mid);
        } else {
            return nums[mid];
        }
        
        return mid;
    }
}
