import java.util.Stack;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;


public class KdTree {
    
    private KdNode root;
    private int size;
    
    private class KdNode {
    
        private final Point2D point;
        private int depth;
        private KdNode left;
        private KdNode right;
        private final RectHV rect;
        
        public KdNode(Point2D point, RectHV rect) {
            this.point = point;
            this.rect = rect;
        }
        
        public boolean isVertical(Point2D that) {
            return depth % 2 == 0;
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
        return root == null || size == 0;
    }
    
    // number of points in the set 
    public int size() {
        return size;
    }
    
    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) return;
        if (root == null) {
            root = new KdNode(p, new RectHV(0, 0, 1, 1));
            root.depth = 0;
        } else {
            KdNode cur = root;
            KdNode prev = root;
            while (cur != null) {
                prev = cur;
                if (cur.distanceTo(p) < 0) cur = cur.left;
                else if (cur.point.equals(p)) return;
                else cur = cur.right;
            }

            if (!prev.isVertical(p)) {
                if (prev.distanceTo(p) < 0) {
                    prev.left = new KdNode(p, new RectHV(prev.rect.xmin(), prev.rect.ymin(), prev.point.x(), prev.rect.ymax()));
                    prev.left.depth = prev.depth + 1;
                } else {
                    prev.right = new KdNode(p, new RectHV(prev.point.x(), prev.rect.ymin(), prev.rect.xmax(), prev.rect.ymax()));
                    prev.right.depth = prev.depth + 1;
                }
            } else {
                if (prev.distanceTo(p) < 0) {
                    prev.left = new KdNode(p, new RectHV(prev.rect.xmin(), prev.rect.ymin(), prev.rect.xmax(), prev.point.y()));
                    prev.left.depth = prev.depth + 1;
                } else {
                    prev.right = new KdNode(p, new RectHV(prev.rect.xmin(), prev.point.y(), prev.rect.xmax(), prev.rect.ymax()));
                    prev.right.depth = prev.depth + 1;
                }          
            }
        }
         size++;
    }
    
    // does the set contain point p? 
    public boolean contains(Point2D p) {
        if (root == null || size == 0) return false;
        KdNode cur = root;
        while (cur != null) {
            if (cur.point.distanceSquaredTo(p) == 0) return true;
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
        return nearestRecursive(Double.POSITIVE_INFINITY, root, p);
    }
    
    
    // void function doesn't work, java pass by value
    private Point2D nearestRecursive(double nearestDis, KdNode cur, Point2D p) {
        if (cur == null) return null;
        if (cur.rect.distanceTo(p) >= nearestDis) return null;
        double distance = cur.point.distanceSquaredTo(p);
        Point2D nearestPoint = null;
        if (distance == 0) return cur.point;
        if (distance < nearestDis) {
            nearestDis = distance;
            nearestPoint = cur.point;
        } 
        
        KdNode node1 = cur.left;
        KdNode node2 = cur.right;
        if (node1 != null && node2 != null) {
            if (node1.rect.distanceTo(p) > node2.rect.distanceTo(p)) {
                node1 = cur.right;
                node2 = cur.left;
            }
        }
        
        Point2D point1 = nearestRecursive(nearestDis, node1, p);       
        if (point1 != null) {
            if (p.distanceSquaredTo(point1) < nearestDis) {
                nearestPoint = point1;
                nearestDis = p.distanceSquaredTo(point1);
            }
        }
        
        Point2D point2 = nearestRecursive(nearestDis, node2, p);
        if (point2 != null) {
            if (p.distanceSquaredTo(point2) < nearestDis) {
                nearestPoint = point2;
//                nearestDis = p.distanceSquaredTo(point2);
            }
        }
        return nearestPoint;
    }    
}