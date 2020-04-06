package HashTab;

public class HashTab {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashTab2 hashTab2 = new HashTab2(5);
		Emp emp1 = new Emp(1, "小王", "1");
		Emp emp2 = new Emp(2, "小红", "0");
		Emp emp3 = new Emp(6, "小红", "0");
		Emp emp4 = new Emp(16, "小红", "0");
		
		// 添加
		hashTab2.add(emp1);
		hashTab2.add(emp2);
		hashTab2.add(emp3);
		hashTab2.add(emp4);
		// 显示
		hashTab2.list();

//		// 修改
//		hashTab2.update(6, "小丽", "0");
//		// 显示
//		System.out.println("-----------------------------");
//		hashTab2.list();
//		// 查找
//		hashTab2.findEmpById(6);
		hashTab2.remove(6);
		System.out.println("-----------------------------");
		hashTab2.list();
	}

}

class HashTab2 {
	private EmpLinkedList[] empList;
	private int size;

	public HashTab2(int size) {
		// TODO Auto-generated constructor stub
		this.size = size;
		// 初始化empList
		empList = new EmpLinkedList[size];
		// 这里要注意一点我初始化一个empList，里面存在的Size个链表此时全部无指向，就是什么都没，
		// 那么如果我要添加时，或者操作任意步骤时，都会报空指针异常错误。
		for (int i = 0; i < size; i++) {
			empList[i] = new EmpLinkedList();
		}
	}

	// 散列函数，哈希表的关键只在，通过某种特定的放置，来寻找key，进而存储value，方法很多，我们用取模法。
	public int hashFun(int id) {
		return id % size;
	}

	// 增加
	public void add(Emp emp) {
		int empNo = hashFun(emp.id);
		empList[empNo].add(emp);
	}

	// 删除
	public void remove(int id) {
		int empNo = hashFun(id);
		empList[empNo].remove(id);
	}

	// 修改
	public void update(int id, String name, String gender) {
		int empNo = hashFun(id);
		empList[empNo].update(id, name, gender);
	}

	// 查看
	public void findEmpById(int id) {
		int empNo = hashFun(id);
		Emp emp = empList[empNo].findEmpById(id);
		// 找到
		if (emp != null) {
			System.out.printf("在第%d条链表中找到员工 id = %d", empNo + 1, id);
		} else {
			System.out.println("在哈希表中，未找到该员工");
		}
	}

	// 显示全部
	public void list() {
		for (int i = 0; i < size; i++) {
			empList[i].list(i);
		}
	}
}

// 创建Emp的链表
class EmpLinkedList {
	// 头指针，这里的头指针是包括数据的。
	private Emp head;

	// 添加
	public void add(Emp emp) {
		// 因为头结点是包含数据的，所以添加第一个节点的时候，我们要把head = emp
		if (head == null) {
			head = emp;
			return;
		}
		// 如果不是第一个节点，那我们需要借助辅助指针，遍历找到链表最后的位置，然后插入数据
		Emp temp = head;
		while (true) {
			// 如果链表到了最后
			if (temp.next == null) {
				break;
			}
			// 后移
			temp = temp.next;
		}
		// 找到最后的位置，添加数据
		temp.next = emp;
	}

	// 删除
	// 这里要注意，之前讲的带头结点的链表，他是用 temp.next = temp.next.next;
	// 但是我们现在不能这么写，如果是该链表里只存在一个数据，那么我执行上述代码，则会空指针异常
	// 因为我的头结点就是我第一个数据本身，只有head = head.next;这样才能删除只有一个节点的链表
	// head = null;
	// 如果该链表里的数据不止一个，我们则用 temp.next = temp.next.next;就可以实现删除。
	public void remove(int id) {
		if (head == null) {
			System.out.println("该链表为空");
			return;
		}
		// 定义辅助指针找到对应位置，做删除操作
		Emp temp = head;
		while (true) {
			// 存在链表中只有一个节点的情况
			if (temp.id == id) {
				head = head.next;
				break;
			}
			// 有多个节点
			if (temp.next.id == id) {
				temp.next = temp.next.next;
				break;
			}
			// 如果没有找到该删除的节点，直接return
			if (temp.next == null) {
				return;
			}
			temp = temp.next;
		}
	}

	// 修改
	public void update(int id, String name, String gender) {
		if (head == null) {
			System.out.println("链表为空");
			return;
		}
		// 辅助指针
		Emp temp = head;
		while (true) {
			if (temp.id == id) {
				temp.name = name;
				temp.gender = gender;
			}
			// 如果没有找到该删除的节点，直接return
			if (temp.next == null) {
				return;
			}
			temp = temp.next;
		}
	}

	// 查看
	// 根据id查找
	public Emp findEmpById(int id) {
		if (head == null) {
			System.out.println("链表为空");
			return null;
		}
		// 辅助指针
		Emp temp = head;
		while (true) {
			if (temp.id == id) {
				break;
			}
			// 如果没有找到该删除的节点，直接return
			if (temp.next == null) {
				temp = null;
				break;
			}
			temp = temp.next;
		}
		return temp;
	}

	// 显示全部
	public void list(int no) {
		if (head == null) {
			System.out.println("第 " + (no + 1) + "链表为空");
			return;
		}
		System.out.print("第 " + (no + 1) + "链表的信息为");
		Emp temp = head;
		while (true) {
			System.out.printf("--> id = %d name = %s gender = %s\t", temp.id, temp.name, temp.gender);
			// 如果没有找到该删除的节点，直接return
			if (temp.next == null) {
				temp = null;
				break;
			}
			temp = temp.next;
		}
		System.out.println();
	}
}

// 新建一个员工类，相当于一个节点
class Emp {
	public int id;
	public String name;
	public String gender;

	public Emp next;

	public Emp(int id, String name, String gender) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
	}
}