public class Board {
    
    private int[][] board;
    private int hamming;
    private int manhattan;
    private int dimension;
    private int[] blank;
    
    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        if (blocks == null || blocks.length == 0 || blocks[0].length == 0)
            throw new NullPointerException();  
        dimension = blocks.length;
        hamming = 0;
        manhattan = 0;
        move = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                board[i][j] = blocks[i][j];
                if (board[i][j] == 0) blank = new int[]{i,j};
                if (board[i][j] != 0 && board[i][j] != i * dimension + j + 1) {
                    hamming++;
                    manhattan += Math.abs(i * dimension + j + 1 - board[i][j]);
                }
            }
        }   
    }
    
    // board dimension n
    public int dimension() {
        return dimension;
    
    }
    
    // number of blocks out of place
    public int hamming() {
        return hamming;
    }
    
    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        return manhattan;
    
    }
    
    // is this board the goal board?
    public boolean isGoal() {
        return hamming == 0;  
    }
    
   // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
    
    }
    
   // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null || !(y instanceof Board) || y.dimension != this.dimension) return false;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (this.board[i][j] != y.board[i][j]) return false;
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Queue<Board> queue = new Queue<>();
        if (blank[0] > 0) queue.add(swap(blank, new int[]{blank[0]-1,blank[1]}));
        if (blank[0] < dimension - 1) queue.add(swap(blank, new int[]{blank[0]+1,blank[1]}));
        if (blank[1] > 0) queue.add(swap(blank, new int[]{blank[0],blank[1]-1}));
        if (blank[1] < dimension - 1) queue.add(swap(blank, new int[]{blank[0],blank[1]+1}));
        return queue;
    }
    
    private Board swap(int[] pos1, int[] pos2) {
        int[][] copy = board.clone();
        copy[pos1[0]][pos1[1]] = board[pos2[0]][pos2[1]];
        copy[pos2[0]][pos2[1]] = board[pos1[0]][pos1[1]];
        return copy;
    }
    
    // string representation of this board (in the output format specified below)
    public String toString() {
    StringBuilder s = new StringBuilder();
    s.append(n + "\n");
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            s.append(String.format("%2d ", tiles[i][j]));
        }
        s.append("\n");
    }
    return s.toString();
}
    
    // unit tests
    public static void main(String[] args) {
        
    }

}