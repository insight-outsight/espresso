package org.ootb.espresso.facilities;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetUtils {
    public static String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return "NA";
        }
    }
}
