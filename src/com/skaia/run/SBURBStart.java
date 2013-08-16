package com.skaia.run;

import com.skaia.client.ClientStart;
import com.skaia.server.ServerStart;
import java.util.Scanner;

/**
 * This is the beginning of the SBURB program.
 * The purpose of this main method is to determine
 * whether the player would like to invoke a new
 * client or server. From there, it calls either
 * <code>com.skaia.server.ServerStart.run()</code> or
 * <code>com.skaia.client.ClientStart.run()</code>.
 * @author Kevin
 */

public class SBURBStart {
    /**
     * Simple main method.
     */
    public static void main(String[] args) {
        // TODO: We can change this around, to make it prettier later.
        //temporary
        System.out.print("type client or server to start one: ");
        String selection = new Scanner(System.in).nextLine();
        if (selection.equals("client")) {
            System.out.println("Client selected!");
            System.out.print("A server address is needed: ");
            ClientStart.run(new Scanner(System.in).nextLine());
        } else if (selection.equals("server")) {
            System.out.println("Server selected!");
            ServerStart.run();
        } else {
            System.out.println("Unknown selection!");
        }
    }
}
