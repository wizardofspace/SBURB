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

    public static Character handleAuthenticationAttempt(ChannelHandlerContext ctx, DataSource ds) {
        //TODO: returns a character instance if successful, otherwise null.
        //TODO: Also, send a AUTHENTICATION_REPONSE packet to send a response.
        return null;
    }
}
