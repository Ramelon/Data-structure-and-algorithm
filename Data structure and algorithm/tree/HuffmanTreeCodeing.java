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
//		System.out.println("�������ַ���");
//		byte[] deCodeHuffmanBytes = deCode(huffmanTreeCodes, huffmanCodeBytes);
//		System.out.println(new String(deCodeHuffmanBytes));
		
		String srcFile = "D:\\1.bmp";
		String dstFile = "D:\\bmp.zip";
		zipFile(srcFile, dstFile);
		
		
		System.out.println("�ɹ�1");
		
		String zipFile = "D:\\bmp.zip";
		String dstFile2 = "D:\\11.bmp";
		unZipFile(zipFile, dstFile2);
		System.out.println("�ɹ�2");
		//System.out.println(stringBuilder);
		
		
	}

	public static void preOrder(Node root) {
		if (root != null) {
			root.preOrder();
		} else {
			System.out.println("�������޷���һ��������");
		}
	}

	//��дһ����������ɶ�ѹ���ļ��Ľ�ѹ
	/**
	 * 
	 * @param zipFile ׼����ѹ���ļ�
	 * @param dstFile ���ļ���ѹ���ĸ�·��
	 */
	public static void unZipFile(String zipFile, String dstFile) {
		
		//�����ļ�������
		InputStream is = null;
		//����һ������������
		ObjectInputStream ois = null;
		//�����ļ��������
		OutputStream os = null;
		try {
			//�����ļ�������
			is = new FileInputStream(zipFile);
			//����һ����  is�����Ķ���������
			ois = new ObjectInputStream(is);
			//��ȡbyte����  huffmanBytes
			byte[] huffmanBytes = (byte[])ois.readObject();
			//��ȡ�շ��������
			Map<Byte,String> huffmanCodes = (Map<Byte,String>)ois.readObject();
			//����
			byte[] bytes = deCode(huffmanCodes, huffmanBytes);
			//��bytes ����д�뵽Ŀ���ļ�
			os = new FileOutputStream(dstFile);
			//д���ݵ� dstFile �ļ�
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
	
	//��д��������һ���ļ�����ѹ��
	/**
	 * 
	 * @param srcFile �㴫���ϣ��ѹ�����ļ���ȫ·��
	 * @param dstFile ����ѹ����ѹ���ļ��ŵ��ĸ�Ŀ¼
	 */
	public static void zipFile(String srcFile, String dstFile) {
		
		//���������
		OutputStream os = null;
		ObjectOutputStream oos = null;
		//�����ļ���������
		FileInputStream is = null;
		try {
			//�����ļ���������
			// ��������ʲô�ļ�  �����Զ����Ƶ���ʽ
			is = new FileInputStream(srcFile);
			//����һ����Դ�ļ���Сһ����byte[]
			byte[] b = new byte[is.available()];
			//��ȡ�ļ�
			is.read(b);
			//ֱ�Ӷ�Դ�ļ�ѹ��
			byte[] huffmanBytes = packZip(b);
			System.out.println(Arrays.toString(huffmanBytes));
			System.out.println(huffmanTreeCodes);
			//�����ļ��������, ���ѹ���ļ�
			os = new FileOutputStream(dstFile);
			//����һ�����ļ������������ObjectOutputStream
			oos = new ObjectOutputStream(os);
			//�� �շ����������ֽ�����д��ѹ���ļ�
			oos.writeObject(huffmanBytes); //�����ǰ�
			//���������Զ������ķ�ʽд�� �շ������룬��Ϊ���Ժ����ǻָ�Դ�ļ�ʱʹ��
			//ע��һ��Ҫ�Ѻշ������� д��ѹ���ļ�
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
	 *            �շ��������
	 * @param huffmanCodeBytes
	 *            �շ�����Ӧ���ֽ�����[-89, 110, 39, 19]
	 * @return "Hallo Word"
	 */
	public static byte[] deCode(Map<Byte, String> huffmanTreeCodes, byte[] huffmanCodeBytes) {
		// ����ƴ�� �շ�����Ӧ���ֽ�����
//		StringBuilder stringBuilder = new StringBuilder();
//
//		// ��������ѭ������ huffmanCodeBytes �õ��������ַ���
//		// ѭ������ byteToBinaryString(boolean flag, byte byteOne)
//		for (int i = 0; i < huffmanCodeBytes.length; i++) {
//			byte byteOne = huffmanCodeBytes[i];
//			// flagΪ���һλʱ������λ�����flag Ϊ true �������һλ
//			boolean flag = (i == huffmanCodeBytes.length - 1);
//			stringBuilder.append(byteToBinaryString(!flag, byteOne));
//		}
		//System.out.println(stringBuilder);
		// ����֮ǰ������Ӻ������õ� huffmanTreeCodes(Byte, String)
		// ����������Ҫ�õ����� huffmanTreeCodes(String, Byte)
		// ���Ǳ��� ���� ��key�Ƿ������ġ�
		// ת�� �շ��������
		Map<String, Byte> tempMap = new HashMap<String, Byte>();
		for (Map.Entry<Byte, String> entry : huffmanTreeCodes.entrySet()) {
			tempMap.put(entry.getValue(), entry.getKey());
		}
		// ͨ��tempMap �� ȷ��������Ҫ��ʲô
		// ����һ��list ����ǰ�����ң�ǰ������һ�����������Ķ������ַ�������ô����ӵ�list
		List<Byte> list = new ArrayList<Byte>();
		// ע�� ����û��i++ ��������
		for (int i = 0; i < stringBuilder.length();) {
			// �������Ʋ���
			int count = 1;
			// �����ж��Ƿ��ҵ�
			boolean flag = true;
			// �����洢�ҵ���ֵ
			Byte byteOne = null;

			while (flag) {
				// ��һֱ����ȡ ֪��֪��Ϊֹ
				String key = stringBuilder.substring(i, i + count);
				// ��ȡ�շ������� ��� vlaue Byte
				byteOne = tempMap.get(key);
				if (byteOne == null) {
					count++;
				} else {
					flag = false;
				}
			}
			list.add(byteOne);
			// ͨ��i+count ������ѭ���Ĳ���
			i += count;
		}
		//System.out.println(list);
		// ��list ���ֵ ���뵽byte������
		byte[] tempByte = new byte[list.size()];
		for (int i = 0; i < list.size(); i++) {
			tempByte[i] = list.get(i);
		}
		return tempByte;
	}

	// �շ������� byte[] [-89, 110, 39, 19]
	// ����ͨ�����byte���� -> ���� ת���ɶ����Ƶ��ַ���
	// -89 -> 10100111
	/**
	 * 
	 * @param flag
	 *            ���flag��ʲô�ã���������byteOne������ʱ�����ܻ���λ������ôflag�����ж��Ƿ񲹸�λ
	 * @param byteOne
	 *            �ֽ����������
	 * @return ������
	 */
	// Hallo Wo d
//	public static String byteToBinaryString(boolean flag, byte byteOne) {
//		// ��ʱ����
//		int temp = byteOne;
//		// ֻҪ��Ӧ�Ķ�������λ��һ��Ϊ1ʱ�����λ��Ϊ1��
//		// 256 1 0000 0000 |= �κ�һ���� �����������ôǰ�涼��0 19����>10011 00010011
//		if (flag) {
//			temp |= 256;
//		} 
//		// 3-> 11 ������ 0011  ���� ���ǰ���0���˵�
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
		// �����շ�����
		Node root = createHuffmanTree(nodes);
		// ��ȡ�շ�������
		huffmanTreeCodes = getCodes(root);
		System.out.println(getCodes(root));
		// ��ȡ��Ӧ�ֽ�����
		byte[] huffmanCodeBytes = zip(bytes, huffmanTreeCodes);
		return huffmanCodeBytes;
	}

	/**
	 * 
	 * @param bytes
	 *            ԭʼ�ַ���ת��byte����
	 * @param huffmanCode
	 *            �������Ǻշ����������Ӧ�� {65=10, 66=11011, 67=1100, 68=0, 69=11010, 70=111}
	 * @return ����һ��byte[] ���� ���Ƕ����ƶ�Ӧ������
	 */
	public static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCode) {
		// ����:����ƴ�������ַ�����
		StringBuilder stringSplicing = new StringBuilder();

		// 68 68 68 68 68 68 68 68
		// stringBuilder = 00000000
		// byte[] ��һλ 0
		// ��󴫻�ȥ����һ��byte������ô���Ұ�stringBuilder��ǰ����ÿ��λ ת��һ����Ӧ�����֣� ��������Ҫreturn��byte����
		// ����ȥ��byte[] ���� һλ���� ��Ӧ��stringBuilder��8���ַ���
		for (byte byteone : bytes) {
			stringSplicing.append(huffmanCode.get(byteone));
		}
		System.out.println(stringSplicing);
		// ��������Ҫ�����ľ��� �Ѷ�Ӧ�Ķ����� ת��һ������
		// �ѵ�ǰ���� ����Ϊ������ 8λ ��ôһ��8λ��Ӧ�ľ���һ�����ӣ�������Ȳ�����
		// ��Ϊ��ǰ���Ȳ�������8ʱ ��ô��������� 1 - 7 ȡ��С + 7 ��ô �õ��ľ��Ƿ��ϵĳ���
		// 9 + 7 = 16 2
		// 10 + 7 = 17 2
		// 15 + 7 = 22 2
		// 16 + 7 = 23 2
		int len = (stringSplicing.length() + 7) / 8;
		// ����˵�ǰ�������ַ��� ���Ի���8λ�ĳ���
		// �Ѷ�Ӧ8λ���ȵ����� �ó������洢��byte�����
		byte[] huffmanCodeBytes = new byte[len];
		int index = 0;
		for (int i = 0; i < stringSplicing.length(); i += 8) {
			// ������ʱ�洢
			String temp;
			// �����±�Խ��
			if (i + 8 > stringSplicing.length()) { // �����λ�漸λ
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

	// �������ɵĺշ�������Ȼ��õ���Ӧ�ĺշ�������
	// {'65':"01"} {'66':"11011"}...
	// �½�һ��hashmap���������
	private static Map<Byte, String> huffmanTreeCodes = new HashMap<Byte, String>();
	// StringBuffer���������һ���ַ����пɱ���ַ�������һ��StringBuffer�������Ժ�
	// ͨ��StringBuffer�ṩ��append()��insert()��reverse()��setCharAt()��setLength()�ȷ������Ըı�����ַ���������ַ����С�
	// һ��ͨ��StringBuffer������������Ҫ���ַ������Ϳ��Ե�������toString()��������ת��Ϊһ��String����
	// stringBuilder: ����ƴ���ַ���
	private static StringBuilder stringBuilder = new StringBuilder();

	/**
	 * 
	 * @param node
	 *            ���Ѿ����ɵĺշ������Ľ��
	 * @param code
	 *            "0" "1" ·��
	 * @param stringBuilder
	 *            �����洢·����Ҳ����˵������ƴ���ַ���
	 */
	public static Map<Byte, String> getCodes(Node root) {
		if (root == null) {
			return null;
		}
		// �������ݹ�
		getCodes(root.left, "0", stringBuilder);
		// �������ݹ�
		getCodes(root.right, "1", stringBuilder);
		return huffmanTreeCodes;
	}

	// �շ������� ���ǽ��÷�����ȫ�����ַ����ָ�������������ͷ��ƽ��������̵����֣���ʱ��֮Ϊ��ѱ���
	// ����ֻ��Ҫ����Ҷ�ӽ��
	public static void getCodes(Node node, String code, StringBuilder stringBuilder) {
		// ��Ϊ�ݹ��ԭ�����ǻ���Ҫ����һ��stringBuilder2 ��ƴ���ַ���
		StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
		stringBuilder2.append(code);
		if (node != null) {
			// �жϸý���Ƿ�ΪҶ�ӽ��
			if (node.data == null) {
				// �������ݹ�
				getCodes(node.left, "0", stringBuilder2);
				// �������ݹ�
				getCodes(node.right, "1", stringBuilder2);
			} else {// Ҷ�ӽ��
					// һ��ͨ��StringBuffer������������Ҫ���ַ������Ϳ��Ե�������toString()��������ת��Ϊһ��String����
				huffmanTreeCodes.put(node.data, stringBuilder2.toString());
			}
		}

	}

	public static List<Node> getNode(byte[] bytes) {
		// �½�ArrayList,�����ÿ���ַ����ֵ�Ƶ��(�Լ�ֵ�Ե���ʽ���)
		List<Node> nodes = new ArrayList<Node>();
		// ͳ���ַ�����Ƶ�� �Լ�ֵ����ʽ���
		Map<Byte, Integer> counts = new HashMap<Byte, Integer>();
		for (byte byteOne : bytes) {
			Integer count = counts.get(byteOne);
			// ���hashmap ����û�д�Ŵ��ַ� �ʹ��
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
			// ��ɭ����ѡ������������Ȩֵ��С�����ϲ�����Ϊһ������������������
			Node leftNode = nodes.get(0);
			// System.out.println(leftNode);
			Node righttNode = nodes.get(1);
			// System.out.println(righttNode);
			// �������ĸ����ȨֵΪ���������������Ȩֵ֮�ͣ�
			Node parentNode = new Node(null, leftNode.weight + righttNode.weight);
			parentNode.left = leftNode;
			parentNode.right = righttNode;
			// ��ɭ����ɾ��ѡȡ����������������������ɭ�֣�
			nodes.remove(leftNode);
			nodes.remove(righttNode);

			nodes.add(parentNode);

		}

		// ��ʱֻ�践�ظ��ڵ㣬���ڵ�����ָ��
		return nodes.get(0);
	}
}

// Collections���м�������
class Node implements Comparable<Node> {
	// �ýڵ��Ȩֵ
	Byte data;
	// ����Ȩ
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
		// ��С��������
		return this.weight - o.weight;
	}

	// ǰ�����
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
