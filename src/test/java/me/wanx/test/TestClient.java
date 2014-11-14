package me.wanx.test;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;




public class TestClient {
	public static void main(String[] args) {
		try {
			Socket client = new Socket("localhost", 9999);
			OutputStream os = client.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.println("di");
			pw.flush();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
