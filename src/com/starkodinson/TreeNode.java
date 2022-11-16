package com.starkodinson;

import com.sun.source.tree.Tree;

import java.util.*;

public class TreeNode<T>
{
    private T data;
    private TreeNode<T> parent;
    private Set<TreeNode<T>> children;
    private TreeNode<T> masterRoot;

    public TreeNode(T data)
    {
        this.data = data;
        this.parent = null;
        this.children = new LinkedHashSet<>();
        masterRoot = this;
    }

    public TreeNode(T data, TreeNode<T> parent)
    {
        this.data = data;
        this.parent = parent;
        parent.addChild(this);
        this.children = new LinkedHashSet<>();
        masterRoot = parent.masterRoot;
    }

    private TreeNode(TreeNode<T> tNode)
    {
        this.data = tNode.data;
        this.parent = tNode.parent;
        this.children = tNode.children;
        this.masterRoot = tNode.masterRoot;
    }

    public T getData()
    {
        return data;
    }

    public void setData(T data)
    {
        this.data = data;
    }

    public TreeNode<T> getParent()
    {
        return parent;
    }

    public void setParent(TreeNode<T> parent)
    {
        this.parent = parent;
    }

    public Set<TreeNode<T>> getChildren()
    {
        return children;
    }

    public void addChild(TreeNode<T> child)
    {
        this.children.add(child);
        child.setParent(this);
    }

    public void removeChild(TreeNode<T> child)
    {
        children.remove(child);
        child.setParent(null);
    }

    public boolean isRootNode()
    {
        return parent == null;
    }

    public boolean isLeafNode()
    {
        return children.size() == 0;
    }

    @Override
    public String toString() { return data.toString(); }


    // STATIC METHODS
    public static <T> Set<TreeNode<T>> levelOrderTraversal(TreeNode<T> root)
    {
        LinkedHashSet<TreeNode<T>> output = new LinkedHashSet<>();
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty())
        {
            TreeNode<T> temp = queue.poll();
            output.add(temp);
            queue.addAll(temp.children);
        }

        return output;
    }

    public static <T> Set<TreeNode<T>> preOrderTraversal(TreeNode<T> root)
    {
        LinkedHashSet<TreeNode<T>> output = new LinkedHashSet<>();
        output.add(root);

        for (TreeNode child : root.children)
        {
            preOrderTraversal(child, output);
        }

        return output;
    }

    private static <T> void preOrderTraversal(TreeNode<T> root, Set<TreeNode<T>> output)
    {
        output.add(root);

        for (TreeNode child : root.children)
        {
            preOrderTraversal(child, output);
        }
    }

    public static <T> Set<TreeNode<T>> postOrderTraversal(TreeNode<T> root)
    {
        LinkedHashSet<TreeNode<T>> output = new LinkedHashSet<>();

        for (TreeNode child : root.children)
        {
            preOrderTraversal(child, output);
        }

        output.add(root);

        return output;
    }

    private static <T> void postOrderTraversal(TreeNode<T> root, Set<TreeNode<T>> output)
    {
        for (TreeNode child : root.children)
        {
            preOrderTraversal(child, output);
        }

        output.add(root);
    }

    public static <T> Set<TreeNode<T>> inOrderTraversal(TreeNode<T> root)
    {
        if (!isBinary(root))
        {
            throw new IllegalArgumentException("In-Order Traversal requires a binary tree as an argument");
        }

        Set<TreeNode<T>> output = new LinkedHashSet<>();

        inOrderTraversal(root, output);

        return output;
    }

    public static <T> void inOrderTraversal(TreeNode<T> root, Set<TreeNode<T>> output)
    {
        if (root.isLeafNode())
        {
            output.add(root);
            return;
        }
        else if (root.children.size() < 2)
        {
            inOrderTraversal(root.children.iterator().next(), output);
            output.add(root);
        }
        else
        {
            Iterator<TreeNode<T>> it = root.children.iterator();
            inOrderTraversal(it.next(), output);
            output.add(root);
            inOrderTraversal(it.next(), output);
        }
    }

    private static <T> boolean isBinary(TreeNode<T> root)
    {
        if (root.children.size() == 0)
        {
            return true;
        }
        else if (root.children.size() == 1)
        {
            return isBinary(new ArrayList<>(root.children).get(0));
        }
        else if (root.children.size() == 2)
        {
            ArrayList<TreeNode<T>> a = new ArrayList(root.children);
            return isBinary(a.get(0)) && isBinary(a.get(1));
        }
        else { return false; }
    }
}
