package queue;

public class CircleQueue {
	private static int num = 7;
	public static void main(String[] args) {
		CirclequeueDemo CirclequeueDemo = new CirclequeueDemo(6);
		//添加数据。
		for(int i = 1; i <= 5; i++) {
			CirclequeueDemo.addQueue(i);
		}
		//CirclequeueDemo.showQueue();
		//将num-1中的人，都从队列的前段移到队列的后端
		while(CirclequeueDemo.Size() > 1) {
			for( int i = 0; i < num % CirclequeueDemo.Size(); i++) {
				//把要取出的元素之前的所有元素添加到末尾
				CirclequeueDemo.addQueue(CirclequeueDemo.getQueue());
			}
			CirclequeueDemo.getQueue();
		}
		System.out.println("最后第" + CirclequeueDemo.getQueue() + "个小朋友赢得比赛");
//		// TODO Auto-generated method stub
//		CirclequeueDemo q = new CirclequeueDemo(5);
//		//添加元素
//		q.addQueue(1);
//		q.addQueue(2);
//		q.addQueue(3);
//		q.addQueue(4);
//		System.out.println("队列内容显示");
//		q.showQueue();
//		//取出一个元素
//		System.out.println("取出的元素为：" + q.getQueue());
//		System.out.println("取出一个元素后队列内容显示");
//		q.showQueue();
//		q.addQueue(5);
//		q.addQueue(6);
//		q.addQueue(7);
//		//显示队列
//		q.showQueue();
//		
	}
	
	
}

//用数组来模拟循环队列
class CirclequeueDemo{
	private int maxSize; //队列的最大长度
	private int front; //头指针
	private int rear; //尾指针
	private int[] arr;
	
	public CirclequeueDemo(int maxSize) {
		// TODO Auto-generated constructor stub
		this.maxSize = maxSize;
		this.front = 0;
		this.rear = 0;
		arr = new int[maxSize];
	}
	
	//判断队列是否满
	public boolean isFull() {
		return front == (rear + 1) % maxSize;
	}
	
	//判断队列是否为空
	public boolean isEmpty() {
		return front == rear;
	}
	
	//添加一个元素到队列
	public void addQueue(int value) {
		//判断队列是否满
		if(isFull()) {
			System.out.println("队列内 元素已满不能继续加入元素");
		}else {
			arr[rear] = value;
			rear = (rear + 1) % maxSize;
		}
	}
	
	//从队列中取出元素
	//一个一个取出，从头取，先进先出
	public int getQueue() {
		if(isEmpty()) {
			throw new RuntimeException("队列为空，不能去除数据");
		}else {
			//从头开始取，取出一个，front需要往后移一位。
			int value = arr[front];
			front = (front + 1) % maxSize;
			return value;
		}
	}
	
	//显示队列
	public void showQueue() {
		if(isEmpty()) {
			System.out.println("队列空，没有数据");
		}else {
			for(int i = front; i < front + Size(); i++) {
				System.out.printf("arr[%d]=%d\n",i % maxSize,arr[i % maxSize]);
			}
		}
	}
	
	//为了循环方便，我们需要知道队列中有用数的个数
	public int Size() {
		//本来有用个数 rear - front 爬数据会溢出， 取模maxSize 必须加上一个maxSize
		return (rear + maxSize - front) % maxSize;
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
