import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {

    public static void main(String[] args){
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        int num = Integer.valueOf(args[0]);
        
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            rq.enqueue(item);
        }       
        while (num > 0) {
            StdOut.println(rq.dequeue());
            num--;         
        } 
    }    
}