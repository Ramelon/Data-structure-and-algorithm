package linkedlist;

public class DoubleLinkedList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DoubleLinkedListDemo doubleLinkedListDemo = new DoubleLinkedListDemo();
		// 创建五个节点
		HeroNode2 node1 = new HeroNode2(1, "刘备");
		HeroNode2 node2 = new HeroNode2(2, "张飞");
		HeroNode2 node3 = new HeroNode2(3, "关羽");
		HeroNode2 node4 = new HeroNode2(4, "吕布");
		HeroNode2 node5 = new HeroNode2(5, "貂蝉");
		// 添加节点
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
//		doubleLinkedListDemo.updataLinkedList(new HeroNode2(5, "夏侯惇"));
//		System.out.println("修改过后的链表");
//		doubleLinkedListDemo.showLinkedList(doubleLinkedListDemo.getHead());
//		//删除第五个
		doubleLinkedListDemo.delLinkedList(5);
		System.out.println("显示删除后的链表");
		doubleLinkedListDemo.showLinkedList(doubleLinkedListDemo.getHead());
	}

}

class DoubleLinkedListDemo{
	private HeroNode2 head = new HeroNode2(0, "");

	// 我头结点是用来方便查找
	// 有个方法来获取头结点。
	public HeroNode2 getHead() {
		return head;
	}

	// 添加节点。
	public void addHeroNode(HeroNode2 heroNode) {
		// 因为head节点是不能动的，因此我们需要一个辅助节点temp
		HeroNode2 temp = head;
		// 添加数据是从链表的最后添加，
		// 如何确认最后的位置，只能，节点.next 这样遍历下去，直到值为null就找到位置。
		while (true) {
			if (temp.next == null) {
				break;
			}
			temp = temp.next;
		}
		temp.next = heroNode;
		heroNode.prev = temp;
	}

	// 显示链表
	public void showLinkedList(HeroNode2 head) {
		// 判断链表是否为空
		if (head.next == null) {
			System.out.println("链表为空");
			return;
		}
		// 因为我们的头结点是不能动的，所以需要一个辅助变量来遍历
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

	// 1 刘备 -> 2 张飞 -> 3 关羽
	// if(temp.id = heroNode.id) temp.name = heroNode.name
	// 修改链表节点数据
	public void updataLinkedList(HeroNode2 heroNode) {
		// 判断链表是否为空
		if (head.next == null) {
			System.out.println("链表为空");
			return;
		}
		// 定义一个辅助节点
		HeroNode2 temp = head.next;
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
		HeroNode2 temp = head.next;
//		while(true) {
//			System.out.println(temp);
//			if(temp.next == null) {
//				break;
//			}
//			temp = temp.next;
//		}
		boolean flag = false;
		// 找到待删除节点的前一个节点。
		while (true) {
			// 找到
			if (temp.id == id) {
				flag = true;
				break;
			}
			// 已经遍历到最后，需要退出循环
			if (temp.next == null) {
				break;
			}
			temp = temp.next;
		}
		if (flag) {
			// 为什么是temp.next.next
//			temp.next = temp.next.next;
			temp.prev.next = temp.next;
			//避免空指针
			if(temp.next != null) {
				temp.next.prev = temp.prev;
			}
		} else {
			System.out.println("没有找到id为" + id);
		}
	}

	public void addReorder(HeroNode2 heroNode) {
		//创建头结点
		HeroNode2 temp = head;
		//创建临时辅助指针
		HeroNode2 current = new HeroNode2(0, "");
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

			if (temp.next != null) {
				//直接写
				heroNode.next = temp.next;
				temp.next = heroNode;
				heroNode.prev = temp;
				heroNode.next.prev = heroNode;
				
				//利用中间辅助指针
//				current.next = temp.next;
//				temp.next = heroNode;
//				heroNode.prev = temp;
//				heroNode.next = current.next;
//				current.next.prev = heroNode;
			}else { //第一个数据，就直接插入
				temp.next = heroNode;
				heroNode.prev = temp;
			}
		} else {
			// id重复
			System.out.println("id" + heroNode.id + "重复");
		}
	}
}

class HeroNode2 {
	public int id;
	public String name;
	public HeroNode2 next;// 用来指向下一个节点
	public HeroNode2 prev;// 用来指向前一个节点
	
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