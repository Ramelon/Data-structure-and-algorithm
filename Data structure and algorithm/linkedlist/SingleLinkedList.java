package linkedlist;

import java.util.Stack;

public class SingleLinkedList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SingleLinkedListDemo singleLinkedListDemo = new SingleLinkedListDemo();
		// ��������ڵ�
		HeroNode node1 = new HeroNode(1, "����");
		HeroNode node2 = new HeroNode(2, "�ŷ�");
		HeroNode node3 = new HeroNode(3, "����");
		HeroNode node4 = new HeroNode(4, "����");
		HeroNode node5 = new HeroNode(5, "����");
		// ��ӽڵ�
		singleLinkedListDemo.addReorder(node5);
		singleLinkedListDemo.addReorder(node1);
		singleLinkedListDemo.addReorder(node3);
		singleLinkedListDemo.addReorder(node2);
		singleLinkedListDemo.addReorder(node4);
		// singleLinkedListDemo.addHeroNode(node1);
		// singleLinkedListDemo.addHeroNode(node2);
		// singleLinkedListDemo.addHeroNode(node3);
		// singleLinkedListDemo.addHeroNode(node4);
		// singleLinkedListDemo.addHeroNode(node5);
		// ��ʾ�ڵ�
		singleLinkedListDemo.showLinkedList(singleLinkedListDemo.getHead());
		System.out.println("�ڵ���Ч����Ϊ��" +  getLinkedLength(singleLinkedListDemo.getHead()));
		System.out.println("�����������ڵ�Ϊ��" + findIndexReciprocal(singleLinkedListDemo.getHead(), 3));
		// �޸�����Ϊ��ʩ
		// System.out.println("-------------------------------");
		// singleLinkedListDemo.updataLinkedList(new HeroNode(5, "��ʩ"));
		// singleLinkedListDemo.showLinkedList(singleLinkedListDemo.getHead());
		// //�޸�����Ϊ��ʩ
		// System.out.println("-------------------------------");
		// singleLinkedListDemo.delLinkedList(5);
		// singleLinkedListDemo.showLinkedList(singleLinkedListDemo.getHead());
		//��ת\
		System.out.println("----------------------");
		LinkedReverseDoStack(singleLinkedListDemo.getHead());
		//singleLinkedListDemo.showLinkedList(singleLinkedListDemo.getHead());
		//�½�һ���ڵ�
		SingleLinkedListDemo singleLinkedListDemo2 = new SingleLinkedListDemo();
		HeroNode node6 = new HeroNode(6, "��");
		HeroNode node7 = new HeroNode(7, "����");
		singleLinkedListDemo.addReorder(node6);
		singleLinkedListDemo.addReorder(node7);
		System.out.println("�ϲ��������");
		mergeLinked(singleLinkedListDemo.getHead(), singleLinkedListDemo2.getHead());
	}

	// ��Ϊ������������ڵ�ĸ������漰����������������Ҫ��һ��ͷ������
	public static int getLinkedLength(HeroNode head) {
		// �ж������Ƿ�Ϊ�գ�ͷ��㲻������
		if (head.next == null)
			return 0;
		HeroNode temp = head.next;
		int length = 0;
		// Ϊʲô��temp != null��������temp.next != null
		// ��Ϊ���Ѿ�temp = head.next;��ʱtemp�Ѿ�ָ�����Ľڵ�
		while (temp != null) {
			length++;
			temp = temp.next;
		}
		return length;
	}

	// ���ҵ������е����ĵ�K���ڵ㡣
	public static HeroNode findIndexReciprocal(HeroNode head, int index) {
		//�ж������Ƿ�Ϊ��
		if(head.next == null){
			return null;
		}
		int length = getLinkedLength(head);
		//�ж�index �Ƿ�Ϸ�
		if(index > length || index <= 0) {
			return null;
		}
		//��ʱ����
		HeroNode temp = head.next;
		
		for(int i = 0; i < length - index; i++) {
			temp = temp.next;
		}
		//���ؽڵ�
		return temp;
	}
	
	//��������ת
	public static void LinkedReverse(HeroNode head) {
		//�ж������Ƿ�Ϊ��,������Ч�ڵ�Ϊ1 ���÷�ת
		if(head.next == null || head.next.next == null){
			return;
		}
		//���帨��ָ�����
		HeroNode temp = head.next;
		HeroNode next = null;
		//�½�һ��ָ��ǰ�ڵ��reverseHead
		HeroNode reverseHead = new HeroNode(0, "");
				
		// ����ԭ���Ľڵ㣬ÿ�α�����ȡ��������reverseHead����ǰ�档
		while(temp != null) {
			//��ʱ���浱ǰ�ڵ����һ���ڵ㡣
			next = temp.next;
			// ������ָ��reverseHead��������ǰ�� �ҵ�һ��temp.next ��null
			temp.next = reverseHead.next; // �ڶ���  ��ǰ HeroNode [id=2, name=�ŷ�] ����> �ҵ�һ�� HeroNode [id=1, name=����]
			// ָ��ǰ�ڵ㣬��Ȼ�õ��ǻ���null
			reverseHead.next = temp; //�ҵ�һ�� HeroNode [id=1, name=����] �� reverseHead �ڶ��� HeroNode [id=2, name=�ŷ�] ����>�ҵ�һ�� HeroNode [id=1, name=����]
			// ���� 1 2 3 4 5
			temp = next;
		}
		// reverseHead�����������Ƿ�ת�������������headָ��reverseHead���ǵõ�һ����ת������
		head.next = reverseHead.next;
	}
	
	// ����ջ���������
	public static void LinkedReverseDoStack(HeroNode head) {
		if(head.next == null) {
			return;
		}
		Stack<HeroNode> stack = new Stack<HeroNode>();
		HeroNode temp = head.next;
		while(temp != null) {
			stack.push(temp);
			temp = temp.next;
		}
		while(stack.size() > 0) {
			System.out.println(stack.pop());
		}
	}
	
	// ��������ϲ�
	public static void mergeLinked(HeroNode head1, HeroNode head2) {
		//����ָ�����
		HeroNode temp1 = head1.next;
		HeroNode temp2 = head2.next;
		
		// ����һ���м���ʱ���������ϲ�
		HeroNode head3 = new HeroNode(0, "");
		HeroNode temp3 = head3;
		
		//�ϲ� ������� 
		//temp1 ��ʱ�������� temp2 û������ �Ͱ�temp1�����ݼ�����ʱ����
		//temp2 ��ʱ�������� temp1 û������ �Ͱ�temp2�����ݼ�����ʱ����
		//����������
		while(temp1 != null || temp2 != null) {
			if(temp1 != null && temp2 == null) {
				temp3.next = temp1;
				temp1 = temp1.next;
			}else if(temp2 != null && temp1 == null) {
				temp3.next = temp2;
				temp2 = temp2.next;
			}else {
				if(temp1.id < temp2.id) {
					temp3.next = temp1;
					temp1 = temp1.next;
				}else {
					temp3.next = temp2;
					temp2 = temp2.next;
				}
			}
			//����
			temp3 = temp3.next;
		}
		SingleLinkedListDemo singleLinkedListDemo = new SingleLinkedListDemo();
		singleLinkedListDemo.showLinkedList(head3);
	}
}

class SingleLinkedListDemo {
	private HeroNode head = new HeroNode(0, "");

	// ��ͷ����������������
	// �и���������ȡͷ��㡣
	public HeroNode getHead() {
		return head;
	}

	// ��ӽڵ㡣
	public void addHeroNode(HeroNode heroNode) {
		// ��Ϊhead�ڵ��ǲ��ܶ��ģ����������Ҫһ�������ڵ�temp
		HeroNode temp = head;
		// ��������Ǵ�����������ӣ�
		// ���ȷ������λ�ã�ֻ�ܣ��ڵ�.next ����������ȥ��ֱ��ֵΪnull���ҵ�λ�á�
		while (true) {
			if (temp.next == null) {
				break;
			}
			temp = temp.next;
		}
		temp.next = heroNode;
	}

	// ��ʾ����
	public void showLinkedList(HeroNode head) {
		// �ж������Ƿ�Ϊ��
		if (head.next == null) {
			System.out.println("����Ϊ��");
			return;
		}
		// ��Ϊ���ǵ�ͷ����ǲ��ܶ��ģ�������Ҫһ����������������
		HeroNode temp = head.next;

		while (true) {
			if (temp == null)
				break;
			System.out.println(temp);
			temp = temp.next;
		}
	}

	// 1 ���� -> 2 �ŷ� -> 3 ����
	// if(temp.id = heroNode.id) temp.name = heroNode.name
	// �޸�����ڵ�����
	public void updataLinkedList(HeroNode heroNode) {
		// �ж������Ƿ�Ϊ��
		if (head.next == null) {
			System.out.println("����Ϊ��");
			return;
		}
		// ����һ�������ڵ�
		HeroNode temp = head.next;
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
		HeroNode temp = head;
		boolean flag = false;
		// �ҵ���ɾ���ڵ��ǰһ���ڵ㡣
		while (true) {
			// �Ѿ������������Ҫ�˳�ѭ��
			if (temp.next == null) {
				break;
			}

			// �ҵ�
			if (temp.next.id == id) {
				flag = true;
				break;
			}
			temp = temp.next;
		}
		if (flag) {
			// Ϊʲô��temp.next.next
			temp.next = temp.next.next;
		} else {
			System.out.println("û���ҵ�idΪ" + id);
		}
	}

	public void addReorder(HeroNode heroNode) {
		HeroNode temp = head;
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
			// �µĽڵ�ָ��tempָ����һ���ڵ�
			heroNode.next = temp.next;
			// temp�ڵ�ָ���µĽڵ�
			temp.next = heroNode;
		} else {
			// id�ظ�
			System.out.println("id" + heroNode.id + "�ظ�");
		}
	}
}

class HeroNode {
	public int id;
	public String name;
	public HeroNode next;// ����ָ����һ���ڵ�

	public HeroNode(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "HeroNode [id=" + id + ", name=" + name + "]";
	}

}