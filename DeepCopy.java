import java.util.*;
/*
 * LC 原题
 */
class RandomListNode {
     int label;
     RandomListNode next, random;
     RandomListNode(int x) { this.label = x; }
}
public static RandomListNode deepCopy(RandomListNode head) {
	if(head==null) return head;
	Map<RandomListNode,RandomListNode> map = new HashMap<>();
	RandomListNode cur = head;
	while(cur!=null) {
		map.put(cur,new RandomListNode(cur.label));
		cur = cur.next;
	}
	cur = head;
	while(cur!=null) {
		map.get(cur).next = map.get(cur.next);
		map.get(cur).random = map.get(cur.random);
		cur = cur.next;
	}
	return map.get(head);
}