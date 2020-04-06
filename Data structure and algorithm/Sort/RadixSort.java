package Sort;

import java.text.SimpleDateFormat;

import java.util.Date;

public class RadixSort {
	public static void main(String[] args) {
		int[] arr = new int[40000000];
		//int[] temp = new int[arr.length];
		for (int i = 0; i < 40000000; i++) {
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
//		int[] arr = {54, 3, 542, 748, 14, 214};
//		System.out.println("排序前:" + Arrays.toString(arr));
//		Sort(arr);
//		System.out.println("排序后:" + Arrays.toString(arr));
	}
	
	public static void Sort(int[] arr) {
		//因为我不知道每个桶应该存放多少。
		//它的存放数即数组的大小
		//基数排序是空间换时间。
		int [][] bucket = new int[10][arr.length];
		int [] bucketCounts = new int[10];
		
		//确定数组中最大的值，然后确定位数，进而确定循环次数
		int max = arr[0];
		for(int i = 1; i < arr.length; i++) {
			if(max < arr[i])
				max = arr[i];
		}
		//确定位数
		int maxLength = String.valueOf(max).length();
		for(int i = 1, n = 1; i <= maxLength; i++, n *= 10) {
			int index = 0;	
			for(int j = 0; j < arr.length; j++) {
				//取对应的位数
				//个位 i = 1, arr[j] / i % 10
				//十位 i = 10, arr[j] / i % 10
				//十位 i = 100, arr[j] / i % 10
				int number = arr[j] / n % 10;
				//给对应的桶赋值
				bucket[number][bucketCounts[number]] = arr[j];
				//记录每个桶的元素的个数
				bucketCounts[number]++;
			}	
			//把记录桶中元素个数的bucketCounts数组遍历
			//如果等于0，说明是空桶，跳过，否则，知道桶中元素的个数，按照个数遍历给原数组。
			for(int j = 0; j < bucketCounts.length; j++) {
				if(bucketCounts[j] != 0) {
					for(int k = 0; k < bucketCounts[j]; k++) {
						arr[index++] = bucket[j][k];
					}
				}
				//一定要加上这句话
				//原来个位是1的数有一个
				//现在十位是1的数有两个
				//counts的值应该是2，可是counts没有被还原到0，所以值是3，会抛出下标越界。
				bucketCounts[j] = 0;
			}

		}
		
		
//		//第一次 按个位存
//		for(int i = 0; i < arr.length; i++) {
//			//取对应的位数
//			int number = arr[i] % 10;
//			//给对应的桶赋值
//			bucket[number][bucketCounts[number]] = arr[i];
//			//记录每个桶的元素的个数
//			bucketCounts[number]++;
//		}
//		int index = 0;		//索引
//		//把记录桶中元素个数的bucketCounts数组遍历
//		//如果等于0，说明是空桶，跳过，否则，知道桶中元素的个数，按照个数遍历给原数组。
//		for(int j = 0; j < bucketCounts.length; j++) {
//			if(bucketCounts[j] != 0) {
//				for(int k = 0; k < bucketCounts[j]; k++) {
//					arr[index++] = bucket[j][k];
//				}
//			}
//			//一定要加上这句话
//			//原来个位是1的数有一个
//			//现在十位是1的数有两个
//			//counts的值应该是2，可是counts没有被还原到0，所以值是3，会抛出下标越界。
//			bucketCounts[j] = 0;
//		}
//		System.out.println("第一轮:" + Arrays.toString(arr));
//		
//		//第二次 按十位存
//		for(int i = 0; i < arr.length; i++) {
//			//取对应的位数
//			int number = arr[i] /10 % 10;
//			//给对应的桶赋值
//			bucket[number][bucketCounts[number]] = arr[i];
//			//记录每个桶的元素的个数
//			bucketCounts[number]++;
//		}
//		index = 0;		//索引
//		//把记录桶中元素个数的bucketCounts数组遍历
//		//如果等于0，说明是空桶，跳过，否则，知道桶中元素的个数，按照个数遍历给原数组。
//		for(int j = 0; j < bucketCounts.length; j++) {
//			if(bucketCounts[j] != 0) {
//				for(int k = 0; k < bucketCounts[j]; k++) {
//					arr[index++] = bucket[j][k];
//				}
//			}
//			bucketCounts[j] = 0;
//		}
//		System.out.println("第二轮:" + Arrays.toString(arr));
//		
//		//第三次 按百位存
//		for(int i = 0; i < arr.length; i++) {
//			//取对应的位数
//			int number = arr[i] / 100;
//			//给对应的桶赋值
//			bucket[number][bucketCounts[number]] = arr[i];
//			//记录每个桶的元素的个数
//			bucketCounts[number]++;
//		}
//		index = 0;		//索引
//		//把记录桶中元素个数的bucketCounts数组遍历
//		//如果等于0，说明是空桶，跳过，否则，知道桶中元素的个数，按照个数遍历给原数组。
//		for(int j = 0; j < bucketCounts.length; j++) {
//			if(bucketCounts[j] != 0) {
//				for(int k = 0; k < bucketCounts[j]; k++) {
//					arr[index++] = bucket[j][k];
//				}
//			}
//			bucketCounts[j] = 0;
//		}
//		System.out.println("第三轮:" + Arrays.toString(arr));
	}
}
