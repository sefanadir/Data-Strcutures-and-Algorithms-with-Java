package q2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Implement Set interface by using a chaining hash table.The reference of the
 * value is stored in the table if there is no collision.Collisions are resolved
 * by inserting the values (mapped to the same table entry) into another hash
 * table of same type.So, the structure is recursive.For each collision, hash
 * table size must be change.
 *
 * @author sefanadir
 * @param <T> generic type
 */
public class RecursiveHashingSet<T> implements Set<T> {

    private int numberOfKeys;
    private Node<T>[] recursiveHashTable;
    private static final int CAPACITY = 10;
    private static final int PRIME_SIZE = 7;
    private StringBuilder table;

    /**
     * This method is no parameter constructor and initializes
     * recursiveHashTable,numberOfKeys and table
     */
    public RecursiveHashingSet() {
        numberOfKeys = 0;
        table = new StringBuilder();
        recursiveHashTable = new Node[CAPACITY];
    }

    /**
     * This class is entry node class
     *
     * @param <T> generic type
     */
    private static class Node<T> {

        T data;
        Node<T>[] next;

        /**
         * This method is no parameter constructor
         */
        public Node() {
            data = null;
            next = null;
        }

        /**
         * This method is one parameter constructor
         *
         * @param data new data
         */
        public Node(T data) {
            this.data = data;
            this.next = null;
        }

        /**
         * This method returns data
         *
         * @return Node data
         */
        public T getData() {
            return data;
        }

        /**
         * This method changes data
         *
         * @param data new Node data
         */
        public void setData(T data) {
            this.data = data;
        }

    }

    class setIterator implements Iterator<T> {

        private int current;
        private T[] elements;

        public setIterator() {
            current = 0;
            elements = (T[]) toArray();
        }

        @Override
        public boolean hasNext() {
            elements = (T[]) toArray();
            if (current < elements.length) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public T next() {
            if (hasNext() == false) {
                throw new NoSuchElementException();
            }
            return elements[current++];
        }
    }

    /**
     * This method returns the number of elements in this set
     *
     * @return the number of elements in this set
     */
    @Override
    public int size() {
        return numberOfKeys;
    }

    /**
     * This method returns true if this set contains no elements.
     *
     * @return true if this set contains no elements
     */
    @Override
    public boolean isEmpty() {
        return numberOfKeys == 0;
    }
// 

    /**
     * This method returns true if this set contains the specified element.
     *
     * @param o element whose presence in this set is to be tested
     * @return true if this set contains the specified element.
     */
    @Override
    public boolean contains(Object o) {
        return contains(recursiveHashTable, o);
    }

    /**
     * This method wrapper method for contains(Object o) method.
     *
     * @param recursiveHashTable head of the set
     * @param o element whose presence in this set is to be tested
     *
     * @return true if this set contains the specified element.
     */
    private boolean contains(Node<T>[] recursiveHashTable, Object o) {
        if (recursiveHashTable == null) {
            return false;
        }

        int index = o.hashCode() % recursiveHashTable.length;
        if (index < 0) {
            index += recursiveHashTable.length;
        }
        if (recursiveHashTable[index] != null && recursiveHashTable[index].getData() != null) {
            if (recursiveHashTable[index].getData().equals(o)) {
                return true;
            } else {
                if (recursiveHashTable[index].next != null) {
                    return contains(recursiveHashTable[index].next, o);
                }
            }
        }
        return false;
    }

    /**
     * This method returns an iterator over the elements in this set.
     *
     * @return an iterator over the elements in this set
     */
    @Override
    public Iterator<T> iterator() {
        return new setIterator();
    }

    /**
     * This method returns an array containing all of the elements in this set.
     *
     * @return an array containing all the elements in this set
     */
    @Override
    public Object[] toArray() {
        Object[] container = null;
        if (numberOfKeys != 0 && recursiveHashTable != null) {
            container = toArray(recursiveHashTable, container, 0);
        }
        return container;
    }

    /**
     * This method wrapper recursive method for toArray() and toArray(T[] a)
     * method.
     *
     * @param recursiveHashTable head of the set
     * @param container an array
     * @param index array index
     * @return an array containing all the elements in this set
     */
    private Object[] toArray(Node<T>[] recursiveHashTable, Object[] container, int index) {

        if (container == null) {
            container = new Object[numberOfKeys];

        }
        for (int i = 0; i < recursiveHashTable.length; ++i) {
            if (recursiveHashTable[i] != null && recursiveHashTable[i].getData() != null) {
                container[index] = recursiveHashTable[i].getData();
                if (recursiveHashTable[i].next != null) {
                    container = toArray(recursiveHashTable[i].next, container, ++index);
                }
                ++index;
            }
        }
        return container;
    }

    /**
     * This method Returns an array containing all of the elements in this set;
     * the runtime type of the returned array is that of the specified array.
     *
     * @param <T> generic type
     * @param a the array into which the elements in this set
     * @return an array containing all the elements in this set
     */
    @Override
    public <T> T[] toArray(T[] a) {
        a = (T[]) toArray(recursiveHashTable, a, 0);
        return a;
    }

    /**
     * This method adds the specified element to this set if it is not already
     * present.More formally, adds the specified element e to this set if the
     * set contains no element e2 such that.If this set already contains the
     * element, the call leaves the set unchanged and returns false.
     *
     * @param e element to be added to this set
     * @return true if this set did not already contain the specified element
     */
    @Override
    public boolean add(T e) {
        if (contains(e)) {
            return false;
        }
        recursiveHashTable = add(recursiveHashTable, e);
        return true;
    }

    /**
     * This method is wrapper recursive method for add(T e) method
     *
     * @param recursiveHashTable head of the set
     * @param e element to be added to this set
     * @return current head of the set
     */
    private Node<T>[] add(Node<T>[] recursiveHashTable, T e) {

        if (recursiveHashTable == null) {
            recursiveHashTable = new Node[PRIME_SIZE];
        }

        int index = e.hashCode() % recursiveHashTable.length;
        if (index < 0) {
            index += recursiveHashTable.length;
        }
        if (recursiveHashTable[index] == null) {
            recursiveHashTable[index] = new Node(e);
            ++numberOfKeys;
        } else {
            if (recursiveHashTable[index].getData() == null) {
                recursiveHashTable[index].setData(e);
            } else {
                recursiveHashTable[index].next = add(recursiveHashTable[index].next, e);
            }
        }
        return recursiveHashTable;
    }

    /**
     * This method removes the specified element from this set if it is present
     *
     * @param o object to be removed from this set, if present
     * @return true if the set contained the specified element
     */
    @Override
    public boolean remove(Object o) {
        int currentSize = numberOfKeys;
        recursiveHashTable = remove(recursiveHashTable, o);
        int newSize = numberOfKeys;
        if (currentSize > newSize) {
            return true;
        }
        return false;
    }

    /**
     * This method is wrapper recursive method for remove(Object o)
     *
     * @param recursiveHashTable head of the set
     * @param o object to be removed from this set, if present
     * @return true if the set contained the specified element
     */
    private Node<T>[] remove(Node<T>[] recursiveHashTable, Object o) {

        if (contains(o)) {
            if (recursiveHashTable == null) {
                return recursiveHashTable;
            }
            int index = o.hashCode() % recursiveHashTable.length;
            if (index < 0) {
                index += recursiveHashTable.length;
            }
            if (recursiveHashTable[index] != null) {
                if (recursiveHashTable[index].data.equals(o)) {
                    recursiveHashTable[index].setData(null);
                    --numberOfKeys;
                } else {
                    if (recursiveHashTable[index].next != null) {
                        recursiveHashTable[index].next = remove(recursiveHashTable[index].next, o);
                    }
                }
            }
            return recursiveHashTable;
        }
        return recursiveHashTable;
    }

    /**
     * This method returns true if this set contains all of the elements of the
     * specified collection.If the specified collection is also a set, this
     * method returns true if it is a subset of this set.
     *
     * @param c collection containing elements
     * @return true if this set contains all of the elements of the specified
     * collection.
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        int sameElement = 0;
        if (c == null || c.isEmpty()) {
            return false;
        }

        for (Object value : c) {
            if (value != null) {
                if (contains(value)) {
                    ++sameElement;
                }
            }
        }
        if (sameElement == numberOfKeys) {
            return true;
        }
        return false;
    }

    /**
     * This method adds all of the elements in the specified collection to this
     * set if they're not already present
     *
     * @param c collection containing elements
     * @return true if this set changed as a result of the call
     */
    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c == null || c.isEmpty()) {
            return false;
        }
        for (T value : c) {
            if (value != null) {
                add(value);
            }
        }
        return true;
    }

    /**
     * This method retains only the elements in this set that are contained in
     * the specified collection.In other words, removes from this set all of its
     * elements that are not contained in the specified collection
     *
     * @param c collection containing elements
     * @return true if this set changed as a result of the call
     */
    @Override
    public boolean retainAll(Collection<?> c) {

        Object[] elements = toArray();
        ArrayList<Object> intersection = new ArrayList<>();
        if (c == null || c.isEmpty()) {
            return false;
        }

        for (Object value_1 : elements) {
            int same = 0;
            for (Object value_2 : c) {
                if (value_1.equals(value_2)) {
                    ++same;
                }
            }
            if (same == 0) {
                intersection.add(value_1);
            }
        }
        removeAll(intersection);
        return true;
    }

    /**
     * This method removes from this set all of its elements that are contained
     * in the specified collection.If the specified collection is also a set,
     * this operation effectively modifies this set so that its value is the
     * asymmetric set difference of the two sets.
     *
     * @param c collection containing elements
     * @return true if this set changed as a result of the call
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null || c.isEmpty()) {
            return false;
        }
        for (Object value : c) {
            if (value != null) {
                remove((T) value);
            }
        }
        return true;
    }

    /**
     * This method clears all of the elements from this set
     */
    @Override
    public void clear() {
        numberOfKeys = 0;
        recursiveHashTable = null;
    }

    /**
     * This method print the set to screen
     *
     * @return empty string
     */
    @Override
    public String toString() {
        toString(recursiveHashTable);
        return "";
    }

    /**
     * This method is wrapper recursive method for toString().
     *
     * @param recursiveHashTable head of the set
     */
    private void toString(Node<T>[] recursiveHashTable) {
        for (int i = 0; i < recursiveHashTable.length; ++i) {
            if (recursiveHashTable[i] != null && recursiveHashTable[i].getData() != null) {
                System.out.println(recursiveHashTable[i].getData());
                if (recursiveHashTable[i].next != null) {
                    toString(recursiveHashTable[i].next);
                }
            }
        }
    }
}
