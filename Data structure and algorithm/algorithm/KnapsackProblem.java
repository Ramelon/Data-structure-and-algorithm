package algorithm;

public class KnapsackProblem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] w = { 2, 3, 5, 7, 8 };
		int[] value = { 3, 4, 6, 8, 10 };
		int[] num = { 1, 2, 3, 4, 5 };
		int capacity = 12; // 5 5 2 8+8+3=19
		int n = w.length;
		// ZeroOneKnapsack(w, value, n, capacity);
		// completeKnapsack(w, value, n, capacity);
		moreKnapsack(w, value, n, capacity, num);
	}

	/**
	 * 
	 * @param w
	 *            商品的重量
	 * @param value
	 *            商品的价值
	 * @param n
	 *            商品的重量
	 * @param capacity
	 *            商品的容量
	 */
	public static void ZeroOneKnapsack(int[] w, int[] value, int n, int capacity) {
		// 因为第一行第一类均为0，所以要加1.
		// 第一行和第一列我们都不会用到，目的是为了更好理解01背包
		int[][] v = new int[n + 1][capacity + 1];

		// 记录路径
		int[][] path = new int[n + 1][capacity + 1];
		// 因为v[0][j] 和 v[i][0]不处理 默认为0
		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < capacity + 1; j++) {
				if (w[i - 1] > j) {
					// 如果背包的容量j小于当前商品的重量。则第i件商品不能装入吧背包。
					v[i][j] = v[i - 1][j];
				} else {
					// 因为value数组从0开始，所以value[i-1],w[i-1]
					// v[i][j] = Math.max(v[i - 1][j], value[i - 1] + v[i - 1][j - w[i - 1]]);
					if (v[i - 1][j] < value[i - 1] + v[i - 1][j - w[i - 1]]) {
						v[i][j] = value[i - 1] + v[i - 1][j - w[i - 1]];
						path[i][j] = 1;
					} else {
						v[i][j] = v[i - 1][j];
					}
				}
			}
		}

		for (int i = 0; i < n + 1; i++) {
			for (int j = 0; j < capacity + 1; j++) {
				if (v[i][j] < 10) {
					System.out.print(v[i][j] + "  ");
				} else {
					System.out.print(v[i][j] + " ");
				}
			}
			System.out.println();
		}

		for (int i = 0; i < n + 1; i++) {
			for (int j = 0; j < capacity + 1; j++) {
				System.out.print(path[i][j] + "  ");
			}
			System.out.println();
		}

		int i = path.length - 1;
		int j = path[0].length - 1;
		while (i > 0 && j > 0) {
			if (path[i][j] == 1) {
				System.out.println("商品" + i + "放入背包");
				// 这里是和之前一样，我放入背包意见商品，那么我的容量也要对应着减少。
				// 不可能说我容量一直为20，如果说不减少，呢么就会重复。
				j -= w[i - 1];
			}
			i--;
		}
	}

	// 01背包：每种商品只能买一个
	// 完全背包：没有限时，可以一种商品无限拿，知道容量塞满
	// 完全背包和我们的01背包很想为什么？
	// 因为我01背包，当我当前商品可以购买时，那么我解决的是对自己的 i-1的子问题来解决01背包。v[i - 1][j - w[i - 1]]
	// v[i][j] = Math.max(v[i - 1][j], value[i - 1] + v[i - 1][j - w[i - 1]]);
	// 完全背包是自己解决 自己的 i的子问题，v[i][j - w[i - 1]]
	public static void completeKnapsack(int[] w, int[] value, int n, int capacity) {
		// 因为第一行第一类均为0，所以要加1.
		// 第一行和第一列我们都不会用到，目的是为了更好理解01背包
		int[][] v = new int[n + 1][capacity + 1];

		// 记录路径
		int[][] path = new int[n + 1][capacity + 1];
		// 因为v[0][j] 和 v[i][0]不处理 默认为0
		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < capacity + 1; j++) {
				if (w[i - 1] > j) {
					// 如果背包的容量j小于当前商品的重量。则第i件商品不能装入吧背包。
					v[i][j] = v[i - 1][j];
				} else {
					// 因为value数组从0开始，所以value[i-1],w[i-1]
					// v[i][j] = Math.max(v[i - 1][j], value[i] + v[i - 1][j - w[i - 1]]);
					if (v[i - 1][j] < value[i - 1] + v[i][j - w[i - 1]]) {
						v[i][j] = value[i - 1] + v[i][j - w[i - 1]];
						path[i][j] = 1;
					} else {
						v[i][j] = v[i - 1][j];
					}
				}
			}
		}

		for (int i = 0; i < n + 1; i++) {
			for (int j = 0; j < capacity + 1; j++) {
				if (v[i][j] < 10) {
					System.out.print(v[i][j] + "  ");
				} else {
					System.out.print(v[i][j] + " ");
				}
			}
			System.out.println();
		}
		System.out.println("path路径：");
		for (int i = 0; i < n + 1; i++) {
			for (int j = 0; j < capacity + 1; j++) {
				System.out.print(path[i][j] + "  ");
			}
			System.out.println();
		}

		int i = path.length - 1;
		int j = path[0].length - 1;
		while (j > 0) {
			if (path[i][j] == 1) {
				// 这里是和之前一样，我放入背包意见商品，那么我的容量也要对应着减少。
				// 不可能说我容量一直为20，如果说不减少，呢么就会重复。
				System.out.println("商品" + i + "放入背包");
				if ((j - w[i - 1]) >= w[i - 1]) {
					System.out.println("商品" + i + "放入背包");
					j -= w[i - 1];
				}
				j -= w[i - 1];
			}

			if (i == 1) {
				i = 1;
			} else {
				i--;
			}
		}
	}

	// 多重背包，购买次数是有限制的，我们给一个num数组 规定限制
	public static void moreKnapsack(int[] w, int[] value, int n, int capacity, int[] num) {
		// 因为第一行第一类均为0，所以要加1.
		// 第一行和第一列我们都不会用到，目的是为了更好理解01背包
		int[][] v = new int[n + 1][capacity + 1];

		// 记录路径
		int[][] path = new int[n + 1][capacity + 1];
		// 因为v[0][j] 和 v[i][0]不处理 默认为0
		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < capacity + 1; j++) {
				if (w[i - 1] > j) {
					// 如果背包的容量j小于当前商品的重量。则第i件商品不能装入吧背包。
					v[i][j] = v[i - 1][j];
				} else {
					// 多重背包我们需要知道当前商品最多能购买几次
					// num[i - 1] 规定此商品最多拿多少
					// j / w[i - 1] 我背包最多能拿多少
					int maxNumber = Math.min(num[i - 1], j / w[i - 1]);
					for (int k = 0; k < maxNumber + 1; k++) {
						if (v[i - 1][j] < k * value[i - 1] + v[i - 1][j - k * w[i - 1]]) {
							v[i][j] = k * value[i - 1] + v[i - 1][j - k * w[i - 1]];
							path[i][j] = 1;
						} else {
							v[i][j] = v[i - 1][j];
						}
					}
				}
			}
		}

		for (int i = 0; i < n + 1; i++) {
			for (int j = 0; j < capacity + 1; j++) {
				if (v[i][j] < 10) {
					System.out.print(v[i][j] + "  ");
				} else {
					System.out.print(v[i][j] + " ");
				}
			}
			System.out.println();
		}
		System.out.println("path路径：");
		for (int i = 0; i < n + 1; i++) {
			for (int j = 0; j < capacity + 1; j++) {
				System.out.print(path[i][j] + "  ");
			}
			System.out.println();
		}

		int i = path.length - 1;
		int j = path[0].length - 1;
		while (j > 0) {
			if (path[i][j] == 1) {
				// 这里是和之前一样，我放入背包意见商品，那么我的容量也要对应着减少。
				// 不可能说我容量一直为20，如果说不减少，呢么就会重复。
				System.out.println("商品" + i + "放入背包");
				if ((j - w[i - 1]) >= w[i - 1]) {
					System.out.println("商品" + i + "放入背包");
					j -= w[i - 1];
				}
				j -= w[i - 1];
			}

			if (i == 1) {
				i = 1;
			} else {
				i--;
			}
		}
	}
}

// 01背包的路径没有问题
// 完全背包的路径有疑问
// 多重背包的路径不确定
// 留给大家思考。
// 大家也可以思考为什么多重背包那么做，可以自己测试一下公式的正确与否。
