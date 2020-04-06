package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class GreedyAlgorithm {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 贪心算法解决01背包问题。
		int capacity = 150;
		int[] weights = { 35, 30, 60, 50, 40, 10, 25 };
		int[] values = { 10, 40, 30, 50, 35, 40, 30 };
		GreedyAlgorithmDemo(capacity, weights, values);
	}

	/**
	 * 
	 * @param capacity
	 *            背包的容量
	 * @param weights
	 *            商品的重量
	 * @param values
	 *            商品的价值
	 */
	public static void GreedyAlgorithmDemo(int capacity, int[] weights, int[] values) {
		// n代表商品的数目
		int n = weights.length;
		// 新建一个map来记录我们的性价比。
		Map<String, Float> quality = new HashMap<String, Float>();
		// 遍历求出我们的性价比，以及存储对应的下标。
		for (int i = 0; i < n; i++) {
			quality.put(String.valueOf(i), (float) values[i] / weights[i]);
		}
		// 现在我们要做的是，根据性价比来排序，并且下标跟着动
		// 将quality的entryset存入List
		List<Map.Entry<String, Float>> list = new ArrayList<>(quality.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<String, Float>>() {

			@Override
			public int compare(Entry<String, Float> o1, Entry<String, Float> o2) {
				// TODO Auto-generated method stub
				// 我们传入的是一个浮点型排序的值
				// 如果说，返回值小于0升序，大于0降序
				if (o1.getValue() - o2.getValue() > 0) {
					return -1;
				} else {
					return 1;
				}
			}

		});
		// 对list进行排序

		// 新建一个Map，result 存放有序的序列
		// 这里注意是LinkedHashMap , 而不是HashMap,否则顺序还是0-6
		// LinkedHashMap，当当于一个链表，是能记录顺序的。
		Map<String, Float> result = new LinkedHashMap<String, Float>();
		for (Map.Entry<String, Float> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		for (Entry<String, Float> entry : result.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}

		// 记录我们那了什么商品
		int[] path = new int[n];
		int maxValue = 0; // 最大价值
		int maxWeight = 0; // 最大重量
		for(Entry<String, Float> entry:result.entrySet()) {
			int index = Integer.parseInt(entry.getKey());
			if(weights[index] <= capacity) {
				path[index] = 1;
				capacity -= weights[index];
				maxValue += values[index];
				maxWeight += weights[index];
			}
		}
		
		System.out.println("最大价值 = " + maxValue);
		System.out.println("最大重量 = " + maxWeight);
		System.out.println("商品路径 = " + Arrays.toString(path));
	}
	

}
