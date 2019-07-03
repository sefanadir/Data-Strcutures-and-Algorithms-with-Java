package datastructures_4;

import org.junit.Test;

import static org.junit.Assert.*;

public class LeftChildRightSiblingTest {

    LeftChildRightSibling<Integer> tree= new LeftChildRightSibling<>(1);
    @Test
    public void addTest() {
        boolean add_1=tree.add(1, 2);
        boolean add_2=tree.add(1, 4);
        boolean add_3=tree.add(1, 7);
        boolean add_4=tree.add(2, 3);
        assertEquals(add_1,true);
        assertEquals(add_2,true);
        assertEquals(add_3,true);
        assertEquals(add_4,true);
    }

    @Test
    public void postOrderSearchTest() {
        tree.add(1, 2);
        tree.add(1, 4);
        tree.add(1, 7);
        tree.add(2, 3);

       assertNull(tree.postOrderSearch(tree.treeRoot,1));
    }

    @Test
    public void levelOrderSearchTest() {
        tree.add(1, 2);
        tree.add(1, 4);
        tree.add(1, 7);
        tree.add(2, 3);

        assertNull(tree.postOrderSearch(tree.treeRoot,1));
    }
}