package demo;

public class Service implements java.io.Serializable {
	private String url;// 链接地址
	private String port;// 端口号
	private String method;// 方法名
	private String key;// 唯一标识：存储时间第一次和时间戳

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Service(String url, String port, String method, String key) {
		this.url = url;
		this.port = port;
		this.method = method;
		this.key = key;
	}

}
