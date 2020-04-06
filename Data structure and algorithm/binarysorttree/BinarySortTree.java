package binarysorttree;

public class BinarySortTree {
	public static void main(String[] args) {
		int[] arr = { 8, 3, 6, 4, 7, 1, 10, 14, 13 };
		BinarySortTreeDemo binarySortTreeDemo = new BinarySortTreeDemo();
		for (int i = 0; i < arr.length; i++) {
			binarySortTreeDemo.add(new Node(arr[i]));
		}

		// �������
		binarySortTreeDemo.infixOrder();

		// ɾ��Ҷ�ӽ��
		binarySortTreeDemo.removeNode(1);
		binarySortTreeDemo.removeNode(7);
		binarySortTreeDemo.removeNode(13);
		binarySortTreeDemo.removeNode(14);
		System.out.println("ɾ����");
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
