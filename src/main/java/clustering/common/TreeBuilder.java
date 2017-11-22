package clustering.common;

import clustering.clusters.Cluster;
import clustering.clusters.Hierarchy;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class TreeBuilder {
    public JTree buildJTree(Hierarchy h) {
        Cluster root = h.getClusters().get(0);
        DefaultMutableTreeNode top = new DefaultMutableTreeNode(root.toString());
        jTreeAddNodes(top, root);
        JTree tree = new JTree(top);
        for (int i = 0; i < tree.getRowCount(); i++) {
            tree.expandRow(i);
        }
        return tree;
    }

    private void jTreeAddNodes(DefaultMutableTreeNode tnode, Cluster c) {
        if (c.hasLeft()) {
            DefaultMutableTreeNode nNode = new DefaultMutableTreeNode(c.getLeft().toString());
            tnode.add(nNode);
            jTreeAddNodes(nNode, c.getLeft());
        }

        if (c.hasRight()) {
            DefaultMutableTreeNode nNode = new DefaultMutableTreeNode(c.getRight().toString());
            tnode.add(nNode);
            jTreeAddNodes(nNode, c.getRight());
        }
    }
}
