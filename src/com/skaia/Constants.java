package com.skaia;

/**
 * This interface just holds <i>public static final</i>
 * references of all the constants
 * @author Kevin
 */
public interface Constants {
    public static final int PORT = 36535;
    
    /*      Packets     */
    public static final byte OP_SERVER_CONNECTION_PING = 0x01;
    public static final byte OP_CLIENT_CONNECTION_PONG = 0x02;
}
