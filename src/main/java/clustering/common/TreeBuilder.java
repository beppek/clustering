package clustering.common;

import clustering.clusters.Cluster;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;
import java.util.List;

public class TreeBuilder {
    private List<String> html;
    private Cluster root;

    public TreeBuilder(Cluster root) {
        this.root = root;
    }

    public String buildHTMLTree() {
        html = new ArrayList<>();
        html.add("<ul>");
        html.add("</ul>");

        addHTMLNodes(1, root);
        StringBuilder sb = new StringBuilder();
        for (String s : html) {
            sb.append(s).append("\n");
        }
        return sb.toString();
    }

    private void addHTMLNodes(int i, Cluster c) {
        if (c.hasRight()) {
            String art = c.getRight().toString();
            if (art.equals("")) {
                html.add(i, "<li><ul>");
                html.add(i + 1, "</ul></li>");
            } else {
                art = art.replaceAll("\"", "'");
                html.add(i, "<li data-jstree='{\"disabled\":true}'>" + art + "</li>");
            }
            addHTMLNodes(i + 1, c.getRight());
        }

        if (c.hasLeft()) {
            String art = c.getLeft().toString();
            if (art.equals("")) {
                html.add(i, "<li><ul>");
                html.add(i + 1, "</ul></li>");
            } else {
                art = art.replaceAll("\"", "'");
                html.add(i, "<li data-jstree='{\"disabled\":true}'>" + art + "</li>");
            }
            addHTMLNodes(i + 1, c.getLeft());
        }
    }

    public JTree buildJTree() {
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
