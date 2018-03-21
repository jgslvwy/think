package connect;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import demo.Service;
import redis.clients.jedis.Jedis;
import util.KeyUtil;
import util.SerializeUtil;

public class connectWorld { 
	/**
	 * 所有的配置层放置网关进行配置
	 * 由网关进行加密，请求由网关进行解密
	 * 对服务层提供者进行收集(访问一次加载到缓存中)
	 */
	public void gather(){
		ClassPathXmlApplicationContext context =new ClassPathXmlApplicationContext("");
		context.start();
        Jedis redis= new Jedis("10.2.31.38", 6379);
        Map<String, Service> services = new ConcurrentHashMap<String, Service>();
        services.put("test".getBytes(), SerializeUtil.serialize(new Service("localhost", "8083","test",KeyUtil.addKey(new Date(),"1"))));
        redis.set();
	}
	
	/**
	 * 如果过了一段时间，该服务没有再次访问，则
	 * think认为是无用的服务，则从缓存中移出
	 * 只第一次访问后的再次判断
	 */
	public void findAll(){
        Jedis redis= new Jedis("10.2.31.38", 6379);
        redis.get
	}
	public void connect() throws IOException {
       
		
	}
}
