import java.util.NoSuchElementException;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    
    private Node<Item> head;
    private Node<Item> tail;
    private int N;
    
    private class Node<Item> {   
        private Item item;
        private Node<Item> next;
        private Node<Item> prev;
        
        private Node(Item item) {
            this.item = item;
            next = null;
            prev = null;
        }
    }
  
    // construct an empty deque
    public Deque() {
        head = null;
        tail  = null;
        N = 0;
    }
    
    // is the deque empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the deque
    public int size() {
        return N;
    }
    
    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException("can't add null item");
        }
        Node<Item> n = new Node<Item>(item);
        if (N == 0) {
            head = tail = n;
        } else {
            head.prev = n;
            n.next = head;
            head = n;
        }
        N++;       
    }
    
    // add the item to the end
    public void addLast(Item item) {
         if (item == null) {
            throw new NullPointerException("can't add null item");
        }       
        Node<Item> n = new Node<Item>(item);
        if (N == 0) {
            head = tail = n;
        }else{
            n.prev = tail;
            tail.next = n;
            tail = n;
        }
         N++;
    }
    
    // remove and return the item from the front
    public Item removeFirst() {
        if (N == 0) {
            throw new NoSuchElementException("can't remove an item from an empty deque");
        }
        Item tmp = head.item;
        if (head.next == null) {
            head = tail = null;           
        } else {      
            head.next.prev = null;
            head = head.next;
        }
        N--;  
        return  tmp;
    }
    
    // remove and return the item from the end
    public Item removeLast() {
        if (N == 0) {
            throw new NoSuchElementException("can't remove an item from an empty deque");
        }
        Item tmp = tail.item;
        if (tail.prev == null) {
            head = tail = null;
        } else {
            tail.prev.next = null;
            tail = tail.prev;
        }
        N--;
        return tmp;
    }
    
    
   // return an iterator over items in order from front to end 
    public Iterator<Item> iterator() {
                            
        return new Iterator<Item>() {
            Node<Item> cur = head;        
            @Override
            public boolean hasNext() {
                return cur != null;
            }
            
            @Override
            public Item next() {
                if (cur == null) throw new NoSuchElementException();
                Item nextItem = cur.item;
                cur = cur.next;
                return nextItem;
                
            }
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
    
    // unit testing
    public static void main(String[] args) {
        Deque<Integer> dll = new Deque<Integer>();
        dll.addFirst(10);
        dll.addFirst(34);
        dll.addLast(56);
        dll.addLast(364);
        dll.removeFirst();
        dll.removeLast();       
    }
}