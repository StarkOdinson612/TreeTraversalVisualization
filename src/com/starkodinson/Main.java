package com.starkodinson;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        // write your code here
        int[] data = {2, 4, 1, 5, 3, 6, 7};
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
