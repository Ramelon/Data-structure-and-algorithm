package stack;

import java.util.Stack;

public class Calculator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ���ʽ
		String expression = "12+2*2/2-1";
		// ������ջ�ͷ���ջ
		ArrayStack2 numStack = new ArrayStack2(15);
		ArrayStack2 operStack = new ArrayStack2(15);
		// ��Ҫһ����������
		int index = 0;
		int num1 = 0, num2 = 0, res = 0;
		int oper = 0;
		// ��ÿ�α����õ���ֵ���뵽ch��
		char ch = ' ';
		// �ַ���ƴ��
		String Splicing = "";
		while (true) {
			// һ�εõ�expression�ַ�����ÿһ���ַ�
			ch = expression.substring(index, index + 1).charAt(0);
			// System.out.println(ch);
			// ����Ƿ��ţ��ʹ������ջ
			if (operStack.isOper(ch)) {
				// ����ջ��Ϊ��
				// System.out.println(operStack.isEmpty());
				if (!operStack.isEmpty()) {
					// peek() �������ڲ����ڴ˶�ջ�����Ķ�������Ӷ�ջ��ȡ����

					if (operStack.Priority(ch) <= operStack.Priority(operStack.peek())) {
						num1 = numStack.pop();
						num2 = numStack.pop();
						oper = operStack.pop();
						// �м���
						res = numStack.ourCalculator(num1, num2, oper);
						// System.out.println(oper);
						// ���м���������ջ
						numStack.push(res);
						// �ѵ�ǰ���������ջ
						operStack.push(ch);
					} else { // ��ǰ�������ȼ�����ջ�������ȼ���
						operStack.push(ch);
					}
				} else {// ջ��
					operStack.push(ch);
				}
			} else { // ��������� 48ASCII 0
				// numStack.push(ch - 48);
				// �����λ��ʱ���õ�ƴ��
				Splicing += ch;
				if (index == expression.length() - 1) {
					numStack.push(Integer.parseInt(Splicing));
				} else {
					// �ж��Ƿ�λ���֣���������ݣ�index++��ƴ�ӣ�����Ƿ��ţ�������ջ
					// ��ǰ�ַ� index , index+1
					// ȡ��ǰ�ַ���һ���ַ� index+1�� index+2
					if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
						numStack.push(Integer.parseInt(Splicing));
						// SplicingҪ���
						Splicing = "";
					}
				}
			}
			index++;
			if (index >= expression.length())
				break;
		}

		// ��ȡ���յĽ��
		while (true) {
			if (operStack.isEmpty()) {
				break;
			}
			num1 = numStack.pop();
			num2 = numStack.pop();
			oper = operStack.pop();
			res = numStack.ourCalculator(num1, num2, oper);
			numStack.push(res);
		}
		System.out.println("���ս����" + numStack.pop());
	}

}

class ArrayStack2 {
	// ջ�Ĵ�С maxSize
	private int maxSize;
	// ��Ҫһ������ģ��ս
	private int[] stack;
	// topָ��
	private int top = -1;

	public ArrayStack2(int maxSize) {
		// TODO Auto-generated constructor stub
		this.maxSize = maxSize;
		stack = new int[maxSize];
	}

	public int peek() {
		// TODO Auto-generated method stub
		return stack[top];
	}

	// �ж�ջ�Ƿ���
	public boolean isFull() {
		return top == maxSize - 1;
	}

	// �ж��Ƿ��ջ
	public boolean isEmpty() {
		return top == -1;
	}

	// ��ջ
	public void push(int value) {
		if (isFull()) {
			System.out.println("ջ�Ѿ����ˡ�");
			return;
		}
		top++;
		stack[top] = value;
	}

	// ��ջ
	public int pop() {
		if (isEmpty()) {
			throw new RuntimeException("ջ�ղ���ȡ���ݡ�");
		}
		int value = stack[top];
		top--;
		return value;
	}

	// ����ջ
	public void showList() {
		if (isEmpty()) {
			throw new RuntimeException("ջ�ղ�����ʾ���ݡ�");
		}
		for (int i = top; i >= 0; i--) {
			System.out.println("stack[" + i + "] = " + stack[i]);
		}
	}

	// ������ȼ������⡣
	// ���ȼ��󷵻�1������0,���Ƿ���-1
	public int Priority(int oper) {
		if (oper == '*' || oper == '/') {
			return 1;
		} else if (oper == '+' || oper == '-') {
			return 0;
		} else {// ��������Ҫ�ķ���
			return -1;
		}
	}

	// �ж��ǲ��������
	public boolean isOper(char c) {
		return c == '+' || c == '-' || c == '*' || c == '/';
	}

	// �жϷ��Ž�����Ӧ�Ĳ�����
	public int ourCalculator(int num1, int num2, int oper) {
		// �����м�������
		int res = 0;
		switch (oper) {
		case '+':
			res = num2 + num1;
			break;
		case '-':
			res = num2 - num1;
			break;
		case '*':
			res = num2 * num1;
			break;
		case '/':
			res = num2 / num1;
			break;
		default:
			break;
		}
		return res;
	}
}