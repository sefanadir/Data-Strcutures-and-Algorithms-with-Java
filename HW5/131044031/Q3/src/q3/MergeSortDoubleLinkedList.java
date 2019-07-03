package q3;

/**
 * Implement merge sort with double linked list.
 *
 * @author sefanadir
 * @param <T> generic type
 */
public class MergeSortDoubleLinkedList<T extends Comparable<T>> {

    private Node<T> head;

    /**
     * This class is entry node class
     *
     * @param <T> generic type
     */
    private static class Node<T> {

        T data;
        Node<T> next;
        Node<T> previous;

        /**
         * This method is no parameter constructor
         */
        public Node() {
            data = null;
            next = null;
            previous = null;
        }

        /**
         * This method is one parameter constructor and initializes data
         *
         * @param value new value
         */
        public Node(T value) {
            data = value;
            next = null;
            previous = null;
        }

        /**
         * This method is three parameter constructor and initializes data, next
         * and previous
         *
         * @param data new data
         * @param next new next node
         * @param prev new previous node
         */
        public Node(T data, Node<T> next, Node<T> previous) {
            this.data = data;
            this.next = next;
            this.previous = previous;
        }

    }

    /**
     * This method separates a doubly linked list into two doubly linked lists
     * of half sizes
     *
     * @param head head node of the array
     * @return separate node
     */
    private Node<T> separate(Node<T> head) {
        if (head == null) {
            return null;
        }
        Node<T> middle_1 = head;
        Node<T> middle_2 = head;
        return separate(middle_1, middle_2);
    }

    /**
     * This method is wrapper recursive method for separate(Node<T> head)
     *
     * @param middle_1 fast node
     * @param middle_2 slow node
     * @return separate node
     */
    private Node<T> separate(Node<T> middle_1, Node<T> middle_2) {
        if (middle_1.next != null && middle_1.next.next != null) {
            return separate(middle_1.next.next, middle_2.next);
        }
        Node<T> temp = middle_2.next;
        middle_2.next = null;
        return temp;
    }
    public void mergeSort(){
        mergeSort(head);
    }
    /**
     * This method provides merge sorts on the generic array
     *
     * @param left separate left node
     * @return node
     */
    private Node<T> mergeSort(Node<T> left) {
        if (left == null) {
            return left;
        }
        if (left.next == null) {
            return left;
        }
        Node<T> right = separate(left);
        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }

    /**
     * This method provides merge operation on the generic array
     *
     * @param left separate left node
     * @param right separate right node
     * @return merge node
     */
    private Node<T> merge(Node<T> left, Node<T> right) {
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
      
        int compare = left.data.compareTo(right.data);
        if (compare < 0) {
            left.next = merge(left.next, right);
            left.next.previous = left;
            left.previous = null;
            return left;
        } else {
            right.next = merge(left, right.next);
            right.next.previous = right;
            right.previous = null;
            return right;
        }
    }

    /**
     * This method prints elements of the generic array to screen and searches
     * recursive
     *
     * @param node head node of the array
     */
    private void printArray(Node<T> node) {
        if (node != null) {
            System.out.print(node.data + " ");
            printArray(node.next);
        }
    }

    public static void main(String[] args) {

        MergeSortDoubleLinkedList<Integer> list = new MergeSortDoubleLinkedList<>();
        list.head = new Node<>(10);
        list.head.next = new Node<>(30);
        list.head.next.next = new Node<>(3);
        list.head.next.next.next = new Node<>(4);
        list.head.next.next.next.next = new Node<>(20);
        list.head.next.next.next.next.next = new Node<>(5);

        Node<Integer> node = null;
        node = list.mergeSort(list.head);
        System.out.println("Linked list after sorting :");
        list.printArray(node);

    }
}
