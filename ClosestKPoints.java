import java.util.*;
/*
 * 给定原点（0,0)和一堆其他的点以及K，返回这些点中离原点距离最近的K个点。
 * edge case：
 * 1. K<=0的时候返回空的数组，而不是null，这个机制比较类似leetcode
 * 2. K比给的点数还要大的时候不必关心剩下来的空位，返回排好序的所有点之后（注意，要排好序而不是直接返回原数组）其余空位默认是null即可
 */
public static Point[] closestK(Point[] points, int k) {
	/*
	 * 本题可以使用Arrays的sort方法，也可以使用PriorityQueue，两种方法在时间复杂度上应该没有区别
	 */
	if(k<=0) return new Point[0];
	Point[] res = new Point[k];
	Arrays.sort(points, new Comparator<Point>() {
		public int compare(Point p1, Point p2) {
			return (p1.x*p1.x+p1.y*p1.y)-(p2.x*p2.x+p2.y*p2.y);
		}
	});
	for(int i = 0;i<k;i++) {
		if(i>=points.length) break;
		res[i] = points[i];
	}
	return res;
}
class Point {
	int x;
	int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}