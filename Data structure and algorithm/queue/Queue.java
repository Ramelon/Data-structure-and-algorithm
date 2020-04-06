package queue;

public class Queue {

	public static void main(String[] args) {
		queueDemo queueDemo = new  queueDemo(6);
		queueDemo.showQueue();
		System.out.println("-------------------------");
		//添加数据1,2,3,4
		queueDemo.addQueue(1);
		queueDemo.addQueue(2);
		queueDemo.addQueue(3);
		queueDemo.addQueue(4);
		//显示队列中的元素
		queueDemo.showQueue();
		System.out.println("-------------------------");
		//去队列中元素
		System.out.println(queueDemo.getQueue());
		System.out.println(queueDemo.getQueue());
		System.out.println("-------------------------");
		//当前的front指向的头的元素
		System.out.println(queueDemo.headQueue());
	}

}

//用数组来模拟顺序队列
class queueDemo{
	private int maxSize; //队列的最大长度
	private int front; //头指针
	private int real; //尾指针
	private int[] arr;
	
	public queueDemo(int maxSize) {
		// TODO Auto-generated constructor stub
		this.maxSize = maxSize;
		this.front = 0;
		this.real = 0;
		arr = new int[maxSize];
	}
	
	//判断队列是否满
	public boolean isFull() {
		return real == maxSize - 1;
	}
	
	//判断队列是否为空
	public boolean isEmpty() {
		return front == real;
	}
	
	//添加一个元素到队列
	public void addQueue(int value) {
		//判断队列是否满
		if(isFull()) {
			System.out.println("队列内 元素已满不能继续加入元素");
		}else {
			arr[real] = value;
			real ++;
		}
	}
	
	//从队列中取出元素
	//一个一个取出，从头取，先进先出
	public int getQueue() {
		if(isEmpty()) {
			throw new RuntimeException("队列为空，不能去除数据");
		}else {
			//从头开始取，取出一个，front需要往后移一位。
			return arr[front++];
		}
	}
	
	//显示队列
	public void showQueue() {
		if(isEmpty()) {
			System.out.println("队列空，没有数据");
		}else {
			for(int i = 0; i < arr.length; i++) {
				System.out.printf("arr[%d]=%d\n",i,arr[i]);
			}
		}
	}
	
	//显示当前front指向的元素
	public int headQueue() {
		if(isEmpty()) {
			throw new RuntimeException("队列为空，不能去除数据");
		}else {
			//front指向的是当前节点，如果取出元素是front会直接+1，
			return arr[front];
		}
	}
}

