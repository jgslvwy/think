package serviceIntercept;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author jgs
 *
 * @param <E>
 */
public class SimpleInterceptor<E>  {
	
	private  ConcurrentLinkedQueue<E> queue = new ConcurrentLinkedQueue<E>();  	
    
	
	/**
	 *拦截消费者，添加到消费者队列
	 *与缓存进行比对，如果存在则解密，不存在
	 *则回退
	 */
	public void addConsumer(){
	  
	}
	
	
	/**
	 * 解密
	 */
	public void  decrypt(){
		
	}
    
	/**
	 * 加密
	 */
	public void encrypt(){
		
	}
	
}
