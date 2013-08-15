package com.skaia.server.login;

import com.skaia.Constants;
import com.skaia.data.output.ByteArrayDataOutputStream;
import com.skaia.data.output.DataSink;

/**
 * This class prepares all of the packets necessary for the login sequence.
 * After calling a method to prepare a packet, use writeTo() to write
 * to a certain context in order to transmit the packet.
 * @author Kevin
 */
public class LoginPacketPrepare {
    public static DataSink prepareConnectionPing() {
        DataSink d = new ByteArrayDataOutputStream();
        d.writeByte(Constants.OP_SERVER_CONNECTION_PING);
        return d;
    }

    public static DataSink prepareLoginGoahead() {
        DataSink d = new ByteArrayDataOutputStream();
        d.writeByte(Constants.OP_SERVER_LOGIN_GOAHEAD);
        return d;
    }
}
