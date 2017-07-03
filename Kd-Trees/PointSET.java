import java.util.TreeSet;
import java.util.Stack;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;


public class PointSET {
    
    private TreeSet<Point2D> pointSet;

    // construct an empty set of points
    public PointSET() {
        pointSet = new TreeSet<>();
    }
    
    // is the set empty?
    public boolean isEmpty() {
        return pointSet.isEmpty();
    }
    
    // number of points in the set 
    public int size() {
        return pointSet.size();
    }
    
    // add the point to the set (if it is not already in the set)
    public void insert (Point2D p) {
        pointSet.add(p);
    }
    
    // does the set contain point p? 
    public boolean contains (Point2D p) {
        return pointSet.contains(p);
    }
    
    // draw all points to standard draw 
    public void draw() {
        for (Point2D p : pointSet) {
            p.draw();
        }
    }
    
    // all points that are inside the rectangle
    public Iterable<Point2D> range (RectHV rect) {
        Stack<Point2D> stack = new Stack<>();
        for (Point2D p : pointSet) {
            if (rect.contains(p))
                stack.push(p);
        }
        return stack;
    }
    
    // a nearest neighbor in the set to point p; null if the set is empty 
    public Point2D nearest(Point2D p) {
        if (pointSet.isEmpty()) return null;
        Double minDis = Double.MAX_VALUE;
        Point2D minPoint = null;
        for (Point2D point : pointSet) {
            if (p.distanceTo(point) < minDis) {
                minDis = p.distanceTo(point);
                minPoint = point;
            }
        }
        return minPoint;
    }

    // unit testing of the methods (optional) 
    public static void main(String[] args) {}
    
}