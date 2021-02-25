package com.commchecker;

import java.util.Arrays;

public class CommChecker {

	public static void main(String[] args) {

		if(args.length < 1){
			usage();
		}
		String[] temp = Arrays.copyOfRange(args, 1, args.length);
		switch (args[0]) {
		
		case "1":
			UdpClient.main(temp);
			break;
		
		case "2":
			UdpServer.main(temp);
			break;
		
		case "3":
			SocketClient.main(temp);
			break;

		case "4":
			SocketServer.main(temp);
			break;

		case "5":
			MultiCastSender.main(temp);
			break;

		case "6":
			MultiCastReceiver.main(temp);
			break;

		default:
			break;
		}
	}
	
	public static void usage(){
		System.out.println("Usage:");
		System.out.println("UdpClient 1,[ip],[port],[interval],[buffer's size]");
		System.out.println("UdpServer 2,[port]");
		System.out.println("SocketClient 3,[ip],[port],[interval],[buffer's size]");
		System.out.println("SocketServer 4,[port],[ip - optional for binding]");
		System.out.println("MultiCastClient 5,[ip],[port],[interval],[buffer's size]");
		System.out.println("MultiCastServer 6,[ip],[port]");
		System.exit(0);
	}

}
