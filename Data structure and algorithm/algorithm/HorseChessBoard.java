package algorithm;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class HorseChessBoard {
	private static int X;
	private static int Y;
	private static boolean visited[]; // ����Ƿ����
	private static boolean isFinished; // �Ƿ����

	public static void main(String[] args) {
		System.out.println("��ʿ�����㷨����������!");
		X = 8;
		Y = 8;
		int row = 5, column =5;
		int[][] chessBoard = new int[X][Y];
		visited = new boolean[X * Y];
		long start = System.currentTimeMillis();
		SolveHorseChessBoardDemo(chessBoard, row - 1, column - 1, 0);
		long end = System.currentTimeMillis();
		System.out.println("����ʱ��" + (end - start) + "����");
		for (int[] r : chessBoard) {
			for(int c: r) {
				System.out.print(c + "\t");
			}
			System.out.println();
		}
	}

	/**
	 * ����:������һ�����̣�������ȫ0���߲�ͨ�������Ǵ��ڲ���������һ�ֽⷨ��
	 * 
	 * @param chessBoard
	 *            ��ά����
	 * @param row
	 *            ��
	 * @param column
	 *            ��
	 * @param step
	 *            ����ߵĲ���
	 */
	public static void SolveHorseChessBoardDemo(int[][] chessBoard, int row, int column, int step) {
		// ����λ�ø�ֵstep
		chessBoard[row][column] = step;
		// ������λ�ñ��ΪTRUE�������ʹ�
		visited[row * X + column] = true;
		// �洢��ǰ����Լ��������ߵ�λ��
		ArrayList<Point> nextPoints = next(new Point(column, row));
		sort(nextPoints);
		
		while (!nextPoints.isEmpty()) {
			// ȡ����ǰ��λ�ã��������Խ�����һ��λ��
			Point temp = nextPoints.remove(0);
			if (!visited[temp.y * X + temp.x]) {
				SolveHorseChessBoardDemo(chessBoard, temp.y, temp.x, step + 1);
			}
		}

		// 1.����û�����꣬����ڵ�ǰλ�á�(û���������������0)
		// 2.�����ߣ�����һ�����ݵĹ�����
		if (step < X * Y - 1 && !isFinished) {
			chessBoard[row][column] = 0;
			visited[row * X + column] = false;
		} else {
			isFinished = true;
		}
	}

	/**
	 * ����: ��������������а��ֿ����Կ����ߣ������ҵ������ߵ�λ�ò����뵽position
	 * 
	 * @param curPoint
	 *            ��ǰ�ĵ��λ��
	 * @return ��ǰ����Լ��������ߵ�λ�õļ���
	 */
	public static ArrayList<Point> next(Point curPoint) {
		// ��ŵ�ǰ����Լ��������ߵ�λ��
		ArrayList<Point> position = new ArrayList<Point>();
		// ����һ����
		Point point = new Point();
		// 5 6 7 0 1 2 3 4
		// �������λ��
		if ((point.x = curPoint.x - 2) >= 0 && (point.y = curPoint.y - 1) >= 0) {
			position.add(new Point(point));
		}
		// �������λ��
		if ((point.x = curPoint.x - 1) >= 0 && (point.y = curPoint.y - 2) >= 0) {
			position.add(new Point(point));
		}
		// 7
		if ((point.x = curPoint.x + 1) < X && (point.y = curPoint.y - 2) >= 0) {
			position.add(new Point(point));
		}
		// 0
		if ((point.x = curPoint.x + 2) < X && (point.y = curPoint.y - 1) >= 0) {
			position.add(new Point(point));
		}
		// 1
		if ((point.x = curPoint.x + 2) < X && (point.y = curPoint.y + 1) < Y) {
			position.add(new Point(point));
		}
		// 2
		if ((point.x = curPoint.x + 1) < X && (point.y = curPoint.y + 2) < Y) {
			position.add(new Point(point));
		}
		// 3
		if ((point.x = curPoint.x - 1) >= 0 && (point.y = curPoint.y + 2) < Y) {
			position.add(new Point(point));
		}
		// 4
		if ((point.x = curPoint.x - 2) >= 0 && (point.y = curPoint.y + 1) < Y) {
			position.add(new Point(point));
		}
		return position;
	}
	
	// ���ݵ�ǰ(x,y)����һ����������һ����λ�ü��ϣ�����size()���зǵݼ���������
	public static void sort(ArrayList<Point> points) {
		points.sort(new Comparator<Point>() {

			@Override
			public int compare(Point o1, Point o2) {
				// TODO Auto-generated method stub
				int num1 = next(o1).size();
				int num2 = next(o2).size();
				// ������Ҫ���������
				if(num1 < num2) {
					return -1;
				}else {
					return 1;
				}
			}
			
		});
	}
}
