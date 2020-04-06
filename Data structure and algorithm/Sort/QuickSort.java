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
		System.out.println("����ǰʱ��:"+data11);
		
		Sort(arr,0,arr.length-1);
		
		Date data2 = new Date();
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String data22 = simpleDateFormat2.format(data2);
		System.out.println("�����ʱ��:"+data22);
//		int[] arr = {49, 38, 65, 97, 76, 13, 27, 49};
//		System.out.println("����ǰ��" + Arrays.toString(arr));
//		Sort(arr, 0, arr.length - 1);
//		System.out.println("�����" + Arrays.toString(arr));
	}
	/**
	 * 
	 * @param arr	������Ҫ���������
	 * @param begin	��λ��
	 * @param end	��λ��
	 */
	public static void Sort(int[] arr, int begin, int end) {
		if(begin < end) {
			//����һ����׼��
			int temp = arr[begin];
			int left = begin; 
			int right = end;
			while(left < right) {
				//�����ұ�
				//�ҵ��ұ�����С�ڻ�׼����Ԫ��
				//�˳�ѭ��ʱ���ұ߶��Ǵ��ڻ�׼����Ԫ��
				//�Ѻͻ�׼����ͨ����Ԫ��Ҳ�ڷ��ұ�
				while(left < right && arr[right] >= temp) {
					//���С�ڻ�׼�����±�
					right--;
				}
				//���ұ�С�ڻ�׼����Ԫ�ظ����
				arr[left] = arr[right];
				//�������
				//�ҵ���߱�����С�ڻ�׼����Ԫ��
				//�˳�ѭ��ʱ����߶�С�ڻ�׼����Ԫ��
				while(left < right && arr[left] < temp) {
					//��ߵ���������Ҫ++
					left++; 
				}
				//����ߴ��ڻ�׼����Ԫ�ظ��ұ�
				arr[right] = arr[left];
			}
			arr[left] = temp;
			//����Ϊ�����֡�
			//������߲����õ��ݹ顣
			Sort(arr, begin, left - 1);
			//�����ұ�
			Sort(arr, left + 1, end);
		}else {
			return;
		}
	}
}
