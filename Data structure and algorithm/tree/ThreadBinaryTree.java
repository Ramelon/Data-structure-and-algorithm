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
	// 线索化时我们需要一个前驱结点来记录。
	private PeopleNode preNode;

	public void setRoot(PeopleNode root) {
		this.root = root;
	}

	// 前序遍历
	public void preOrder() {
		if (this.root != null) {
			this.root.preOrder();
		} else {
			System.out.println("二叉树为空，无法遍历");
		}
	}

	// 中序遍历
	public void infixOrder() {
		if (this.root != null) {
			this.root.infixOrder();
		} else {
			System.out.println("二叉树为空，无法遍历");
		}
	}

	// 后前序遍历
	public void postOrder() {
		if (this.root != null) {
			this.root.postOrder();
		} else {
			System.out.println("二叉树为空，无法遍历");
		}
	}

	// 前序线索。
	public void preThreadNode(PeopleNode node) {
		// 首先我们要判断node为不为空。
		// 如果为空，我们不能给他增加线索
		if (node == null) {
			return;
		}
		// 线索先前结点
		// 线索前驱结点,
		// 如果左子节点为空，那么我们需要添加线索，我们就需要把当前节点的leftType变为1
		if (node.getLeft() == null) {
			node.setLeft(preNode);
			node.setLeftType(1);
		}
		// 处理后继节点
		if (preNode != null && preNode.getRight() == null) {
			preNode.setRight(node);
			preNode.setRightType(1);
		}
		// 1 2 3 1是2 的前驱
		// 当相遇遍历操作每一个当前节点, 让每一个结点当下一个结点的前序节点
		preNode = node;
		// 递归线索左子节点
		// 如果当前节点已经输出到有线索的结点，我在一直递归会报错。
		if (node.getLeftType() == 0) {
			preThreadNode(node.getLeft());
		}
		// 递归线索右子结点
		if (node.getRightType() == 0) {
			preThreadNode(node.getRight());
		}
	}

	// 前序遍历线索
	public void preThreadList(PeopleNode node) {
		while (node != null) {
			// 打印当前结点
			while (node.getLeftType() == 0) {
				System.out.println(node);
				node = node.getLeft();
			}
			System.out.println(node);
			node = node.getRight();
		}
	}

	// 中序
	// 前序线索。
	public void infixThreadNode(PeopleNode node) {
		// 首先我们要判断node为不为空。
		// 如果为空，我们不能给他增加线索
		if (node == null) {
			return;
		}

		infixThreadNode(node.getLeft());

		// 线索先前结点
		// 线索前驱结点,
		// 如果左子节点为空，那么我们需要添加线索，我们就需要把当前节点的leftType变为1
		if (node.getLeft() == null) {
			node.setLeft(preNode);
			node.setLeftType(1);
		}
		// 处理后继节点
		if (preNode != null && preNode.getRight() == null) {
			preNode.setRight(node);
			preNode.setRightType(1);
		}
		// 1 2 3 1是2 的前驱
		// 当相遇遍历操作每一个当前节点, 让每一个结点当下一个结点的前序节点
		preNode = node;
		// System.out.println(preNode);
		// 递归线索左子节点
		// 如果当前节点已经输出到有线索的结点，我在一直递归会报错。

		// 递归线索右子结点
		infixThreadNode(node.getRight());

	}

	// 中序遍历线索
	public void infixThreadList(PeopleNode node) {
		while (node != null && node.getLeftType() == 0) {
			node = node.getLeft();
		}

		while (node != null) {
			System.out.println(node);
			// 当前结点有线索，而且是右指针
			if (node.getRightType() == 1) {
				node = node.getRight();
			} else {// 右指针不是线索，那么我们要找到右子树开始的节点
				node = node.getRight();
				while (node != null && node.getLeftType() == 0) {
					node = node.getLeft();
				}
			}
		}
	}

	// 后序
	// 后序线索。
	public void postThreadNode(PeopleNode node) {
		// 首先我们要判断node为不为空。
		// 如果为空，我们不能给他增加线索
		if (node == null) {
			return;
		}
		// 递归线索左子节点
		// 如果当前节点已经输出到有线索的结点，我在一直递归会报错。
		postThreadNode(node.getLeft());

		// 递归线索右子结点
		postThreadNode(node.getRight());
		// 线索先前结点
		// 线索前驱结点,
		// 如果左子节点为空，那么我们需要添加线索，我们就需要把当前节点的leftType变为1
		if (node.getLeft() == null) {
			node.setLeft(preNode);
			node.setLeftType(1);
		}
		// 处理后继节点
		if (preNode != null && preNode.getRight() == null) {
			preNode.setRight(node);
			preNode.setRightType(1);
		}
		// 1 2 3 1是2 的前驱
		// 当相遇遍历操作每一个当前节点, 让每一个结点当下一个结点的前序节点
		preNode = node;
		//System.out.println(preNode);

	}

	// 后序遍历线索
	public void postThreadList(PeopleNode node) {
		// 按照中序的方法找
		while (node != null && node.getLeftType() == 0) {
			node = node.getLeft();
		}

		while (node != null) {
			// 右结点是线索
			if(node.getRightType() == 1) {
				System.out.println(node);
				preNode = node;
				node = node.getRight();
			}else {// 右边不是线索怎么办
				// 如果上个处理的结点是当前结点的右结点
				if(node.getRight() == preNode) {
					System.out.println(node);
					if( node == root) {
						return;
					}
					preNode = node;
					node = node.getParent();
				}else {
					// 如果从左结点进入 则 找到有子树的最左节点
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
	// 我们做后序线索时要用到。
	private PeopleNode parent;

	// 如果leftType==1 说明没有孩子结点， 有的是线索指针
	// 如果leftType==0 说明有孩子结点，则不用线索指针
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

	// 前序遍历，用递归
	public void preOrder() {
		// 输出当前节点
		// DLR
		System.out.println(this);
		// 前序递归遍历， 左子树
		if (this.left != null) {
			this.left.preOrder();
		}
		// 前序递归遍历， 右子树
		if (this.right != null) {
			this.right.preOrder();
		}
	}

	// 中序遍历
	public void infixOrder() {
		// LDR
		// 中序递归遍历 左子树
		if (this.left != null) {
			this.left.infixOrder();
		}

		// 输出当前结点
		System.out.println(this);

		// 中序递归遍历 右子树
		if (this.right != null) {
			this.right.infixOrder();
		}
	}

	// 后序遍历
	public void postOrder() {
		// LRD
		// 后序递归遍历 左子树
		if (this.left != null) {
			this.left.postOrder();
		}

		// 后序递归遍历 右子树
		if (this.right != null) {
			this.right.postOrder();
		}

		// 输出当前结点
		System.out.println(this);
	}
}