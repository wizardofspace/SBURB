package com.skaia.client.net;

import com.skaia.Constants;
import com.skaia.client.login.LoginPacketHandler;
import com.skaia.data.input.ByteArrayDataInputStream;
import com.skaia.data.input.DataSource;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 *
 * @author Kevin
 */
public class ClientMessageHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //do nothing.
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

    private void handlePacket(ChannelHandlerContext ctx, DataSource ds, int opcode) {
        switch (opcode) {
            case Constants.OP_SERVER_CONNECTION_PING:
                LoginPacketHandler.handleConnectionPing(ctx, ds);
                break;
            case Constants.OP_SERVER_LOGIN_GOAHEAD:
                LoginPacketHandler.handleLoginGoahead(ctx, ds);
                break;
            case Constants.OP_SERVER_AUTHENTICATION_RESPONSE:
                LoginPacketHandler.handleAuthenticationResponse(ctx, ds);
                break;
        }
    }
}
