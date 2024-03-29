package com.gx.code.utils.nio;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SelectorUtils {
    public void startServer() throws Exception {
        int channels = 0;
        int nKeys = 0;
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(InetAddress.getLocalHost(), 9080));
        serverSocketChannel.configureBlocking(false);
        // Four SelectionKey totally: CONNECT, ACCEPT, READ, WRITE
        // A key representing the registration of this channel with the given selector.
        SelectionKey returnedSelectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println(returnedSelectionKey);
        while (true) {
            // Select inform us that the event we are interested in happens by select method.
            // This method will be blocking until at least one key can be returned.
            nKeys = selector.select();
            System.out.println("NBTest: Number of keys after select operation: " + nKeys);
           
            // If the event we registered happens, the nKeys should be greater than 0.
            if (nKeys > 0) {
                // Selector returns a group of SelectionKeys
                // We retrieve the registered channel by "channel" method in the returned keys.
                Set selectedKeys = selector.selectedKeys();
                Iterator i = selectedKeys.iterator();
                while (i.hasNext()) {
                    SelectionKey selectionKey = (SelectionKey) i.next();
                    System.out.println(selectionKey);
                    // Since the current key is handled, we can remove it from the ready keys.
                    i.remove();
                   
                    if (selectionKey.isAcceptable()) {
                        // Get the registered channel by "channel" method.
                        Socket socket = ((ServerSocketChannel) selectionKey.channel()).accept().socket();
                        SocketChannel sc = socket.getChannel();
                       
                        sc.configureBlocking(false);
                        sc.register(selector, SelectionKey.OP_READ
                                | SelectionKey.OP_WRITE);
                        System.out.println(++channels);
                    } else {
                        System.out.println("NBTest: Channel not acceptable");
                    }
                }
            } else {
                System.out.println("NBTest: Select finished without any keys.");
            }
        }
    }

}
