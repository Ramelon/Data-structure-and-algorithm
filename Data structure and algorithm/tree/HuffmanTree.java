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

	//前序遍历
	public static void preOrder(Node2 root) {
		if(root != null) {
			root.preOrder();
		}else {
			System.out.println("空树");
		}
	}
	
	public static Node2 createHuffmanTree(int arr[]) {
		// 会对数组进行删除，取出 ，赋值，
		// 如果用传统数组就非常的不方便，所以我们List来帮助我们操作。
		List<Node2> nodes = new ArrayList<Node2>();
		// 增加for 型循环
		for (int data : arr) {
			// N个权值节点
			nodes.add(new Node2(data));
		}
		while (nodes.size() > 1) {
			Collections.sort(nodes);
			//System.out.println(nodes);
			// 在森林中选出两个根结点的权值最小的树合并，作为一棵新树的左、右子树，
			Node2 leftNode = nodes.get(0);
			//System.out.println(leftNode);
			Node2 righttNode = nodes.get(1);
			//System.out.println(righttNode);
			// 且新树的根结点权值为其左、右子树根结点权值之和；
			Node2 parentNode = new Node2(leftNode.data + righttNode.data);
			parentNode.left = leftNode;
			parentNode.right = righttNode;
			// 从森林中删除选取的两棵树，并将新树加入森林；
			nodes.remove(leftNode);
			nodes.remove(righttNode);

			nodes.add(parentNode);
			
		}

		// 此时只需返回根节点，根节点里有指向。
		return nodes.get(0);
	}
}

// Collections进行集合排序。
class Node2 implements Comparable<Node2> {
	// 该节点的权值
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
		// 从小到大排序。
		return this.data - o.data;
	}

	// 前序遍历
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
