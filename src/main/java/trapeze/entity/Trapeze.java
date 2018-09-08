package trapeze.entity;

import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

/**
 * �򵥵Ĺ���
 * 
 * @author ����˧
 * @date 2018��9��6��
 * @version 1.0
 */
public class Trapeze extends ReentrantLock {
	private static int FACTOR = 2;
	private static int THRESHOLD = 1000;
	int weight;
	int size;
	int length;
	Seat[] seat = new Seat[FACTOR];
	Rope rope;

	static class Seat<T> {
		private int hash;
		private static int length_init = 500;
		private static int width_init = 500;
		private static int ply_init = 10;
		int length;
		int width;
		int ply;
		T t;

		public Seat() {
			this.length = length_init;
			this.width = width_init;
			this.ply = ply_init;
		}

		public Seat(int length, int width, int ply) {
			this.length = length;
			this.width = width;
			this.ply = ply;
		}

	}

	static class Rope {
		int length;
		String color;
		int size;
	}

	/**
	 * ���ĳ��clazz,��Ҫռ������Ӷ�����Ϣ
	 * 
	 * @author ����˧
	 * @throws Exception
	 * @date 2018��9��6��
	 */
	public void add(Object o) {
		try {
			if (tryLock()) {
				if (size == 0) {
					Seat<Object> seatEntity = new Seat();
					seatEntity.t = o;
					seatEntity.hash = hash(o);
					seat[size++] = seatEntity;
					return;
				}
				if (size == seat.length) {
					throw new Exception("over size");
				}
				// �ж��Ƿ���֮ǰ�ͷŵ�λ�ã�����У�������ȥ�Ǹ�������ȥ��һ��λ��
				if (findHash(seat, 0) != -1) {
					for (int i = 0; i < seat.length; i++) {
						if (seat[i].hash == 0) {
							seat[i].t = o;
							seat[i].hash = hash(o);
						}
					}
					return;
				} else {
					Seat<Object> seatEntity = new Seat();
					seatEntity.t = o;
					seatEntity.hash = hash(o);
					seat[++size] = seatEntity;
					return;
				}
			}
		} catch (Exception e) {

		} finally {
			unlock();
		}
	}

	/**
	 * ĳ�����ͷ�λ�ã���Ϊ���������ݽṹ�������ı�ռ䣬 �޷���¼��λ�ã�������ʱ����ʱ��������ķ�ʽ������Ƴ� ���ᱣ��ԭ��λ��
	 * 
	 * @author ����˧
	 * @date 2018��9��6��
	 */
	public void remove(Object o) {
		if (size == 0)
			return;
		int position = findHash(seat, hash(o));
		if (-1 == position)
			return;
		else {
			seat[position] = new Seat();
			seat[position].hash = 0;
		}
	}

	/**
	 * �ҵ�ʵ���Ӧ��hash��Ӧ��λ��
	 * 
	 * @author ����˧
	 * @date 2018��9��6��
	 */
	public int findHash(Seat[] seat, int hash) {
		if (Objects.nonNull(seat)) {
			for (int i = 0; i < seat.length; i++) {
				if (seat[i].hash == hash)
					return i;
			}
		}
		return -1;
	}	

	/**
	 * �ҵ�ʵ���Ӧ��hash
	 * 
	 * @author ����˧
	 * @param <T>
	 * @date 2018��9��6��
	 */
	public <T> T find(Object o) {
		int hash = hash(o);
		if (Objects.nonNull(seat)) {
			for (int i = 0; i < seat.length; i++) {
				if (seat[i].hash == hash)
					return (T) seat[i];
			}
		}
		return null;
	}

	/**
	 * �ж��Ƿ񳬹���ǧ�ܳ��ܵ�����
	 * 
	 * @author ����˧
	 * @date 2018��9��6��
	 * @return
	 */
	protected Boolean isOver(int wei) {
		if (wei + weight > THRESHOLD) {
			return false;
		} else {
			return true;
		}
	}

	static final int hash(Object key) {
		int h;
		// ����hashCode�����޷����ƶ�����λ
		return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
	}
}
