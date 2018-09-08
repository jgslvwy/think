package trapeze.entity;

import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 简单的功能
 * 
 * @author 荆国帅
 * @date 2018年9月6日
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
	 * 添加某个clazz,需要占用则添加对象信息
	 * 
	 * @author 荆国帅
	 * @throws Exception
	 * @date 2018年9月6日
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
				// 判断是否有之前释放的位置，如果有，则抢先去那个，否则去另一个位置
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
	 * 某个人释放位置，因为是数组数据结构，连续的表空间， 无法记录其位置，所以暂时用临时对象替代的方式，如果移除 还会保持原有位置
	 * 
	 * @author 荆国帅
	 * @date 2018年9月6日
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
	 * 找到实体对应的hash对应的位置
	 * 
	 * @author 荆国帅
	 * @date 2018年9月6日
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
	 * 找到实体对应的hash
	 * 
	 * @author 荆国帅
	 * @param <T>
	 * @date 2018年9月6日
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
	 * 判断是否超过秋千能承受的重量
	 * 
	 * @author 荆国帅
	 * @date 2018年9月6日
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
		// 计算hashCode，并无符号移动到低位
		return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
	}
}
