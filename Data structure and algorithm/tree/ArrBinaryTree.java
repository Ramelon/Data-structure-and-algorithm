package tree;

public class ArrBinaryTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = { 1, 2, 3, 4, 5, 6 };
		ArrBinaryTreeDemo arrBinaryTreeDemo = new ArrBinaryTreeDemo(arr);
		arrBinaryTreeDemo.preOrder(); //1,2,4,5,3,6
	}

}

class ArrBinaryTreeDemo {
	// �洢��������
	private int[] arr;

	public ArrBinaryTreeDemo(int[] arr) {
		this.arr = arr;
	}

	// ����
	public void preOrder() {
		this.preOrder(0);
	}
	// ǰ��
	public void preOrder(int index) {
		// ���������������Ϊ��
		// arr.length = 0
		if (arr == null || arr.length == 0) {
			System.out.println("����Ϊ�գ������޷�������һ������");
		}

		// ǰ�� DLR
		// �����ǰԪ��
		System.out.println(arr[index]);
		// ���ӽ��ݹ�
		if ((2 * index + 1) < arr.length) {
			preOrder(2 * index + 1);
		}
		// ���ӽ��ݹ�
		if ((2 * index + 2) < arr.length) {
			preOrder(2 * index + 2);
		}
	}
	// ����

	// ����
}