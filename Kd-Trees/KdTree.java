import java.util.Stack;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;


public class KdTree {
    
    private KdNode root;
    private int size;
    
    private class KdNode {
    
        private Point2D point;
        private int depth;
        private KdNode left;
        private KdNode right;
        
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
    public void insert(Point2D p) {
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
                else if (cur.point.distanceTo(p) == 0) return;
                else cur = cur.right;
            }
            if (prev.distanceTo(p) < 0) prev.left = added;
            else prev.right = added; 
            added.depth = prev.depth + 1;
        }
         size++;
    }
    
    // does the set contain point p? 
    public boolean contains (Point2D p) {
        if (root == null || size == 0) return false;
        KdNode cur = root;
        while (cur != null) {
            if (cur.point.distanceTo(p) == 0) return true;
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
    
    private void drawNode(KdNode node) {
        node.point.draw();
        if (node.left != null) drawNode(node.left);
        if (node.right != null) drawNode(node.right);
    }
    
    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        Stack<Point2D> stack = new Stack<>();
        rangeRecursive(rect, stack, root);
        return stack;
    }
    
    private void rangeRecursive(RectHV rect, Stack<Point2D> stack, KdNode cur) {
        if (cur == null) return;
        if (rect.contains(cur.point)) {
            stack.push(cur.point);
        }
        double pointCoord = cur.point.y();
        double rectMin = rect.ymin();
        double rectMax = rect.ymax();
        if (cur.depth % 2 != 0) {
            pointCoord = cur.point.x();
            rectMin = rect.xmin();
            rectMax = rect.xmax();
        }
        
        if (rectMin < pointCoord) rangeRecursive(rect, stack, cur.left);
        if (rectMax >= pointCoord) rangeRecursive(rect, stack, cur.right);
    }
    
    // a nearest neighbor in the set to point p; null if the set is empty 
    public Point2D nearest(Point2D p) {
        double nearestDis = Double.POSITIVE_INFINITY;
        Point2D nearestPoint = nearestRecursive(nearestDis, nearestPoint, root, p);
        return nearestPoint;
    }
    
    
    // void function doesn't work
    private Point2D nearestRecursive(double nearestDis, Point2D nearestPoint, KdNode cur, Point2D p) {
        if (cur == null) return nearestPoint;
        double distance = cur.point.distanceTo(p);
        if (distance < nearestDis) {
            nearestDis = distance;
            nearestPoint = cur.point;
        }
        if (cur.distanceTo(p) < 0) nearestPoint = nearestRecursive(nearestDis, nearestPoint, cur.left, p);
        else nearestPoint = nearestRecursive(nearestDis, nearestPoint, cur.right, p);
        return nearestPoint;
    }    
}