package algorithm;

public class BinarySerrch {
	public static void main(String[] args) {
		int arr[] = { 1, 5, 55, 61, 124, 165, 999 };
		int index = binarySearchDemo(arr, 111);
		if(index == -1) {
			System.out.println("未找到需查找的值");
		}else {
			System.out.println("找到:" + arr[index] + ",下标为:" + index);
		}
	}

	// 首先，假设表中元素是按升序排列，将表中间位置记录的关键字与查找关键字比较，如果两者相等，
	// 则查找成功；否则利用中间位置记录将表分成前、后两个子表，如果中间位置记录的关键字大于查
	// 找关键字，则进一步查找前一子表，否则进一步查找后一子表。重复以上过程，直到找到满足条件
	// 的记录，使查找成功，或直到子表不存在为止，此时查找不成功。

	/**
	 * 
	 * @param arr
	 *            被查找的数组(升序)
	 * @param searchValue
	 *            (要查找的值)
	 * @return 如果找到，返回对应的下标。如果没找到，返回-1
	 */
	public static int binarySearchDemo(int[] arr, int searchValue) {
		int left = 0;
		int right = arr.length - 1;

		while (left <= right) {
			int mid = (left + right) / 2;
			if (searchValue == arr[mid]) {
				return mid;
			} else if (arr[mid] > searchValue) {
				// 往左找
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return -1;
	}
}
