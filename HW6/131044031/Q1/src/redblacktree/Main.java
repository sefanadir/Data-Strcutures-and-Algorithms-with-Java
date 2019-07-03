package redblacktree;

/**
 *
 * @author sefanadir
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RedBlackTree<Integer> rbt_1 = new RedBlackTree<>();
        rbt_1.add(60);
        rbt_1.add(100);
        rbt_1.add(80);
        rbt_1.add(160);
        rbt_1.add(130);
        rbt_1.add(90);
        rbt_1.add(95);
        rbt_1.add(115);
        rbt_1.add(125);
        rbt_1.add(120);
        rbt_1.add(124);
        rbt_1.add(122);
        rbt_1.add(121);
        rbt_1.add(123);
        System.out.println(rbt_1.toString());

        RedBlackTree<Integer> rbt_2 = new RedBlackTree<>();
        rbt_2.add(1000);
        rbt_2.add(100);
        rbt_2.add(550);
        rbt_2.add(2000);
        rbt_2.add(1500);
        rbt_2.add(4000);
        rbt_2.add(3000);
        rbt_2.add(6000);
        rbt_2.add(5000);
        rbt_2.add(8000);
        rbt_2.add(7600);
        rbt_2.add(9000);
        rbt_2.add(8500);
        rbt_2.add(9500);
        System.out.println(rbt_2.toString());
    }

}
