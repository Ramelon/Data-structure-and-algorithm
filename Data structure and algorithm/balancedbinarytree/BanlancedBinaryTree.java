package balancedbinarytree;

public class BanlancedBinaryTree {
	public static void main(String[] args) {
		int[] arr = { 7, 6, 10, 8, 11, 9 };// 2, 1, 4, 3, 5, 6
		BanlancedBinaryTreeDemo banlancedBinaryTreeDemo = new BanlancedBinaryTreeDemo();
		for (int i = 0; i < arr.length; i++) {
			banlancedBinaryTreeDemo.add(new Node(arr[i]));
		}

		banlancedBinaryTreeDemo.infixOrder();
		System.out.println("整棵树的高度：" + banlancedBinaryTreeDemo.getRoot().height());
		System.out.println("左子树的高度：" + banlancedBinaryTreeDemo.getRoot().left.height());
		System.out.println("右子树的高度：" + banlancedBinaryTreeDemo.getRoot().right.height());
		System.out.println("根结点：           " + banlancedBinaryTreeDemo.getRoot());
	}
}

class BanlancedBinaryTreeDemo {
	private Node root;

	public Node getRoot() {
		return root;
	}

	public void add(Node node) {
		if (root == null) {
			root = node;
		} else {
			root.add(node);
		}
	}

	public void infixOrder() {
		if (root != null) {
			root.infixOrder();
		} else {
			System.out.println("二叉排序树为空");
		}
	}

	// 查到要删除的结点
	public Node search(int value) {
		if (root == null) {
			return null;
		} else {
			return root.serach(value);
		}
	}

	// 查到要删除的结点的父结点
	public Node serchParent(int value) {
		if (root == null) {
			return null;
		} else {
			return root.searchParent(value);
		}
	}

	// node: 当做root节点来使用
	// 找到这一部分中最小值
	public int removeMinNode(Node node) {
		Node targetNode = node;
		// 循环找到最小结点
		while (targetNode.left != null) {
			targetNode = targetNode.left;
		}
		// 删除最小结点
		removeNode(targetNode.value);
		return targetNode.value;
	}

	// 具体实现删除结点
	public void removeNode(int value) {
		if (root == null) {
			return;
		} else {
			// 查到要删除的结点
			Node targetNode = search(value);
			// 如果没有找到要删除的结点直接返回
			if (targetNode == null) {
				return;
			}
			// 找到父结点
			Node parent = serchParent(value);
			System.out.println(parent);
			// 1、我们删除的是叶子结点
			// targetNode.left targetNode.right 都为空 则是叶子结点 直接删除
			if (targetNode.left == null && targetNode.right == null) {
				// 我们要判断这个叶子结点是父结点的左子节点 还是右子结点。
				if (parent.left != null && parent.left.value == value) {
					parent.left = null;
				} else if (parent.right != null && parent.right.value == value) {
					parent.right = null;
				}
			} else if (targetNode.left != null && targetNode.right != null) { // 说明我们要删除的节点是有两棵树
				// targetNode.right 因为我是把它当做根结点来使用
				int minvalue = removeMinNode(targetNode.right);
				targetNode.value = minvalue;
			} else { // 删除一个树的节点
				if (targetNode.left != null) {
					if (parent != null) {
						// 判断targetNode是左子结点还是右子结点
						// 这里一定要注意 一边是顺时针一边是逆时针
						if (parent.left.value == value) {
							parent.left = targetNode.left;
						} else { // parent.right.value == value
							// 我的父结点 右边 是要指向 我要删除节点的左边
							// 因为我要删除节点左边是最小的
							parent.right = targetNode.left;
						}
					} else {
						// 比如说我只有三个节点。
						// 根结点要删除
						root = targetNode.left;
					}
				} else { // targetNode.right != null
					if (parent != null) {
						if (parent.left.value == value) {
							parent.left = targetNode.right;
						} else {
							parent.right = targetNode.right;
						}
					} else {
						root = targetNode.right;
					}
				}
			}

		}
	}
}

class Node {
	int value;
	Node left;
	Node right;

	public Node(int value) {
		// TODO Auto-generated constructor stub
		this.value = value;
	}

	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}

	// 返回当前结点的左子树结点的高度
	public int leftHeight() {
		if (left == null) {
			return 0;
		}
		return left.height();
	}

	// 返回当前结点的右子树结点的高度
	public int rightHeight() {
		if (right == null) {
			return 0;
		}
		return right.height();
	}

	// 返回当前结点的高度
	public int height() {
		return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
	}

	// 左旋转
	public void leftRotate() {
		// 创建新的结点， 那么新的结点的值就是当前结点的值。
		Node newNode = new Node(value);
		// 新的结点的左子树指向的是当前结点的左子树
		newNode.left = this.left;
		// 新的结点的右子树指向当前结点的右子树的左子树
		newNode.right = this.right.left;
		// 把当前结点的值转换成当前结点的右子节点的值
		this.value = this.right.value;
		// 把当前结点的右子树指向当前结点的右子树的右子树
		this.right = this.right.right;
		// 设置当前结点的左子树 使我们新创建的结点
		this.left = newNode;
	}

	// 右旋转
	public void rightRotate() {
		// 创建新的结点， 那么新的结点的值就是当前结点的值。
		Node newNode = new Node(value);
		// 新的结点的右子树指向的是当前结点的右子树
		newNode.right = this.right;
		// 新的结点的左子树指向当前结点的左子树的右子树
		newNode.left = this.left.right;
		// 把当前结点的值转换成当前结点的左子节点的值
		this.value = this.left.value;
		// 把当前结点的左子树指向当前结点的左子树的右子树
		this.left = this.left.left;
		// 设置当前结点的右子树 使我们新创建的结点
		this.right = newNode;
	}

	// 中许遍历 输出的顺序 就是排序后的顺序
	public void infixOrder() {
		if (this.left != null) {
			this.left.infixOrder();
		}
		System.out.println(this);
		if (this.right != null) {
			this.right.infixOrder();
		}
	}

	public void add(Node node) {
		// 我们需要一个根结点 不然直接返回null
		if (node == null) {
			return;
		}
		// node.value
		if (this.value > node.value) {
			if (this.left == null) {
				// 遍历后移
				this.left = node;
			} else {// 递归左子树 找到合适的位置添加。
				this.left.add(node);
			}
		} else {
			if (this.right == null) {
				this.right = node;
			} else {
				this.right.add(node);
			}
		}
		// 如果右子树高度和左子树高度差大于1， 那么说明不是平衡二叉树 ，所以我们要左旋转
		if (rightHeight() - leftHeight() > 1) {
			// 1.如果当前结点的右子树的左子树大于右子树的高度
			if (right != null && this.right.rightHeight() > this.left.leftHeight()) {
				// 需要把当前结点的右子树右旋转
				right.rightRotate();
				// 整体左旋转
				leftRotate();
			} else {
				leftRotate();
			}
			return;
		}

		// 如果左子树高度和右子树高度差大于1， 那么说明不是平衡二叉树 ，所以我们要右旋转
		if (leftHeight() - rightHeight() > 1) {
			// 1.如果当前结点的左子树的右子树大于左子树的高度
			if (left != null && this.left.rightHeight() > this.left.leftHeight()) {
				// 当前结点的左子树左旋转
				left.leftRotate();
				// 整体右旋转
				rightRotate();
			} else {
				rightRotate();
			}
			return;
		}
	}

	// 我们需要找到我们要删除的结点。
	public Node serach(int value) {
		if (this.value == value) {
			return this;
		} else if (this.value > value) {
			// 判断当前结点的左子结点是否为空
			if (this.left == null) {
				return null;
			}
			return this.left.serach(value);
		} else {
			if (this.right == null) {
				return null;
			}
			return this.right.serach(value);
		}
	}

	// 然后找到删除的结点的父结点。
	public Node searchParent(int value) {
		// 当前结点是否为我们要删除结点的父结点
		if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
			return this;
		} else {
			// 递归左子树 找到父结点
			if (this.left != null && this.value > value) {
				return this.left.searchParent(value);
			} else if (this.right != null && this.value <= value) {// 递归右子树找到父结点
				return this.right.searchParent(value);
			} else {
				// 没有找到。
				return null;
			}
		}
	}
}
