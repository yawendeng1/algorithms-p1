
public class Deque<Item> implements Iterable<Item>{
    
    private Node head;
    private Node last;
    private int N;
    
    private class Node(Item item){   
        Item this.item = item;
        Node next;
        Node prev;
    }
  
    // construct an empty deque
    public Deque(){
        head = null;
        last  = null;
        N = 0;
    }
    
    // is the deque empty?
    public boolean isEmpty(){
        return N == 0;
    }

    // return the number of items on the deque
    public int size(){
        return N;
    }
    
    // add the item to the front
    public void addFirst(Item item){
        Node n = new Node(item);
        n.prev = head;
        head.next = n;
        if (N == 0){
            last = n;
            n.next = null;
        }else{
        Node next = head.next;
        n.prev = head;
        n.next = next;
        next.prev = n;
        head.next = n;
        }
        N++;       
    }
    
    // add the item to the end
    public void addLast(Item item){
        Node n = new Node(item);
        n.next = null;
        n.prev = last;
        last.next = n;
        last = n;
        N++;
    }
    
    // remove and return the item from the front
    public Item removeFirst(){
        head.next.next.prev = head;
        head.next = head.next.next;
        N--;
    }
    
    // remove and return the item from the end
    public Item removeLast(){
        
    }
    
   // return an iterator over items in order from front to end 
    public Iterator<Item> iterator(){
    }
    
    // unit testing
    public static void main(String[] args){
    }
}