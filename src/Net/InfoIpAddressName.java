/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Net;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author nazarov
 */

public class InfoIpAddressName {

    static String hostname = "Unknown";

    public static void main(String[] args) {

        try {
            InetAddress addr;
            addr = InetAddress.getLocalHost();
            hostname = addr.getHostName();
            
            System.out.println(addr.getHostAddress());
            System.out.println(hostname);
            
        } catch (UnknownHostException ex) {
            System.out.println("Hostname can not be resolved");
        }
    }
}
