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
		System.out.println("����ǰʱ��:"+data11);
		
		Sort(arr, 0, arr.length - 1, temp);
		
		Date data2 = new Date();
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String data22 = simpleDateFormat2.format(data2);
		System.out.println("�����ʱ��:"+data22);
//		int[] arr = {5, 4, 7, 9, 3, 8, 2, 1};
//		System.out.println("����ǰ:" + Arrays.toString(arr));
//		int[] temp = new int[arr.length];
//		Sort(arr, 0, arr.length - 1, temp);
//		System.out.println("�����:" + Arrays.toString(arr));
	}
	
	/**
	 * 
	 * @param arr	Ҫ���������
	 * @param left	�������
	 * @param mid	�����м�����
	 * @param right	�����ұ�����
	 * @param temp	��ʱ����
	 */
	public static void Sort(int[] arr,int left, int right, int[] temp) {
		if(left < right) {
			int mid = (right + left) / 2;
			//���
			Sort(arr, left, mid, temp);
			//�ұ�
			Sort(arr, mid + 1, right, temp);
			meger(arr, left, mid, right, temp);
		}
	}
	
	public static void meger(int[] arr, int left, int mid, int right, int[] temp) {
		int leftIndex = left;
		int rightIndex = mid + 1;
		int k = 0;
		
		//����ߺ��ұ��������кϲ���һ�����������
		while(leftIndex <= mid && rightIndex <= right ) {
			//������Ԫ�ش����ұ߽���������++
			if(arr[leftIndex] <= arr[rightIndex]) {
				temp[k++] = arr[leftIndex++];
			}else
				temp[k++] = arr[rightIndex++];
		}
		
		//�����ʣ�����ݷ���temp
		while( leftIndex <= mid ) {
			temp[k++] = arr[leftIndex++];
		}
		//���ұ�ʣ�����ݷ���temp
		while( rightIndex <= right ) {
			temp[k++] = arr[rightIndex++];
		}
		
		//��temp����������ݸ�ֵ��ԭ����
		k = 0;
		int templeft = left;
		while(templeft <= right) {
			arr[templeft++] = temp[k++];
		}
 	}
}
