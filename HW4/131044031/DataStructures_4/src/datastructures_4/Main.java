package datastructures_4;
/**
 *
 * @author sefanadir
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LeftChildRightSibling<Integer> tree = new LeftChildRightSibling<>(1);

        tree.add(1, 2);
        tree.add(1, 4);
        tree.add(1, 7);
        tree.add(2, 3);
        tree.add(2, 6);
        tree.add(3, 5);
        tree.add(3, 8);

        StringBuilder printTree = new StringBuilder();
        tree.preOrderTraverse(tree.treeRoot, 5, printTree);
        System.out.println(printTree);

    }
}