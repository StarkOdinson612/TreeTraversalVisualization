package com.starkodinson;

import com.sun.source.tree.Tree;

public class Main {

    public static void main(String[] args) {
	// write your code here
        TreeNode<String> cdr = new TreeNode<String>("C Drive");
        TreeNode<String> prgms = new TreeNode<>("Programs", cdr);
        TreeNode<String> wndws = new TreeNode<>("Windows", cdr);
        TreeNode<String> bj = new TreeNode<>("BlueJ", prgms);
        TreeNode<String> grnft = new TreeNode<>("Greenfoot", prgms);
        TreeNode<String> fnts = new TreeNode<>("Fonts", wndws);
        TreeNode<String> sys32 = new TreeNode<>("System32", wndws);

        System.out.println("Pre Order: " + TreeNode.preOrderTraversal(cdr));
        System.out.println("In Order: " + TreeNode.inOrderTraversal(cdr));
        System.out.println("Post Order: " + TreeNode.postOrderTraversal(cdr) + "\n\n");

        TreeNode<String> usrs = new TreeNode<>("Users", cdr);
        TreeNode<String> hmn = new TreeNode<>("Human", usrs);
        TreeNode<String> mrtn = new TreeNode<>("Martian Manhunter", usrs);
        TreeNode<String> guest = new TreeNode<>("Guests", usrs);

        try
        {
            System.out.println("In Order Non-Binary: " + TreeNode.inOrderTraversal(cdr));
        }
        catch (IllegalArgumentException iae)
        {
            System.out.println("Error: " + iae);
        }
    }
}
