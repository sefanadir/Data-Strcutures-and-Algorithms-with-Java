package datastructures_4;

/**
 *
 * @author sefanadir
 * @param <Object> generic type
 */
public class LeftChildRightSibling<Object> extends BinaryTree<Object> {

    public final Node<Object> treeRoot;

    /**
     * One parameter constructor
     *
     * @param data add data to tree root for first node
     */
    public LeftChildRightSibling(Object data) {
        treeRoot = new Node<>(data);
    }

    /**
     * This method adds new Node to tree
     *
     * @param addNode new data
     * @param addData adding parent data
     * @return if adding is success, return true but if adding is not success,
     * return false
     */
    public boolean add(Object addNode, Object addData) {

        Node addToNode = levelOrderSearch(treeRoot, addNode);
        if (addToNode == null) {
            return false;
        }
        if (addToNode.left != null) {
            Node temp = addToNode.left;
            while (temp.right != null) {
                temp = temp.right;
            }
            temp.right = new Node(addData);
            return true;

        } else {
            addToNode.left = new Node(addData);
            return true;
        }
    }

    /**
     * method to traverse to obtain the string representation of the tree.
     *
     * @param node root node
     * @param depth depth of tree
     * @param sb string builder buffer
     */
    @Override
    protected void preOrderTraverse(Node<Object> node, int depth, StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
            sb.append("  ");
        }
        if (node != null) {
            sb.append(node.toString());
            sb.append("\n");
            preOrderTraverse(node.left, depth - 1, sb);
            preOrderTraverse(node.right, depth, sb);
        }
    }

    /**
     * Traverse a node before traversing its subtrees starting from the root
     * node Search for the item during traversal Return the Node reference if
     * the item is in the tree and null if it is not in the tree.
     *
     * @param root tree root node
     * @param searchData searching data in the tree
     * @return target Node Object
     */
    public Node postOrderSearch(Node root, Object searchData) {
        while (root != null) {
            if (root.left != null) {
                postOrderSearch(root.left, searchData);
            }
            //  System.out.println(root.data);
            root = root.right;
        }
        return root;
    }

    /**
     * Traverses the tree in level order; First the root node (node in the first
     * level), then the children of the root node (nodes in the second level),
     * then the children of the nodes in the second level ( nodes in the third
     * level), and so on.
     *
     * @param root tree root node
     * @param searchData searching data in the tree
     * @return target Node Object
     */
    public Node levelOrderSearch(Node root, Object searchData) {
        while (root != null) {
            // System.out.println(root.data);
            if (root.data == searchData) {
                return root;
            }
            if (root.right != null) {
                levelOrderSearch(root.right, searchData);
            }
            root = root.left;
        }
        return null;
    }
}
