package Sort;

import java.text.SimpleDateFormat;
import java.util.*;

public class BubbleSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int[] arr = {2, 7, -1, 10, -2, 4};
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
		//System.out.println(Arrays.toString(arr));
	}
	
	public static void Sort(int[] arr) {
		int temp = 0; //中介 a,b c=a; a=b; b=c
		for(int i = 0; i < arr.length - 1; i++) {
			//arr.length -i -1
			for(int j = 0; j < arr.length -i -1; j++) { 
				if( arr[j] > arr[j+1] ) {
					temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j+1] = temp;
				}
			}
		}
	}

}
