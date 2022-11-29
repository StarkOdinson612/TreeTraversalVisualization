package com.starkodinson;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        // write your code here
        int[] data = {3, 7, 3, 4, 32, 5, 64, 34, 2, 12, 5, 6, 23, 43, 62, 56, 54};
        List<TreeNode<Integer>> heap = new LinkedList<>();

        for (int i = 0; i < data.length; i++)
        {
            if (i == 0) { heap.add(new TreeNode<Integer>(data[i])); }
            else
            {
                TreeNode<Integer> p = heap.get((i - 1)/ 2);
                TreeNode<Integer> c = new TreeNode(data[i], p);
                heap.add(c);
            }
        }

        System.out.println("RANDOM: " + heap);

    }
}
