package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = {27,8,15,15,30,5};
		Node2 root = createHuffmanTree(arr);
		//System.out.println(root.right.right.right);
		//System.out.println(root.right.right.left);
		preOrder(root);
	}

	//ǰ�����
	public static void preOrder(Node2 root) {
		if(root != null) {
			root.preOrder();
		}else {
			System.out.println("����");
		}
	}
	
	public static Node2 createHuffmanTree(int arr[]) {
		// ����������ɾ����ȡ�� ����ֵ��
		// ����ô�ͳ����ͷǳ��Ĳ����㣬��������List���������ǲ�����
		List<Node2> nodes = new ArrayList<Node2>();
		// ����for ��ѭ��
		for (int data : arr) {
			// N��Ȩֵ�ڵ�
			nodes.add(new Node2(data));
		}
		while (nodes.size() > 1) {
			Collections.sort(nodes);
			//System.out.println(nodes);
			// ��ɭ����ѡ������������Ȩֵ��С�����ϲ�����Ϊһ������������������
			Node2 leftNode = nodes.get(0);
			//System.out.println(leftNode);
			Node2 righttNode = nodes.get(1);
			//System.out.println(righttNode);
			// �������ĸ����ȨֵΪ���������������Ȩֵ֮�ͣ�
			Node2 parentNode = new Node2(leftNode.data + righttNode.data);
			parentNode.left = leftNode;
			parentNode.right = righttNode;
			// ��ɭ����ɾ��ѡȡ����������������������ɭ�֣�
			nodes.remove(leftNode);
			nodes.remove(righttNode);

			nodes.add(parentNode);
			
		}

		// ��ʱֻ�践�ظ��ڵ㣬���ڵ�����ָ��
		return nodes.get(0);
	}
}

// Collections���м�������
class Node2 implements Comparable<Node2> {
	// �ýڵ��Ȩֵ
	int data;

	Node2 left;
	Node2 right;

	public Node2(int data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Node [data=" + data + "]";
	}

	@Override
	public int compareTo(Node2 o) {
		// TODO Auto-generated method stub
		// ��С��������
		return this.data - o.data;
	}

	// ǰ�����
	public void preOrder() {
		System.out.println(this);
		if(this.left != null) {
			this.left.preOrder();
		}
		if(this.right != null) {
			this.right.preOrder();
		}
	}
}
