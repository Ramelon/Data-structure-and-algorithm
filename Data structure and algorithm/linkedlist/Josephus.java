package linkedlist;

public class Josephus {
	public static void main(String[] args) {
		CircleLinkedList circleLinkedList = new CircleLinkedList();
		circleLinkedList.addPeopleLinkedList(5);
		circleLinkedList.showPeople();
		circleLinkedList.WinPeople(1, 3, 5);
	}
}

// �����ڵ�
class CircleLinkedList {
	
	// ����һ���ڵ�
	private People first = null;

	// ȷ����ӵ�����,Ȼ�󴴽���������
	public void addPeopleLinkedList(int nums) {
		if (nums <= 0) {
			System.out.println("nums��ֵ����");
			return;
		}

		// ���帨��ָ�룬
		People tempPeople = null;
		
		for(int i = 1; i <= nums; i++) {
			People people = new People(i);
			if( i == 1) { //��һ����
				first = people;
				// ��ָ�����Լ�
				first.setNext(first);
				// ֵ����ǰ����
				tempPeople = first;
			}else{ //��Ϊfirst��ָ��ͷ,people���ָ��ͷ��������һ��ѭ������
				tempPeople.setNext(people);
				//setNext this.next = next
				people.setNext(first);
				tempPeople = people;
			}
		}
	}
	
	//������ʾ����
	public void showPeople(){
		People tempPeople = first;
		while(true) {
			System.out.println("people:" + tempPeople.getId());
			//�Ѿ���ɱ�����һȦ�����
			if(tempPeople.getNext() == first) {
				break;
			}
			//����
			tempPeople = tempPeople.getNext();
		}
	}

	// ������⣬Լɪ�����⡣
	/**
	 * 
	 * @param start �����￪ʼ
	 * @param count �����ٴ�
	 * @param nums  �ж����˲μ� ��������У����
	 */ 
	public void WinPeople(int start, int count, int nums) {
		//�Դ������Ĳ�������У��
		if(start < 1 || count > nums) {
			System.out.println("��������");
			return;
		}
		// ����
		People temp = first;
		// ����tempָ�룬�൱������Ȼ����temp��������
		while(true) {
			if(temp.getNext() == first) {
				break;
			}
			//���� 
			temp = temp.getNext();
		}
	
		// first, temp ָ��Ҫͬʱ����start-1��
		// ���Ĵ����￪ʼ��׼������ȫ�����꣬
		for(int i = 0; i < start - 1; i++) {
			first = first.getNext();
			temp = temp.getNext();
		}
		
		//��������Ҫ��Ȧ
		while(true) {
			if(temp == first) {
				break;
			}
			// ִ��count-1��
			for(int j = 0; j < count - 1; j++) {
				first = first.getNext();
				temp = temp.getNext();
			}
			System.out.println(first.getId() + "�ų�Ȧ��");
			//firstָ�����
			first = first.getNext();
			// temp�����ƣ���ֱ��������һ���ڵ�ֱ��ָ��first��
			// tempֱ�ӻص����һ��
			// û�ι���һ��ѭ�������൱�ڳ�ֵ����
			temp.setNext(first);
		}
		// ��ʣ�����һ���˳�ѭ��

		System.out.println(first.getId() + "����������");
	}
}

// �ڵ���
class People {
	private int id;
	private People next;

	public People(int id) {
		// TODO Auto-generated constructor stub
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public People getNext() {
		return next;
	}

	public void setNext(People next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "People [id=" + id + "]";
	}

}
