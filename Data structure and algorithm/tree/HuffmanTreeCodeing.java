package tree;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanTreeCodeing {

	public static void main(String[] args) {
		// // TODO Auto-generated method stub
//		String str = "Hallo Wo d";
//		System.out.println(str);
//		byte[] bytes = str.getBytes();
//
//		byte[] huffmanCodeBytes = packZip(bytes);
//		System.out.println(Arrays.toString(huffmanCodeBytes));
//
//		System.out.println(huffmanTreeCodes);
//
//		System.out.println("解码后的字符串");
//		byte[] deCodeHuffmanBytes = deCode(huffmanTreeCodes, huffmanCodeBytes);
//		System.out.println(new String(deCodeHuffmanBytes));
		
		String srcFile = "D:\\1.bmp";
		String dstFile = "D:\\bmp.zip";
		zipFile(srcFile, dstFile);
		
		
		System.out.println("成功1");
		
		String zipFile = "D:\\bmp.zip";
		String dstFile2 = "D:\\11.bmp";
		unZipFile(zipFile, dstFile2);
		System.out.println("成功2");
		//System.out.println(stringBuilder);
		
		
	}

	public static void preOrder(Node root) {
		if (root != null) {
			root.preOrder();
		} else {
			System.out.println("空树。无法下一步操作。");
		}
	}

	//编写一个方法，完成对压缩文件的解压
	/**
	 * 
	 * @param zipFile 准备解压的文件
	 * @param dstFile 将文件解压到哪个路径
	 */
	public static void unZipFile(String zipFile, String dstFile) {
		
		//定义文件输入流
		InputStream is = null;
		//定义一个对象输入流
		ObjectInputStream ois = null;
		//定义文件的输出流
		OutputStream os = null;
		try {
			//创建文件输入流
			is = new FileInputStream(zipFile);
			//创建一个和  is关联的对象输入流
			ois = new ObjectInputStream(is);
			//读取byte数组  huffmanBytes
			byte[] huffmanBytes = (byte[])ois.readObject();
			//读取赫夫曼编码表
			Map<Byte,String> huffmanCodes = (Map<Byte,String>)ois.readObject();
			//解码
			byte[] bytes = deCode(huffmanCodes, huffmanBytes);
			//将bytes 数组写入到目标文件
			os = new FileOutputStream(dstFile);
			//写数据到 dstFile 文件
			os.write(bytes);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		} finally {
			
			try {
				os.close();
				ois.close();
				is.close();
			} catch (Exception e2) {
				// TODO: handle exception
				System.out.println(e2.getMessage());
			}
			
		}
	}
	
	//编写方法，将一个文件进行压缩
	/**
	 * 
	 * @param srcFile 你传入的希望压缩的文件的全路径
	 * @param dstFile 我们压缩后将压缩文件放到哪个目录
	 */
	public static void zipFile(String srcFile, String dstFile) {
		
		//创建输出流
		OutputStream os = null;
		ObjectOutputStream oos = null;
		//创建文件的输入流
		FileInputStream is = null;
		try {
			//创建文件的输入流
			// 我们无论什么文件  我们以二进制的形式
			is = new FileInputStream(srcFile);
			//创建一个和源文件大小一样的byte[]
			byte[] b = new byte[is.available()];
			//读取文件
			is.read(b);
			//直接对源文件压缩
			byte[] huffmanBytes = packZip(b);
			System.out.println(Arrays.toString(huffmanBytes));
			System.out.println(huffmanTreeCodes);
			//创建文件的输出流, 存放压缩文件
			os = new FileOutputStream(dstFile);
			//创建一个和文件输出流关联的ObjectOutputStream
			oos = new ObjectOutputStream(os);
			//把 赫夫曼编码后的字节数组写入压缩文件
			oos.writeObject(huffmanBytes); //我们是把
			//这里我们以对象流的方式写入 赫夫曼编码，是为了以后我们恢复源文件时使用
			//注意一定要把赫夫曼编码 写入压缩文件
			// 
			oos.writeObject(huffmanTreeCodes);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally {
			try {
				is.close();
				oos.close();
				os.close();
			}catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
		}
		
	}
	
	/**
	 * 
	 * @param huffmanTreeCodes
	 *            赫夫曼编码表
	 * @param huffmanCodeBytes
	 *            赫夫曼对应的字节数组[-89, 110, 39, 19]
	 * @return "Hallo Word"
	 */
	public static byte[] deCode(Map<Byte, String> huffmanTreeCodes, byte[] huffmanCodeBytes) {
		// 用来拼接 赫夫曼对应的字节数组
//		StringBuilder stringBuilder = new StringBuilder();
//
//		// 我们现在循环遍历 huffmanCodeBytes 得到二进制字符串
//		// 循环调用 byteToBinaryString(boolean flag, byte byteOne)
//		for (int i = 0; i < huffmanCodeBytes.length; i++) {
//			byte byteOne = huffmanCodeBytes[i];
//			// flag为最后一位时它不补位，如果flag 为 true 则是最后一位
//			boolean flag = (i == huffmanCodeBytes.length - 1);
//			stringBuilder.append(byteToBinaryString(!flag, byteOne));
//		}
		//System.out.println(stringBuilder);
		// 我们之前编码的视乎我们用到 huffmanTreeCodes(Byte, String)
		// 而我们现在要用到的是 huffmanTreeCodes(String, Byte)
		// 我们编码 解码 的key是反过来的。
		// 转换 赫夫曼编码表
		Map<String, Byte> tempMap = new HashMap<String, Byte>();
		for (Map.Entry<Byte, String> entry : huffmanTreeCodes.entrySet()) {
			tempMap.put(entry.getValue(), entry.getKey());
		}
		// 通过tempMap 来 确定我们需要存什么
		// 定义一个list ，从前往后找，前面满足一个长度条件的二进制字符串，那么就添加到list
		List<Byte> list = new ArrayList<Byte>();
		// 注意 这里没有i++ ！！！！
		for (int i = 0; i < stringBuilder.length();) {
			// 用来控制步长
			int count = 1;
			// 用来判断是否找到
			boolean flag = true;
			// 用来存储找到的值
			Byte byteOne = null;

			while (flag) {
				// 我一直做截取 知道知道为止
				String key = stringBuilder.substring(i, i + count);
				// 获取赫夫曼树表 里的 vlaue Byte
				byteOne = tempMap.get(key);
				if (byteOne == null) {
					count++;
				} else {
					flag = false;
				}
			}
			list.add(byteOne);
			// 通过i+count 来控制循环的步长
			i += count;
		}
		//System.out.println(list);
		// 把list 里的值 存入到byte数组里
		byte[] tempByte = new byte[list.size()];
		for (int i = 0; i < list.size(); i++) {
			tempByte[i] = list.get(i);
		}
		return tempByte;
	}

	// 赫夫曼编码 byte[] [-89, 110, 39, 19]
	// 我们通过这个byte数组 -> 遍历 转换成二进制的字符串
	// -89 -> 10100111
	/**
	 * 
	 * @param flag
	 *            这个flag有什么用，传进来的byteOne是正数时，可能会少位数，那么flag就是判断是否补高位
	 * @param byteOne
	 *            字节数组里的数
	 * @return 二进制
	 */
	// Hallo Wo d
//	public static String byteToBinaryString(boolean flag, byte byteOne) {
//		// 临时变量
//		int temp = byteOne;
//		// 只要对应的二个二进位有一个为1时，结果位就为1。
//		// 256 1 0000 0000 |= 任何一个数 如过是正数那么前面都补0 19——>10011 00010011
//		if (flag) {
//			temp |= 256;
//		} 
//		// 3-> 11 不会是 0011  正数 会把前面的0过滤掉
//		String str = Integer.toBinaryString(temp);
//		if (flag) {
//			return str.substring(str.length() - 8);
//		} else {
//			System.out.println(str);
//			return str;
//		}
//	}

	public static byte[] packZip(byte[] bytes) {
		List<Node> nodes = getNode(bytes);
		// 创建赫夫曼树
		Node root = createHuffmanTree(nodes);
		// 获取赫夫曼编码
		huffmanTreeCodes = getCodes(root);
		System.out.println(getCodes(root));
		// 获取对应字节数组
		byte[] huffmanCodeBytes = zip(bytes, huffmanTreeCodes);
		return huffmanCodeBytes;
	}

	/**
	 * 
	 * @param bytes
	 *            原始字符串转成byte数组
	 * @param huffmanCode
	 *            就是我们赫夫曼树编码对应表 {65=10, 66=11011, 67=1100, 68=0, 69=11010, 70=111}
	 * @return 返回一个byte[] 数组 就是二进制对应的数组
	 */
	public static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCode) {
		// 作用:用来拼接最后的字符串。
		StringBuilder stringSplicing = new StringBuilder();

		// 68 68 68 68 68 68 68 68
		// stringBuilder = 00000000
		// byte[] 第一位 0
		// 最后传回去的是一个byte数组那么，我把stringBuilder从前到后每八位 转成一个对应的数字， 存入我们要return的byte数组
		// 传回去的byte[] 数组 一位数字 对应着stringBuilder里8个字符。
		for (byte byteone : bytes) {
			stringSplicing.append(huffmanCode.get(byteone));
		}
		System.out.println(stringSplicing);
		// 现在我们要操作的就是 把对应的二进制 转成一个整数
		// 把当前长度 划分为无数个 8位 那么一个8位对应的就是一个数子，如果长度不够呢
		// 因为当前长度不能整除8时 那么他多出来的 1 - 7 取最小 + 7 那么 得到的就是符合的长度
		// 9 + 7 = 16 2
		// 10 + 7 = 17 2
		// 15 + 7 = 22 2
		// 16 + 7 = 23 2
		int len = (stringSplicing.length() + 7) / 8;
		// 解决了当前二进制字符串 可以划分8位的长度
		// 把对应8位长度的数子 拿出来。存储到byte数组里。
		byte[] huffmanCodeBytes = new byte[len];
		int index = 0;
		for (int i = 0; i < stringSplicing.length(); i += 8) {
			// 用来临时存储
			String temp;
			// 放置下标越界
			if (i + 8 > stringSplicing.length()) { // 溢出几位存几位
				temp = stringSplicing.substring(i);
			} else {
				temp = stringSplicing.substring(i, i + 8);
			}

			huffmanCodeBytes[index] = (byte) Integer.parseInt(temp, 2);
			index++;
		}
		stringBuilder = stringSplicing;
		return huffmanCodeBytes;
	}

	// 解析生成的赫夫曼树，然后得到相应的赫夫曼编码
	// {'65':"01"} {'66':"11011"}...
	// 新建一个hashmap来存放数据
	private static Map<Byte, String> huffmanTreeCodes = new HashMap<Byte, String>();
	// StringBuffer对象则代表一个字符序列可变的字符串，当一个StringBuffer被创建以后，
	// 通过StringBuffer提供的append()、insert()、reverse()、setCharAt()、setLength()等方法可以改变这个字符串对象的字符序列。
	// 一旦通过StringBuffer生成了最终想要的字符串，就可以调用它的toString()方法将其转换为一个String对象。
	// stringBuilder: 用来拼接字符串
	private static StringBuilder stringBuilder = new StringBuilder();

	/**
	 * 
	 * @param node
	 *            我已经构成的赫夫曼树的结点
	 * @param code
	 *            "0" "1" 路径
	 * @param stringBuilder
	 *            用来存储路径，也就是说作用是拼接字符串
	 */
	public static Map<Byte, String> getCodes(Node root) {
		if (root == null) {
			return null;
		}
		// 左子树递归
		getCodes(root.left, "0", stringBuilder);
		// 右子树递归
		getCodes(root.right, "1", stringBuilder);
		return huffmanTreeCodes;
	}

	// 赫夫曼编码 就是将该方法完全依据字符出现概率来构造异字头的平均长度最短的码字，有时称之为最佳编码
	// 我们只需要操作叶子结点
	public static void getCodes(Node node, String code, StringBuilder stringBuilder) {
		// 因为递归的原因，我们还需要声明一个stringBuilder2 来拼接字符串
		StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
		stringBuilder2.append(code);
		if (node != null) {
			// 判断该结点是否为叶子结点
			if (node.data == null) {
				// 左子树递归
				getCodes(node.left, "0", stringBuilder2);
				// 右子树递归
				getCodes(node.right, "1", stringBuilder2);
			} else {// 叶子结点
					// 一旦通过StringBuffer生成了最终想要的字符串，就可以调用它的toString()方法将其转换为一个String对象。
				huffmanTreeCodes.put(node.data, stringBuilder2.toString());
			}
		}

	}

	public static List<Node> getNode(byte[] bytes) {
		// 新建ArrayList,并存放每个字符出现的频率(以键值对的形式存放)
		List<Node> nodes = new ArrayList<Node>();
		// 统计字符出现频率 以键值对形式存放
		Map<Byte, Integer> counts = new HashMap<Byte, Integer>();
		for (byte byteOne : bytes) {
			Integer count = counts.get(byteOne);
			// 如果hashmap 里面没有存放此字符 就存放
			if (count == null) {
				counts.put(byteOne, 1);
			} else {
				counts.put(byteOne, count + 1);
			}
		}

		for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
			nodes.add(new Node(entry.getKey(), entry.getValue()));
		}
		return nodes;
	}

	public static Node createHuffmanTree(List<Node> nodes) {

		while (nodes.size() > 1) {
			Collections.sort(nodes);
			// System.out.println(nodes);
			// 在森林中选出两个根结点的权值最小的树合并，作为一棵新树的左、右子树，
			Node leftNode = nodes.get(0);
			// System.out.println(leftNode);
			Node righttNode = nodes.get(1);
			// System.out.println(righttNode);
			// 且新树的根结点权值为其左、右子树根结点权值之和；
			Node parentNode = new Node(null, leftNode.weight + righttNode.weight);
			parentNode.left = leftNode;
			parentNode.right = righttNode;
			// 从森林中删除选取的两棵树，并将新树加入森林；
			nodes.remove(leftNode);
			nodes.remove(righttNode);

			nodes.add(parentNode);

		}

		// 此时只需返回根节点，根节点里有指向。
		return nodes.get(0);
	}
}

// Collections进行集合排序。
class Node implements Comparable<Node> {
	// 该节点的权值
	Byte data;
	// 结点的权
	int weight;
	Node left;
	Node right;

	public Node(Byte data, int weight) {
		this.data = data;
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Node [data=" + data + ", weight=" + weight + "]";
	}

	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		// 从小到大排序。
		return this.weight - o.weight;
	}

	// 前序遍历
	public void preOrder() {
		System.out.println(this);
		if (this.left != null) {
			this.left.preOrder();
		}
		if (this.right != null) {
			this.right.preOrder();
		}
	}
}
