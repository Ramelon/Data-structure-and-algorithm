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
		System.out.println("��ջ");
		System.out.println(stackDemo.pop());
		stackDemo.showList();
	}

}

class StackDemo{
	// ջ�Ĵ�С maxSize
	private int maxSize;
	// ��Ҫһ������ģ��ս
	private int[] stack;
	// topָ��
	private int top = -1;
	
	public StackDemo(int maxSize) {
		// TODO Auto-generated constructor stub
		this.maxSize = maxSize;
		stack = new int[maxSize];
	}
	
	// �ж�ջ�Ƿ���
	public boolean isFull() {
		return top == maxSize - 1;
	}
	
	// �ж��Ƿ��ջ
	public boolean isEmpty() {
		return top == - 1;
	}
	
	// ��ջ
	public void push(int value) {
		if(isFull()) {
			System.out.println("ջ�Ѿ����ˡ�");
			return;
		}
		top ++;
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
		if(isEmpty()) {
			throw new RuntimeException("ջ�ղ�����ʾ���ݡ�");
		}
		for(int i = top; i >= 0; i--) {
			System.out.println("stack[" + i + "] = " + stack[i]);
		}
	}
}