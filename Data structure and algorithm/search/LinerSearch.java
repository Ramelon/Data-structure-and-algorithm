package search;

public class LinerSearch {
	public static void main(String[] args) {
		//数组无顺序
		int arr[] = {-1, 3, 7, 8, 9, 2, 5};
		int index = Search(arr, 5);
		if(index == -1) {
			System.out.println("没有找到");
		}else {
			System.out.println("下标为:" + index);
		}

	}
	
	/**
	 * 
	 * @param arr 被查找的数组
	 * @param searchValue 要查找的值
	 * @return
	 */
	public static int Search(int[] arr, int searchValue) {
		for(int i = 0; i < arr.length; i++) {
			//如果找到值，则返回对应的下表
			if(arr[i] == searchValue) {
				return i;
			}
		}
		//没有找到，返回-1
		return -1;
	}
}	
