package com.skaia.server.net;

import com.skaia.Constants;
import com.skaia.data.input.ByteArrayDataInputStream;
import com.skaia.data.input.DataSource;
import com.skaia.server.login.LoginPacketHandler;
import com.skaia.server.login.LoginPacketPrepare;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;

/**
 * This class handles the messages transmitted by a client.
 * Upon receiving a message, it converts it into a DataSource
 * for easy handling, pops off an OpCode, and sends the packet
 * on its way to be handled accordingly.
 * @author Kevin
 */
public class ServerMessageHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LoginPacketPrepare.prepareConnectionPing().writeTo(ctx);
    }
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!(msg instanceof byte[]))
           ; //TODO: panic!
        
        byte[] array = (byte[]) msg;
        DataSource ds = new ByteArrayDataInputStream(array);
        handlePacket(ctx, ds, ds.readByte());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //TODO: panic!
    }

    /**
     * Handles the packet according to the Opcode, which characterizes the code.
     * @param ctx The sender of the message/packet.
     * @param ds The packet, without the initial opcode.
     * @param opcode The opcode, which determines the type of packet being handled.
     */
    private void handlePacket(ChannelHandlerContext ctx, DataSource ds, int opcode) {
        switch (opcode) {
            case Constants.OP_CLIENT_CONNECTION_PONG:
                LoginPacketHandler.handleConnectionPong(ctx, ds);
                break;
            case Constants.OP_CLIENT_AUTHENTICATION_ATTEMPT:
                Character c = LoginPacketHandler.handleAuthenticationAttempt(ctx, ds);
                if (c != null) {
                    ctx.attr(new AttributeKey<Character>("player")).set(c);
                    //TODO: If in register mode, send to register mode screen, otherwise send to logged in map.
                }
                break;
        }
    }
}
