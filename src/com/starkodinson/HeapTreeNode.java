package com.starkodinson;

import java.util.*;

public class HeapTreeNode {
    protected Integer data;

    public HeapTreeNode(int data) {
        this.data = data;
    }

    public HeapTreeNode(int data, HeapTreeNode parent) {
        this.data = data;
    }

    public Integer getData() { return this.data; }

    @Override
    public String toString() { return data.toString(); }

    public void setData(int data)
    {
        this.data = data;
    }

    private static int parentIndex(int i) { return (i - 1) / 2; }
    private static int leftChildIndex(int i) { return i * 2 + 1; }
    private static int rightChildIndex(int i) { return i * 2 + 2; }

    public static int largestChildIndex(List<HeapTreeNode> heap, int i, int c) {
        int lci = leftChildIndex(i);
        int rci = rightChildIndex(i);

        if (lci >= c) { return -1; }
        else if (rci >= c)
        {
            return heap.get(lci).getData() > heap.get(i).getData() ? lci : -1;
        }


        if (heap.get(rci).getData() > heap.get(lci).getData())
        {
            if (heap.get(rci).getData() > heap.get(i).getData()) {
                return rci;
            }
        }

        if (heap.get(lci).getData() > heap.get(i).getData()) { return lci; }

        return -1;
    }

    private static int smallestChildIndex(List<HeapTreeNode> heap, int i, int c) {
        int lci = leftChildIndex(i);
        int rci = rightChildIndex(i);

        if (lci >= c) { return -1; }
        else if (rci >= c) { return heap.get(lci).getData() < heap.get(i).getData() ? lci : -1; }


        if (heap.get(rci).getData() < heap.get(lci).getData())
        {
            if (heap.get(rci).getData() < heap.get(i).getData()) {
                return rci;
            }
        }

        if (heap.get(lci).getData() < heap.get(i).getData()) { return lci; }

        return -1;
    }

    public static void heapSort(List<HeapTreeNode> heap)
    {
        for (int c = 0; c < heap.size(); c++)
        {
            swap(heap, 0, heap.size() - 1 - c);
            siftDownMax(heap, 0, heap.size() - 1 - c);
        }
    }

    public static void heapSortMin(List<HeapTreeNode> heap)
    {
        for (int c = 0; c < heap.size(); c++)
        {
            swap(heap, 0, heap.size() - 1 - c);
            siftDownMin(heap, 0, heap.size() - 1 - c);
        }
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
        for (int i = heap.size() - 1; i >=0; i--)
        {
            siftDownMax(heap, i, heap.size());
        }
    }

    public static void buildMinHeap(List<HeapTreeNode> heap) {
        for (int i = heap.size() - 1; i >=0; i--)
        {
            siftDownMin(heap, i, heap.size());
        }
    }

    public static void siftDownMax(List<HeapTreeNode> heap, int index, int c)
    {
        int greatestChildIndex = largestChildIndex(heap, index, c);
        if (greatestChildIndex == -1) { return; }
        swap(heap, greatestChildIndex, index);
        siftDownMax(heap, greatestChildIndex, c);
    }

    public static void siftDownMin(List<HeapTreeNode> heap, int index, int c)
    {
        int smallestChildIndex = smallestChildIndex(heap, index, c);
        if (smallestChildIndex == -1) { return; }
        swap(heap, smallestChildIndex, index);
        siftDownMin(heap, smallestChildIndex, c);
    }

    private static void swap(List<HeapTreeNode> heap, int t, int i)
    {
        int temp = heap.get(i).getData();
        heap.get(i).setData(heap.get(t).getData());
        heap.get(t).setData(temp);
    }
}
