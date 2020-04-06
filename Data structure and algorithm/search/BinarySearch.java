package search;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {
	public static void main(String[] args) {
		int[] arr = {1, 10, 20, 100, 523, 1000, 1234, 1234, 1234};
		System.out.println(Search2(arr, 0, arr.length, 1234));
	}
	
	/**
	 * 
	 * @param arr 被查找的数组
	 * @param left 起始位置
	 * @param right 结束位置
	 * @param searchValue 要查找的值
	 * @return
	 */
	public static int Search(int[] arr, int left, int right, int searchValue) {
		if(left <= right) {
			//如果可以找到，就可以找到中间值
			int mid = (left + right) / 2;
			//判断查找值是否比mid大
			if(searchValue > arr[mid]) {
				return Search(arr, mid + 1, right, searchValue);
			}else if(searchValue < arr[mid]) {
				return Search(arr, left , mid - 1, searchValue);
			}else { //相等，直接返回下标
				return mid;
			}
		}else {
			return -1;
		}
	}
	
	//现在有一个问题，如何获得一个序列中所有相同数的索引。
	//如果我找到我要查找的数，我就左右分，左边查找是否存在我要找的数，右边查找是否存在我要找的数。
	//返回多个值 用 ArrayList接受。
	public static List<Integer> Search2(int[] arr, int left, int right, int searchValue) {
		if(left <= right) {
			//如果可以找到，就可以找到中间值
			int mid = (left + right) / 2;
			//判断查找值是否比mid大
			if(searchValue > arr[mid]) {
				return Search2(arr, mid + 1, right, searchValue);
			}else if(searchValue < arr[mid]) {
				return Search2(arr, left , mid - 1, searchValue);
			}else { //相等，直接返回下标
				List<Integer> searchIndexList = new ArrayList<Integer>();
				//向左边找
				int temp = mid -1;
				while(true) {
					//如果下标超出 或者 值不能与查找值退出循环
					if(temp < 0 || arr[temp] != searchValue) {
						break;
					}
					searchIndexList.add(temp);
					//往前移
					temp -= 1;
				}
				
				//根据mid 来分组，则mid也要存
				searchIndexList.add(mid);
				
				//向右查找
				temp = mid + 1;
				while(true) {
					//如果下标超出 或者 值不能与查找值退出循环
					if(temp > arr.length -1 || arr[temp] != searchValue) {
						break;
					}
					searchIndexList.add(temp);
					//往后移
					temp += 1;
				}
				return searchIndexList;
			}
		}else {
			return new ArrayList<Integer>();
		}
	}
}
