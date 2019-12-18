package utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializableUtils {

	/**
	 * 字节数组转换为对象(序列化)
	 * 
	 * @author liaowei
	 * @date 2017年3月31日 下午2:00:17
	 * @param bytes
	 * @return
	 */
	public static Object ByteToObject(byte[] bytes) {

		Object obj = new Object();
		try {
			// bytearray to object
			ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
			ObjectInputStream oi = new ObjectInputStream(bi);
			obj = oi.readObject();
			bi.close();
			oi.close();
		} catch (Exception e) {
			System.out.println("translation" + e.getMessage());
			e.printStackTrace();
		}
		return obj;
	}

	/**
     * 对象转换为字节数组(反序列化)
     *
     * @author liaowei
     * @date 2017年3月31日 下午2:00:17
     * @param obj
     * @return
     */
	public static byte[] ObjectToByte(Object obj) {
		byte[] bytes = null;
		try {
			// object to bytearray
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			ObjectOutputStream oo = new ObjectOutputStream(bo);
			oo.writeObject(obj);
			bytes = bo.toByteArray();
			bo.close();
			oo.close();
		} catch (Exception e) {
			System.out.println("translation" + e.getMessage());
			e.printStackTrace();
		}
		return (bytes);
	}
}
