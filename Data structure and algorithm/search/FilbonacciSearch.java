package search;

import java.util.Arrays;

public class FilbonacciSearch {
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 5, 8, 10, 50, 222, 1222 };
		// System.out.println(Arrays.toString(Filbonacci()));
		System.out.println(Search(arr, 1222));
	}

	// 创建斐波那契数列
	public static int[] Filbonacci() {
		int[] k = new int[20];
		k[0] = 1;
		k[1] = 1;
		for (int i = 2; i < k.length; i++) {
			k[i] = k[i - 1] + k[i - 2];
		}
		return k;
	}

	// 查找
	public static int Search(int[] arr, int searchValue) {
		int low = 0; // 左边位置
		int high = arr.length - 1; // 右边位置
		int mid = 0; // 中间数
		int k = 0; // 斐波那契索引
		int F[] = Filbonacci(); // 斐波那契数列
		// 找到在斐波那契数列找一个略大于查找表中元素个数的数F[k];
		while (high > F[k] - 1) {
			k++;
		}

		// 将原查找表扩展为长度为F[n](如果要补充元素，则补充重复最后一个元素，直到满足F[n]个元素)
		int[] temp = Arrays.copyOf(arr, F[k]); // 如果长度不满足则自动补0 {0,1} 3个 {0,1,0} => {0,1,1}
		for (int i = high + 1; i < temp.length; i++) {
			temp[i] = arr[high];
		}

		while (low <= high) {
			mid = low + F[k - 1] - 1;
			if (searchValue > temp[mid]) {
				low = mid + 1;
				//F[k] = F[k-1] + F[k-2]
				//1 1 2 3 5 8 13 21 
				//1 2 3  、、  4 5 6 7 8
				k -= 2;
			} else if (searchValue < temp[mid]) {
				high = mid - 1;
				k -= 1;
			} else {
				//需要确定，返回下标
				if (mid <= high) {
					return mid;
				} else {
					return high;
				}
			}
		}
		return -1;
	}
}
