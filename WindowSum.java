import java.util.*;
/*
 * 给定一个整型list和一个窗口长度，求出滑动窗口的时候窗口内的元素和
 * edge case：
 * 1. 窗口长度大于list大小，则结果的每一项是从该元素起一直到结尾元素的累和
 * 2. 窗口长度为0或比0小，则返回一个空的list
 * 3. 给出的list为空，返回一个空的list
 */
public static ArrayList<Integer> windowSum(List<Integer> list, int k) {
	ArrayList<Integer> res = new ArrayList<>();
	if(list==null || list.size()==0 || k<=0) return res;
	if(k>list.size()) {
		int sum = 0;
		for(int i = 0;i<list.size();i++) sum += list.get(i);
		for(int i = 0;i<list.size();i++) {
			sum -= i==0?0:list.get(i-1);
			res.add(i);
		}
	}
	else {
		int sum = 0;
		for(int i = 0;i<list.size();i++) {
			sum += list.get(i);
			if(i-k+1>=0) {
				res.add(sum);
				sum -= list.get(i-k+1);
			}
		}
	}
	return res;
}