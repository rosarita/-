import java.util.*;
/*
 * 给出一个字符串，求最长的子回文字符串。
 * LC 原题。本题可以用全局变量也可以不用
 * edge case：给出字符串为空的时候。此时返回“”还是返回null由test case而定
 */
public static String longestSub(String s) {
	if(s==null || s.length()==0) return "";
	int[] pos = new int[2];
	for(int i = 1;i<s.length();i++) {
		helper(s,i-1,i,pos);
		helper(s,i,i,pos);
	}
	return s.substring(pos[0],pos[0]+pos[1]);
}
private static void helper(String s, int start, int end, int[] pos) {
	//pos[0]: 回文字符串起始位置 pos[1]：回文字符串长度
	while(start>=0 && end<s.length()) {
		if(s.charAt(start)!=s.charAt(end)) break;
		start--;end++;
	}
	if(end-start-1>pos[1]) {
		pos[0] = start+1;
		pos[1] = end-start-1;
	}
}