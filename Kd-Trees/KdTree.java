import java.util.TreeSet;
import java.util.Stack;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;


public class KdTree {
    
    KdNode root;
    int size;
    
    private class KdNode {
    
        Point2D point;
        int depth;
        KdNode left;
        KdNode right;
        
        public KdNode(Point2D point) {
            this.point = point;
        }
        
        public double distanceTo(Point2D that) {
            if (depth % 2 == 0) {
                return that.y() - this.point.y();
            } else {
                return that.x() - this.point.x();
            }
        }  
    }
    
    // construct an empty set of points
    public KdTree() {
        root = null;
        size = 0;
    }
    
    // is the set empty?
    public boolean isEmpty() {
        return root == null;
    }
    
    // number of points in the set 
    public int size() {
        return size;
    }
    
    // add the point to the set (if it is not already in the set)
    public void insert (Point2D p) {
        KdNode added = new KdNode(p);
        if (root == null) {
            root = added;
            root.depth = 0;
        } else {
            KdNode cur = root;
            KdNode prev = root;
            while (cur != null) {
                prev = cur;
                if (cur.distanceTo(p) < 0) cur = cur.left;
                else if (cur.distanceTo(p) == 0) return;
                else cur = cur.right;
            }
            if (cur.distanceTo(p) < 0) prev.left = added;
            else prev.right = added;           
        }
    }
    
    // does the set contain point p? 
    public boolean contains (Point2D p) {
        if (root == null) return false;
        KdNode cur = root;
        while (cur != null) {
            if (cur.distanceTo(p) == 0) return true;
            if (cur.distanceTo(p) < 0) cur = cur.left;
            else cur = cur.right;       
        }
        return false;
    }
    
    // draw all points to standard draw 
    public void draw() {
        if (root == null) return;
        drawNode(root);     
    }
    
    private void drawNode(KdNode root) {
        root.point.draw();
        if (root.left != null) drawNode(root.left);
        if (root.right != null) drawNode(root.right);
    }
    
    // all points that are inside the rectangle
    public Iterable<Point2D> range (RectHV rect) {
        Stack<Point2D> stack = new Stack<>();
        rangeRecursive(rect,stack,root);
        return stack;
    }
    
    private void rangeRecursive(RectHV rect, Stack<Point2D> stack, KdNode cur) {
        if (cur == null) return;
        if (rect.contains(cur.point)) {
            stack.push(cur.point);
            rangeRecursive(rect, stack, cur.left);
            rangeRecursive(rect, stack, cur.right);
        } else {
            if (rect.distanceTo(cur.point) < 0) rangeRecursive(rect, stack, cur.left);
            else rangeRecursive(rect, stack, cur.right);       
        }
    }
    
    // a nearest neighbor in the set to point p; null if the set is empty 
    public Point2D nearest(Point2D p) {
        double nearestDis = Double.POSITIVE_INFINITY;
        KdNode nearestNode = null;
        nearestRecursive(nearestDis, nearestNode, root, p);
        return nearestNode.point;
    }
    
    private void nearestRecursive(Double nearestDis, KdNode nearestNode, KdNode cur, Point2D p) {
        if (cur == null) return;
        double distance = cur.point.distanceSquaredTo(p);
        if (distance < nearestDis) {
            if (distance == 0) return;
            nearestDis = distance;
            nearestNode = cur;         
            if (cur.distanceTo(p) < 0) nearestRecursive(nearestDis, nearestNode, cur.left, p);
            else nearestRecursive(nearestDis, nearestNode, cur.right, p);
        }
    }

    // unit testing of the methods (optional) 
    public static void main(String[] args) {}
    
}