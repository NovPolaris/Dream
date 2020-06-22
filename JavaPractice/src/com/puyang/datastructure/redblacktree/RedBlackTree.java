package com.puyang.datastructure.redblacktree;

import java.util.*;

public class RedBlackTree {
    private final RedBlackNode root;

    private final int size;

    private final Iterator<RedBlackNode> iterator;

    private final Iterator<RedBlackNode> begin;

    private final Iterator<RedBlackNode> end;

    public RedBlackTree() {
        this.root = null;
        this.size = 0;
        this.iterator = null;
        this.begin = null;
        this.end = null;
    }
/*
    public Iterator<RedBlackNode> insert() {}

    public Iterator<RedBlackNode> search() {}

    public Iterator<RedBlackNode> lower_bound() {}

    public Iterator<RedBlackNode> upper_bound() {}

    public boolean remove(RedBlackNode) {}

    public boolean remove(Iterator<RedBlackNode> it) {}

    public void clear() {}

    public boolean isEmpty() {
        return this.size == 0;
    }

    public RedBlackNode getRoot() {
        return this.root;
    }

    public int getSize() {
        return this.size;
    }

    public Iterator<RedBlackNode> getIterator() {
        return this.iterator;
    }

    private void solveDoubleRed(RedBlackNode node) {}

    private void solveLostBlack(RedBlackNode node) {}

    private RedBlackNode find() {}

    private void removeTree(RedBlackNode node) {}
*/
    private void reconstruct(RedBlackNode node1, RedBlackNode node2, RedBlackNode node3,
                             RedBlackNode t0, RedBlackNode t1, RedBlackNode t2, RedBlackNode t3) {
        node1.left = t0;
        node1.right = t1;
        node2.left = node1;
        node2.right = node3;
        node3.left = t2;
        node3.right = t3;
        node1.father = node2;
        node3.father = node2;

        if (t0 != null) {
            t0.father = node1;
        }
        if (t1 != null) {
            t1.father = node1;
        }
        if (t2 != null) {
            t2.father = node3;
        }
        if (t3 != null) {
            t3.father = node3;
        }
    }
}
