import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats{
    
    private int numOfTrails;
    private double[] fractions;
    
    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials){
        if (n <= 0)
            throw new java.lang.IllegalArgumentException("n must be at least 1");
        if (trials <= 0)
            throw new java.lang.IllegalArgumentException("trails must be at least 1");
        numOfTrails = trials;
        fractions = new double[trials];
        for (int i = 0; i < numOfTrails; i++){
           Percolation pc = new Percolation(n);
           while (!pc.percolates()){
               int row = StdRandom.uniform(1,n+1);
               int col = StdRandom.uniform(1,n+1);
               pc.open(row,col); 
           }
           double fraction = (double) pc.numberOfOpenSites()/(n*n);
           fractions[i] = fraction;
        }
    }
    
    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(fractions);
    }
    
    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(fractions);
    }
    
    
    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        return mean() - 1.96 * stddev() / Math.sqrt(numOfTrails);    
    }
    
    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return mean() + 1.96 * stddev() / Math.sqrt(numOfTrails);
    }
    
    //test client
    public static void main(String[] args){
        Stopwatch timer = new Stopwatch();
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(N, T);
        StdOut.println("mean\t\t\t\t\t= " + ps.mean());
        StdOut.println("stddev\t\t\t\t\t= " + ps.stddev());
        StdOut.println("95% confidence interval\t= " + ps.confidenceLo() + ", " + ps.confidenceHi());
        StdOut.println("Time elapsed\t\t\t= " + timer.elapsedTime());
    }
}