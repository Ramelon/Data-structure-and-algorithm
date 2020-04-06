package binarysorttree;

public class BinarySortTree {
	public static void main(String[] args) {
		int[] arr = { 8, 3, 6, 4, 7, 1, 10, 14, 13 };
		BinarySortTreeDemo binarySortTreeDemo = new BinarySortTreeDemo();
		for (int i = 0; i < arr.length; i++) {
			binarySortTreeDemo.add(new Node(arr[i]));
		}

		// 中序遍历
		binarySortTreeDemo.infixOrder();

		// 删除叶子结点
		binarySortTreeDemo.removeNode(1);
		binarySortTreeDemo.removeNode(7);
		binarySortTreeDemo.removeNode(13);
		binarySortTreeDemo.removeNode(14);
		System.out.println("删除后");
		binarySortTreeDemo.infixOrder();


	}
}

class BinarySortTreeDemo {
	private Node root;

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
