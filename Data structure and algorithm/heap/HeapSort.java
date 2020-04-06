package heap;

import java.util.Arrays;

public class HeapSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = { 8, 13, 15, 11, 7, 9 };
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}

	public static void sort(int arr[]) {
		int temp = 0;
		// 将一个无序序列构建成一个大顶堆
		for (int i = arr.length / 2 - 1; i >= 0; i--) {
			adjustHeap(arr, i, arr.length);
		}

		// 排序序列，将堆顶的元素值和尾部的元素交换
		// 重构最大堆
		for (int j = arr.length - 1; j > 0; j--) {
			temp = arr[j];
			arr[j] = arr[0];
			arr[0] = temp;
			adjustHeap(arr, 0, j);
		}
	}

	// arr.length / 2 - 1
	// 第子树将一个无序序列构建成一个大顶堆
	public static void adjustHeap(int arr[], int i, int length) {
		// 判断当前结点与子结点的大小。
		int temp = arr[i];

		// k = 2*i+1
		// 大顶堆：arr[i] >= arr[2*i+1] && arr[i] >= arr[2*i+2]
		for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
			// 判断左子节点 右子结点谁大
			if (k + 1 < length && arr[k] > arr[k + 1]) { // 第一个位置
				// 右子结点大，我们指向右子结点
				k++;
			}
			if (arr[k] < temp) {  // 第二个位置
				// 子结点大于父结点， 子结点赋值父结点
				arr[i] = arr[k];
				// 记录当前我temp应当赋值的地方
				i = k;
			} else {
				break;
			}
		}
		// 在循环结束后才能做最后的值赋值
		// 和最大值交换位置
		arr[i] = temp;
	}
}
