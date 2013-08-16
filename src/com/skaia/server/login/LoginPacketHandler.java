package com.skaia.server.login;

import com.skaia.data.input.DataSource;
import io.netty.channel.ChannelHandlerContext;

/**
 * The Login Packet Handler handles packets meant for the main
 * login sequence.
 * @author Kevin
 */
public class LoginPacketHandler {
    /**
     * Initializes the LoginPacketHandler.
     */
    public static void initialize() {
        //TODO: What even goes here?
    }

    public static void handleConnectionPong(ChannelHandlerContext ctx, DataSource ds) {
        LoginPacketPrepare.prepareLoginGoahead().writeTo(ctx); //respond with a goahead.
    }

    public static void handleAuthenticationAttempt(ChannelHandlerContext ctx, DataSource ds) {
        String username = ds.readLengthASCIIString();
        String password = ds.readLengthASCIIString();
        //TODO: Attach player to ctx in ctx.attr() and vice-versa in a constructor.
        //TODO: Also, send a AUTHENTICATION_REPONSE packet to send a response.
    }
}
