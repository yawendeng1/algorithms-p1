public class Solver {
    
    
    private class GameNode implements Comparable<SearchNode> {
        private Board board;
        private int moves;
        private GameNode prev;
        
        public GameNode(Board board) {
            this.board = board;
            moves = 0;
            prev = null;
        }
        
        private int priority() {
            return moves + board.manhattan();
        }
        
        @Override
        public int compareTo(GameNode that) {
            if (this.priority < that.priority) return -1;
            else if (this.priority == that.priority) return 0;
            else return 1;
        }    
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
    
    }
    
    // is the initial board solvable?
    public boolean isSolvable() {}
    
    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {}
    
    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {}
    
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