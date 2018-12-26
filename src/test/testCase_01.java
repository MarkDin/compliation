package test;

import code.compliation_01;
import code.exception_01;

import java.util.ArrayList;

public class testCase_01 {
	public void test_split_by_semicolon() {
		new compliation_01().Init();
		String programString = "   const count = 11, sum = 52.5, char = 'c', str = \"q1234q\"; count = 11;  ";
		ArrayList<String> res = new compliation_01().split_by_semicolon(programString);
		if (res != null) {
			for (Object res1 : res) {
				System.out.println(res1);
			}
		}
	}
	
	public void test_type_judge() {
		String exp = "a = \"dfd\" ";
		String type = new compliation_01().type_judge(exp);
		System.out.println(type);
	}
	
	public static void test_classify_and_output() throws exception_01 {
		ArrayList<String> arrayList= new ArrayList<String>(); 
		arrayList.add("a = 'f'");
		arrayList.add("b = 9088");
		arrayList.add("c = \"dfdf4534  \"");
		new compliation_01().classify_and_output(arrayList);
//		String[] t = { "a = 'f'", "b = 9088", "c = \"dfdf4534  \"" };
//		new compliation_01().classify_and_output(t);
	}
	
	public void test_const_judge() throws Exception {
		String str = "const   a   =   'd'";
		boolean is_cosnt = new compliation_01().const_judge(str);
		System.out.println(is_cosnt);
	}
	
	public static void main(String args[]) {
		compliation_01.Init();
		try {
			test_classify_and_output();
		} catch (exception_01 e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
