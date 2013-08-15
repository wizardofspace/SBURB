package com.skaia.client.login;

import com.skaia.Constants;
import com.skaia.data.output.ByteArrayDataOutputStream;
import com.skaia.data.output.DataSink;

/**
 *
 * @author Kevin
 */
public class LoginPacketPrepare {
    public static DataSink prepareConnectionPong() {
        DataSink d = new ByteArrayDataOutputStream();
        d.writeByte(Constants.OP_CLIENT_CONNECTION_PONG);
        return d;
    }
}
