package serviceIntercept;

import java.io.IOException;
import java.nio.file.DirectoryStream.Filter;

public class SimpleFilter implements Filter {
	@Override
	public boolean accept(Object entry) throws IOException {
		return false;
	}
	
	/**
	 * 判断是否是白名单，如果是白名单，则不进行拦截
	 * @return
	 * @throws IOException
	 */
	public boolean accept() throws IOException{
		return false;
	}
   
	
	
}
