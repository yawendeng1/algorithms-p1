import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DequeTest {
    Deque<String> deque;
    
    @Before
    public void setUp() {
        deque = new Deque<String>();
    }
    
    @Test
    public void shouldInstantiateDequeWithNoNodes() {          
        assertTrue(deque instanceof Deque);        
        assertEquals(0,deque.size());
        assertTrue(deque.isEmpty());
    }
    
    @Test
    public void shouldCreateNewNodeWithSettingsWhenAddedToFront() {
        String newNodeFront = "newNode";
        deque.addFirst(newNodeFront);
        assertEquals(1,deque.size());
        assertEquals(null,deque.head.getNext());
        assertEquals(newNodeFront,deque.head.getItem());
        assertEquals(newNodeFront,deque.tail.getItem());
        assertEquals(null,deque.head.getPrevious());
    }
    
    @Test
    public void shouldCreateNewNodeWithSettingsWhenAddedToback() {
        String newNodeBack = "newNode";
        deque.addLast(newNodeBack);
        assertEquals(1,deque.size());
        assertEquals(null,deque.head.getNext());
        assertEquals(newNodeBack,deque.tail.getItem());
        assertEquals(newNodeBack,deque.head.getItem());
        assertEquals(null,deque.head.getPrevious());
    }
    
    @Test 
    public void shouldKnowItsItsSequenceAndItsNeighbours() {
        String frontNode = "front";
        String backNode = "back";
        deque.addLast(frontNode);
        deque.addLast(backNode);
        
        String headItem = deque.head.getItem();
        String headNext = deque.head.getNext().getItem();
        String tailItem = deque.tail.getItem();
        String tailPrevious = deque.tail.getPrevious().getItem();
       
        assertEquals(2,deque.size());
       
        assertNull(deque.head.getPrevious());
        assertEquals(backNode,headNext);
        assertEquals(frontNode,headItem);
        
        assertEquals(backNode,tailItem);
        assertEquals(frontNode,tailPrevious);
        assertNull(deque.tail.getNext());
    }
    
    @Test
    public void shouldRemoveAppropriateLinksWhenRemoved() {
        String frontNode = "front";
        String backNode = "back";
        deque.addLast(frontNode);
        deque.addLast(backNode);
        deque.removeLast();

        assertEquals(1,deque.size());   
        assertNull(deque.head.getNext());       
    }
    
    @Test
    public void removeCleanlyIfNoPrevOrNext() {
        String frontNode = "front";
        deque.addLast(frontNode);
        deque.removeFirst();
        
        assertEquals(0,deque.size());
    }
        
}