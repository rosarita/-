import java.util.*;
/*
 * 本题给出一些city，和它们之间供电所需要的花费，相当于给出一些带权图的边。本题要求链接每一个城市，使得供电总花费最小，既最小生成树，结果需要按照natural
 * order 排序
 * 使用kruskal算法和union find算法
 * edge case：
 * 1. 给定list为空的时候，返回一个新的空list
 * 2. 边的条数和点的个数不满足差1的关系，返回null
 */
class Connection{
    String node1;
    String node2;
    int cost;
    public Connection(String a, String b, int c){
        node1 = a;
        node2 = b;
        cost = c;
    }
}
public static ArrayList<Connection> getLowCost(ArrayList<Connection> connections) {
	if(connections==null || connections.size()==0) return new ArrayList<Connection>();
	//build a map for union find
	Map<String,String> map = new HashMap<>();
	for(Connection c:connections) {
		map.put(c.node1,c.node1);
		map.put(c.node2,c.node2);
	}
	//sort edges for kruskal
	Collections.sort(connections, new Comparator<Connection>() {
		public int compare(Connection c1, Connection c2) {
			return c1.cost-c2.cost;
		}
	});
	ArrayList<Connection> res = new ArrayList<>();
	for(Connection c:connections) {
		//select an edge and check it's been connected in new graph. If not, add it into new graph
		if(union(c.node1,c.node2,map)) {
			res.add(c);
		}
	}
	//if number of cities isn't one number larger than number of edges, return null
	if(res.size()!=map.size()+1) return null;
	//sort result. if first cities of two connection are the same, then sort then using the second
	Collections.sort(res, new Comparator<Connection>() {
		public int compare(Connection c1, Connection c2) {
			if(c1.node1.equals(c2.node1)) {
				return c1.node2.compareTo(c2.node2);
			}
			return c1.node1.compareTo(c2.node1);
		}
	});
	return res;
}
//following functions are for union find
private static String find(String city, Map<String,String> map) {
	while(!city.equals(map.get(city)) city = map.get(city);
	return city;
}
private static boolean union(String city1, String city2, Map<String,String> map) {
	//union函数在已经联通的时候返回false，成功union则返回true
	String r1 = find(city1);
	String r2 = find(city2);
	if(r1.equals(r2)) return false;
	map.put(r2,r1);
	return true;
}