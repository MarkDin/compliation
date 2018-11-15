package code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class compliation_02 {
	/***
	 * 架构 全局变量 首先每个非终结符对应的First集Follow集必须初始化和分配空间 初始的set的size大小为0 终结符的first集为本身
	 * lookedhead表示当前正在匹配的字母 first函数用来求first集 follow函数。。。。 select函数求。。。
	 * is_left_left判断是否为LL(1)型文法 match函数用来判断lookedhead和传入参数是否匹配 返回true/false
	 * Parse_S函数是开始符S对应的分析程序
	 */
	HashSet<String> VT; // 终结符集
	HashSet<String> VN; // 非终结符集
	HashMap<String, HashSet<String>> First; // first集合
	HashMap<String, HashSet<String>> Follow; // follow集合
	HashMap<String, Boolean> Nullable; // 记录产生式是否可以推出ε
	HashMap<String, HashSet<String>> expSet; // 产生式集合

	public compliation_02(HashSet<String> VT, HashMap<String, HashSet<String>> first,
			HashMap<String, HashSet<String>> follow) {
		this.VT = VT;
		First = first;
		Follow = follow;
	}

	public void Init() {
		/**
		 * 给每个非终结符对应的First集Follow集必须初始化和分配空间 初始的set的size大小为0
         * 初始化所有字母表的nullable为false
         * 置所有终结符的first集为本身
		 */
		// 给非终结符的first集合分配空间
		for (String str : VN) {
			HashSet<String> temp = new HashSet<String>();
			First.put(str, temp);
		}
		// 给非终结符的first集合分配空间
		for (String str : VN) {
			HashSet<String> temp = new HashSet<String>();
			Follow.put(str, temp);
		}
		// 初始化终结符的first集为本身
//        for (String t : VT) {
//			HashSet<String> temp = new HashSet<>();
//			temp.add(t);
//			First.put(t, temp);
//		}
//
		for (String t : VT) {
//			HashSet<String> temp = new HashSet<>();
//			temp.add(t);
//			Follow.put(t, temp);
		}
		for (String t :
			 VN) {
			Nullable.put("test", false);
		}
	}

	public boolean isTerminal(String str) {
		/**
		 * 判断参数中的str是否在终结符集合中
		 */
		return true ? VT.contains(str) : false;
	}

	public HashSet<String> getFirst(String left, boolean flag) {
		/**
		 * 用递归的方式来求参数中left的first集合
		 */
		if (!First.get(left).isEmpty()) { // 如果已经求出了first集 返回即可
			flag = true;
			return First.get(left);
		} else {
			while (!flag) {
				HashSet<String> set = expSet.get(left);
				for (String right : set) {
					First.put(right, getFirst(right, flag));
					if (!First.get(right).isEmpty()) {
						flag = true;
					}
				}
			}
		}
		return null;
	}

	public HashSet<String> getFirst(String left) {
		/**
		 * 用递归的方式来求参数中left的first集合
		 */
		if (!First.get(left).isEmpty()) { // 如果已经求出了first集 返回即可
			return First.get(left);
		} else {
			HashSet<String> rightSet = expSet.get(left);
			for (String right : rightSet) {
				HashSet<String> set = First.get(left);// 先取出first集合
				set.addAll(getFirst(String.valueOf(right.charAt(0))));// 添加元素
				First.put(left, set); // 重新加入到first集
			}
		}

		return First.get(left);
	}

	public void first(Exp exp) {
		/**
		 * 求参数中exp左部的first集合
		 */
		ArrayList<String> rights = exp.getRightList(); // 取出exp产生式右部分的集合
		for (String right : rights) {
			HashSet<String> set = expSet.get(right); // 取出right的产生式集合
			for (String t : // 遍历右边表达式的首个字母
			set) {
				boolean flag = false;
				while (flag) {
					HashSet<String> t_set = expSet.get(t);
					for (String tt : t_set) {
						if (isTerminal(tt)) { // 找到终结符了 先不返回 置标记为true 继续遍历寻找终结符
							flag = true;
							HashSet<String> right_set = First.get(exp.left);// 取出first集合
							right_set.add(tt); // 添加元素
							First.put(exp.left, right_set); // 整体添加到对应first集合
						}
					}
				}
			}
		}
	}
	
	public boolean isNullable(char[] seq) {
		/**
		 * 检查seq中的每一个符号是否都可以推空
		 * 若是返回true 否则返回false
		 */
		for(int i = 0; i < seq.length; i++) {
			if (Nullable.get(seq[i]) != true) {
				return false;
			}
		}
		return true;
	}

	public void nullable(char[] seq) {
        /**
         * 判断所有非终结符是否可以推空
         * 是 则置nullable为true 否 则置false
         */
    }

//	public void first(Exp exp) {
//		/**
//		 *
//		 */
//		int i, j, k;
//	}

	public void follow(String left, String right) {
		/**
		 * 求参数中aim的follow集合 1.取出expSet右边的set集合 并遍历其右部 找到与包含aim的 命名为str str对应的左边为A
		 * 2.若在str中aim后面没有字符 即aim在str中最右边 那么把follow(A)加入其follow集 3.若在str后面有字符* 如果*不能推出ε
		 * 那么把first(*)中非空元素加入follow(aim) 如果*能推出ε 那么把
		 */
		int i, j, k, size;
		//String right = exp.getRightList(); // 取出exp的右边的产生式
		HashSet<String> temp = new HashSet<>(); // 临时存放follow集的set集合
		k = right.length();
		size = 0; // 初始化follow集合大小为0
		for (i = 0; i < k; i++) {
			for (j = i + 1; j < k; j++) {
				if (isNullable(right.substring(i, j).toCharArray())) { // 判断i+1到j之间的串是否可以全部为空
					String str = String.valueOf(right.charAt(j+1));
					temp.addAll(First.get(str));// 将j后一个符号的first集加入到所求的follow集中
				}
				if(isNullable(right.substring(i, k-1).toCharArray())) {
					temp.addAll(Follow.get(left)); // 所求符号后面全部可推空的时候 将产生式左边的follow集加入到所求的follow集
				}
				if (temp.size() == size) { // 若在此轮迭代计算中temp集合没有变化 说明计算完毕 退出循环
					break;
				}
				size = temp.size();
			}

		}

	}

	public static void main(String args[]) {
		// new compliation_02().Init();
	}
}