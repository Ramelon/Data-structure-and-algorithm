package search;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {
	public static void main(String[] args) {
		int[] arr = {1, 10, 20, 100, 523, 1000, 1234, 1234, 1234};
		System.out.println(Search2(arr, 0, arr.length, 1234));
	}
	
	/**
	 * 
	 * @param arr �����ҵ�����
	 * @param left ��ʼλ��
	 * @param right ����λ��
	 * @param searchValue Ҫ���ҵ�ֵ
	 * @return
	 */
	public static int Search(int[] arr, int left, int right, int searchValue) {
		if(left <= right) {
			//��������ҵ����Ϳ����ҵ��м�ֵ
			int mid = (left + right) / 2;
			//�жϲ���ֵ�Ƿ��mid��
			if(searchValue > arr[mid]) {
				return Search(arr, mid + 1, right, searchValue);
			}else if(searchValue < arr[mid]) {
				return Search(arr, left , mid - 1, searchValue);
			}else { //��ȣ�ֱ�ӷ����±�
				return mid;
			}
		}else {
			return -1;
		}
	}
	
	//������һ�����⣬��λ��һ��������������ͬ����������
	//������ҵ���Ҫ���ҵ������Ҿ����ҷ֣���߲����Ƿ������Ҫ�ҵ������ұ߲����Ƿ������Ҫ�ҵ�����
	//���ض��ֵ �� ArrayList���ܡ�
	public static List<Integer> Search2(int[] arr, int left, int right, int searchValue) {
		if(left <= right) {
			//��������ҵ����Ϳ����ҵ��м�ֵ
			int mid = (left + right) / 2;
			//�жϲ���ֵ�Ƿ��mid��
			if(searchValue > arr[mid]) {
				return Search2(arr, mid + 1, right, searchValue);
			}else if(searchValue < arr[mid]) {
				return Search2(arr, left , mid - 1, searchValue);
			}else { //��ȣ�ֱ�ӷ����±�
				List<Integer> searchIndexList = new ArrayList<Integer>();
				//�������
				int temp = mid -1;
				while(true) {
					//����±곬�� ���� ֵ���������ֵ�˳�ѭ��
					if(temp < 0 || arr[temp] != searchValue) {
						break;
					}
					searchIndexList.add(temp);
					//��ǰ��
					temp -= 1;
				}
				
				//����mid �����飬��midҲҪ��
				searchIndexList.add(mid);
				
				//���Ҳ���
				temp = mid + 1;
				while(true) {
					//����±곬�� ���� ֵ���������ֵ�˳�ѭ��
					if(temp > arr.length -1 || arr[temp] != searchValue) {
						break;
					}
					searchIndexList.add(temp);
					//������
					temp += 1;
				}
				return searchIndexList;
			}
		}else {
			return new ArrayList<Integer>();
		}
	}
}
