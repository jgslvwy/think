package service;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

public class Think_Start {
	private int port;

	public Think_Start(int port) {
		super();
		this.port = port;
	}

	public void run() {
		// 配置Server
		ServerBootstrap bootstrap = new ServerBootstrap(
				new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
		// 设置pipeFactory

		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = Channels.pipeline(new DiscardServerHandler());
				return pipeline;
			}
		});// 绑定server端端口
		bootstrap.bind(new InetSocketAddress(port));
	}

	public static void main(String[] args) {
		new Think_Start(8080).run();
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	 static class DiscardServerHandler extends SimpleChannelHandler {
		@Override
		public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
			System.out.println("--------服务端-------");
			String str = "Hello world, I'm server";
			ChannelBuffer buffer = ChannelBuffers.buffer(str.length());
			buffer.writeBytes(str.getBytes());
			e.getChannel().write(buffer);
		}

		@Override
		public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
			ChannelBuffer buffer = (ChannelBuffer) e.getMessage();
			System.out.println("服务端接收到消息:" + buffer.toString(Charset.defaultCharset()));
		}
	}
}
