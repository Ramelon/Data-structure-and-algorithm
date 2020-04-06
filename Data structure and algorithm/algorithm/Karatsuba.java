package algorithm;

public class Karatsuba {
	public static void main(String[] args) {
		double value = KaratsubaDemo(110, 123);
		System.out.println(value);
	}

	public static double KaratsubaDemo(int num1, int num2) {
		// ������Ҫ�õݹ�ģ�����������ôд
		if (num1 < 10 && num2 < 10) {
			return num1 * num2;
		}

		// 1.ȡ�������ĳ��ȣ�ȡ�������ĳ��ȡ�
		// m = 4;
		int m = Math.max(String.valueOf(num1).length(), String.valueOf(num2).length());
		// 2.�����������Ҫ����һ�£�half = m / 2;
		int half = m / 2;
		// 3.x1 = num1 / Math.pow(10,half); x0 = num1 % Math.pow(10,half);
		int x1 = (int) (num1 / Math.pow(10, half));
		int x0 = (int) (num1 % Math.pow(10, half));
		// 4.���ڵ�y1��y0�Ͳ���3һ����
		int y1 = (int) (num2 / Math.pow(10, half));
		int y0 = (int) (num2 % Math.pow(10, half));
		System.out.println("x1=" + x1 + ",x0=" + x0 + ",y1=" + y1 + ",y0=" + y0);
		// 5.�ݹ����(x1,y1)
		double z2 = KaratsubaDemo(x1, y1);
		// 6.�ݹ����(x0,y0)
		double z0 = KaratsubaDemo(x0, y0);
		// 7.�ݹ����((x0+x1),(y0+y1))
		double z1 = KaratsubaDemo((x0 + x1), (y0 + y1));
		// 8.�����ù�ʽ return��
		System.out.println("z2=" + z2 + ",z0=" + z0 + ",z1=" + z1);
		return z2 * Math.pow(10, 2 * half) + ((z1 - z0 - z2) * Math.pow(10, half)) + z0;
	}
}
