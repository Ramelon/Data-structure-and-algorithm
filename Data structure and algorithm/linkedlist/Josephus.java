package linkedlist;

public class Josephus {
	public static void main(String[] args) {
		CircleLinkedList circleLinkedList = new CircleLinkedList();
		circleLinkedList.addPeopleLinkedList(5);
		circleLinkedList.showPeople();
		circleLinkedList.WinPeople(1, 3, 5);
	}
}

// 创建节点
class CircleLinkedList {
	
	// 代表一个节点
	private People first = null;

	// 确定添加的人数,然后创建环形链表
	public void addPeopleLinkedList(int nums) {
		if (nums <= 0) {
			System.out.println("nums的值错误");
			return;
		}

		// 定义辅助指针，
		People tempPeople = null;
		
		for(int i = 1; i <= nums; i++) {
			People people = new People(i);
			if( i == 1) { //第一个人
				first = people;
				// 我指向我自己
				first.setNext(first);
				// 值给当前链表
				tempPeople = first;
			}else{ //因为first是指向头,people最后指向头，所以是一个循环链表
				tempPeople.setNext(people);
				//setNext this.next = next
				people.setNext(first);
				tempPeople = people;
			}
		}
	}
	
	//遍历显示链表
	public void showPeople(){
		People tempPeople = first;
		while(true) {
			System.out.println("people:" + tempPeople.getId());
			//已经完成遍历。一圈到起点
			if(tempPeople.getNext() == first) {
				break;
			}
			//后移
			tempPeople = tempPeople.getNext();
		}
	}

	// 求解问题，约瑟夫问题。
	/**
	 * 
	 * @param start 从哪里开始
	 * @param count 数多少次
	 * @param nums  有多少人参加 进行数据校验用
	 */ 
	public void WinPeople(int start, int count, int nums) {
		//对传进来的参数进行校验
		if(start < 1 || count > nums) {
			System.out.println("数据有误");
			return;
		}
		// 辅助
		People temp = first;
		// 创建temp指针，相当于链表，然后用temp来操作。
		while(true) {
			if(temp.getNext() == first) {
				break;
			}
			//后移 
			temp = temp.getNext();
		}
	
		// first, temp 指针要同时后移start-1次
		// 到哪从哪里开始，准备工作全部做完，
		for(int i = 0; i < start - 1; i++) {
			first = first.getNext();
			temp = temp.getNext();
		}
		
		//接下来就要出圈
		while(true) {
			if(temp == first) {
				break;
			}
			// 执行count-1次
			for(int j = 0; j < count - 1; j++) {
				first = first.getNext();
				temp = temp.getNext();
			}
			System.out.println(first.getId() + "号出圈。");
			//first指针后移
			first = first.getNext();
			// temp不后移，我直接跳过下一个节点直接指向first。
			// temp直接回到最后一个
			// 没次构成一个循环链表，相当于充值链表。
			temp.setNext(first);
		}
		// 当剩下最后一个退出循环

		System.out.println(first.getId() + "活了下来。");
	}
}

// 节点类
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
