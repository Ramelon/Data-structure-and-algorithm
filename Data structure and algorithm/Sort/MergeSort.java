package Sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MergeSort {
	public static void main(String[] args) {
		int[] arr = new int[8000000];
		int[] temp = new int[arr.length];
		for (int i = 0; i < 8000000; i++) {
			arr[i] = (int)(Math.random()* 800000);
		}
		Date data1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String data11 = simpleDateFormat.format(data1);
		System.out.println("排序前时间:"+data11);
		
		Sort(arr, 0, arr.length - 1, temp);
		
		Date data2 = new Date();
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String data22 = simpleDateFormat2.format(data2);
		System.out.println("排序后时间:"+data22);
//		int[] arr = {5, 4, 7, 9, 3, 8, 2, 1};
//		System.out.println("排序前:" + Arrays.toString(arr));
//		int[] temp = new int[arr.length];
//		Sort(arr, 0, arr.length - 1, temp);
//		System.out.println("排序后:" + Arrays.toString(arr));
	}
	
	/**
	 * 
	 * @param arr	要排序的数组
	 * @param left	数组左边
	 * @param mid	数组中间索引
	 * @param right	数组右边索引
	 * @param temp	临时数组
	 */
	public static void Sort(int[] arr,int left, int right, int[] temp) {
		if(left < right) {
			int mid = (right + left) / 2;
			//左边
			Sort(arr, left, mid, temp);
			//右边
			Sort(arr, mid + 1, right, temp);
			meger(arr, left, mid, right, temp);
		}
	}
	
	public static void meger(int[] arr, int left, int mid, int right, int[] temp) {
		int leftIndex = left;
		int rightIndex = mid + 1;
		int k = 0;
		
		//把左边和右边两个序列合并成一个有序的序列
		while(leftIndex <= mid && rightIndex <= right ) {
			//如果左边元素大于右边交换，索引++
			if(arr[leftIndex] <= arr[rightIndex]) {
				temp[k++] = arr[leftIndex++];
			}else
				temp[k++] = arr[rightIndex++];
		}
		
		//把左边剩余数据放入temp
		while( leftIndex <= mid ) {
			temp[k++] = arr[leftIndex++];
		}
		//把右边剩余数据放入temp
		while( rightIndex <= right ) {
			temp[k++] = arr[rightIndex++];
		}
		
		//把temp数组里的数据赋值给原数组
		k = 0;
		int templeft = left;
		while(templeft <= right) {
			arr[templeft++] = temp[k++];
		}
 	}
}
