package com.xian.open.leetcode.middle;

import java.util.*;

/**
 * @author xian
 * @description
 * @createTime 2021/10/20 13:54
 */
public class Solution04 {

    public static int maxLength (int[] arr) {
        // write code here
        Queue<Integer> queue = new ArrayDeque<>();
        int max = 0;
        for (int i : arr) {
            while (queue.contains(i)) {
                queue.poll();
            }
            queue.add(i);
            max = Math.max(max,queue.size());
        }
        return max;
    }

    public int lowestCommonAncestor (TreeNode root, int o1, int o2) {
        // write code here
        // 两个栈，分别保存o1和o2的路径
        Stack<TreeNode> o1Stack = new Stack<>();
        Stack<TreeNode> o2Stack = new Stack<>();
        getPath(root,o1,o1Stack);
        getPath(root,o2,o2Stack);
        int val = root.val;
        while (!(o1Stack.isEmpty() || o2Stack.isEmpty())) {
            int val1 = o1Stack.pop().val;
            int val2 = o2Stack.pop().val;
            if (val1 == val2) {
                val = val1;
            }
        }
        return val;
    }
    private boolean getPath(TreeNode root, int target, Stack<TreeNode> path) {
        if (root == null) return false;
        if (root.val == target
                || getPath(root.left,target,path)
                || getPath(root.right,target,path )) {
            path.push(root);
            return true;
        }
        return false;
    }


    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        Arrays.sort(input);
        for (int i = 0; i < k; i++) {
            list.add(input[i]);
        }
        return list;
    }

    /**
     * 给定一棵二叉树，分别按照二叉树先序，中序和后序打印所有的节点
     * @param root
     * @return
     */
    public int[][] threeOrders (TreeNode root) {
        // write code here
        List<Integer> preList = new ArrayList<>();
        List<Integer> inList = new ArrayList<>();
        List<Integer> afterList = new ArrayList<>();
        pre(root,preList);
        in(root,inList);
        after(root,afterList);
        int[][] result = new int[3][preList.size()];
        int[] preArr = new int[preList.size()];
        int[] inArr = new int[inList.size()];
        int[] afterArr = new int[afterList.size()];
        for (int i = 0; i < preArr.length; i++) {
            preArr[i] = preList.get(i);
            inArr[i] = inList.get(i);
            afterArr[i] = afterList.get(i);
        }
        result[0] = preArr;
        result[1] = inArr;
        result[2] = afterArr;
        return result;
    }

    private void pre(TreeNode root, List<Integer> list) {
        if (root != null) {
            list.add(root.val);
            pre(root.left,list);
            pre(root.right,list);
        }
    }
    private void in(TreeNode root, List<Integer> list) {
        if (root != null) {
            in(root.left,list);
            list.add(root.val);
            in(root.right,list);
        }
    }
    private void after(TreeNode root, List<Integer> list) {
        if (root != null) {
            after(root.left,list);
            after(root.right,list);
            list.add(root.val);
        }
    }


    /**
     * 请实现有重复数字的升序数组的二分查找
     * 给定一个 元素有序的（升序）长度为n的整型数组 nums 和一个目标值 target  ，
     * 写一个函数搜索 nums 中的第一个出现的target，如果目标值存在返回下标，否则返回 -1
     * @param nums
     * @param target
     * @return
     */
    public int search (int[] nums, int target) {
        // write code here
        return 0;
    }

    /**
     * 一个整型数组里除了两个数字只出现一次，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
     * @param array
     * @return
     */
    public static int[] FindNumsAppearOnce (int[] array) {
        // write code here
        int[] arr = new int[2];
        Map<Integer, Object>  map = new HashMap<>();
        for (int i : array) {
            Object object = map.get(i);
            if (object == null) {
                map.put(i, new Object());
            } else {
                map.remove(i);
            }
        }
        int idx = 0;
        for (Map.Entry<Integer, Object> entry : map.entrySet()) {
            System.out.println(entry);
            arr[idx++] = entry.getKey();
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {1,4,1,6};
        FindNumsAppearOnce(arr);
    }


    public class TreeNode {
     int val = 0;
     TreeNode left = null;
     TreeNode right = null;
   }



}
