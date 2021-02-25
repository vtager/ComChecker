package com.commchecker;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
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
public class SocketServer implements Runnable{
	
	private DataInputStream dis;
	private static InetAddress dip;
	private static int port;

	public static void main(String args[]){
		if(args.length < 1){
			System.out.println("Usage:[port],[ip - optional for binding");
			System.exit(0);
		}
		try {
			if(args.length > 1) {
				dip = InetAddress.getByName(args[1]);
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		port = Integer.parseInt(args[0]);
		SocketServer u = new SocketServer();
		System.out.println("Application started");
		u.test();
		Thread commThread = new Thread(u);
		commThread.start();
	}
	
	public void test(){	
		ServerSocket serverSocket = null;
		try {
			if(dip != null){
				serverSocket = new ServerSocket(port, 0, dip);
			}
			else{
				serverSocket = new ServerSocket(port, 0);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("waiting for connection on port:"+serverSocket == null? null:serverSocket.getLocalPort());
		try {
			Socket currSocket=serverSocket.accept();
			dis = new DataInputStream(currSocket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() {
		int counter = 0;
		boolean exit = false;
		while (!exit) {
			try {
				long b = dis.readLong();
				int bufferSize = dis.readInt();
				if(bufferSize > 0) {
					dis.skipBytes(bufferSize);
				}
				System.out.println(DateFormat.getTimeInstance(DateFormat.MEDIUM).format(new Date()) + " DATA LATENCY " + (System.currentTimeMillis() - b) + " " + counter++ + " buffersize " + bufferSize);
			}catch(Exception ex){
				ex.printStackTrace();
				exit = true;
			}
		}
	}
	
	/*public void run() {
		int counter = 0;
		while (true) {
			try {
				long b = System.currentTimeMillis();
				dataOutputStream.writeLong(b);
				System.out.println(DateFormat.getTimeInstance(DateFormat.MEDIUM).format(new Date()) + " DATA SENT " + b + " " + counter++);
				Thread.sleep(sleep);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}*/
	
}
