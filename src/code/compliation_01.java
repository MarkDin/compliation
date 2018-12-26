package code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

;

public class compliation_01 {

    // 定义错误
    static String ERRORS[] = {"It is not a cosntant declation statement! Please input a string again.",
            "Wrong! It is not a identifier.", "Wrong! There are more than one char in.",
            "Wrong! The integer cant't be started with '0'.", "There is no semicolon exists in"};
    static Map<String, String> my_exceptions = new HashMap<String, String>();// 定义异常
    // 定义常量数据类型
    final String CONSTANT_TYPES[] = {"unknowType", "integer", "char", "string", "float"};
    // 下面定义常数变量类型的正则表达式
    Pattern variable_name_pattern = Pattern.compile("^[_,$,a-z,A-Z][_,$,\\d,a-z,A-Z]*");
    Pattern integer_pattern = Pattern.compile("^[+,-]?[1-9]+\\d*");
    Pattern float_pattern = Pattern.compile("^[+,-]?\\d+\\.\\d+");
    Pattern char_pattern = Pattern.compile("^'[a-z]{1}'$");
    Pattern string_pattern = Pattern.compile("^\"[a-z]{2,}\"$");

    public static void Init() {
        my_exceptions.put("RESERVED_WORD__ERROR", ERRORS[0]);// 保留字const错误
        my_exceptions.put("IDENTIFIER_ERROR", ERRORS[1]);// 标识符定义错误
        my_exceptions.put("CHAR_BEYOND_ERROR", ERRORS[2]);// 字符定义错误
        my_exceptions.put("INTEGER_START_WITH_ZERO_ERROR", ERRORS[3]);// 整数定义错误
        my_exceptions.put("SEMICOLON_ERROR", ERRORS[4]);// 不存在分号错误
        // 预编译正则表达式
//		String integer_pattern = "^/d+";
//		String float_pattern = "^/d+/./d+";
//		String char_pattern = "[a-z]{1}";
//		String string_pattern = "[a-z]{2,}";
    }

    public static void main(String[] args) throws exception_01 {
        Init();
        compliation_01 t = new compliation_01();
        String program = "const count=10,12sum=81.5,char1=‘ff’,max=0016;";
        if (t.const_judge(program)) {
            int index = program.indexOf("t") + 1;
            program = program.substring(index, program.length()).trim();
        }
        System.out.println(program);
        ArrayList<String> exps = t.split_by_comma(program);
        t.classify_and_output(exps);
//		if(t.const_judge(program)) {
//			int index = program.indexOf("t") + 1;
//			program = program.substring(index, program.length()).trim();
//		}
//		System.out.println(program);
//		ArrayList<String> exps = t.split_by_comma(program);
//		for(String exp: exps) {
//			System.out.println(exp);
//		}
//		String test_str = "12sum";
//		System.out.println(t.variable_name_pattern.matcher(test_str).matches());
//		int $_34_ = 5;
    }

    public ArrayList<String> split_by_semicolon(String program) {
        // 输入：整个程序的string
        // 输出：被分号切割后的多个字符串集合
        program = program.trim();
        if (program.indexOf(";") == -1) {
            System.out.println(my_exceptions.get("SEMICOLON_ERROR"));
            return null;
        } else {
            String temp[] = program.split(";");
            ArrayList<String> res = new ArrayList<String>();
            for (String obj : temp) {
                res.add(obj.trim());
            }
            if (res.size() <= 1) {
                System.out.println("只有一个分号");
            }
            return res;
        }

    }

    /**
     *description TODO
     *@param: program
     *@return  java.util.ArrayList<java.lang.String>
     *@Exception
     *@创建人  DingKe
     *@创建时间  9:57 2018/12/26
     */
    public ArrayList<String> split_by_comma(String program) {
        program = program.trim();
        String temp[] = program.split(",");
        ArrayList<String> res = new ArrayList<String>();
        for (String obj : temp) {
            res.add(obj.trim());
        }
        return res;
    }

    public boolean const_judge(String program) throws exception_01 {
        // 输入：一个表达式的字符串
        // 功能: 判断整个程序开头是否是包含const关键字
        // 输出：true/false
        program = program.trim();
        if (program.startsWith("const")) {
            program = program.substring(program.indexOf("t") + 1, program.length());
            return true;
        } else {
            int index = program.indexOf("=");
            String object = program.substring(0, index).trim();// 取出第一个等号之前的字符串
            if (object.split(" ").length > 1 && !object.split(" ")[0].equals("const")) {
                throw new exception_01(my_exceptions.get("IDENTIFIER_ERROR"));

            }
            return false;
        }
//cofgfnst   a   =   'd'
    }

    public String type_judge(String right_part_of_exp) {
        // 输入：一个表达式的字符串
        // 输出：表达式等号右边的数据类型
        // 改进的地方是返回类型单独设置一个类

        // final String CONSTANT_TYPES[] = { "integer", "char", "chracter", "float" };
//		 Matcher matcher = pattern.matcher(source);
//	     return matcher.matches()
//		integer_pattern = "^/d+";
//		String float_pattern = "^/d+/./d+";
//		String char_pattern = "[a-z]{1}";
//		String string_pattern = "[a-z]{2,}";

        // 方法一:正则表达式匹配
        if (integer_pattern.matcher(right_part_of_exp).matches()) {
            return CONSTANT_TYPES[1];
        }
        if (char_pattern.matcher(right_part_of_exp).matches()) {
            return CONSTANT_TYPES[2];
        }
        if (string_pattern.matcher(right_part_of_exp).matches()) {
            return CONSTANT_TYPES[3];
        }
        if (float_pattern.matcher(right_part_of_exp).matches()) {
            return CONSTANT_TYPES[4];
        }
        return CONSTANT_TYPES[0]; // 表示未知类型

        // 方法二:直接判断

    }

    public String get_right_part_error(String right_part_of_exp) {
        // 输入:错误的表达式右边的字符串
        // 功能:找出右边字符串定义的错误类型
        // 输出 my_exceptions中对应的错误
        // 字符定义错误
        if (right_part_of_exp.charAt(0) == '\'' && right_part_of_exp.length() > 3) {
            System.out.println(my_exceptions.get("CHAR_BEYOND_ERROR"));
            return my_exceptions.get("CHAR_BEYOND_ERROR");
        } else if (right_part_of_exp.startsWith("0")) {
            System.out.println(my_exceptions.get("INTEGER_START_WITH_ZERO_ERROR"));
            return my_exceptions.get("INTEGER_START_WITH_ZERO_ERROR");
        } else {
            return null;
        }


    }

    public void classify_and_output(String exps[]) throws exception_01 {
        for (String exp : exps) {
            String temp[] = exp.split("=");
            if (temp.length < 2) { // 对表达式进行检查
                throw new exception_01("表达式格式错误");
            }
            String left = temp[0].trim();
            String right = temp[1].trim();
            String type = type_judge(right);

            System.out.println(left + "(" + type + ", " + right + ")");
        }
    }

    public boolean variable_name_judge(String left_part_of_exp) {
        if (variable_name_pattern.matcher(left_part_of_exp).matches()) {
            return true;
        }
        return false;
    }

    public void classify_and_output(ArrayList<String> exps) throws exception_01 {
        for (String exp : exps) {
            String temp[] = exp.split("=");
            if (temp.length < 2) { // 对表达式进行检查
                throw new exception_01("表达式格式错误");
            }
            String left = temp[0].trim();
            String right = temp[1].trim();
            String type = type_judge(right);

            boolean is_left_match = variable_name_judge(left);
            boolean is_right_match = !type.equals(CONSTANT_TYPES[0]); // CONSTANT_TYPES[0]代表不正确
            System.out.println(temp[0] + " ," + temp[1] + ", type = " + type + " ," + is_left_match + is_right_match + "------");
            if (is_left_match && is_right_match) {
                System.out.println(left + "(" + type + "," + right + ")");
            } else {
                String errors = "";
                if (!is_left_match) {
                    errors = errors + my_exceptions.get("IDENTIFIER_ERROR") + ",";
                }
                if (!is_right_match) {
                    errors = errors + get_right_part_error(right) + ",";
                }
                System.out.println(left + "(" + errors + ")");
            }

        }
    }

}

class DataType {
    String type_name;// 数据类型名
    String patter;// 正则表达式模式串
    Map<String, String> Exceptions = new HashMap<String, String>();// 该数据类型可能的异常

    public DataType(String type_name, String patter) {
        this.type_name = type_name;
        this.patter = patter;
    }
}