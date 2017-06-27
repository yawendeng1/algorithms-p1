import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import java.util.Stack;

public class Solver {
    private boolean isSolvable;
    private GameNode lastNode;
    
    private class GameNode implements Comparable<GameNode> {
        private Board board;
        private int moves;
        private GameNode prev;
        private boolean isTwin;
        
        public GameNode(Board board, boolean isTwin) {
            this.board = board;
            moves = 0;
            prev = null;
            this.isTwin = isTwin;
        }
        
        public GameNode(Board board, GameNode prev, int moves, boolean isTwin) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
            this.isTwin = isTwin;
        
        }
        
        @Override
        public int compareTo(GameNode that) {
            int priority1 = this.board.manhattan() + this.moves;
            int priority2 = that.board.manhattan() + that.moves;
            if (priority1 < priority2) return -1;
            else if (priority1 == priority2) return 0;
            else return 1;
        }    
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) 
            throw new IllegalArgumentException();
        
        isSolvable = true;
        MinPQ<GameNode> nodes = new MinPQ<>();
        
        nodes.insert(new GameNode(initial, false));
        nodes.insert(new GameNode(initial.twin(), true));
        
        while (!nodes.isEmpty()) {
            GameNode current = nodes.delMin();
            if (!current.isTwin) {
                lastNode = current;
            }
            if (current.board.isGoal()) {
                if (current.isTwin) isSolvable = false;
                break;
            }
            
            for (Board neighbor : current.board.neighbors()) {
                if (current.prev == null || !current.prev.board.equals(neighbor)) {
                    nodes.insert(new GameNode(neighbor, current, current.moves + 1, current.isTwin));
                    
                }
            }
        }
        
    
    }
    
    // is the initial board solvable?
    public boolean isSolvable() {
        return isSolvable;
    }
    
    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (isSolvable()) return lastNode.moves;
        return -1;
    
    }
    
    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!isSolvable()) return null;
        Stack<Board> solutions = new Stack<Board>();
        GameNode current = lastNode;
        
        while (current.prev != null) {
            solutions.push(current.board);
            current = current.prev;
        }
        solutions.push(current.board);
        
        Stack<Board> solutions2 = new Stack<Board>();
        while (!solutions.empty()) {
            solutions2.push(solutions.pop());
        }
        
        return solutions2;
    }
    
    // solve a slider puzzle (given below)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
            blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
        
        // solve the puzzle
        Solver solver = new Solver(initial);
        
        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}