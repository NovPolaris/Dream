package com.puyang.leetcode;

class Solution {
    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        return helper(preorder, 0, preorder.length - 1);
    }

    private TreeNode helper(int[] preorder, int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            return new TreeNode(preorder[start]);
        }

        TreeNode node = new TreeNode(preorder[start]);
        int index = find(preorder, start + 1, end, preorder[start]);
        node.left = helper(preorder, start + 1, index - 1);
        node.right = helper(preorder, index, end);
        return node;
    }

    private int find(int[] preorder, int start, int end, int target) {
        for (int i = start; i < end; i++) {
            if (preorder[i] > target) {
                return i;
            }
        }

        return end + 1;
    }
}
