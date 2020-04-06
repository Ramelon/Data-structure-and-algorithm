package heap;

import java.util.Arrays;

public class HeapSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = { 8, 13, 15, 11, 7, 9 };
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}

	public static void sort(int arr[]) {
		int temp = 0;
		// ��һ���������й�����һ���󶥶�
		for (int i = arr.length / 2 - 1; i >= 0; i--) {
			adjustHeap(arr, i, arr.length);
		}

		// �������У����Ѷ���Ԫ��ֵ��β����Ԫ�ؽ���
		// �ع�����
		for (int j = arr.length - 1; j > 0; j--) {
			temp = arr[j];
			arr[j] = arr[0];
			arr[0] = temp;
			adjustHeap(arr, 0, j);
		}
	}

	// arr.length / 2 - 1
	// ��������һ���������й�����һ���󶥶�
	public static void adjustHeap(int arr[], int i, int length) {
		// �жϵ�ǰ������ӽ��Ĵ�С��
		int temp = arr[i];

		// k = 2*i+1
		// �󶥶ѣ�arr[i] >= arr[2*i+1] && arr[i] >= arr[2*i+2]
		for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
			// �ж����ӽڵ� ���ӽ��˭��
			if (k + 1 < length && arr[k] > arr[k + 1]) { // ��һ��λ��
				// ���ӽ�������ָ�����ӽ��
				k++;
			}
			if (arr[k] < temp) {  // �ڶ���λ��
				// �ӽ����ڸ���㣬 �ӽ�㸳ֵ�����
				arr[i] = arr[k];
				// ��¼��ǰ��tempӦ����ֵ�ĵط�
				i = k;
			} else {
				break;
			}
		}
		// ��ѭ�����������������ֵ��ֵ
		// �����ֵ����λ��
		arr[i] = temp;
	}
}
