package com.skaia.server;

import com.skaia.server.net.ServerMessageHandler;
import com.skaia.Constants;
import com.skaia.server.login.LoginPacketHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The starting point for Server operations.
 * Loads maps from some serializable file, 
 * waits for incoming connections, &c.
 * @author Kevin
 */
public class ServerStart {
    /**
     * Invoked upon choosing "Server" as mode of operation.
     */
    public static void run() {
        /* Server Handler initialization */
        GameInformation.initialize(); //loads all of the game info
        LoginPacketHandler.initialize(); //Initializes the AuthenticationHandler
        
        /* Network initialization */
        // Start Thread Loops
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        
        try {
            //Try to attach listeners onto server.
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
             .channel(NioServerSocketChannel.class)
             .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(
                            new ByteArrayEncoder(),
                            new ByteArrayDecoder(),
                            new ServerMessageHandler());
                }
            });
            
            // Bind to port and start to accept incoming connections.
            b.bind(Constants.PORT).sync().channel().closeFuture().sync();
        } catch (InterruptedException ex) {
            Logger.getLogger(ServerStart.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //Shut down if an exception is caught.
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
