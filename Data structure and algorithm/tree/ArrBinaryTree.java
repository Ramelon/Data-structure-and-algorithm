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
	// 存储结点的数组
	private int[] arr;

	public ArrBinaryTreeDemo(int[] arr) {
		this.arr = arr;
	}

	// 重载
	public void preOrder() {
		this.preOrder(0);
	}
	// 前序
	public void preOrder(int index) {
		// 如果传进来的数组为空
		// arr.length = 0
		if (arr == null || arr.length == 0) {
			System.out.println("数组为空，我们无法进行下一步操作");
		}

		// 前序 DLR
		// 输出当前元素
		System.out.println(arr[index]);
		// 左子结点递归
		if ((2 * index + 1) < arr.length) {
			preOrder(2 * index + 1);
		}
		// 右子结点递归
		if ((2 * index + 2) < arr.length) {
			preOrder(2 * index + 2);
		}
	}
	// 中序

	// 后序
}