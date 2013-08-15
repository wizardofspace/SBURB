package com.skaia;

/**
 * This interface just holds <i>public static final</i>
 * references of all the constants
 * @author Kevin
 */
public interface Constants {
    public static final int PORT = 36535;
    
    /*      Packets     */
    
    /**
     * Structure: [OPCODE] <p>
     * Sent at the beginning of the connection, client should respond
     * with a OP_CLIENT_CONNECTION_PONG.
     */
    public static final byte OP_SERVER_CONNECTION_PING          = 0x01;
    
    /**
     * Structure: [OPCODE] <p>
     * Sent as a reponse of a SERVER_CONNECTION_PING, and tells the
     * server that the connection is valid.
     */
    public static final byte OP_CLIENT_CONNECTION_PONG          = 0x02;
    
    /**
     * Structure: [OPCODE] <p>
     * Sent as a go-ahead, so the client knows that the server is
     * awaiting login attempts.
     */
    public static final byte OP_SERVER_LOGIN_GOAHEAD            = 0x03;
    
    /**
     * Structure: [OPCODE] [LA-String username] [LA-String password] <p>
     * This characterizes a login attempt, including a username and
     * password. If the server is still in registration mode, the
     * server-wide password will be accepted. Otherwise, only a
     * username-password combination of an already-registered user
     * will be accepted.
     */
    public static final byte OP_CLIENT_AUTHENTICATION_ATTEMPT   = 0x04;
    
    /**
     * Structure: [OPCODE] [Integer response] <p>
     * The authentication reponse tells the client if the authentication
     * is successful or not. The response ID is characterized as so: <p>
     * 1 = successful <p>
     * 2 = unsuccessful, already registered (tried server password,
     * but name already taken)<p>
     * 3 = unsuccessful, registration over (tried server password,
     * but registration is blocked) <p>
     * 4 = unsuccessful, username-password combination <p>
     * 5 = unsuccessful, server is offline or not accepting logins <p>
     */
    public static final byte OP_SERVER_AUTHENTICATION_RESPONSE  = 0x05;
}
