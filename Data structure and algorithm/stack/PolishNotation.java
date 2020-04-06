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
		// �ó����
		int res = Calculator(list);
		System.out.println(res);
	}

	// 1.����׺���ʽת����List��
	// infix ��׺ infixExpressionList => ��׺���ʽ�б�
	public static List<String> toinfixExpressionList(String expression) {
		// ����һ��List�������׺���ʽ
		List<String> list = new ArrayList<String>();
		int index = 0;
		String Splicing; // ��λ��ƴ��
		// ��������ÿһ���ַ�
		char ch;

		do {
			// һ��һ�������ַ�
			ch = expression.charAt(index);
			// �����֣���ֱ�Ӽ��뵽list
			if (ch < 48 || ch > 57) {
				list.add("" + ch);
				index++;
			} else { // ����
				Splicing = "";
				while (index < expression.length() && (ch = expression.charAt(index)) >= 48
						&& (ch = expression.charAt(index)) <= 57) {
					Splicing += ch;
					index++;
					// Ϊʲô ch = expression.charAt(index); ��д��whileѭ������д�˾ͻ��±�Խ�磬�Լ�˼��һ�¡�
				}
				list.add(Splicing);
			}

		} while (index < expression.length());
		return list;
	}

	// 2.����׺Listת����ǰ׺���ʽ��
	// Prefix ǰ׺ parsePrefixExpression
	public static List<String> parsePrefixExpression(List<String> list) {
		// �����洢���
		List<String> result = new ArrayList<String>();
		// ����ջ�������
		Stack<String> operStack = new Stack<String>();
		// ��ǰ׺���ʽ
		// �����������
		for (int i = list.size() - 1; i >= 0; i--) {
			String ch = list.get(i);
			if (ch.matches("\\d+")) { // �ж��Ƿ�Ϊ���֣������ж϶������
				result.add(ch);
			} else if (ch.equals(")")) {
				operStack.add(ch);
			} else if (ch.equals("(")) {
				// �ѷ���ջ�� ����������ķ��Ŵ���result
				while (!operStack.peek().equals(")")) {
					result.add(operStack.pop());
				}
				operStack.pop(); // ��")"��ʧ
			} else { // �����ַ� �������ȼ�����ջ��ջ
				// ��ջΪ�� ��ǰ�������ȼ�С�ڵ��� ջ�����ȼ� �� �� ����ջ ��ջ����result
				// ���ȼ��жϱ�׼��һ�� ��õ���ǰ׺���ʽ�б���ܲ���ͬ ���ǲ�Ӱ������� <= ��< ���õ��Ľ����һ���ġ�
				while (operStack.size() != 0 && priority.get(ch) <= priority.get(operStack.peek())) {
					result.add(operStack.pop());
				}
				// ��ǰ���Ż���Ҫ�����ջ��
				operStack.push(ch);
			}
		}

		// ��ʱ����ջ����ʣ��һ������
		while (operStack.size() != 0) {
			result.add(operStack.pop());
		}
		// һ������ǰ׺���ʽ��list
		return result;
	}

	// 3.���õ���ǰ׺���ʽ��ֵ��
	public static int Calculator(List<String> list) {
		Stack<String> stack = new Stack<String>();
		for (String item : list) {
			// ��������֣���ֱ�ӽ�ջ
			if (item.matches("\\d+")) {
				stack.push(item);
			} else {
				// ֮ǰ������ջ���ʽ ��׺
				// Ҫnum2 ��ǰ num2-num1 ��ǰ׺���ʽ�������� num1-num2����
				// ����������ţ���Ҫȡ��������������飬��ջ�г�ջ�������ֽ�������
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
					throw new RuntimeException("����������޷����м���");
				}
				// ���м�����ջ
				stack.push("" + res);
			}
		}
		// ���ջ�л��ᱣ�����һ�����������Ҫ��ջ
		return Integer.parseInt(stack.pop());
	}

}

// ����һ�����ȼ�����
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
			// System.out.println("������������������ȼ�" + oper);
			break;
		}
		return result;
	}
}