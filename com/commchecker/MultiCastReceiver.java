package com.commchecker;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.BindException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.util.Date;

public class MultiCastReceiver {
	
	private static InetAddress dip;
	private static int port;
	
	Thread thread;
	MulticastSocket socket;
	int counter;
	private StartServer commTask;
	
	public static void main(String args[]){
		if(args.length < 2){
			System.out.println("Usage:[ip],[port]");
			System.exit(0);
		}
		try {
			dip = InetAddress.getByName(args[0]);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		port = Integer.parseInt(args[1]);
		MultiCastReceiver u = new MultiCastReceiver();
		u.createTasks();
		u.execute();
		System.out.println("Application started");
	}
	
	private void execute() {
		try {
			socket = new MulticastSocket(port);
			socket.joinGroup(dip);
		} catch (Throwable e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Thread commThread;
		commThread = new Thread(commTask);
		try {
			commThread.start();
		} catch (IllegalThreadStateException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}

	private void createTasks() {
		commTask = new StartServer();
		
	}

	
	public class StartServer implements Runnable {

		public void run(){
			try{
				byte[] buffer = new byte[65335];
					while(true){
						try{
							DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
							socket.receive(packet);
							InetAddress client = packet.getAddress();
							ByteBuffer byteBuffer = ByteBuffer.wrap(packet.getData());
							long b = byteBuffer.getLong();
							int bufferSize = byteBuffer.getInt();
							//System.out.println(" Received from " + client +  " " + counter);
							System.out.println("Received from " + client + " " +  DateFormat.getTimeInstance(DateFormat.MEDIUM).format(new Date()) + " DATA LATENCY " + (System.currentTimeMillis() - b) + " " + counter++
									+ " buffer size " + bufferSize);
						}
						catch(UnknownHostException ue){
							System.out.println(ue);
						}
					}
			}
			catch(BindException b){
				System.out.println(b);
			}
			catch(IOException ex){
				System.out.println(ex);
			}
	}
		
	}
	
	
}
				
