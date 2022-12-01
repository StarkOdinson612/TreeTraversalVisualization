package com.starkodinson;

import java.util.*;

public class HeapTreeNode extends TreeNode {
    protected int data;

    public HeapTreeNode(int data) {
        super(data);
        this.data = data;
    }

    public HeapTreeNode(int data, HeapTreeNode parent) {
        super(data, parent);
        this.data = data;
    }

    @Override
    public Integer getData() { return this.data; }

    private static int parentIndex(int i) { return (i - 1) / 2; }
    private static int leftChildIndex(int i) { return i * 2 + 1; }
    private static int rightChildIndex(int i) { return i * 2 + 2; }

    private static int largestChildIndex(List<HeapTreeNode> heap, int i) {
        int lci = leftChildIndex(i);
        int rci = rightChildIndex(i);

        if (lci >= heap.size()) { return -1; }
        else if (rci > heap.size()) { return heap.get(lci).getData() > heap.get(i).getData() ? lci : -1; }


        if (heap.get(rci).getData() > heap.get(lci).getData())
        {
            if (heap.get(rci).getData() > heap.get(i).getData()) {
                return rci;
            }
        }

        if (heap.get(lci).getData() > heap.get(i).getData()) { return lci; }

        return -1;
    }


    public static List<HeapTreeNode> buildHeap(int[] arr)
    {
        List<HeapTreeNode> heap = new LinkedList<>();

        for (int i = 0; i < arr.length; i++)
        {
            if (i == 0) { heap.add(new HeapTreeNode(arr[i])); }
            else
            {
                HeapTreeNode p = heap.get((i - 1)/ 2);
                HeapTreeNode c = new HeapTreeNode(arr[i], p);
                heap.add(c);
            }
        }

        return heap;
    }

    public static void buildMaxHeap(List<HeapTreeNode> heap) {
        for (int i = 0; i < heap.size() - 1; i++)
        {
            siftDown(heap, i);
        }
    }

    public static void siftDown(List<HeapTreeNode> heap, int index, int c)
    {
        int greatestChildIndex = largestChildIndex(heap, index);
        if (greatestChildIndex == -1) { return; }
        swap(heap, greatestChildIndex, index);
        siftDown(heap, greatestChildIndex);
    }

    private static void swap(List<HeapTreeNode> heap, int t, int i)
    {
        int temp = heap.get(i).getData();
        heap.get(i).setData(heap.get(t).getData());
        heap.get(t).setData(temp);
    }
}
