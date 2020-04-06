package Sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertSort {

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
//		System.out.println("排序前：" + Arrays.toString(arr));
//		Sort(arr);
//		System.out.println("排序后：" + Arrays.toString(arr));
	}

	public static void Sort(int[] arr) {
		int insertval = 0;
		int insertindex = 0;
		for(int i = 1; i < arr.length; i++) {
			insertval = arr[i]; //把插入的值付给insertvalue
			insertindex = i - 1; //arr[1] arr[0] 
			
			//inserindex >= 0 保证能找到插入的位置
			//insertvalue < arr[inserindex] arr[insertindex] 后移 原本的位置就给insertvalue
			//indsertvalue > arr[indertindex] arr[insertindex]不用后移
			while(insertindex >=0 && insertval < arr[insertindex]) { 
				//后移
				arr[insertindex + 1] = arr[insertindex];
				insertindex --;
			}
			//退出while循环，说明找到了位置，赋值
			if(insertindex + 1 != i) {
				arr[insertindex + 1] = insertval;
			}
		}
	}
}
