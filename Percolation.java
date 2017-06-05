import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Percolation{
    
    private boolean[][] grid;
    private WeightedQuickUnionUF wquf;
    private int gridSize;
    private int virtualTop;
    private int virtualBottom;
    
    
    // create n-by-n grid, with all sites blocked
    public Percolation(int n){    
        if (n <= 0)
            throw new IllegalArgumentException("N must be at least 1");
        
        gridSize = n;
        grid = new boolean[gridSize][gridSize];
        wquf = new WeightedQuickUnionUF(gridSize*gridSize+2);
        virtualTop = 0;
        virtualBottom = gridSize*gridSize+1;
    }
    
    private int getUnionFindIndex(int row,int col){
        return (row-1)*gridSize + col-1 + 1;
    }
    
    private boolean isValidate(int row,int col){
        if (row > 0 && row <= gridSize && col >0 && col <= gridSize){
            return true;
        } else {
            return false;
        }
    }
    
    private void validateIndices(int row,int col){
        if (!isValidate(row,col))
            throw new java.lang.IndexOutOfBoundsException("Index out of bounds");
    }
    
    private int[][] getNeighbors(int row,int col){
        return new int[][]{{row-1,col},{row,col-1},{row,col+1},{row+1,col}};
    }
    
    // open site (row, col) if it is not open already
    public void open(int row, int col){
        validateIndices(row,col);
        if (!isOpen(row, col)){
            grid[row-1][col-1] = true;
            int index = getUnionFindIndex(row, col);
            if (row == 1){
                wquf.union(index, virtualTop);
            }
            if (row == gridSize){
                wquf.union(index,virtualBottom);
            }
            
            for (int[] neighbor:getNeighbors(row,col)){
                if (isValidate(neighbor[0],neighbor[1]) && isOpen(neighbor[0],neighbor[1])){
                        int ngbrIndex = getUnionFindIndex(neighbor[0],neighbor[1]);
                        wquf.union(index,ngbrIndex);
                }       
            }
        }
    }
    
    // is site (row, col) open?
    public boolean isOpen(int row, int col){
        validateIndices(row,col);
        return grid[row-1][col-1];
    }
    
    // is site (row, col) full?
    public boolean isFull(int row, int col){ 
        validateIndices(row,col);
        return wquf.connected(virtualTop,getUnionFindIndex(row,col));  
    }
   
    // number of open sites
    public int numberOfOpenSites(){
        return wquf.count();      
    }
    
    // does the system percolate?
    public boolean percolates(){
        return wquf.connected(virtualTop, virtualBottom);
        
    }
    
    // test client (optional)
    public static void main(String[] args){
                Percolation p = new Percolation(5);
        StdOut.println(p.isFull(1, 1));
        StdOut.println(p.isOpen(2, 1));
        StdOut.println(p.isFull(2, 1));
        p.open(3, 4);
        StdOut.println(p.isOpen(3, 4));
        StdOut.println(p.isFull(3, 4));
        StdOut.println(p.percolates());
        
    }
    
    
    
    
}