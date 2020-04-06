package tree;

public class BinaryTree {

	public static void main(String[] args) {
		BinaryTreeDemo binaryTree = new BinaryTreeDemo();

		// ��������㣬����ֵ
		HeroNode root = new HeroNode(1, "����");
		binaryTree.setRoot(root);

		HeroNode node2 = new HeroNode(2, "�ŷ�");
		HeroNode node3 = new HeroNode(3, "����");
		HeroNode node4 = new HeroNode(4, "����");
		HeroNode node5 = new HeroNode(5, "����");
		root.setLeft(node2);
		root.setRight(node3);
		node3.setLeft(node4);
		node3.setRight(node5);

		// // ���� ǰ����� 1,2,3,4,5
		// System.out.println("ǰ�����");
		// binaryTree.preOrder();
		//
		// // ���� ������� 2,1,4,3,5
		// System.out.println("�������");
		// binaryTree.infixOrder();
		//
		// // ���� ������� 2,4,5,3,1
		// System.out.println("�������");
		// binaryTree.postOrder();

		// // ǰ�����
		// HeroNode temp;
		// temp = binaryTree.preOrderSearch(4);
		// System.out.println(temp);

		// �������
		// HeroNode temp;
		// temp = binaryTree.infixOrderSearch(4);
		// System.out.println(temp);

		// �������
		// HeroNode temp;
		// temp = binaryTree.postOrderSearch(4);
		// System.out.println(temp);
		binaryTree.remove(4);
		System.out.println("ǰ�����");
		binaryTree.preOrder();
	}
}

class BinaryTreeDemo {
	// ����������Ҫ����һ�����ڵ�
	private HeroNode root;

	public void setRoot(HeroNode root) {
		this.root = root;
	}

	// ǰ�����
	public void preOrder() {
		if (this.root != null) {
			this.root.preOrder();
		} else {
			System.out.println("������Ϊ�գ��޷�����");
		}
	}

	// �������
	public void infixOrder() {
		if (this.root != null) {
			this.root.infixOrder();
		} else {
			System.out.println("������Ϊ�գ��޷�����");
		}
	}

	// ��ǰ�����
	public void postOrder() {
		if (this.root != null) {
			this.root.postOrder();
		} else {
			System.out.println("������Ϊ�գ��޷�����");
		}
	}

	// ǰ���������
	public HeroNode preOrderSearch(int id) {
		if (root != null) {
			return root.preOrderSearch(id);
		} else {
			return null;
		}
	}

	// ǰ���������
	public HeroNode infixOrderSearch(int id) {
		if (root != null) {
			return root.infixOrderSearch(id);
		} else {
			return null;
		}
	}

	// ǰ���������
	public HeroNode postOrderSearch(int id) {
		if (root != null) {
			return root.postOrderSearch(id);
		} else {
			return null;
		}
	}

	public void remove(int id) {
		if (root != null) {
			if (root.getId() == id) {
				root = null;
			} else {
				root.remove(id);
			}

		} else {
			System.out.println("��ǰ������Ϊ��");
		}
	}
}

class HeroNode {
	private int id;
	private String name;

	private HeroNode left;
	private HeroNode right;

	public HeroNode(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HeroNode getLeft() {
		return left;
	}

	public void setLeft(HeroNode left) {
		this.left = left;
	}

	public HeroNode getRight() {
		return right;
	}

	public void setRight(HeroNode right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "HeroNode [id=" + id + ", name=" + name + "]";
	}

	// ������� �� ǰ�����������������������
	// 1.1 �����ǰ���(���ڵ�) D
	// 1.2 ������ӽ�㲻Ϊ�գ���ݹ�ǰ��������ӽ�㡣 L
	// 1.3 ������ӽ�㲻Ϊ�գ���ݹ�ǰ��������ӽ�㡣 R
	// ǰ��������õݹ�
	public void preOrder() {
		// �����ǰ�ڵ�
		// DLR
		System.out.println(this);
		// ǰ��ݹ������ ������
		if (this.left != null) {
			this.left.preOrder();
		}
		// ǰ��ݹ������ ������
		if (this.right != null) {
			this.right.preOrder();
		}
	}

	// �������
	public void infixOrder() {
		// LDR
		// ����ݹ���� ������
		if (this.left != null) {
			this.left.infixOrder();
		}

		// �����ǰ���
		System.out.println(this);

		// ����ݹ���� ������
		if (this.right != null) {
			this.right.infixOrder();
		}
	}

	// �������
	public void postOrder() {
		// LRD
		// ����ݹ���� ������
		if (this.left != null) {
			this.left.postOrder();
		}

		// ����ݹ���� ������
		if (this.right != null) {
			this.right.postOrder();
		}

		// �����ǰ���
		System.out.println(this);
	}

	// ǰ���������
	public HeroNode preOrderSearch(int id) {
		// �жϵ�ǰ����ǲ���Ҫ�ҵĽ��
		System.out.println(this.id);
		if (this.id == id) {
			return this;
		}
		// ������ȣ��жϵ�ǰ������ӽ���Ƿ�Ϊ�գ������Ϊ�գ���ݹ�ǰ����ҡ�
		HeroNode temp = null;
		if (this.left != null) {
			temp = this.left.preOrderSearch(id);
		}
		// ������ӽ��ݹ��ҵ����򷵻أ����û���ҵ����жϵ�ǰ�������ӽ�㡣
		// �����Ϊ�գ���ݹ�ǰ����ҡ�
		if (temp != null) {
			// ˵�������Ѿ��ҵ��ò��ҵĽ��
			return temp;
		}
		// �ݹ������ӽ��
		if (this.right != null) {
			temp = this.right.preOrderSearch(id);
		}
		return temp;
	}

	// �����������
	public HeroNode infixOrderSearch(int id) {

		// ������ȣ��жϵ�ǰ������ӽ���Ƿ�Ϊ�գ������Ϊ�գ���ݹ�������ҡ�
		HeroNode temp = null;
		if (this.left != null) {
			temp = this.left.infixOrderSearch(id);
		}

		// ������ӽ��ݹ��ҵ����򷵻أ����û���ҵ����жϵ�ǰ�������ӽ�㡣
		// �����Ϊ�գ���ݹ�������ҡ�
		if (temp != null) {
			// ˵�������Ѿ��ҵ��ò��ҵĽ��
			return temp;
		}

		System.out.println(this.id);
		// �жϵ�ǰ����ǲ���Ҫ�ҵĽ��
		if (this.id == id) {
			return this;
		}
		// �ݹ������ӽ��
		if (this.right != null) {
			temp = this.right.infixOrderSearch(id);
		}
		return temp;
	}

	// �����������
	public HeroNode postOrderSearch(int id) {

		// ������ȣ��жϵ�ǰ������ӽ���Ƿ�Ϊ�գ������Ϊ�գ���ݹ�������ҡ�
		HeroNode temp = null;
		if (this.left != null) {
			temp = this.left.postOrderSearch(id);
		}

		// ������ӽ��ݹ��ҵ����򷵻أ����û���ҵ����жϵ�ǰ�������ӽ�㡣
		// �����Ϊ�գ���ݹ�������ҡ�
		if (temp != null) {
			// ˵�������Ѿ��ҵ��ò��ҵĽ��
			return temp;
		}

		// �ݹ������ӽ��
		if (this.right != null) {
			temp = this.right.postOrderSearch(id);
		}
		if (temp != null) {
			// ˵�������Ѿ��ҵ��ò��ҵĽ��
			return temp;
		}

		System.out.println(this.id);
		// �жϵ�ǰ����ǲ���Ҫ�ҵĽ��
		if (this.id == id) {
			return this;
		}
		return temp;
	}

	// �������
	// 1.����ǿ�����ֻ��һ��root��㣬����ֱ�ӽ��������ÿռ��ɡ�
	// 2.��Ϊ���ǵ����ǵ����(ɾ����������֮ǰ���Ĺ�ϣ��ɾ��)��
	// ��������Ҫ�жϵ�ǰ�����ӽ���Ƿ�Ҫɾ����
	// 3.�����ǰ�������ӽ�㲻Ϊ�գ������ӽ�����Ҫɾ���Ľڵ㣬
	// ���ǽ�this.left = null;Ȼ�󷵻أ�(�����ݹ�)
	// 4.�����ǰ�������ӽ�㲻Ϊ�գ������ӽ�����Ҫɾ���Ľڵ㣬
	// ���ǽ�this.right = null;Ȼ�󷵻أ�(�����ݹ�)
	// 5.�ݹ�3,4���衣
	public void remove(int id) {
		// 3.�����ǰ�������ӽ�㲻Ϊ�գ������ӽ�����Ҫɾ���Ľڵ㣬
		// ���ǽ�this.left = null;Ȼ�󷵻أ�(�����ݹ�)
		if (this.left != null && this.left.id == id) {
			this.left = null;
			return;
		}

		// 4.�����ǰ�������ӽ�㲻Ϊ�գ������ӽ�����Ҫɾ���Ľڵ㣬
		// ���ǽ�this.right = null;Ȼ�󷵻أ�(�����ݹ�)
		if (this.right != null && this.right.id == id) {
			this.right = null;
			return;
		}
		// �ݹ鲽����
		if (this.left != null) {
			this.left.remove(id);
		}
		// �ݹ鲽����
		if (this.right != null) {
			this.right.remove(id);
		}
	}
}

