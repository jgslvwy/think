package first.impl;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import first.UserService;

@Service("userService")
@Component

public class UserServiceImpl implements UserService{
 

	public String sayHello(String  id) {

		System.out.println("hello world----------------------------");

		StringBuffer sb=new StringBuffer();;

		for (int i = 0; i < 10; i++) {

			sb=sb.append("hello world-->"+i+"===="+id+"\n");

		}

		return sb.toString();

	}

	public String test(int a,int b) {

		return (a+b)+"";

	}

	public String test2() {

		String str="hello dubbo";

		return str;

	}

}
