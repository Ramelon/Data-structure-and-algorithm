package Sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class QuickSort {
	public static void main(String[] args) {
		int[] arr = new int[8000000];
		for (int i = 0; i < 8000000; i++) {
			arr[i] = (int)(Math.random()* 800000);
		}
		Date data1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String data11 = simpleDateFormat.format(data1);
		System.out.println("排序前时间:"+data11);
		
		Sort(arr,0,arr.length-1);
		
		Date data2 = new Date();
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String data22 = simpleDateFormat2.format(data2);
		System.out.println("排序后时间:"+data22);
//		int[] arr = {49, 38, 65, 97, 76, 13, 27, 49};
//		System.out.println("排序前：" + Arrays.toString(arr));
//		Sort(arr, 0, arr.length - 1);
//		System.out.println("排序后：" + Arrays.toString(arr));
	}
	/**
	 * 
	 * @param arr	传进来要排序的数组
	 * @param begin	左位置
	 * @param end	右位置
	 */
	public static void Sort(int[] arr, int begin, int end) {
		if(begin < end) {
			//定义一个基准数
			int temp = arr[begin];
			int left = begin; 
			int right = end;
			while(left < right) {
				//处理右边
				//找到右边所有小于基准数的元素
				//退出循环时，右边都是大于基准数的元素
				//把和基准数想通过的元素也在放右边
				while(left < right && arr[right] >= temp) {
					//获得小于基准数的下标
					right--;
				}
				//把右边小于基准数的元素给左边
				arr[left] = arr[right];
				//处理左边
				//找到左边边所有小于基准数的元素
				//退出循环时，左边都小于基准数的元素
				while(left < right && arr[left] < temp) {
					//左边递增，所以要++
					left++; 
				}
				//把左边大于基准数的元素给右边
				arr[right] = arr[left];
			}
			arr[left] = temp;
			//划分为两部分。
			//处理左边部分用到递归。
			Sort(arr, begin, left - 1);
			//处理右边
			Sort(arr, left + 1, end);
		}else {
			return;
		}
	}
}
