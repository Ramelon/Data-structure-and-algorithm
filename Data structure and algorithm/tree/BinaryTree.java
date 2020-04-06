package tree;

public class BinaryTree {

	public static void main(String[] args) {
		BinaryTreeDemo binaryTree = new BinaryTreeDemo();

		// 创建根结点，并赋值
		HeroNode root = new HeroNode(1, "刘备");
		binaryTree.setRoot(root);

		HeroNode node2 = new HeroNode(2, "张飞");
		HeroNode node3 = new HeroNode(3, "关羽");
		HeroNode node4 = new HeroNode(4, "吕布");
		HeroNode node5 = new HeroNode(5, "貂蝉");
		root.setLeft(node2);
		root.setRight(node3);
		node3.setLeft(node4);
		node3.setRight(node5);

		// // 遍历 前序遍历 1,2,3,4,5
		// System.out.println("前序遍历");
		// binaryTree.preOrder();
		//
		// // 遍历 中序遍历 2,1,4,3,5
		// System.out.println("中序遍历");
		// binaryTree.infixOrder();
		//
		// // 遍历 后序遍历 2,4,5,3,1
		// System.out.println("后序遍历");
		// binaryTree.postOrder();

		// // 前序查找
		// HeroNode temp;
		// temp = binaryTree.preOrderSearch(4);
		// System.out.println(temp);

		// 中序查找
		// HeroNode temp;
		// temp = binaryTree.infixOrderSearch(4);
		// System.out.println(temp);

		// 后序查找
		// HeroNode temp;
		// temp = binaryTree.postOrderSearch(4);
		// System.out.println(temp);
		binaryTree.remove(4);
		System.out.println("前序遍历");
		binaryTree.preOrder();
	}
}

class BinaryTreeDemo {
	// 这里我们需要创建一个根节点
	private HeroNode root;

	public void setRoot(HeroNode root) {
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

	// 前序遍历查找
	public HeroNode preOrderSearch(int id) {
		if (root != null) {
			return root.preOrderSearch(id);
		} else {
			return null;
		}
	}

	// 前序遍历查找
	public HeroNode infixOrderSearch(int id) {
		if (root != null) {
			return root.infixOrderSearch(id);
		} else {
			return null;
		}
	}

	// 前序遍历查找
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
			System.out.println("当前二叉树为空");
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

	// 遍历结点 ， 前序遍历，中序遍历，后序遍历
	// 1.1 输出当前结点(根节点) D
	// 1.2 如果左子结点不为空，则递归前序遍历左子结点。 L
	// 1.3 如果右子结点不为空，则递归前序遍历左子结点。 R
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

	// 前序遍历查找
	public HeroNode preOrderSearch(int id) {
		// 判断当前结点是不是要找的结点
		System.out.println(this.id);
		if (this.id == id) {
			return this;
		}
		// 如果不等，判断当前结点左子结点是否为空，如果不为空，则递归前序查找。
		HeroNode temp = null;
		if (this.left != null) {
			temp = this.left.preOrderSearch(id);
		}
		// 如果左子结点递归找到。则返回，如果没有找到。判断当前结点的右子结点。
		// 如果不为空，则递归前序查找。
		if (temp != null) {
			// 说明我们已经找到该查找的结点
			return temp;
		}
		// 递归找右子结点
		if (this.right != null) {
			temp = this.right.preOrderSearch(id);
		}
		return temp;
	}

	// 中序遍历查找
	public HeroNode infixOrderSearch(int id) {

		// 如果不等，判断当前结点左子结点是否为空，如果不为空，则递归中序查找。
		HeroNode temp = null;
		if (this.left != null) {
			temp = this.left.infixOrderSearch(id);
		}

		// 如果左子结点递归找到。则返回，如果没有找到。判断当前结点的右子结点。
		// 如果不为空，则递归中序查找。
		if (temp != null) {
			// 说明我们已经找到该查找的结点
			return temp;
		}

		System.out.println(this.id);
		// 判断当前结点是不是要找的结点
		if (this.id == id) {
			return this;
		}
		// 递归找右子结点
		if (this.right != null) {
			temp = this.right.infixOrderSearch(id);
		}
		return temp;
	}

	// 后序遍历查找
	public HeroNode postOrderSearch(int id) {

		// 如果不等，判断当前结点左子结点是否为空，如果不为空，则递归中序查找。
		HeroNode temp = null;
		if (this.left != null) {
			temp = this.left.postOrderSearch(id);
		}

		// 如果左子结点递归找到。则返回，如果没有找到。判断当前结点的右子结点。
		// 如果不为空，则递归中序查找。
		if (temp != null) {
			// 说明我们已经找到该查找的结点
			return temp;
		}

		// 递归找右子结点
		if (this.right != null) {
			temp = this.right.postOrderSearch(id);
		}
		if (temp != null) {
			// 说明我们已经找到该查找的结点
			return temp;
		}

		System.out.println(this.id);
		// 判断当前结点是不是要找的结点
		if (this.id == id) {
			return this;
		}
		return temp;
	}

	// 步骤分析
	// 1.如果是空树，只有一个root结点，我们直接将二叉树置空即可。
	// 2.因为我们的树是单向的(删除方法很像之前讲的哈希表删除)，
	// 所以我们要判断当前结点的子结点是否要删除。
	// 3.如果当前结点的左子结点不为空，且左子结点就是要删除的节点，
	// 我们将this.left = null;然后返回，(结束递归)
	// 4.如果当前结点的右子结点不为空，且右子结点就是要删除的节点，
	// 我们将this.right = null;然后返回，(结束递归)
	// 5.递归3,4步骤。
	public void remove(int id) {
		// 3.如果当前结点的左子结点不为空，且左子结点就是要删除的节点，
		// 我们将this.left = null;然后返回，(结束递归)
		if (this.left != null && this.left.id == id) {
			this.left = null;
			return;
		}

		// 4.如果当前结点的右子结点不为空，且右子结点就是要删除的节点，
		// 我们将this.right = null;然后返回，(结束递归)
		if (this.right != null && this.right.id == id) {
			this.right = null;
			return;
		}
		// 递归步骤三
		if (this.left != null) {
			this.left.remove(id);
		}
		// 递归步骤四
		if (this.right != null) {
			this.right.remove(id);
		}
	}
}

