package balancedbinarytree;

public class BanlancedBinaryTree {
	public static void main(String[] args) {
		int[] arr = { 7, 6, 10, 8, 11, 9 };// 2, 1, 4, 3, 5, 6
		BanlancedBinaryTreeDemo banlancedBinaryTreeDemo = new BanlancedBinaryTreeDemo();
		for (int i = 0; i < arr.length; i++) {
			banlancedBinaryTreeDemo.add(new Node(arr[i]));
		}

		banlancedBinaryTreeDemo.infixOrder();
		System.out.println("�������ĸ߶ȣ�" + banlancedBinaryTreeDemo.getRoot().height());
		System.out.println("�������ĸ߶ȣ�" + banlancedBinaryTreeDemo.getRoot().left.height());
		System.out.println("�������ĸ߶ȣ�" + banlancedBinaryTreeDemo.getRoot().right.height());
		System.out.println("����㣺           " + banlancedBinaryTreeDemo.getRoot());
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
			System.out.println("����������Ϊ��");
		}
	}

	// �鵽Ҫɾ���Ľ��
	public Node search(int value) {
		if (root == null) {
			return null;
		} else {
			return root.serach(value);
		}
	}

	// �鵽Ҫɾ���Ľ��ĸ����
	public Node serchParent(int value) {
		if (root == null) {
			return null;
		} else {
			return root.searchParent(value);
		}
	}

	// node: ����root�ڵ���ʹ��
	// �ҵ���һ��������Сֵ
	public int removeMinNode(Node node) {
		Node targetNode = node;
		// ѭ���ҵ���С���
		while (targetNode.left != null) {
			targetNode = targetNode.left;
		}
		// ɾ����С���
		removeNode(targetNode.value);
		return targetNode.value;
	}

	// ����ʵ��ɾ�����
	public void removeNode(int value) {
		if (root == null) {
			return;
		} else {
			// �鵽Ҫɾ���Ľ��
			Node targetNode = search(value);
			// ���û���ҵ�Ҫɾ���Ľ��ֱ�ӷ���
			if (targetNode == null) {
				return;
			}
			// �ҵ������
			Node parent = serchParent(value);
			System.out.println(parent);
			// 1������ɾ������Ҷ�ӽ��
			// targetNode.left targetNode.right ��Ϊ�� ����Ҷ�ӽ�� ֱ��ɾ��
			if (targetNode.left == null && targetNode.right == null) {
				// ����Ҫ�ж����Ҷ�ӽ���Ǹ��������ӽڵ� �������ӽ�㡣
				if (parent.left != null && parent.left.value == value) {
					parent.left = null;
				} else if (parent.right != null && parent.right.value == value) {
					parent.right = null;
				}
			} else if (targetNode.left != null && targetNode.right != null) { // ˵������Ҫɾ���Ľڵ�����������
				// targetNode.right ��Ϊ���ǰ��������������ʹ��
				int minvalue = removeMinNode(targetNode.right);
				targetNode.value = minvalue;
			} else { // ɾ��һ�����Ľڵ�
				if (targetNode.left != null) {
					if (parent != null) {
						// �ж�targetNode�����ӽ�㻹�����ӽ��
						// ����һ��Ҫע�� һ����˳ʱ��һ������ʱ��
						if (parent.left.value == value) {
							parent.left = targetNode.left;
						} else { // parent.right.value == value
							// �ҵĸ���� �ұ� ��Ҫָ�� ��Ҫɾ���ڵ�����
							// ��Ϊ��Ҫɾ���ڵ��������С��
							parent.right = targetNode.left;
						}
					} else {
						// ����˵��ֻ�������ڵ㡣
						// �����Ҫɾ��
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

	// ���ص�ǰ�������������ĸ߶�
	public int leftHeight() {
		if (left == null) {
			return 0;
		}
		return left.height();
	}

	// ���ص�ǰ�������������ĸ߶�
	public int rightHeight() {
		if (right == null) {
			return 0;
		}
		return right.height();
	}

	// ���ص�ǰ���ĸ߶�
	public int height() {
		return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
	}

	// ����ת
	public void leftRotate() {
		// �����µĽ�㣬 ��ô�µĽ���ֵ���ǵ�ǰ����ֵ��
		Node newNode = new Node(value);
		// �µĽ���������ָ����ǵ�ǰ����������
		newNode.left = this.left;
		// �µĽ���������ָ��ǰ������������������
		newNode.right = this.right.left;
		// �ѵ�ǰ����ֵת���ɵ�ǰ�������ӽڵ��ֵ
		this.value = this.right.value;
		// �ѵ�ǰ����������ָ��ǰ������������������
		this.right = this.right.right;
		// ���õ�ǰ���������� ʹ�����´����Ľ��
		this.left = newNode;
	}

	// ����ת
	public void rightRotate() {
		// �����µĽ�㣬 ��ô�µĽ���ֵ���ǵ�ǰ����ֵ��
		Node newNode = new Node(value);
		// �µĽ���������ָ����ǵ�ǰ����������
		newNode.right = this.right;
		// �µĽ���������ָ��ǰ������������������
		newNode.left = this.left.right;
		// �ѵ�ǰ����ֵת���ɵ�ǰ�������ӽڵ��ֵ
		this.value = this.left.value;
		// �ѵ�ǰ����������ָ��ǰ������������������
		this.left = this.left.left;
		// ���õ�ǰ���������� ʹ�����´����Ľ��
		this.right = newNode;
	}

	// ������� �����˳�� ����������˳��
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
		// ������Ҫһ������� ��Ȼֱ�ӷ���null
		if (node == null) {
			return;
		}
		// node.value
		if (this.value > node.value) {
			if (this.left == null) {
				// ��������
				this.left = node;
			} else {// �ݹ������� �ҵ����ʵ�λ����ӡ�
				this.left.add(node);
			}
		} else {
			if (this.right == null) {
				this.right = node;
			} else {
				this.right.add(node);
			}
		}
		// ����������߶Ⱥ��������߶Ȳ����1�� ��ô˵������ƽ������� ����������Ҫ����ת
		if (rightHeight() - leftHeight() > 1) {
			// 1.�����ǰ�����������������������������ĸ߶�
			if (right != null && this.right.rightHeight() > this.left.leftHeight()) {
				// ��Ҫ�ѵ�ǰ��������������ת
				right.rightRotate();
				// ��������ת
				leftRotate();
			} else {
				leftRotate();
			}
			return;
		}

		// ����������߶Ⱥ��������߶Ȳ����1�� ��ô˵������ƽ������� ����������Ҫ����ת
		if (leftHeight() - rightHeight() > 1) {
			// 1.�����ǰ�����������������������������ĸ߶�
			if (left != null && this.left.rightHeight() > this.left.leftHeight()) {
				// ��ǰ��������������ת
				left.leftRotate();
				// ��������ת
				rightRotate();
			} else {
				rightRotate();
			}
			return;
		}
	}

	// ������Ҫ�ҵ�����Ҫɾ���Ľ�㡣
	public Node serach(int value) {
		if (this.value == value) {
			return this;
		} else if (this.value > value) {
			// �жϵ�ǰ�������ӽ���Ƿ�Ϊ��
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

	// Ȼ���ҵ�ɾ���Ľ��ĸ���㡣
	public Node searchParent(int value) {
		// ��ǰ����Ƿ�Ϊ����Ҫɾ�����ĸ����
		if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
			return this;
		} else {
			// �ݹ������� �ҵ������
			if (this.left != null && this.value > value) {
				return this.left.searchParent(value);
			} else if (this.right != null && this.value <= value) {// �ݹ��������ҵ������
				return this.right.searchParent(value);
			} else {
				// û���ҵ���
				return null;
			}
		}
	}
}
