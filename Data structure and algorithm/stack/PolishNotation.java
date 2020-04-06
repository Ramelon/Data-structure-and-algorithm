package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolishNotation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String expression = "a+b*c+(d*e+f)*g";

		List<String> list = new ArrayList<String>();
		list = toinfixExpressionList(expression);
		System.out.println(list);
		list = parsePrefixExpression(list);
		System.out.println(list);
		// 得出结果
		int res = Calculator(list);
		System.out.println(res);
	}

	// 1.将中缀表达式转换成List。
	// infix 中缀 infixExpressionList => 中缀表达式列表
	public static List<String> toinfixExpressionList(String expression) {
		// 声明一个List来存放中缀表达式
		List<String> list = new ArrayList<String>();
		int index = 0;
		String Splicing; // 多位数拼接
		// 用来遍历每一个字符
		char ch;

		do {
			// 一个一个遍历字符
			ch = expression.charAt(index);
			// 非数字，则直接加入到list
			if (ch < 48 || ch > 57) {
				list.add("" + ch);
				index++;
			} else { // 数字
				Splicing = "";
				while (index < expression.length() && (ch = expression.charAt(index)) >= 48
						&& (ch = expression.charAt(index)) <= 57) {
					Splicing += ch;
					index++;
					// 为什么 ch = expression.charAt(index); 不写在while循环里，如果写了就会下标越界，自己思考一下。
				}
				list.add(Splicing);
			}

		} while (index < expression.length());
		return list;
	}

	// 2.将中缀List转换成前缀表达式。
	// Prefix 前缀 parsePrefixExpression
	public static List<String> parsePrefixExpression(List<String> list) {
		// 用来存储结果
		List<String> result = new ArrayList<String>();
		// 符号栈来存符号
		Stack<String> operStack = new Stack<String>();
		// 求前缀表达式
		// 从右往左遍历
		for (int i = list.size() - 1; i >= 0; i--) {
			String ch = list.get(i);
			if (ch.matches("\\d+")) { // 判断是否为数字，可以判断多个数字
				result.add(ch);
			} else if (ch.equals(")")) {
				operStack.add(ch);
			} else if (ch.equals("(")) {
				// 把符号栈里 括号里包含的符号存入result
				while (!operStack.peek().equals(")")) {
					result.add(operStack.pop());
				}
				operStack.pop(); // 让")"消失
			} else { // 其他字符 按照优先级来进栈出栈
				// 当栈为空 当前符号优先级小于等于 栈顶优先级 就 往 符号栈 出栈到到result
				// 优先级判断标准不一样 则得到的前缀表达式列表可能不相同 但是不影响最后结果 <= 或< 最后得到的结果是一样的。
				while (operStack.size() != 0 && priority.get(ch) <= priority.get(operStack.peek())) {
					result.add(operStack.pop());
				}
				// 当前符号还需要入符号栈。
				operStack.push(ch);
			}
		}

		// 此时符号栈还会剩下一个符号
		while (operStack.size() != 0) {
			result.add(operStack.pop());
		}
		// 一个包含前缀表达式的list
		return result;
	}

	// 3.将得到的前缀表达式求值。
	public static int Calculator(List<String> list) {
		Stack<String> stack = new Stack<String>();
		for (String item : list) {
			// 如果是数字，则直接进栈
			if (item.matches("\\d+")) {
				stack.push(item);
			} else {
				// 之前讲过的栈表达式 中缀
				// 要num2 在前 num2-num1 而前缀表达式倒过来的 num1-num2即可
				// 如果遇到符号，就要取出最近的两个数组，把栈中出栈两个数字进行运算
				int num1 = Integer.parseInt(stack.pop());
				int num2 = Integer.parseInt(stack.pop());
				int res = 0;
				if (item.equals("+")) {
					res = num1 + num2;
				} else if (item.equals("-")) {
					res = num1 - num2;
				} else if (item.equals("*")) {
					res = num1 * num2;
				} else if (item.equals("/")) {
					res = num1 / num2;
				} else {
					throw new RuntimeException("运算符错误，无法进行计算");
				}
				// 把中间结果进栈
				stack.push("" + res);
			}
		}
		// 最后栈中还会保留最后一个结果，所以要出栈
		return Integer.parseInt(stack.pop());
	}

}

// 定义一个优先级的类
class priority {
	private static int ADD = 1;
	private static int SUB = 1;
	private static int MUL = 2;
	private static int DIV = 2;

	public static int get(String oper) {
		int result = 0;
		switch (oper) {
		case "+":
			result = ADD;
			break;
		case "-":
			result = SUB;
			break;
		case "*":
			result = MUL;
			break;
		case "/":
			result = DIV;
			break;
		default:
			// System.out.println("不包含该运算符的优先级" + oper);
			break;
		}
		return result;
	}
}