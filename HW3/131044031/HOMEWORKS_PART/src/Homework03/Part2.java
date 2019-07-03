package Homework03;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author sefanadir
 * @param <T> generic tipte olması
 */
public class Part2<T> extends LinkedList<T> {

    private final ArrayList<T> disableObjects;
    private final ArrayList<Integer> disableIndexs;

    public Part2() {
        disableObjects = new ArrayList<>();
        disableIndexs = new ArrayList<>();
    }

    /**
     *
     * @param object disable edilen eleman
     * @return disable işlemi başarılı ise 1 return eder.
     * @throws Exception listede böyle bir eleman yoksa hata fırlatır.
     */
    public int disable(T object) throws Exception {
        if (contains(object)) {
            disableObjects.add(object);
            disableIndexs.add(indexOf(object));
            remove(object);
            return 1;
        } else {
            throw new Exception("Object is not available in this list");
        }
    }

    /**
     * Disable edilen elemanları geri enable eder.
     *
     * @param object enable edilecek olan elemandır
     * @return enable işlemi başarılı ise 1 return edilir.
     * @throws Exception eleman daha önce disable edilmemişse hata fırlatır.
     */
    public int enable(T object) throws Exception {
        if (disableObjects.contains(object)) {
            int index = disableObjects.indexOf(object);
            add(disableIndexs.get(index), object);

            disableObjects.remove(object);
            disableIndexs.remove(index);
            return 1;
        } else {
            throw new Exception("Object is already enable");
        }
    }

    /**
     * Disable edilen elemanları gösterir
     */
    public void showDisabled() {
        if (disableObjects.size() > 0) {
            for (int i = 0; i < disableObjects.size(); ++i) {
                System.out.println(disableObjects.get(i) + " ");
            }
        } else {
            System.out.println("Not have disable objects");
        }
    }
}