import java.util.Queue;
import java.util.LinkedList;

public class Board {
    
    private final int[][] board;
    private final int N;
    
    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        if (blocks == null || blocks.length == 0 || blocks[0].length == 0)
            throw new NullPointerException();  
        N = blocks.length;
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = blocks[i][j];
            }
        }   
    }
    
    // board dimension n
    public int dimension() {
        return N;
    
    }
    
    // number of blocks out of place
    public int hamming() {
        int hamming = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!(i == N - 1 && j == N - 1) && board[i][j] != 1 + i * N + j)
                    hamming++;
            }
        }
        return hamming;
    }
    
    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int manhattan = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!(i == N - 1 && j == N - 1) && board[i][j] != 1 + i * N + j) {
                    int correctX = (board[i][j] - 1) / N;
                    int correctY = board[i][j] - 1 - correctX * N;
                    manhattan += Math.abs(i - correctX) + Math.abs(j - correctY);
                }
            }
        }
        return manhattan;
    }
    
    // is this board the goal board?
    public boolean isGoal() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] != 1 + i * N + j)
                    return false;
            }
        } 
        return true;
    }
    
   // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        Board twin = new Board(board);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - 1; j++) {
                if (board[i][j] != 0 && board[i][j + 1] != 0) {
                    swap(i, j, i, j + 1);
                    return twin;
                }
            }
        }
        return twin;
    }
    
   // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null || !(y instanceof Board)) return false;
        Board that = (Board) y;
        if (that.N != this.N) return false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.board[i][j] != that.board[i][j]) return false;
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        int blankX = 0;
        int blankY = 0;
        boolean found = false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0) {
                    blankX = i;
                    blankY = j;
                    found = true;
                    break;
                }
            }
            if (found)
                break;
        }
        Queue<Board> queue = new LinkedList<Board>();
        if (blankX > 0) queue.add(swap(blankX, blankY, blankX - 1, blankY));
        if (blankX < N - 1) queue.add(swap(blankX, blankY, blankX + 1, blankY));
        if (blankY > 0) queue.add(swap(blankX, blankY, blankX, blankY - 1));
        if (blankY < N - 1) queue.add(swap(blankX, blankY, blankX, blankY + 1));
        return queue;
    }
    
    private Board swap(int i0, int j0, int i1, int j1) {
        int[][] copy = board.clone();
        copy[i0][j0] = board[i1][j1];
        copy[i1][j1] = board[i0][j0];
        return new Board(copy);
    }
    
    // string representation of this board (in the output format specified below)
    public String toString() {
    StringBuilder s = new StringBuilder();
    s.append(N + "\n");
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            s.append(String.format("%2d ", board[i][j]));
        }
        s.append("\n");
    }
    return s.toString();
}
    
    // unit tests
    public static void main(String[] args) {
        
    }

}