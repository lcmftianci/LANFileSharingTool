package ServerCore;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import common.Log4j;

/*
 * 服务器端
 * create by lilong
 */
public class Servernet {
		
		public static void main(String[] args) throws IOException{
			final ServerSocket server = new ServerSocket(8090);
			Log4j.Logout("Server启动" + server);
			
			new Thread(){
				public void run() {
					super.run();
					while(true){
						try {
							Socket client = server.accept();
							Log4j.Logout("Client接入" + client);
							WaitThread watiThread = new WaitThread(client);
							watiThread.start();
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					}
				}
			}.start();
			//server.close();
		}
		
		/*
		 * 线程等待
		 * */
		public static class WaitThread extends Thread{
			//constructor
			public WaitThread(Socket client) {
				super();
				this.client = client;
		}
			private Socket client;
			private DataInputStream reader;
			
			public void run() {
				super.run();
				while(true){
					if(reader == null){
						try {
							reader = new DataInputStream(client.getInputStream());
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					}
					try {
						String msg = reader.readUTF();
						Log4j.Logout(msg);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			}
		}
}
