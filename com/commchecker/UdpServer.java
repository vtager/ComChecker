package com.commchecker;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.util.Date;

public class UdpServer {

	private static int port;
	private static DatagramSocket socket;
	private static int counter;
	private static ByteBuffer body;

	public static void main(String args[]) {
		if(args.length < 1){
			System.out.println("Usage:[port]");
			System.exit(0);
		}
		try {
			port = Integer.parseInt(args[0]);
			socket = new DatagramSocket(port);
			body = ByteBuffer.allocate(865335);
			System.out.println("Application started");
			while(true){
				DatagramPacket packet = new DatagramPacket(body.array(), body.array().length);
				socket.receive(packet);
				InetAddress client = packet.getAddress();
				ByteBuffer byteBuffer = ByteBuffer.wrap(packet.getData());
				long b = byteBuffer.getLong();
				int bufferSize = byteBuffer.getInt();
				//System.out.println(" Received from " + client +  " " + counter);
				System.out.println("Received from " + client + " " +  DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.LONG).format(new Date()) + " DATA LATENCY " + (System.currentTimeMillis() - b) + " " + counter++ 
						+ " buffer size " + bufferSize);	
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}
}
