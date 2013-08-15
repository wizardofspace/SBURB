package com.skaia.client.login;

import com.skaia.data.input.DataSource;
import io.netty.channel.ChannelHandlerContext;

/**
 *
 * @author Kevin
 */
public class LoginPacketHandler {
    public static void initialize() {
        
    }

    public static void handleConnectionPing(ChannelHandlerContext ctx, DataSource ds) {
        LoginPacketPrepare.prepareConnectionPong().writeTo(ctx);
    }

    public static void handleLoginGoahead(ChannelHandlerContext ctx, DataSource ds) {
        //TODO: Load login screen now
    }

    public static void handleAuthenticationResponse(ChannelHandlerContext ctx, DataSource ds) {
        int responseType = ds.readInt();
        
        switch (responseType) {
            case 1: //successful
                //TODO: Login screen fades to black, waits for next packet.
                break;
            case 2: //unsuccessful, already registered
                break;
            case 3: //unsuccessful, registration is over
                break;
            case 4: //unsuccessful, password incorrect
                break;
            case 5: //unsuccessful, server is offline (not accepting logins)
                break;
        }
    }
}
