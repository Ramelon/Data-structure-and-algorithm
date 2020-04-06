package Sort;

import java.text.SimpleDateFormat;

import java.util.Date;

public class SelectSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = new int[80000];
		for (int i = 0; i < 80000; i++) {
			arr[i] = (int)(Math.random()* 800000);
		}
		
		Date data1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String data11 = simpleDateFormat.format(data1);
		System.out.println("排序前时间:"+data11);
		
		Sort(arr);
		
		Date data2 = new Date();
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String data22 = simpleDateFormat2.format(data2);
		System.out.println("排序后时间:"+data22);
//		int[] arr = {2, 7, -1, 10, -2, 4};
//		
//		System.out.println("排序前:" + Arrays.toString(arr));
//		Sort(arr);
//		System.out.println("排序后:" + Arrays.toString(arr));
	}
	
	public static void Sort(int[] arr) {
		int temp = 0;
		for(int i = 0; i < arr.length - 1; i++) {
			int index = i;
			for(int j = i + 1; j < arr.length; j++) {
				if( arr[index] > arr[j] ) {
					index = j;
				}
			}
			//将最小的值交换
			if(index != i) {
				temp = arr[i];
				arr[i] = arr[index];
				arr[index] = temp;
			}
			
		}
	}
}
