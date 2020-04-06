package tree;


public class ThreadBinaryTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PeopleNode root = new PeopleNode(1, "A");
		PeopleNode node2 = new PeopleNode(2, "B");
		PeopleNode node3 = new PeopleNode(3, "C");
		PeopleNode node4 = new PeopleNode(4, "D");
		PeopleNode node5 = new PeopleNode(5, "E");
		PeopleNode node6 = new PeopleNode(6, "F");

		root.setLeft(node2);
		root.setRight(node3);
		node2.setParent(root);
		node4.setParent(node2);
		node3.setParent(root);
		node5.setParent(node3);
		node6.setParent(node3);
		node2.setRight(node4);
		node3.setLeft(node5);
		node3.setRight(node6);

		ThreadBinaryTreeDemo threadBinaryTreeDemo = new ThreadBinaryTreeDemo();
		threadBinaryTreeDemo.setRoot(root);
		// threadBinaryTreeDemo.preOrder();
		threadBinaryTreeDemo.postThreadNode(root);
		threadBinaryTreeDemo.postThreadList(root);
	}

}

class ThreadBinaryTreeDemo {
	private PeopleNode root;
	// ������ʱ������Ҫһ��ǰ���������¼��
	private PeopleNode preNode;

	public void setRoot(PeopleNode root) {
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

	// ǰ��������
	public void preThreadNode(PeopleNode node) {
		// ��������Ҫ�ж�nodeΪ��Ϊ�ա�
		// ���Ϊ�գ����ǲ��ܸ�����������
		if (node == null) {
			return;
		}
		// ������ǰ���
		// ����ǰ�����,
		// ������ӽڵ�Ϊ�գ���ô������Ҫ������������Ǿ���Ҫ�ѵ�ǰ�ڵ��leftType��Ϊ1
		if (node.getLeft() == null) {
			node.setLeft(preNode);
			node.setLeftType(1);
		}
		// �����̽ڵ�
		if (preNode != null && preNode.getRight() == null) {
			preNode.setRight(node);
			preNode.setRightType(1);
		}
		// 1 2 3 1��2 ��ǰ��
		// ��������������ÿһ����ǰ�ڵ�, ��ÿһ����㵱��һ������ǰ��ڵ�
		preNode = node;
		// �ݹ��������ӽڵ�
		// �����ǰ�ڵ��Ѿ�������������Ľ�㣬����һֱ�ݹ�ᱨ��
		if (node.getLeftType() == 0) {
			preThreadNode(node.getLeft());
		}
		// �ݹ��������ӽ��
		if (node.getRightType() == 0) {
			preThreadNode(node.getRight());
		}
	}

	// ǰ���������
	public void preThreadList(PeopleNode node) {
		while (node != null) {
			// ��ӡ��ǰ���
			while (node.getLeftType() == 0) {
				System.out.println(node);
				node = node.getLeft();
			}
			System.out.println(node);
			node = node.getRight();
		}
	}

	// ����
	// ǰ��������
	public void infixThreadNode(PeopleNode node) {
		// ��������Ҫ�ж�nodeΪ��Ϊ�ա�
		// ���Ϊ�գ����ǲ��ܸ�����������
		if (node == null) {
			return;
		}

		infixThreadNode(node.getLeft());

		// ������ǰ���
		// ����ǰ�����,
		// ������ӽڵ�Ϊ�գ���ô������Ҫ������������Ǿ���Ҫ�ѵ�ǰ�ڵ��leftType��Ϊ1
		if (node.getLeft() == null) {
			node.setLeft(preNode);
			node.setLeftType(1);
		}
		// �����̽ڵ�
		if (preNode != null && preNode.getRight() == null) {
			preNode.setRight(node);
			preNode.setRightType(1);
		}
		// 1 2 3 1��2 ��ǰ��
		// ��������������ÿһ����ǰ�ڵ�, ��ÿһ����㵱��һ������ǰ��ڵ�
		preNode = node;
		// System.out.println(preNode);
		// �ݹ��������ӽڵ�
		// �����ǰ�ڵ��Ѿ�������������Ľ�㣬����һֱ�ݹ�ᱨ��

		// �ݹ��������ӽ��
		infixThreadNode(node.getRight());

	}

	// �����������
	public void infixThreadList(PeopleNode node) {
		while (node != null && node.getLeftType() == 0) {
			node = node.getLeft();
		}

		while (node != null) {
			System.out.println(node);
			// ��ǰ�������������������ָ��
			if (node.getRightType() == 1) {
				node = node.getRight();
			} else {// ��ָ�벻����������ô����Ҫ�ҵ���������ʼ�Ľڵ�
				node = node.getRight();
				while (node != null && node.getLeftType() == 0) {
					node = node.getLeft();
				}
			}
		}
	}

	// ����
	// ����������
	public void postThreadNode(PeopleNode node) {
		// ��������Ҫ�ж�nodeΪ��Ϊ�ա�
		// ���Ϊ�գ����ǲ��ܸ�����������
		if (node == null) {
			return;
		}
		// �ݹ��������ӽڵ�
		// �����ǰ�ڵ��Ѿ�������������Ľ�㣬����һֱ�ݹ�ᱨ��
		postThreadNode(node.getLeft());

		// �ݹ��������ӽ��
		postThreadNode(node.getRight());
		// ������ǰ���
		// ����ǰ�����,
		// ������ӽڵ�Ϊ�գ���ô������Ҫ������������Ǿ���Ҫ�ѵ�ǰ�ڵ��leftType��Ϊ1
		if (node.getLeft() == null) {
			node.setLeft(preNode);
			node.setLeftType(1);
		}
		// �����̽ڵ�
		if (preNode != null && preNode.getRight() == null) {
			preNode.setRight(node);
			preNode.setRightType(1);
		}
		// 1 2 3 1��2 ��ǰ��
		// ��������������ÿһ����ǰ�ڵ�, ��ÿһ����㵱��һ������ǰ��ڵ�
		preNode = node;
		//System.out.println(preNode);

	}

	// �����������
	public void postThreadList(PeopleNode node) {
		// ��������ķ�����
		while (node != null && node.getLeftType() == 0) {
			node = node.getLeft();
		}

		while (node != null) {
			// �ҽ��������
			if(node.getRightType() == 1) {
				System.out.println(node);
				preNode = node;
				node = node.getRight();
			}else {// �ұ߲���������ô��
				// ����ϸ�����Ľ���ǵ�ǰ�����ҽ��
				if(node.getRight() == preNode) {
					System.out.println(node);
					if( node == root) {
						return;
					}
					preNode = node;
					node = node.getParent();
				}else {
					// ������������ �� �ҵ�������������ڵ�
					node = node.getRight();
					
					while(node != null && node.getLeftType() == 0) {
						node = node.getLeft();
					}
				}
			}
			
		}
	}
}

class PeopleNode {
	private int id;
	private String name;

	private PeopleNode left;
	private PeopleNode right;
	// ��������������ʱҪ�õ���
	private PeopleNode parent;

	// ���leftType==1 ˵��û�к��ӽ�㣬 �е�������ָ��
	// ���leftType==0 ˵���к��ӽ�㣬��������ָ��
	private int leftType;
	private int rightType;

	public PeopleNode(int id, String name) {
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

	public PeopleNode getLeft() {
		return left;
	}

	public void setLeft(PeopleNode left) {
		this.left = left;
	}

	public PeopleNode getRight() {
		return right;
	}

	public void setRight(PeopleNode right) {
		this.right = right;
	}

	public PeopleNode getParent() {
		return parent;
	}

	public void setParent(PeopleNode parent) {
		this.parent = parent;
	}

	public int getLeftType() {
		return leftType;
	}

	public void setLeftType(int leftType) {
		this.leftType = leftType;
	}

	public int getRightType() {
		return rightType;
	}

	public void setRightType(int rightType) {
		this.rightType = rightType;
	}

	@Override
	public String toString() {
		return "PeopleNode [id=" + id + ", name=" + name + "]";
	}

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
}