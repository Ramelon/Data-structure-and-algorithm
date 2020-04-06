package HashTab;

public class HashTab {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashTab2 hashTab2 = new HashTab2(5);
		Emp emp1 = new Emp(1, "С��", "1");
		Emp emp2 = new Emp(2, "С��", "0");
		Emp emp3 = new Emp(6, "С��", "0");
		Emp emp4 = new Emp(16, "С��", "0");
		
		// ���
		hashTab2.add(emp1);
		hashTab2.add(emp2);
		hashTab2.add(emp3);
		hashTab2.add(emp4);
		// ��ʾ
		hashTab2.list();

//		// �޸�
//		hashTab2.update(6, "С��", "0");
//		// ��ʾ
//		System.out.println("-----------------------------");
//		hashTab2.list();
//		// ����
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
		// ��ʼ��empList
		empList = new EmpLinkedList[size];
		// ����Ҫע��һ���ҳ�ʼ��һ��empList��������ڵ�Size�������ʱȫ����ָ�򣬾���ʲô��û��
		// ��ô�����Ҫ���ʱ�����߲������ⲽ��ʱ�����ᱨ��ָ���쳣����
		for (int i = 0; i < size; i++) {
			empList[i] = new EmpLinkedList();
		}
	}

	// ɢ�к�������ϣ��Ĺؼ�ֻ�ڣ�ͨ��ĳ���ض��ķ��ã���Ѱ��key�������洢value�������ܶ࣬������ȡģ����
	public int hashFun(int id) {
		return id % size;
	}

	// ����
	public void add(Emp emp) {
		int empNo = hashFun(emp.id);
		empList[empNo].add(emp);
	}

	// ɾ��
	public void remove(int id) {
		int empNo = hashFun(id);
		empList[empNo].remove(id);
	}

	// �޸�
	public void update(int id, String name, String gender) {
		int empNo = hashFun(id);
		empList[empNo].update(id, name, gender);
	}

	// �鿴
	public void findEmpById(int id) {
		int empNo = hashFun(id);
		Emp emp = empList[empNo].findEmpById(id);
		// �ҵ�
		if (emp != null) {
			System.out.printf("�ڵ�%d���������ҵ�Ա�� id = %d", empNo + 1, id);
		} else {
			System.out.println("�ڹ�ϣ���У�δ�ҵ���Ա��");
		}
	}

	// ��ʾȫ��
	public void list() {
		for (int i = 0; i < size; i++) {
			empList[i].list(i);
		}
	}
}

// ����Emp������
class EmpLinkedList {
	// ͷָ�룬�����ͷָ���ǰ������ݵġ�
	private Emp head;

	// ���
	public void add(Emp emp) {
		// ��Ϊͷ����ǰ������ݵģ�������ӵ�һ���ڵ��ʱ������Ҫ��head = emp
		if (head == null) {
			head = emp;
			return;
		}
		// ������ǵ�һ���ڵ㣬��������Ҫ��������ָ�룬�����ҵ���������λ�ã�Ȼ���������
		Emp temp = head;
		while (true) {
			// ������������
			if (temp.next == null) {
				break;
			}
			// ����
			temp = temp.next;
		}
		// �ҵ�����λ�ã��������
		temp.next = emp;
	}

	// ɾ��
	// ����Ҫע�⣬֮ǰ���Ĵ�ͷ�������������� temp.next = temp.next.next;
	// �����������ڲ�����ôд������Ǹ�������ֻ����һ�����ݣ���ô��ִ���������룬����ָ���쳣
	// ��Ϊ�ҵ�ͷ�������ҵ�һ�����ݱ���ֻ��head = head.next;��������ɾ��ֻ��һ���ڵ������
	// head = null;
	// ���������������ݲ�ֹһ������������ temp.next = temp.next.next;�Ϳ���ʵ��ɾ����
	public void remove(int id) {
		if (head == null) {
			System.out.println("������Ϊ��");
			return;
		}
		// ���帨��ָ���ҵ���Ӧλ�ã���ɾ������
		Emp temp = head;
		while (true) {
			// ����������ֻ��һ���ڵ�����
			if (temp.id == id) {
				head = head.next;
				break;
			}
			// �ж���ڵ�
			if (temp.next.id == id) {
				temp.next = temp.next.next;
				break;
			}
			// ���û���ҵ���ɾ���Ľڵ㣬ֱ��return
			if (temp.next == null) {
				return;
			}
			temp = temp.next;
		}
	}

	// �޸�
	public void update(int id, String name, String gender) {
		if (head == null) {
			System.out.println("����Ϊ��");
			return;
		}
		// ����ָ��
		Emp temp = head;
		while (true) {
			if (temp.id == id) {
				temp.name = name;
				temp.gender = gender;
			}
			// ���û���ҵ���ɾ���Ľڵ㣬ֱ��return
			if (temp.next == null) {
				return;
			}
			temp = temp.next;
		}
	}

	// �鿴
	// ����id����
	public Emp findEmpById(int id) {
		if (head == null) {
			System.out.println("����Ϊ��");
			return null;
		}
		// ����ָ��
		Emp temp = head;
		while (true) {
			if (temp.id == id) {
				break;
			}
			// ���û���ҵ���ɾ���Ľڵ㣬ֱ��return
			if (temp.next == null) {
				temp = null;
				break;
			}
			temp = temp.next;
		}
		return temp;
	}

	// ��ʾȫ��
	public void list(int no) {
		if (head == null) {
			System.out.println("�� " + (no + 1) + "����Ϊ��");
			return;
		}
		System.out.print("�� " + (no + 1) + "�������ϢΪ");
		Emp temp = head;
		while (true) {
			System.out.printf("--> id = %d name = %s gender = %s\t", temp.id, temp.name, temp.gender);
			// ���û���ҵ���ɾ���Ľڵ㣬ֱ��return
			if (temp.next == null) {
				temp = null;
				break;
			}
			temp = temp.next;
		}
		System.out.println();
	}
}

// �½�һ��Ա���࣬�൱��һ���ڵ�
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