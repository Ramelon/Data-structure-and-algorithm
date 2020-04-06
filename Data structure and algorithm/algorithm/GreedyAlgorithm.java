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
		// ̰���㷨���01�������⡣
		int capacity = 150;
		int[] weights = { 35, 30, 60, 50, 40, 10, 25 };
		int[] values = { 10, 40, 30, 50, 35, 40, 30 };
		GreedyAlgorithmDemo(capacity, weights, values);
	}

	/**
	 * 
	 * @param capacity
	 *            ����������
	 * @param weights
	 *            ��Ʒ������
	 * @param values
	 *            ��Ʒ�ļ�ֵ
	 */
	public static void GreedyAlgorithmDemo(int capacity, int[] weights, int[] values) {
		// n������Ʒ����Ŀ
		int n = weights.length;
		// �½�һ��map����¼���ǵ��Լ۱ȡ�
		Map<String, Float> quality = new HashMap<String, Float>();
		// ����������ǵ��Լ۱ȣ��Լ��洢��Ӧ���±ꡣ
		for (int i = 0; i < n; i++) {
			quality.put(String.valueOf(i), (float) values[i] / weights[i]);
		}
		// ��������Ҫ�����ǣ������Լ۱������򣬲����±���Ŷ�
		// ��quality��entryset����List
		List<Map.Entry<String, Float>> list = new ArrayList<>(quality.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<String, Float>>() {

			@Override
			public int compare(Entry<String, Float> o1, Entry<String, Float> o2) {
				// TODO Auto-generated method stub
				// ���Ǵ������һ�������������ֵ
				// ���˵������ֵС��0���򣬴���0����
				if (o1.getValue() - o2.getValue() > 0) {
					return -1;
				} else {
					return 1;
				}
			}

		});
		// ��list��������

		// �½�һ��Map��result ������������
		// ����ע����LinkedHashMap , ������HashMap,����˳����0-6
		// LinkedHashMap��������һ���������ܼ�¼˳��ġ�
		Map<String, Float> result = new LinkedHashMap<String, Float>();
		for (Map.Entry<String, Float> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		for (Entry<String, Float> entry : result.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}

		// ��¼��������ʲô��Ʒ
		int[] path = new int[n];
		int maxValue = 0; // ����ֵ
		int maxWeight = 0; // �������
		for(Entry<String, Float> entry:result.entrySet()) {
			int index = Integer.parseInt(entry.getKey());
			if(weights[index] <= capacity) {
				path[index] = 1;
				capacity -= weights[index];
				maxValue += values[index];
				maxWeight += weights[index];
			}
		}
		
		System.out.println("����ֵ = " + maxValue);
		System.out.println("������� = " + maxWeight);
		System.out.println("��Ʒ·�� = " + Arrays.toString(path));
	}
	

}
