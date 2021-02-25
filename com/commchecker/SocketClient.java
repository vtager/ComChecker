package com.commchecker;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.util.Date;

/**
 * 
 */

/**
 * @author vtager
 *
 */
public class SocketClient {
	
	private static InetAddress dip;
	private static int port;
	private static long sleep;
	private static byte[] buffer;
	private static int buffersize = 0;

	public static void main(String args[]){
		if(args.length < 3){
			System.out.println("Usage:[ip],[port],[interval],[buffer's size]");
			System.exit(0);
		}
		try {
			dip = InetAddress.getByName(args[0]);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		port = Integer.parseInt(args[1]);
		sleep = Long.parseLong(args[2]);
		if(args.length > 3) {
			buffer = new byte[Integer.parseInt(args[3])];
			buffersize = buffer.length;
			
		}
		SocketClient u = new SocketClient();
		System.out.println("Application started");
		u.test();
	}
	
	public void test(){
	Socket socket = null;
	try {
		int counter = 0;
		socket = new Socket(dip, port);
		System.out.println(dip.getHostAddress());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		boolean exit = false;
		while (!exit) {
			try {
				long b = System.currentTimeMillis();
				dos.writeLong(b);
				dos.writeInt(buffersize);
				if(buffer != null) {
					dos.write(buffer);
				}
				System.out.println(DateFormat.getTimeInstance(DateFormat.MEDIUM).format(new Date()) + " DATA SENT " + b + " " + counter++);
				Thread.sleep(sleep);
			}catch(Exception ex){
				ex.printStackTrace();
				exit = true;
			}
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
	
	
	/*public void test(){
		Socket socket = null;
		try {
			int counter = 0;
			socket = new Socket(dip, port);
			System.out.println(dip.getHostAddress());
			DataInputStream inputStream = new DataInputStream(socket.getInputStream());
			while(true){
				long b = inputStream.readLong();
				System.out.println("DATA LATENCY " + (System.currentTimeMillis() - b) + " " + counter++);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}*/
}
