package com.starkodinson;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        // write your code here
        int[] data = {3, 7, 3, 4, 32, 5, 64, 34, 2, 12, 5, 6, 23, 43, 62, 56, 54};
        List<HeapTreeNode> heap = new LinkedList<>();

        for (int i = 0; i < data.length; i++)
        {
            if (i == 0) { heap.add(new HeapTreeNode(data[i])); }
            else
            {
                HeapTreeNode p = heap.get((i - 1)/ 2);
                HeapTreeNode c = new HeapTreeNode(data[i], p);
                heap.add(c);
            }
        }

        System.out.println("RANDOM: " + heap);
        HeapTreeNode.buildMaxHeap(heap);
        System.out.println("MAX: " + heap);
    }
}
