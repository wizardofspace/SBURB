package com.skaia.client;

import com.skaia.Constants;
import com.skaia.client.login.LoginPacketHandler;
import com.skaia.client.net.ClientMessageHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The starting point for Client operations.
 * Starts the graphics layer, tries to connect to a
 * server, &c.
 * @author Kevin
 */

public class ClientStart {
    /**
     * Invoked upon choosing "Server" as mode of operation.
     */
    public static void run(final String ipAddress) {
        //TODO: Initialize LWJGL?
        //TODO: Initialize the PacketHandlers, like the server does.
        LoginPacketHandler.initialize();
        
        //We want to start the networking in a separate thread, though.
        new Thread(new Runnable() {
            @Override
            public void run() {
                networkingRun(ipAddress);
            }
        }).start();
    }
    
    private static void networkingRun(String ipAddress) {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
             .channel(NioSocketChannel.class)
             .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(
                            new ByteArrayEncoder(),
                            new ByteArrayDecoder(),
                            new ClientMessageHandler());
                }
             });

            // Start the connection attempt.
            b.connect(ipAddress, Constants.PORT).sync().channel().closeFuture().sync();
        } catch (InterruptedException ex) {
            Logger.getLogger(ClientStart.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            group.shutdownGracefully();
        }
    }
}
