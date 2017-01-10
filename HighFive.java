import java.util.*;
/*
 * 给出一堆result对象，表示学生的成绩。已知每个学生至少有五个成绩，返回每个学生五个最高分的平均值。
 */
class Result{
    int id;
    int value;
    public Result(int id, int value){
        this.id = id;
        this.value = value;
    }
}
public static Map<Integer,Double> solution(Result[] results) {
	if(results==null || results.length==0) return new HashMap<Integer,Integer>();
	Map<Integer, List<Integer>> map = new HashMap<>();
	for(Result r:results) {
		if(!map.containsKey(r.id)) {
			map.put(r.id, new ArrayList<Integer>());
		}
		map.get(r.id).add(r.value);
	}
	Map<Integer,Double> res = new HashMap<>();
	for(int i:map.keySet()) {
		List<Integer> list = map.get(i);
		Collections.sort(list);
		Collections.reverse(list);
		int sum = 0;
		for(int j = 0;j<5;j++) sum += list.get(j);
		double ave = (double)sum/(double)5;
		res.put(i,ave);
	}
	return res;
}