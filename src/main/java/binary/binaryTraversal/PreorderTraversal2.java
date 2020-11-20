package binary.binaryTraversal;

import java.util.ArrayList;

/**
 * 二叉树的前序遍历
 * 分治+递归
 */
public class PreorderTraversal2 {

    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        ArrayList<Integer> left = preorderTraversal(root.left);
        ArrayList<Integer> right = preorderTraversal(root.right);

        //前序遍历的顺序为：中-左-右
        result.add(root.val);
        result.addAll(left);
        result.addAll(right);

        return result;
    }
}
