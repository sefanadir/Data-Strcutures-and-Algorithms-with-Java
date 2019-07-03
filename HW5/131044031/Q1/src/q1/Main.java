package q1;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author sefanadir
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        DoubleHashingMap<String, Integer> mapObject = new DoubleHashingMap<>();

        mapObject.put("toyota", 123);
        mapObject.put("mercedes", 248);
        mapObject.put("fiat", 347);

        Set<Map.Entry<String, Integer>> tableEntrySet = new HashSet<>();
        tableEntrySet = mapObject.entrySet();

        Iterator iterator = tableEntrySet.iterator();
        System.out.println("size:" + tableEntrySet.size());

        System.out.println(tableEntrySet.iterator().next().getKey() + "->" + tableEntrySet.iterator().next().getValue());
        System.out.println(tableEntrySet.iterator().next().getKey() + "->" + tableEntrySet.iterator().next().getValue());
        System.out.println(tableEntrySet.iterator().next().getKey() + "->" + tableEntrySet.iterator().next().getValue());

        System.out.println(mapObject.toString());
    }

}
