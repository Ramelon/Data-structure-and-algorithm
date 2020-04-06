package stack;

import java.util.Stack;

public class ArrayStcak {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StackDemo stackDemo = new StackDemo(5);
		stackDemo.push(1);
		stackDemo.push(2);
		stackDemo.push(3);
		stackDemo.push(4);
		stackDemo.push(5);
		stackDemo.showList();
		System.out.println("出栈");
		System.out.println(stackDemo.pop());
		stackDemo.showList();
	}

}

class StackDemo{
	// 栈的大小 maxSize
	private int maxSize;
	// 需要一个数组模拟战
	private int[] stack;
	// top指针
	private int top = -1;
	
	public StackDemo(int maxSize) {
		// TODO Auto-generated constructor stub
		this.maxSize = maxSize;
		stack = new int[maxSize];
	}
	
	// 判断栈是否满
	public boolean isFull() {
		return top == maxSize - 1;
	}
	
	// 判断是否空栈
	public boolean isEmpty() {
		return top == - 1;
	}
	
	// 入栈
	public void push(int value) {
		if(isFull()) {
			System.out.println("栈已经满了。");
			return;
		}
		top ++;
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
		if(isEmpty()) {
			throw new RuntimeException("栈空不能显示数据。");
		}
		for(int i = top; i >= 0; i--) {
			System.out.println("stack[" + i + "] = " + stack[i]);
		}
	}
}