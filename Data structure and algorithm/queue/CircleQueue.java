package queue;

public class CircleQueue {
	private static int num = 7;
	public static void main(String[] args) {
		CirclequeueDemo CirclequeueDemo = new CirclequeueDemo(6);
		//������ݡ�
		for(int i = 1; i <= 5; i++) {
			CirclequeueDemo.addQueue(i);
		}
		//CirclequeueDemo.showQueue();
		//��num-1�е��ˣ����Ӷ��е�ǰ���Ƶ����еĺ��
		while(CirclequeueDemo.Size() > 1) {
			for( int i = 0; i < num % CirclequeueDemo.Size(); i++) {
				//��Ҫȡ����Ԫ��֮ǰ������Ԫ����ӵ�ĩβ
				CirclequeueDemo.addQueue(CirclequeueDemo.getQueue());
			}
			CirclequeueDemo.getQueue();
		}
		System.out.println("����" + CirclequeueDemo.getQueue() + "��С����Ӯ�ñ���");
//		// TODO Auto-generated method stub
//		CirclequeueDemo q = new CirclequeueDemo(5);
//		//���Ԫ��
//		q.addQueue(1);
//		q.addQueue(2);
//		q.addQueue(3);
//		q.addQueue(4);
//		System.out.println("����������ʾ");
//		q.showQueue();
//		//ȡ��һ��Ԫ��
//		System.out.println("ȡ����Ԫ��Ϊ��" + q.getQueue());
//		System.out.println("ȡ��һ��Ԫ�غ����������ʾ");
//		q.showQueue();
//		q.addQueue(5);
//		q.addQueue(6);
//		q.addQueue(7);
//		//��ʾ����
//		q.showQueue();
//		
	}
	
	
}

//��������ģ��ѭ������
class CirclequeueDemo{
	private int maxSize; //���е���󳤶�
	private int front; //ͷָ��
	private int rear; //βָ��
	private int[] arr;
	
	public CirclequeueDemo(int maxSize) {
		// TODO Auto-generated constructor stub
		this.maxSize = maxSize;
		this.front = 0;
		this.rear = 0;
		arr = new int[maxSize];
	}
	
	//�ж϶����Ƿ���
	public boolean isFull() {
		return front == (rear + 1) % maxSize;
	}
	
	//�ж϶����Ƿ�Ϊ��
	public boolean isEmpty() {
		return front == rear;
	}
	
	//���һ��Ԫ�ص�����
	public void addQueue(int value) {
		//�ж϶����Ƿ���
		if(isFull()) {
			System.out.println("������ Ԫ���������ܼ�������Ԫ��");
		}else {
			arr[rear] = value;
			rear = (rear + 1) % maxSize;
		}
	}
	
	//�Ӷ�����ȡ��Ԫ��
	//һ��һ��ȡ������ͷȡ���Ƚ��ȳ�
	public int getQueue() {
		if(isEmpty()) {
			throw new RuntimeException("����Ϊ�գ�����ȥ������");
		}else {
			//��ͷ��ʼȡ��ȡ��һ����front��Ҫ������һλ��
			int value = arr[front];
			front = (front + 1) % maxSize;
			return value;
		}
	}
	
	//��ʾ����
	public void showQueue() {
		if(isEmpty()) {
			System.out.println("���пգ�û������");
		}else {
			for(int i = front; i < front + Size(); i++) {
				System.out.printf("arr[%d]=%d\n",i % maxSize,arr[i % maxSize]);
			}
		}
	}
	
	//Ϊ��ѭ�����㣬������Ҫ֪���������������ĸ���
	public int Size() {
		//�������ø��� rear - front �����ݻ������ ȡģmaxSize �������һ��maxSize
		return (rear + maxSize - front) % maxSize;
	}
	
	//��ʾ��ǰfrontָ���Ԫ��
	public int headQueue() {
		if(isEmpty()) {
			throw new RuntimeException("����Ϊ�գ�����ȥ������");
		}else {
			//frontָ����ǵ�ǰ�ڵ㣬���ȡ��Ԫ����front��ֱ��+1��
			return arr[front];
		}
	}
}
