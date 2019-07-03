package btree;

/**
 *
 * @author sefanadir
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BTree<Integer> btree_1 = new BTree<>(5);
        btree_1.add(10);
        btree_1.add(22);
        btree_1.add(30);
        btree_1.add(40);
        btree_1.add(5);
        btree_1.add(7);
        btree_1.add(8);
        btree_1.add(13);
        btree_1.add(15);
        btree_1.add(18);
        btree_1.add(20);
        btree_1.add(26);
        btree_1.add(27);
        btree_1.add(32);
        btree_1.add(35);
        btree_1.add(38);
        btree_1.add(42);
        btree_1.add(46);

        System.out.println(btree_1.toString());

        System.out.println("35 searching...");
        if (btree_1.contains(35)) {
            System.out.println(btree_1.find(35) + "found");
        } else {
            System.out.println("35 didn't find");
        }

        System.out.println("7 searching...");
        if (btree_1.contains(7)) {
            System.out.println(btree_1.find(7) + "found");
        } else {
            System.out.println("7 didn't find");
        }

        System.out.println("100 searching...");
        if (btree_1.contains(100)) {
            System.out.println(btree_1.find(100) + "found");
        } else {
            System.out.println("100 didn't find");
        }

        System.out.println("46 searching...");
        if (btree_1.contains(46)) {
            System.out.println(btree_1.find(46) + "found");
        } else {
            System.out.println("46 didn't find");
        }

        BTree<Integer> btree_2 = new BTree<>(7);
        btree_2.add(11);
        btree_2.add(24);
        btree_2.add(38);
        btree_2.add(45);
        btree_2.add(56);
        btree_2.add(74);
        btree_2.add(81);
        btree_2.add(19);
        btree_2.add(14);
        btree_2.add(88);
        btree_2.add(29);
        btree_2.add(26);
        btree_2.add(100);
        btree_2.add(250);
        btree_2.add(150);
        btree_2.add(138);

        System.out.println(btree_2.toString());

        System.out.println("38 searching...");
        if (btree_2.contains(38)) {
            System.out.println(btree_1.find(38) + "found");
        } else {
            System.out.println("38 didn't find");
        }

        System.out.println("26 searching...");
        if (btree_2.contains(26)) {
            System.out.println(btree_1.find(26) + "found");
        } else {
            System.out.println("26 didn't find");
        }

        System.out.println("959 searching...");
        if (btree_2.contains(959)) {
            System.out.println(btree_1.find(959) + "found");
        } else {
            System.out.println("959 didn't find");
        }

        System.out.println("872 searching...");
        if (btree_2.contains(872)) {
            System.out.println(btree_1.find(872) + "found");
        } else {
            System.out.println("872 didn't find");
        }
    }
}
