package linkedlist;

public class DoubleLinkedList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DoubleLinkedListDemo doubleLinkedListDemo = new DoubleLinkedListDemo();
		// ��������ڵ�
		HeroNode2 node1 = new HeroNode2(1, "����");
		HeroNode2 node2 = new HeroNode2(2, "�ŷ�");
		HeroNode2 node3 = new HeroNode2(3, "����");
		HeroNode2 node4 = new HeroNode2(4, "����");
		HeroNode2 node5 = new HeroNode2(5, "����");
		// ��ӽڵ�
//		doubleLinkedListDemo.addHeroNode(node1);
//		doubleLinkedListDemo.addHeroNode(node2);
//		doubleLinkedListDemo.addHeroNode(node3);
//		doubleLinkedListDemo.addHeroNode(node4);
//		doubleLinkedListDemo.addHeroNode(node5);
		doubleLinkedListDemo.addReorder(node3);
		doubleLinkedListDemo.addReorder(node2);
		doubleLinkedListDemo.addReorder(node1);
		doubleLinkedListDemo.addReorder(node5);
		doubleLinkedListDemo.addReorder(node4);
		doubleLinkedListDemo.showLinkedList(doubleLinkedListDemo.getHead());
//		doubleLinkedListDemo.updataLinkedList(new HeroNode2(5, "�ĺ"));
//		System.out.println("�޸Ĺ��������");
//		doubleLinkedListDemo.showLinkedList(doubleLinkedListDemo.getHead());
//		//ɾ�������
		doubleLinkedListDemo.delLinkedList(5);
		System.out.println("��ʾɾ���������");
		doubleLinkedListDemo.showLinkedList(doubleLinkedListDemo.getHead());
	}

}

class DoubleLinkedListDemo{
	private HeroNode2 head = new HeroNode2(0, "");

	// ��ͷ����������������
	// �и���������ȡͷ��㡣
	public HeroNode2 getHead() {
		return head;
	}

	// ��ӽڵ㡣
	public void addHeroNode(HeroNode2 heroNode) {
		// ��Ϊhead�ڵ��ǲ��ܶ��ģ����������Ҫһ�������ڵ�temp
		HeroNode2 temp = head;
		// ��������Ǵ�����������ӣ�
		// ���ȷ������λ�ã�ֻ�ܣ��ڵ�.next ����������ȥ��ֱ��ֵΪnull���ҵ�λ�á�
		while (true) {
			if (temp.next == null) {
				break;
			}
			temp = temp.next;
		}
		temp.next = heroNode;
		heroNode.prev = temp;
	}

	// ��ʾ����
	public void showLinkedList(HeroNode2 head) {
		// �ж������Ƿ�Ϊ��
		if (head.next == null) {
			System.out.println("����Ϊ��");
			return;
		}
		// ��Ϊ���ǵ�ͷ����ǲ��ܶ��ģ�������Ҫһ����������������
		HeroNode2 temp = head.next;

		while (true) {
			if (temp == null)
				break;
			//System.out.println(temp.prev);
			System.out.println(temp);
//			System.out.println(temp.next);
//			System.out.println("------------------------");
			temp = temp.next;
		}
	}

	// 1 ���� -> 2 �ŷ� -> 3 ����
	// if(temp.id = heroNode.id) temp.name = heroNode.name
	// �޸�����ڵ�����
	public void updataLinkedList(HeroNode2 heroNode) {
		// �ж������Ƿ�Ϊ��
		if (head.next == null) {
			System.out.println("����Ϊ��");
			return;
		}
		// ����һ�������ڵ�
		HeroNode2 temp = head.next;
		boolean flag = false;
		// �Ƿ��ҵ���Ӧ�ڵ�
		while (true) {
			// ����ҵ���
			if (temp.id == heroNode.id) {

				flag = true;
				break;
			}
			// ��ֹ��ָ�뱨��
			if (temp.next == null) {
				break;
			}
			temp = temp.next; // temp����
		}

		if (flag) {
			temp.name = heroNode.name;
		} else {
			System.out.println("û���ҵ�idΪ" + heroNode.id);
		}
	}

	// ɾ���ڵ�
	public void delLinkedList(int id) {
		HeroNode2 temp = head.next;
//		while(true) {
//			System.out.println(temp);
//			if(temp.next == null) {
//				break;
//			}
//			temp = temp.next;
//		}
		boolean flag = false;
		// �ҵ���ɾ���ڵ��ǰһ���ڵ㡣
		while (true) {
			// �ҵ�
			if (temp.id == id) {
				flag = true;
				break;
			}
			// �Ѿ������������Ҫ�˳�ѭ��
			if (temp.next == null) {
				break;
			}
			temp = temp.next;
		}
		if (flag) {
			// Ϊʲô��temp.next.next
//			temp.next = temp.next.next;
			temp.prev.next = temp.next;
			//�����ָ��
			if(temp.next != null) {
				temp.next.prev = temp.prev;
			}
		} else {
			System.out.println("û���ҵ�idΪ" + id);
		}
	}

	public void addReorder(HeroNode2 heroNode) {
		//����ͷ���
		HeroNode2 temp = head;
		//������ʱ����ָ��
		HeroNode2 current = new HeroNode2(0, "");
		boolean flag = false;
		while (true) {
			// �������
			if (temp.next == null) {
				flag = true;
				break;
			} else if (temp.next.id > heroNode.id) {
				flag = true;
				break;
			} else if (temp.next.id == heroNode.id) {
				break;
			}
			temp = temp.next;
		}
		if (flag) {

			if (temp.next != null) {
				//ֱ��д
				heroNode.next = temp.next;
				temp.next = heroNode;
				heroNode.prev = temp;
				heroNode.next.prev = heroNode;
				
				//�����м丨��ָ��
//				current.next = temp.next;
//				temp.next = heroNode;
//				heroNode.prev = temp;
//				heroNode.next = current.next;
//				current.next.prev = heroNode;
			}else { //��һ�����ݣ���ֱ�Ӳ���
				temp.next = heroNode;
				heroNode.prev = temp;
			}
		} else {
			// id�ظ�
			System.out.println("id" + heroNode.id + "�ظ�");
		}
	}
}

class HeroNode2 {
	public int id;
	public String name;
	public HeroNode2 next;// ����ָ����һ���ڵ�
	public HeroNode2 prev;// ����ָ��ǰһ���ڵ�
	
	public HeroNode2(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "HeroNode2 [id=" + id + ", name=" + name + "]";
	}

}