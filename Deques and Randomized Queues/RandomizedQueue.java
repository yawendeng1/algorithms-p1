import java.util.Iterator;
import java.util.NoSuchElementException;   
import edu.princeton.cs.algs4.StdRandom;
    
public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private Item[] s;
    private int capacity;
    private int N;
    
   // construct an empty randomized queue 
    public RandomizedQueue() {
        N = 0;
        capacity = 1;
        s = (Item[]) new Object[capacity];
    }
    // is the queue empty?
    public boolean isEmpty() {
        return N == 0;
    }  
    
    // return the number of items on the queue
    public int size() {
        return N;
    
    }
    
    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new NullPointerException("item can't be null.");
        if (N+1 > capacity) {
            capacity = 2*capacity;
            Item[] ns = (Item[]) new Object[capacity];
            for (int i = 0; i < N; i++) {
                ns[i] = s[i];
            }
            s = ns;
        }
        s[N++] = item;
        
    }
    
    // remove and return a random item
    public Item dequeue() {
        if (s == null || N == 0)
            throw new NoSuchElementException();
        int r = StdRandom.uniform(N);
        Item ri = s[r];
        s[r] = s[N-1];
        if (capacity/4 > N) {
            capacity = capacity/2;
            Item[] ns = (Item[]) new Object[capacity];
            for (int i = 0; i < N; i++) {
                ns[i] = s[i];
            }           
            s = ns;            
        }        
        s[--N] = null;   
        return ri;
    }
    
    // return (but do not remove) a random item
    public Item sample() {
        if (s == null || N == 0)
            throw new NoSuchElementException();
        int r = StdRandom.uniform(N);
        return s[r];
    }  
    
   // return an independent iterator over items in random order 
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }
    
    private class ArrayIterator implements Iterator<Item>{
        private Item[] tmpItem = (Item[]) new Object[capacity];
        private int count = N;
        
        public ArrayIterator() {
            for (int i = 0; i < capacity; i++) {
                tmpItem[i] = s[i];
            }
        }
        
        @Override
        public boolean hasNext() {
            return count != 0;           
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
        
        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            int index = StdRandom.uniform(count);
            Item item = tmpItem[index];
            tmpItem[index] = tmpItem[count-1];
            tmpItem[count--] = null;
            return item;
        }
    }
    
    // unit testing (optional)
    public static void main(String[] args) {
    }
}
