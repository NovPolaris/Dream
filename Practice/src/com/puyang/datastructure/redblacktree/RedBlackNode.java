package com.puyang.datastructure.redblacktree;

import java.util.*;

public class RedBlackNode implements Iterator<RedBlackNode> {
    RedBlackColor nodeColor;
    int val;
    RedBlackNode father;
    RedBlackNode left;
    RedBlackNode right;
    RedBlackNode precursor;
    RedBlackNode successor;

    public RedBlackNode(int val) {
        this.val = val;
        this.nodeColor = RedBlackColor.RED;
        this.father = null;
        this.left = null;
        this.right = null;
        this.precursor = null;
        this.successor = null;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public RedBlackNode next() {
        return null;
    }

    private enum RedBlackColor {
        RED,
        BLACK;
    }
}
