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
		System.out.println("����ǰʱ��:"+data11);
		
		Sort(arr);
		
		Date data2 = new Date();
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String data22 = simpleDateFormat2.format(data2);
		System.out.println("�����ʱ��:"+data22);
//		int[] arr = {54, 3, 542, 748, 14, 214};
//		System.out.println("����ǰ:" + Arrays.toString(arr));
//		Sort(arr);
//		System.out.println("�����:" + Arrays.toString(arr));
	}
	
	public static void Sort(int[] arr) {
		//��Ϊ�Ҳ�֪��ÿ��ͰӦ�ô�Ŷ��١�
		//���Ĵ����������Ĵ�С
		//���������ǿռ任ʱ�䡣
		int [][] bucket = new int[10][arr.length];
		int [] bucketCounts = new int[10];
		
		//ȷ������������ֵ��Ȼ��ȷ��λ��������ȷ��ѭ������
		int max = arr[0];
		for(int i = 1; i < arr.length; i++) {
			if(max < arr[i])
				max = arr[i];
		}
		//ȷ��λ��
		int maxLength = String.valueOf(max).length();
		for(int i = 1, n = 1; i <= maxLength; i++, n *= 10) {
			int index = 0;	
			for(int j = 0; j < arr.length; j++) {
				//ȡ��Ӧ��λ��
				//��λ i = 1, arr[j] / i % 10
				//ʮλ i = 10, arr[j] / i % 10
				//ʮλ i = 100, arr[j] / i % 10
				int number = arr[j] / n % 10;
				//����Ӧ��Ͱ��ֵ
				bucket[number][bucketCounts[number]] = arr[j];
				//��¼ÿ��Ͱ��Ԫ�صĸ���
				bucketCounts[number]++;
			}	
			//�Ѽ�¼Ͱ��Ԫ�ظ�����bucketCounts�������
			//�������0��˵���ǿ�Ͱ������������֪��Ͱ��Ԫ�صĸ��������ո���������ԭ���顣
			for(int j = 0; j < bucketCounts.length; j++) {
				if(bucketCounts[j] != 0) {
					for(int k = 0; k < bucketCounts[j]; k++) {
						arr[index++] = bucket[j][k];
					}
				}
				//һ��Ҫ������仰
				//ԭ����λ��1������һ��
				//����ʮλ��1����������
				//counts��ֵӦ����2������countsû�б���ԭ��0������ֵ��3�����׳��±�Խ�硣
				bucketCounts[j] = 0;
			}

		}
		
		
//		//��һ�� ����λ��
//		for(int i = 0; i < arr.length; i++) {
//			//ȡ��Ӧ��λ��
//			int number = arr[i] % 10;
//			//����Ӧ��Ͱ��ֵ
//			bucket[number][bucketCounts[number]] = arr[i];
//			//��¼ÿ��Ͱ��Ԫ�صĸ���
//			bucketCounts[number]++;
//		}
//		int index = 0;		//����
//		//�Ѽ�¼Ͱ��Ԫ�ظ�����bucketCounts�������
//		//�������0��˵���ǿ�Ͱ������������֪��Ͱ��Ԫ�صĸ��������ո���������ԭ���顣
//		for(int j = 0; j < bucketCounts.length; j++) {
//			if(bucketCounts[j] != 0) {
//				for(int k = 0; k < bucketCounts[j]; k++) {
//					arr[index++] = bucket[j][k];
//				}
//			}
//			//һ��Ҫ������仰
//			//ԭ����λ��1������һ��
//			//����ʮλ��1����������
//			//counts��ֵӦ����2������countsû�б���ԭ��0������ֵ��3�����׳��±�Խ�硣
//			bucketCounts[j] = 0;
//		}
//		System.out.println("��һ��:" + Arrays.toString(arr));
//		
//		//�ڶ��� ��ʮλ��
//		for(int i = 0; i < arr.length; i++) {
//			//ȡ��Ӧ��λ��
//			int number = arr[i] /10 % 10;
//			//����Ӧ��Ͱ��ֵ
//			bucket[number][bucketCounts[number]] = arr[i];
//			//��¼ÿ��Ͱ��Ԫ�صĸ���
//			bucketCounts[number]++;
//		}
//		index = 0;		//����
//		//�Ѽ�¼Ͱ��Ԫ�ظ�����bucketCounts�������
//		//�������0��˵���ǿ�Ͱ������������֪��Ͱ��Ԫ�صĸ��������ո���������ԭ���顣
//		for(int j = 0; j < bucketCounts.length; j++) {
//			if(bucketCounts[j] != 0) {
//				for(int k = 0; k < bucketCounts[j]; k++) {
//					arr[index++] = bucket[j][k];
//				}
//			}
//			bucketCounts[j] = 0;
//		}
//		System.out.println("�ڶ���:" + Arrays.toString(arr));
//		
//		//������ ����λ��
//		for(int i = 0; i < arr.length; i++) {
//			//ȡ��Ӧ��λ��
//			int number = arr[i] / 100;
//			//����Ӧ��Ͱ��ֵ
//			bucket[number][bucketCounts[number]] = arr[i];
//			//��¼ÿ��Ͱ��Ԫ�صĸ���
//			bucketCounts[number]++;
//		}
//		index = 0;		//����
//		//�Ѽ�¼Ͱ��Ԫ�ظ�����bucketCounts�������
//		//�������0��˵���ǿ�Ͱ������������֪��Ͱ��Ԫ�صĸ��������ո���������ԭ���顣
//		for(int j = 0; j < bucketCounts.length; j++) {
//			if(bucketCounts[j] != 0) {
//				for(int k = 0; k < bucketCounts[j]; k++) {
//					arr[index++] = bucket[j][k];
//				}
//			}
//			bucketCounts[j] = 0;
//		}
//		System.out.println("������:" + Arrays.toString(arr));
	}
}
