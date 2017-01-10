import java.util.*;
/*
 * 给定两个矩形对象，里面包含两个point对象，point对象里面包含两个点。这两个点是对角。如果重合，就返回true.不重合返回false。
 * 注意：本题有人说需要做预处理，因为给定的两个对角可能是左上右下，也可能是左下右上。不过不做预处理也有通过全部test case的例子
 * 给出预处理版本和非预处理版本，都很简单
 * edge case: 点线相切不算重合
 */
class Point {
	int x;
	int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
class Rectangle {
	Point topLeft;
	Point bottomRight;
}
public static boolean isOverlap(Rectangle r1, Rectangle r2) {
	/*
	 * 预处理
	 * 非预处理按照LC 223即可
	 */
	int right = Math.min(Math.max(r1.topLeft.x,r1.bottomRight.x),Math.max(r2.topLeft.x,r2.bottomRight.x));
	int left = Math.max(Math.min(r1.topLeft.x,r1.bottomRight.x),Math.max(r2.topLeft.x,r2.bottomRight.x));
	int top = Math.min(Math.max(r1.topLeft.y,r1.bottomRight.y),Math.max(r2.topLeft.y,r2.bottomRight.y));
	int bottom = Math.max(Math.min(r1.topLeft.y,r1.bottomRight.y),Math.min(r2.topLeft.y,r2.bottomRight.y));
	return right>left && top>bottom;
}