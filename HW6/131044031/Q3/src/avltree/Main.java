package avltree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author sefanadir
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            
            BufferedReader bufRead = null;
            FileReader fileRead = null;
            fileRead = new FileReader("tree.txt");
            bufRead = new BufferedReader(fileRead);

            BinaryTree<String> readBinaryTree = BinaryTree.readBinaryTree(bufRead);
            System.out.println(readBinaryTree.toString());
            AVLTree<String> avlTree = new AVLTree<>(readBinaryTree);
            System.out.println(avlTree.toString());
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
