package com.commchecker;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.util.Date;

public class UdpClient {
	private static ByteBuffer body;
	private static InetAddress dip;
	private static int port;
	private static long sleep;
	static DatagramSocket socket;
	private static byte[] buffer;
	private static int buffersize = 0;
	

	public static void main(String args[]) {
		if(args.length < 3){
			System.out.println("Usage:[ip],[port],[interval],[buffer's size]");
			System.exit(0);
		}
		try {
			dip = InetAddress.getByName(args[0]);
			socket = new DatagramSocket();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		port = Integer.parseInt(args[1]);
		sleep = Long.parseLong(args[2]);
		if(args.length > 3) {
			buffer = new byte[Integer.parseInt(args[3])];
			buffersize = buffer.length;
			
		}
		int counter = 0;
		
		while (true) {
			try {
				body = ByteBuffer.allocate(12 + buffersize);
				body.putLong(System.currentTimeMillis());
				body.putInt(buffersize);
				DatagramPacket packet = new DatagramPacket(body.array(), body.array().length, dip, port);
				socket.send(packet);
				System.out.println(DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.LONG).format(new Date()) + " DATA SENT "  + counter++ + " buffer size " + buffersize);
				Thread.sleep(sleep);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}