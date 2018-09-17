package binaryTraversal;

import java.util.ArrayList;

/**
 * 遍历+递归
 */
public class PreorderTraversal1 {
	public ArrayList<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        traverse(root, result);
        return result;
    }
        
    private void traverse(TreeNode root, ArrayList<Integer> result) {
    	//中止条件
        if (root == null) {
            return;
        }
        
        result.add(root.val);
        traverse(root.left, result);
        traverse(root.right, result);
    }
}

class TreeNode {
	public int val;
	public TreeNode left, right;
	
	public TreeNode(int val) {
		this.val = val;
		this.left = this.right = null;
	}
}