# ComChecker
communication testing utility

USAGE

UdpClient 1,[ip],[port],[interval],[buffer's size]

UdpServer 2,[port]

SocketClient 3,[ip],[port],[interval],[buffer's size]

SocketServer 4,[port],[ip - optional for binding]

MultiCastClient 5,[ip],[port],[interval],[buffer's size]

MultiCastServer 6,[ip],[port]


1. Send 30000 bytes via udp

java -jar commchecker.jar 1 localhost 25001 1000 30000
java -jar commchecker.jar 2 25001 

2. Send 3000 bytes via tcp

java -jar commchecker.jar 3 localhost 25001 1000 3000
java -jar commchecker.jar 4 25001

3. Send 60006 bytes via multicast

java -jar commchecker.jar 5 227.100.100.100 25001 1000 60000
java -jar commchecker.jar 6 227.100.100.100 25001
