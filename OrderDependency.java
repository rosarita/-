import java.util.*;
/*
 * 给定一个order dependency list，每个order dependency中含有两个order pre和cur，想要做cur就要先做pre，其实就是有向图的一条边
 * 要求输出按照dependency排序的order list，实际上就是拓扑排序，题目标明不会成环，但也可判断一下
 * edge case：给出的order中可能有两个不同的order instance却有相同的order name，最终的结果每个order只出现一次，不管在给出的参数中
 * 出现多少次。因此在建立map的时候需要用order name而不是order实例作为key
 * 本题可参照leetcode上的sequence reconstruction
 */
class Order {
	String orderName;
	public Order(String orderName) {
		this.orderName = orderName;
	}
}
class OrderDependency {
	Order pre;
	Order cur;
	public OrderDependency(Order pre, Order cur) {
		this.pre = pre;
		this.cur = cur;
	}
}
public static List<Order> dependency(List<OrderDependency> list) {
	if(list==null || list.size()==0) return new ArrayList<Order>();//此处返回null或者空的list根据test case而定，每道题似乎是不一样的
	//topological sort with BFS
	Map<String,Integer> indegree = new HashMap<>();//indegree map
	Map<String,Set<String>> map = new HashMap<>();//record how many values come after key
	Map<String,Order> pair = new HashMap<>();//record ordername and order instance
	//第三个map有人说可以在结束时直接根据order name创立新的order，但这样会生成新的和给定的不同的instance，可以试试
	//building graph and indegree map
	for(OrderDependency od:list) {
		if(!pair.containsKey(od.pre.orderName)) {
			pair.put(od.pre.orderName,od.pre);
		}
		if(!pair.containsKey(od.cur.orderName)) {
			pair.put(od.cur.orderName,od.cur);
		}
		if(!map.containsKey(od.pre.orderName)) {
			map.put(od.pre.orderName,new HashSet<String>());
			indegree.put(od.pre.orderName,0);
		}
		if(!map.containsKey(od.cur.orderName)) {
			map.put(od.cur.orderName,new HashSet<String>());
			indegree.put(od.cur.orderName,0);
		}
		Set<String> set = map.get(od.pre.orderName);
		if(set.add(od.cur.orderName)) {
			indegree.put(od.cur.orderName, indegree.get(od.cur.orderName)+1);
		}
	}
	//A queue for BFS
	LinkedList<String> queue = new LinkedList<>();
	//put all elements which indegree are zero into the queue
	for(String name:indegree.keySet()) {
		if(indegree.get(name)==0) queue.offer(name);
	}
	List<Order> res = new ArrayList<>();
	while(!queue.isEmpty()) {
		String s = queue.poll();
		res.add(pair.get(s));
		Set<String> set = map.get(s);
		for(String str:set) {
			indegree.put(str,indegree.get(str)-1);//move an edge
			if(indegree.get(str)==0) queue.offer(str);//if the element's indegree is zero after moving edge, add it into queue
		}
	}
	return res;
}