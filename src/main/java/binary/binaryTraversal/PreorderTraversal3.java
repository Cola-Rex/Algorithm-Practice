package binary.binaryTraversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树的前序遍历
 * 非递归＋栈
 */
public class PreorderTraversal3 {

	public List<Integer> preorderTraversal(TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();
		List<Integer> result = new ArrayList<>();
		
		if(root == null) {
			return result;
		}
		
		stack.push(root);
		while(!stack.empty()) {
			TreeNode node = stack.pop();
			result.add(node.val);
			//先右后左 入栈，先左后右 出栈
			if(node.right != null) {
				stack.push(node.right);
			}
			if(node.left != null) {
				stack.push(node.left);
			}
		}
		return result;
	}
}
