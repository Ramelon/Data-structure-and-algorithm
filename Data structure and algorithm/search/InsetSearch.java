package search;

public class InsetSearch {
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 30 };
		System.out.println("�±꣺" + Search(arr, 0, arr.length - 1, 2));
	}

	public static int Search(int[] arr, int left, int right, int searchValue) {
		System.out.println("������һ��");
		if (left <= right) {
			//int mid = (left + right) / 2;
			// ����Ӧϵ��,��߲��ҵ�Ч�ʡ�
			int mid = left + (searchValue - arr[left]) / (arr[right] - arr[left]) * (right - left);
			if (searchValue > arr[mid]) {
				return Search(arr, mid + 1, right, searchValue);
			} else if (searchValue < arr[mid]) {
				return Search(arr, left, mid - 1, searchValue);
			} else {
				return mid;
			}
		} else {
			return -1;
		}
	}
}
