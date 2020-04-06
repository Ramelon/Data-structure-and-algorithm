package linkedlist;

import java.util.Stack;

public class SingleLinkedList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SingleLinkedListDemo singleLinkedListDemo = new SingleLinkedListDemo();
		// 创建五个节点
		HeroNode node1 = new HeroNode(1, "刘备");
		HeroNode node2 = new HeroNode(2, "张飞");
		HeroNode node3 = new HeroNode(3, "关羽");
		HeroNode node4 = new HeroNode(4, "吕布");
		HeroNode node5 = new HeroNode(5, "貂蝉");
		// 添加节点
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
		// 显示节点
		singleLinkedListDemo.showLinkedList(singleLinkedListDemo.getHead());
		System.out.println("节点有效个数为：" +  getLinkedLength(singleLinkedListDemo.getHead()));
		System.out.println("倒数第三个节点为：" + findIndexReciprocal(singleLinkedListDemo.getHead(), 3));
		// 修改貂蝉为西施
		// System.out.println("-------------------------------");
		// singleLinkedListDemo.updataLinkedList(new HeroNode(5, "西施"));
		// singleLinkedListDemo.showLinkedList(singleLinkedListDemo.getHead());
		// //修改貂蝉为西施
		// System.out.println("-------------------------------");
		// singleLinkedListDemo.delLinkedList(5);
		// singleLinkedListDemo.showLinkedList(singleLinkedListDemo.getHead());
		//反转\
		System.out.println("----------------------");
		LinkedReverseDoStack(singleLinkedListDemo.getHead());
		//singleLinkedListDemo.showLinkedList(singleLinkedListDemo.getHead());
		//新建一个节点
		SingleLinkedListDemo singleLinkedListDemo2 = new SingleLinkedListDemo();
		HeroNode node6 = new HeroNode(6, "马超");
		HeroNode node7 = new HeroNode(7, "赵云");
		singleLinkedListDemo.addReorder(node6);
		singleLinkedListDemo.addReorder(node7);
		System.out.println("合并后的链表");
		mergeLinked(singleLinkedListDemo.getHead(), singleLinkedListDemo2.getHead());
	}

	// 因为我们是求链表节点的个数，涉及到遍历，所以我们要传一个头结点进来
	public static int getLinkedLength(HeroNode head) {
		// 判断链表是否为空，头结点不算在内
		if (head.next == null)
			return 0;
		HeroNode temp = head.next;
		int length = 0;
		// 为什么是temp != null，而不是temp.next != null
		// 因为我已经temp = head.next;此时temp已经指向后面的节点
		while (temp != null) {
			length++;
			temp = temp.next;
		}
		return length;
	}

	// 查找单链表中倒数的第K个节点。
	public static HeroNode findIndexReciprocal(HeroNode head, int index) {
		//判断链表是否为空
		if(head.next == null){
			return null;
		}
		int length = getLinkedLength(head);
		//判断index 是否合法
		if(index > length || index <= 0) {
			return null;
		}
		//临时辅助
		HeroNode temp = head.next;
		
		for(int i = 0; i < length - index; i++) {
			temp = temp.next;
		}
		//返回节点
		return temp;
	}
	
	//将单链表反转
	public static void LinkedReverse(HeroNode head) {
		//判断链表是否为空,链表有效节点为1 则不用反转
		if(head.next == null || head.next.next == null){
			return;
		}
		//定义辅助指针变量
		HeroNode temp = head.next;
		HeroNode next = null;
		//新建一个指向当前节点的reverseHead
		HeroNode reverseHead = new HeroNode(0, "");
				
		// 遍历原来的节点，每次遍历都取出来放入reverseHead的最前面。
		while(temp != null) {
			//暂时保存当前节点的下一个节点。
			next = temp.next;
			// 将数据指向reverseHead这个链表的前端 我第一次temp.next 是null
			temp.next = reverseHead.next; // 第二次  当前 HeroNode [id=2, name=张飞] ——> 我第一次 HeroNode [id=1, name=刘备]
			// 指向当前节点，不然得到是会是null
			reverseHead.next = temp; //我第一次 HeroNode [id=1, name=刘备] 给 reverseHead 第二次 HeroNode [id=2, name=张飞] ——>我第一次 HeroNode [id=1, name=刘备]
			// 后移 1 2 3 4 5
			temp = next;
		}
		// reverseHead这个链表里就是反转后的链表，所以我head指向reverseHead就是得到一个反转的链表
		head.next = reverseHead.next;
	}
	
	// 利用栈来解决逆序
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
	
	// 两个链表合并
	public static void mergeLinked(HeroNode head1, HeroNode head2) {
		//辅助指针变量
		HeroNode temp1 = head1.next;
		HeroNode temp2 = head2.next;
		
		// 创建一个中间临时练来用来合并
		HeroNode head3 = new HeroNode(0, "");
		HeroNode temp3 = head3;
		
		//合并 三种情况 
		//temp1 此时还有数据 temp2 没有数据 就把temp1的数据加入临时链表
		//temp2 此时还有数据 temp1 没有数据 就把temp2的数据加入临时链表
		//都还有数据
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
			//后移
			temp3 = temp3.next;
		}
		SingleLinkedListDemo singleLinkedListDemo = new SingleLinkedListDemo();
		singleLinkedListDemo.showLinkedList(head3);
	}
}

class SingleLinkedListDemo {
	private HeroNode head = new HeroNode(0, "");

	// 我头结点是用来方便查找
	// 有个方法来获取头结点。
	public HeroNode getHead() {
		return head;
	}

	// 添加节点。
	public void addHeroNode(HeroNode heroNode) {
		// 因为head节点是不能动的，因此我们需要一个辅助节点temp
		HeroNode temp = head;
		// 添加数据是从链表的最后添加，
		// 如何确认最后的位置，只能，节点.next 这样遍历下去，直到值为null就找到位置。
		while (true) {
			if (temp.next == null) {
				break;
			}
			temp = temp.next;
		}
		temp.next = heroNode;
	}

	// 显示链表
	public void showLinkedList(HeroNode head) {
		// 判断链表是否为空
		if (head.next == null) {
			System.out.println("链表为空");
			return;
		}
		// 因为我们的头结点是不能动的，所以需要一个辅助变量来遍历
		HeroNode temp = head.next;

		while (true) {
			if (temp == null)
				break;
			System.out.println(temp);
			temp = temp.next;
		}
	}

	// 1 刘备 -> 2 张飞 -> 3 关羽
	// if(temp.id = heroNode.id) temp.name = heroNode.name
	// 修改链表节点数据
	public void updataLinkedList(HeroNode heroNode) {
		// 判断链表是否为空
		if (head.next == null) {
			System.out.println("链表为空");
			return;
		}
		// 定义一个辅助节点
		HeroNode temp = head.next;
		boolean flag = false;
		// 是否找到对应节点
		while (true) {
			// 如果找到。
			if (temp.id == heroNode.id) {

				flag = true;
				break;
			}
			// 防止空指针报错
			if (temp.next == null) {
				break;
			}
			temp = temp.next; // temp后移
		}

		if (flag) {
			temp.name = heroNode.name;
		} else {
			System.out.println("没有找到id为" + heroNode.id);
		}
	}

	// 删除节点
	public void delLinkedList(int id) {
		HeroNode temp = head;
		boolean flag = false;
		// 找到待删除节点的前一个节点。
		while (true) {
			// 已经遍历到最后，需要退出循环
			if (temp.next == null) {
				break;
			}

			// 找到
			if (temp.next.id == id) {
				flag = true;
				break;
			}
			temp = temp.next;
		}
		if (flag) {
			// 为什么是temp.next.next
			temp.next = temp.next.next;
		} else {
			System.out.println("没有找到id为" + id);
		}
	}

	public void addReorder(HeroNode heroNode) {
		HeroNode temp = head;
		boolean flag = false;
		while (true) {
			// 链表到最后
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
			// 新的节点指向temp指向下一个节点
			heroNode.next = temp.next;
			// temp节点指向新的节点
			temp.next = heroNode;
		} else {
			// id重复
			System.out.println("id" + heroNode.id + "重复");
		}
	}
}

class HeroNode {
	public int id;
	public String name;
	public HeroNode next;// 用来指向下一个节点

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