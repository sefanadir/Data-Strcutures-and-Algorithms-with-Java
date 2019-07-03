package q2;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author sefanadir
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("---------Recursive Hashing Set---------");
        RecursiveHashingSet<Integer> setObject = new RecursiveHashingSet<>();
        setObject.add(14);
        setObject.add(24);
        setObject.add(94);
        setObject.add(12);

        setObject.toString();
        System.out.println("---------------------------------------");
        if (setObject.contains(15)) {
            System.out.println("contains 15");
        } else {
            System.out.println("don't contain 15");
        }

        if (setObject.contains(24)) {
            System.out.println("contains 24");
        } else {
            System.out.println("don't contain 24");
        }
        System.out.println("---------------------------------------");
        if (setObject.remove(15)) {
            System.out.println("success remove 15");
        } else {
            System.out.println("unseccess remove 15");
        }
        if (setObject.remove(12)) {
            System.out.println("success remove 12 ");
        } else {
            System.out.println("unsuccess remove 12 ");
        }
        System.out.println("---------------------------------------");
        setObject.toString();

        System.out.println("-----------------addAll----------------");
        ArrayList<Integer> collection1 = new ArrayList<>();
        collection1.add(7);
        collection1.add(34);
        setObject.addAll(collection1);
        setObject.toString();

        System.out.println("-----------------removeAll---------------");
        ArrayList<Integer> collection = new ArrayList<>();
        collection.add(94);
        collection.add(34);
        setObject.removeAll(collection);
        setObject.toString();

        System.out.println("-----------------toArray----------------");
        Object[] container1 = setObject.toArray();

        for (int i = 0; i < container1.length; i++) {
            System.out.println(container1[i]);
        }

        System.out.println("--------------containsAll----------------");
        ArrayList<Integer> collection3 = new ArrayList<>();
        collection3.add(14);
        collection3.add(24);

        if (setObject.containsAll(collection3)) {
            System.out.println("collection contains all elements");
        } else {
            System.out.println("collection don't contain all elements");
        }

        System.out.println("--------------retainAll----------------");
        ArrayList<Integer> collection4 = new ArrayList<>();
        collection4.add(14);
        collection4.add(24);

        setObject.retainAll(collection4);
        setObject.toString();

        System.out.println("------------toArray(T[] a)-------------");
        Object[] container2 = null;
        container2 = setObject.toArray(container2);
        for (int i = 0; i < container2.length; i++) {
            System.out.println(container2[i]);
        }
        System.out.println("---------------iterator----------------");
        Iterator<Integer> iter = setObject.iterator();

        while (iter.hasNext()) {
            Object a = iter.next();
            System.out.println(a);
        }

    }

}
