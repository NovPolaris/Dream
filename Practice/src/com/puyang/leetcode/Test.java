package com.puyang.leetcode;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // Parameter

        // Result
        System.out.println("------------ Start ------------");
        System.out.println(solution.bstFromPreorder(new int[]{8,5,1,7,10,12}));
        System.out.println("------------- End -------------");
    }

    public static TreeNode buildTree(Integer[] A) {
        if (A == null || A.length == 0 || A[0] == null) {
            return null;
        }
        TreeNode[] arr = new TreeNode[A.length];
        arr[0] = new TreeNode(A[0]);
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == null) {
                count++;
                continue;
            }
            if ((i - count) * 2 + 1 < A.length && A[(i - count) * 2 + 1] != null) {
                arr[(i - count) * 2 + 1] = new TreeNode(A[(i - count) * 2 + 1]);
                arr[i].left = arr[(i - count) * 2 + 1];
            }
            if ((i - count) * 2 + 2 < A.length && A[(i - count) * 2 + 2] != null) {
                arr[(i - count) * 2 + 2] = new TreeNode(A[(i - count) * 2 + 2]);
                arr[i].right = arr[(i - count) * 2 + 2];
            }
        }

        return arr[0];
    }

    public static int[][] buildMatrix(String arrayString) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();

        String[] arr = arrayString.substring(1, arrayString.length() - 1).split(",");

        if (arr.length == 0 || arr.length == 1 && arr[0].length() == 0) {
            return new int[0][0];
        }
        for (String s : arr) {
            if (s.charAt(0) == '[') {
                temp = new ArrayList<>();
                temp.add(Integer.parseInt(s.substring(1)));
            } else if (s.charAt(s.length() - 1) == ']') {
                temp.add(Integer.parseInt(s.substring(0, s.length() - 1)));
                list.add(new ArrayList<>(temp));
            } else {
                temp.add(Integer.parseInt(s));
            }
        }

        int[][] matrix = new int[list.size()][list.get(0).size()];

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(0).size(); j++) {
                matrix[i][j] = list.get(i).get(j);
            }
        }

        return matrix;
    }
}
