package algorithm;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class HorseChessBoard {
	private static int X;
	private static int Y;
	private static boolean visited[]; // 标记是否访问
	private static boolean isFinished; // 是否完成

	public static void main(String[] args) {
		System.out.println("骑士周游算法正在运行中!");
		X = 8;
		Y = 8;
		int row = 5, column =5;
		int[][] chessBoard = new int[X][Y];
		visited = new boolean[X * Y];
		long start = System.currentTimeMillis();
		SolveHorseChessBoardDemo(chessBoard, row - 1, column - 1, 0);
		long end = System.currentTimeMillis();
		System.out.println("花费时间" + (end - start) + "毫秒");
		for (int[] r : chessBoard) {
			for(int c: r) {
				System.out.print(c + "\t");
			}
			System.out.println();
		}
	}

	/**
	 * 功能:最后输出一个棋盘，可能是全0，走不通，可能是存在步长，这是一种解法。
	 * 
	 * @param chessBoard
	 *            二维数组
	 * @param row
	 *            行
	 * @param column
	 *            列
	 * @param step
	 *            马儿走的步长
	 */
	public static void SolveHorseChessBoardDemo(int[][] chessBoard, int row, int column, int step) {
		// 当亲位置赋值step
		chessBoard[row][column] = step;
		// 档期你位置标记为TRUE，被访问过
		visited[row * X + column] = true;
		// 存储当前点可以继续往下走的位置
		ArrayList<Point> nextPoints = next(new Point(column, row));
		sort(nextPoints);
		
		while (!nextPoints.isEmpty()) {
			// 取出当前的位置，进而可以进入下一个位置
			Point temp = nextPoints.remove(0);
			if (!visited[temp.y * X + temp.x]) {
				SolveHorseChessBoardDemo(chessBoard, temp.y, temp.x, step + 1);
			}
		}

		// 1.棋盘没有走完，最后还在当前位置。(没有完成任务，棋盘置0)
		// 2.可以走，处于一个回溯的过程中
		if (step < X * Y - 1 && !isFinished) {
			chessBoard[row][column] = 0;
			visited[row * X + column] = false;
		} else {
			isFinished = true;
		}
	}

	/**
	 * 功能: 最完美的情况下有八种可能性可以走，所以找到可以走的位置并加入到position
	 * 
	 * @param curPoint
	 *            当前的点的位置
	 * @return 当前点可以继续往下走的位置的集合
	 */
	public static ArrayList<Point> next(Point curPoint) {
		// 存放当前点可以继续往下走的位置
		ArrayList<Point> position = new ArrayList<Point>();
		// 声明一个点
		Point point = new Point();
		// 5 6 7 0 1 2 3 4
		// 走五这个位置
		if ((point.x = curPoint.x - 2) >= 0 && (point.y = curPoint.y - 1) >= 0) {
			position.add(new Point(point));
		}
		// 走六这个位置
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
	
	// 根据当前(x,y)的下一步的所有下一步的位置集合，按照size()进行非递减排序，升序
	public static void sort(ArrayList<Point> points) {
		points.sort(new Comparator<Point>() {

			@Override
			public int compare(Point o1, Point o2) {
				// TODO Auto-generated method stub
				int num1 = next(o1).size();
				int num2 = next(o2).size();
				// 我们想要最后是升序
				if(num1 < num2) {
					return -1;
				}else {
					return 1;
				}
			}
			
		});
	}
}
