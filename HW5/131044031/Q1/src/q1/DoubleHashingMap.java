package q1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Implement Map interface by using an open addressing hash table.Collisions
 * are resolved by double hashing.
 *
 * @author sefanadir
 * @param <K> generic type
 * @param <V> generic type
 */
public class DoubleHashingMap< K, V> implements Map<K, V> {

    private keyValuePair< K, V>[] doubleHashTable;
    private static final int TABLE_CAPACITY = 50;
    private final double OCCUPANCY_RATE_TABLE = 0.75;
    private int hashFunction1 = TABLE_CAPACITY;
    private int hashFunction2 = 7;
    private int deletingElements = 0;
    private int standingElements = 0;

    /**
     * This method is no parameter constructor and initializes doubleHashTable
     * array
     */
    public DoubleHashingMap() {
        doubleHashTable = new keyValuePair[TABLE_CAPACITY];
    }

    /**
     * This nested class contains key-value pairs.
     */
    private static class keyValuePair< K, V> implements Map.Entry<K, V> {

        private K key;
        private V value;
        private int deletedState;

        /**
         * This constructor is no parameter constructor
         */
        public keyValuePair() {
            this.key = null;
            this.value = null;
            this.deletedState = 0;
        }

        /**
         * This constructor creates a new key value pair.
         *
         * @param key key of pair
         * @param value value of pair
         */
        public keyValuePair(K key, V value) {
            this.key = key;
            this.value = value;
            this.deletedState = 0;
        }

        /**
         * This method returns the key.
         *
         * @return key
         */
        @Override
        public K getKey() {
            return key;
        }

        /**
         * This method returns the value.
         *
         * @return value
         */
        @Override
        public V getValue() {
            return value;
        }

        /**
         * This method sets the value.
         *
         * @param value The new value
         * @return old value
         */
        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        /**
         * This method returns the deleted state.
         *
         * @return deleted state
         */
        public int getDeletedState() {
            return deletedState;
        }

        /**
         * This method sets the deleted state.
         *
         * @param deletedState The new deleted state
         */
        public void setDeletedState(int deletedState) {
            this.deletedState = deletedState;
        }
    }

    /**
     * This method returns the number of entries in the map
     */
    @Override
    public int size() {
        return standingElements;
    }

    /**
     * This method returns true if map is empty
     */
    @Override
    public boolean isEmpty() {
        return standingElements == 0;
    }

    /**
     * This method returns true if this map contains a mapping for the specified
     * key.
     *
     * @param key specified map key
     * @return true if this map contains the specified key
     */
    @Override
    public boolean containsKey(Object key) {
        for (int i = 0; i < doubleHashTable.length; ++i) {
            if (doubleHashTable[i] != null) {
                if (doubleHashTable[i].getDeletedState() != 1 && doubleHashTable[i].getKey().equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method returns true if this map maps one or more keys to the
     * specified value
     *
     * @param value specified map value
     * @return true if this map maps the specified value
     */
    @Override
    public boolean containsValue(Object value) {
        for (int i = 0; i < doubleHashTable.length; ++i) {
            if (doubleHashTable[i] != null) {
                if (doubleHashTable[i].getDeletedState() != 1 && doubleHashTable[i].getValue().equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method returns the value to which the specified key is mapped, or
     * null if this map contains no mapping for the key.
     *
     * @param key specified map key
     * @return returns the value to which the specified key is mapped
     */
    @Override
    public V get(Object key) {
        int index = findIndex(key);
        if (doubleHashTable[index] != null) {
            return doubleHashTable[index].value;
        } else {
            return null;
        }
    }

    /**
     * Associates the specified value with the specified key in this map If the
     * map previously contained a mapping for the key, the old value is replaced
     * by the specified value.
     *
     * @param key specified map key
     * @param value specified map value
     * @return return old value or null
     */
    @Override
    public V put(K key, V value) {
        int index = findIndex(key);

        if (doubleHashTable[index] == null) {
            doubleHashTable[index] = new keyValuePair<>(key, value);
            ++standingElements;

            double loadFactor = (double) (standingElements + deletingElements) / doubleHashTable.length;
            if (loadFactor > OCCUPANCY_RATE_TABLE) {
                reallocDoubleHashing();
            }
            return null;
        } else {
            V oldValue = doubleHashTable[index].value;
            doubleHashTable[index].value = value;
            return oldValue;
        }
    }

    /**
     * if this map contains a mapping from key k to value v such that, that
     * mapping is removed.
     *
     * @param key specified map key
     * @return removed value
     */
    @Override
    public V remove(Object key) {
        int index = findIndex(key);
        if (doubleHashTable[index] == null) {
            return null;
        }
        V oldValue = doubleHashTable[index].value;
        doubleHashTable[index].setDeletedState(1);
        --standingElements;
        return oldValue;
    }

    /**
     * This method copies all of the mappings from the specified map to this
     * mapThe effect of this call is equivalent to that of calling put(k, v) on
     * this map once for each mapping from key k to value v in the specified
     * map.The behavior of this operation is undefined if the specified map is
     * modified while the operation is in progress.
     *
     * @param m mapping to be stored in this map
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

        for (K key : m.keySet()) {
            V value = m.get(key);
            if (key != null && value != null) {
                put(key, value);
            }
        }
    }

    /**
     * This method clears all of the mappings from this map.The map will be
     * empty after this call returns.
     *
     */
    @Override
    public void clear() {
        doubleHashTable = null;
        standingElements = 0;
        deletingElements = 0;
    }

    /**
     * This method returns a set view of the keys contained in the map.
     *
     * @return a set of the keys in the map
     */
    @Override
    public Set<K> keySet() {
        Set<K> tableKeys = new HashSet<>();
        for (int i = 0; i < doubleHashTable.length; ++i) {
            if (doubleHashTable[i] != null && doubleHashTable[i].getDeletedState() != 1) {
                tableKeys.add(doubleHashTable[i].getKey());
            }
        }
        return tableKeys;
    }

    /**
     * This method returns a collection view of the values contained in this map
     *
     * @return a collection view of the values contained in this map
     */
    @Override
    public Collection<V> values() {
        ArrayList<V> tableValues = new ArrayList<>();
        for (int i = 0; i < doubleHashTable.length; ++i) {
            if (doubleHashTable[i] != null && doubleHashTable[i].getDeletedState() != 1) {
                tableValues.add(doubleHashTable[i].getValue());
            }
        }
        return tableValues;
    }

    /**
     * This method returns a set view of the mappings contained in this map
     *
     * @return a set view of the mappings contained in this map
     */
    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> tableEntrySet = new HashSet<>();

        for (int i = 0; i < doubleHashTable.length; ++i) {
            keyValuePair<K, V> entryObject = new keyValuePair<>();
            if (doubleHashTable[i] != null && doubleHashTable[i].getDeletedState() != 1) {
                entryObject.key = doubleHashTable[i].getKey();
                entryObject.value = doubleHashTable[i].getValue();
                tableEntrySet.add(entryObject);
            }
        }

        return tableEntrySet;
    }

    /**
     * This method finds either the target key or the first empty slot in the
     * search chain using double hashing.
     *
     * @param key The key of the target object
     * @return The position of the target or the first empty slot if the target
     * is not in the table.
     */
    private int findIndex(Object key) {

        int tableIndex1 = key.hashCode() % hashFunction1;

        if (tableIndex1 < 0) {
            tableIndex1 += hashFunction1;
        }

        if ((doubleHashTable[tableIndex1] != null) && (!key.equals(doubleHashTable[tableIndex1].key))) {
            int tableIndex2 = hashFunction2 - (key.hashCode() % hashFunction2);
            if (tableIndex2 < 0) {
                tableIndex2 += hashFunction2;
            }

            int multiplication = 1;

            do {
                tableIndex2 = calculateSecondHash(tableIndex1, tableIndex2, multiplication);
                if (tableIndex2 >= doubleHashTable.length) {
                    tableIndex2 = 0;
                }
                ++multiplication;
            } while ((doubleHashTable[tableIndex2] != null) && (!key.equals(doubleHashTable[tableIndex2].key)));

            tableIndex1 = tableIndex2;
        }
        return tableIndex1;
    }

    private int calculateSecondHash(int hash1, int hash2, int multiplication) {
        return hash1 + multiplication * hash2 % hashFunction1;
    }

    /**
     * This method expands table size when loadFactor exceeds 0.75
     */
    private void reallocDoubleHashing() {
        keyValuePair< K, V>[] oldTable = doubleHashTable;
        doubleHashTable = new keyValuePair[2 * oldTable.length + 1];

        standingElements = 0;
        deletingElements = 0;
        for (keyValuePair<K, V> searchTable : oldTable) {

            if ((searchTable != null) && (searchTable.getDeletedState() != 1)) {
                put(searchTable.key, searchTable.value);
            }
        }
        hashFunction1 = doubleHashTable.length;
    }

    /**
     * This method fills key and value of the map to StringBuilder and prints to
     * screen
     *
     * @return a StringBuilder that contains key and value of the map
     */
    @Override
    public String toString() {
        StringBuilder table = new StringBuilder();

        for (int i = 0; i < doubleHashTable.length; ++i) {
            if (doubleHashTable[i] != null && doubleHashTable[i].getDeletedState() != 1) {
                table.append(doubleHashTable[i].getKey());
                table.append("->");
                table.append(doubleHashTable[i].getValue());
                table.append("\n");
            }
        }
        return table.toString();
    }
}
