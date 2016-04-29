package org.bosco.lib.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;


/**
 * 
 * @author netty book sample code author
 *
 */

public class TestSelector {

	public static void main(String []args) {
		TestSelector main = new TestSelector();
		main.startServer();
	}
	private void startServer() {
		try (
			Selector selector = Selector.open();
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()
		) {
			if ((serverSocketChannel.isOpen()) && (selector.isOpen())) {
				serverSocketChannel.configureBlocking(false);
				serverSocketChannel.bind(new InetSocketAddress(8888));
				
				serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
				System.out.println("starte test");
				
				while (true) {
					selector.select();
					Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
					
					while (keys.hasNext()) {
						SelectionKey key = (SelectionKey) keys.next();
						keys.remove();
						
						if (!key.isValid()) {
							continue;
						}
						
						if (key.isAcceptable()) {
							acceptOP(key, selector);
						}
						else if (key.isReadable()) {
							
						}
						else if (key.isWritable()) {
							
						}
					}
				}
			}
			else {
				System.out.println("test");
			}
		}
		catch (IOException e) {		// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
	}
	
	
	private void acceptOP(SelectionKey key, Selector selector)
			throws IOException 
	{
		ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
		SocketChannel socketChannel = serverChannel.accept();
		socketChannel.configureBlocking(false);
		
		
	}
}
