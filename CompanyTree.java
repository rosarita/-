import java.util.*;
/*
 * 本题给定一个多叉树，每个树节点有一个值，求平均值最小的子树，返回该子树的根节点
 * 关于本题是否可以使用全局变量，据说曾经是不行的，后来编译器改了之后可以了，使用java的可以试试，如果不能用使用数组传参也是一样的
 */
class Node {
    int val;
    ArrayList<Node> children;
    public Node(int val){
        this.val = val;
        children = new ArrayList<Node>();
    }
}
class Count {
	int sum;
	int count;
	public Count(int sum, int count) {
		this.sum = sum;
		this.count = count;
	}
}
class Solution {
	static double res = Double.MIN_VALUE;
	static Node node;
	public static Node getHighAve(Node root) {
		if(root==null) return null;
		helper(root);
		return node;
	}
	private Count helper(Node root) {
		if(root.children==null || root.children.size()==0) return new Count(root.val,1);
		int sum = root.val;
		int count = 1;
		for(Node n:root.children) {
			Count c = helper(n);
			sum += c.sum;
			count += c.count;
		}
		double ave = (double)sum/(double)count;
		if(ave>res) {
			res = ave;
			node = root;
		}
		return new Count(sum,count);
	}
}