package com.commchecker;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.util.Date;

public class MultiCastSender {
	private static ByteBuffer body;
	private static InetAddress dip;
	private static int port;
	private static long sleep;
	private static byte[] buffer;
	private static int buffersize = 0;
	
	static MulticastSocket socket;
	int counter = 0;
	
	
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
		
		try{
			socket = new MulticastSocket();
			socket.setTimeToLive(15);
		}
		catch(Throwable e){
			System.out.println(e);
		}
		int counter = 0;
		
		while(true){
			try {
				body = ByteBuffer.allocate(12 + buffersize);
				body.putLong(System.currentTimeMillis());
				body.putInt(buffersize);
				DatagramPacket packet = new DatagramPacket(body.array(), body.array().length, dip, port);
					socket.send(packet);
				System.out.println(DateFormat.getTimeInstance(DateFormat.MEDIUM).format(new Date()) + " DATA SENT "  + counter++ + " buffer size " + buffersize);
				Thread.sleep(sleep);
			} catch (InterruptedException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	}

