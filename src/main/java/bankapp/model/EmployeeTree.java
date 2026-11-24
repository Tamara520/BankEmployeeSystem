/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankapp.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * EmployeeTree - binary tree of employees built with LEVEL-ORDER insertion.
 * Each new node goes in the first free place, left before right, so the
 * tree is always complete. A queue is used for insertion and traversal.
 */
public class EmployeeTree {

    /** One node of the tree: an employee and up to two children. */
    private static class Node {
        final Employee employee;
        Node left;
        Node right;

        Node(Employee employee) { this.employee = employee; }
    }

    private Node root;

    /**
     * Inserts an employee in level order (breadth-first).
     * Time: O(n), because the queue may visit every node.
     *
     * @param employee the employee to insert
     */
    public void insert(Employee employee) {
        Node newNode = new Node(employee);
        if (root == null) {
            root = newNode;
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.left == null) {        // fill left first
                current.left = newNode;
                return;
            }
            queue.add(current.left);
            if (current.right == null) {       // then right
                current.right = newNode;
                return;
            }
            queue.add(current.right);          // both taken: move to next node
        }
    }

    /**
     * Level-order traversal: one list of employees per level of the tree.
     *
     * @return the levels, from the root (level 0) downwards
     */
    public List<List<Employee>> levelOrder() {
        List<List<Employee>> levels = new ArrayList<>();
        if (root == null) {
            return levels;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();               // nodes on this level
            List<Employee> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node current = queue.poll();
                level.add(current.employee);
                if (current.left != null)  { queue.add(current.left); }
                if (current.right != null) { queue.add(current.right); }
            }
            levels.add(level);
        }
        return levels;
    }

    /** Height = number of levels (empty tree = 0). Recursive, O(n). */
    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    /** Total number of nodes. Recursive, O(n). */
    public int countNodes() {
        return countNodes(root);
    }

    private int countNodes(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    public boolean isEmpty() {
        return root == null;
    }
}

