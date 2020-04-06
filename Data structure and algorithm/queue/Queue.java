package queue;

public class Queue {

	public static void main(String[] args) {
		queueDemo queueDemo = new  queueDemo(6);
		queueDemo.showQueue();
		System.out.println("-------------------------");
		//�������1,2,3,4
		queueDemo.addQueue(1);
		queueDemo.addQueue(2);
		queueDemo.addQueue(3);
		queueDemo.addQueue(4);
		//��ʾ�����е�Ԫ��
		queueDemo.showQueue();
		System.out.println("-------------------------");
		//ȥ������Ԫ��
		System.out.println(queueDemo.getQueue());
		System.out.println(queueDemo.getQueue());
		System.out.println("-------------------------");
		//��ǰ��frontָ���ͷ��Ԫ��
		System.out.println(queueDemo.headQueue());
	}

}

//��������ģ��˳�����
class queueDemo{
	private int maxSize; //���е���󳤶�
	private int front; //ͷָ��
	private int real; //βָ��
	private int[] arr;
	
	public queueDemo(int maxSize) {
		// TODO Auto-generated constructor stub
		this.maxSize = maxSize;
		this.front = 0;
		this.real = 0;
		arr = new int[maxSize];
	}
	
	//�ж϶����Ƿ���
	public boolean isFull() {
		return real == maxSize - 1;
	}
	
	//�ж϶����Ƿ�Ϊ��
	public boolean isEmpty() {
		return front == real;
	}
	
	//���һ��Ԫ�ص�����
	public void addQueue(int value) {
		//�ж϶����Ƿ���
		if(isFull()) {
			System.out.println("������ Ԫ���������ܼ�������Ԫ��");
		}else {
			arr[real] = value;
			real ++;
		}
	}
	
	//�Ӷ�����ȡ��Ԫ��
	//һ��һ��ȡ������ͷȡ���Ƚ��ȳ�
	public int getQueue() {
		if(isEmpty()) {
			throw new RuntimeException("����Ϊ�գ�����ȥ������");
		}else {
			//��ͷ��ʼȡ��ȡ��һ����front��Ҫ������һλ��
			return arr[front++];
		}
	}
	
	//��ʾ����
	public void showQueue() {
		if(isEmpty()) {
			System.out.println("���пգ�û������");
		}else {
			for(int i = 0; i < arr.length; i++) {
				System.out.printf("arr[%d]=%d\n",i,arr[i]);
			}
		}
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

