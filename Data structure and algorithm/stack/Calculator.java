package stack;

import java.util.Stack;

public class Calculator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 表达式
		String expression = "12+2*2/2-1";
		// 创建数栈和符号栈
		ArrayStack2 numStack = new ArrayStack2(15);
		ArrayStack2 operStack = new ArrayStack2(15);
		// 需要一个遍历变量
		int index = 0;
		int num1 = 0, num2 = 0, res = 0;
		int oper = 0;
		// 将每次遍历得到的值存入到ch中
		char ch = ' ';
		// 字符串拼接
		String Splicing = "";
		while (true) {
			// 一次得到expression字符串的每一个字符
			ch = expression.substring(index, index + 1).charAt(0);
			// System.out.println(ch);
			// 如果是符号，就存入符号栈
			if (operStack.isOper(ch)) {
				// 符号栈不为空
				// System.out.println(operStack.isEmpty());
				if (!operStack.isEmpty()) {
					// peek() 方法用于查找在此堆栈顶部的对象，无需从堆栈中取出。

					if (operStack.Priority(ch) <= operStack.Priority(operStack.peek())) {
						num1 = numStack.pop();
						num2 = numStack.pop();
						oper = operStack.pop();
						// 中间结果
						res = numStack.ourCalculator(num1, num2, oper);
						// System.out.println(oper);
						// 把中间结果放入数栈
						numStack.push(res);
						// 把当前符号入符号栈
						operStack.push(ch);
					} else { // 当前符号优先级大于栈顶的优先级。
						operStack.push(ch);
					}
				} else {// 栈空
					operStack.push(ch);
				}
			} else { // 如果是数字 48ASCII 0
				// numStack.push(ch - 48);
				// 处理多位数时，用到拼接
				Splicing += ch;
				if (index == expression.length() - 1) {
					numStack.push(Integer.parseInt(Splicing));
				} else {
					// 判断是否位数字，如果是数据，index++，拼接，如果是符号，数字入栈
					// 当前字符 index , index+1
					// 取当前字符后一个字符 index+1， index+2
					if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
						numStack.push(Integer.parseInt(Splicing));
						// Splicing要清空
						Splicing = "";
					}
				}
			}
			index++;
			if (index >= expression.length())
				break;
		}

		// 获取最终的结果
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
		System.out.println("最终结果：" + numStack.pop());
	}

}

class ArrayStack2 {
	// 栈的大小 maxSize
	private int maxSize;
	// 需要一个数组模拟战
	private int[] stack;
	// top指针
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

	// 判断栈是否满
	public boolean isFull() {
		return top == maxSize - 1;
	}

	// 判断是否空栈
	public boolean isEmpty() {
		return top == -1;
	}

	// 入栈
	public void push(int value) {
		if (isFull()) {
			System.out.println("栈已经满了。");
			return;
		}
		top++;
		stack[top] = value;
	}

	// 出栈
	public int pop() {
		if (isEmpty()) {
			throw new RuntimeException("栈空不能取数据。");
		}
		int value = stack[top];
		top--;
		return value;
	}

	// 遍历栈
	public void showList() {
		if (isEmpty()) {
			throw new RuntimeException("栈空不能显示数据。");
		}
		for (int i = top; i >= 0; i--) {
			System.out.println("stack[" + i + "] = " + stack[i]);
		}
	}

	// 解决优先级的问题。
	// 优先级大返回1，否则0,不是符号-1
	public int Priority(int oper) {
		if (oper == '*' || oper == '/') {
			return 1;
		} else if (oper == '+' || oper == '-') {
			return 0;
		} else {// 不是我想要的符号
			return -1;
		}
	}

	// 判断是不是运算符
	public boolean isOper(char c) {
		return c == '+' || c == '-' || c == '*' || c == '/';
	}

	// 判断符号进行相应的操作。
	public int ourCalculator(int num1, int num2, int oper) {
		// 计算中间结果变量
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