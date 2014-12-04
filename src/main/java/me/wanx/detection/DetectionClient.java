package me.wanx.detection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import me.wanx.common.detection.DetectionConstant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
* @ClassName: DetectionClient 
* @Description: socket检测客户端 
* @author gqwang
* @date 2014年11月14日 下午2:54:48 
*
 */
public class DetectionClient {
	private static final Logger logger = LoggerFactory.getLogger(DetectionClient.class);
	
	private String host;
	private int port;
	
	public DetectionClient(String host,int port){
		this.host = host;
		this.port = port;
		this.client();
	}
	
	public void client(){
		BufferedReader reader = null;
		PrintWriter writer = null;
		try {
			Socket socket = new Socket(host, port);
			while(true){
				writer = new PrintWriter(socket.getOutputStream());
				writer.println(DetectionConstant.SERVER_SEND_STR);
				writer.flush();
				
				logger.info("client向server发送消息完成");
				
				//获取数据
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String info = reader.readLine();
				logger.info("client收到server发送的消息是:"+info);
				try {
					logger.info("client休息5s");
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (UnknownHostException e) {
			logger.error("client找不到主机",e);
		} catch (IOException e) {
			logger.error("client服务器建立socket连接失败",e);
		}finally{
			writer.close();
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
//		DetectionClient client = new DetectionClient("localhost",9999);
//		client.client();
		ClassLoader cl = DetectionClient.class.getClassLoader();
		System.out.println(cl);
		System.out.println(cl.getParent());
	}

}
