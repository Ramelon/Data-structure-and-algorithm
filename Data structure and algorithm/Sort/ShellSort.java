package Sort;

import java.text.SimpleDateFormat;

import java.util.Date;

public class ShellSort {
	public static void main(String[] args) {
		int[] arr = new int[8000000];
		for (int i = 0; i < 8000000; i++) {
			arr[i] = (int)(Math.random()* 800000);
		}
		
		Date data1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String data11 = simpleDateFormat.format(data1);
		System.out.println("����ǰʱ��:"+data11);
		
		Sort2(arr);
		
		Date data2 = new Date();
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String data22 = simpleDateFormat2.format(data2);
		System.out.println("�����ʱ��:"+data22);
//		int[] arr = {8, 5, 1, 10, 2, 3, 7, 4, 6, 0};
//		System.out.println("����ǰ��" + Arrays.toString(arr));
//		Sort(arr);
//		System.out.println("�����" + Arrays.toString(arr));
	}
	
	public static void Sort(int[] arr) {
		int temp = 0;
		//10����5 2 1 0
		for(int step = arr.length / 2; step > 0; step /= 2) {
			for(int i = step; i < arr.length; i++) {
				//ΪʲôҪj -= 5�� ��Ϊ��ֻ����һ�Σ��κ�Jֵ��5Ϊ�������˳�ѭ��
				for(int j = i - step; j >= 0; j -= step) {
					//����
					if(arr[j] > arr[j+step]) {
						temp = arr[j];
						arr[j] = arr[j+step];
						arr[j+step] = temp;
					}
				}
			}
		}
		/*
		int temp = 0;
		//��һ�� ��5��
		for(int i = 5; i < arr.length; i++) {
			//ΪʲôҪj -= 5�� ��Ϊ��ֻ����һ�Σ��κ�Jֵ��5Ϊ�������˳�ѭ��
			for(int j = i - 5; j >= 0; j -= 5) {
				//����
				if(arr[j] > arr[j+5]) {
					temp = arr[j];
					arr[j] = arr[j+5];
					arr[j+5] = temp;
				}
			}
		}
		
		//�ڶ��ַ���2��
		for(int i = 2; i < arr.length; i++) {
			//ΪʲôҪj -= 5�� ��Ϊ��ֻ����һ�Σ��κ�Jֵ��5Ϊ�������˳�ѭ��
			for(int j = i - 2; j >= 0; j -= 2) {
				//����
				if(arr[j] > arr[j+2]) {
					temp = arr[j];
					arr[j] = arr[j+2];
					arr[j+2] = temp;
				}
			}
		}
		
		//�ڶ��ַ���1��
		for(int i = 1; i < arr.length; i++) {
			//ΪʲôҪj -= 5�� ��Ϊ��ֻ����һ�Σ��κ�Jֵ��5Ϊ�������˳�ѭ��
			for(int j = i - 1; j >= 0; j -= 1) {
				//����
				if(arr[j] > arr[j+1]) {
					temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
		*/
	}
	
	//ϣ��������λ��
	public static void Sort2(int[] arr) {
		int temp = 0;
		for(int step = arr.length / 2; step > 0; step /= 2) {
			//��step��Ԫ���У�������в�������
			for(int i = step; i < arr.length; i++) {//5 j = 0 arr[0] = 3 arr[8] = 8
				int j = i;
				temp = arr[j];
				if(temp < arr[j - step]) {
					while(j - step >= 0 && temp < arr[j-step]) {
						arr[j] = arr[j-step];
						//j -= step;ѭ��ִ��һ�� �˳�
						j -= step;
					}
					arr[j] = temp;
				}
			}
		}
	}
	
}
