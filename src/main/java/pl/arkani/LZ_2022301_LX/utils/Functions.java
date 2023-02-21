package pl.arkani.LZ_2022301_LX.utils;


import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


import java.io.IOException;

import java.util.Properties;

public class Functions {


    public static String RPad(String str, Integer length, char car) {
        return (str + String.format("%" + length + "s", "").replace(" ", String.valueOf(car))).substring(0, length);
    }

    public static String LPad(String str, Integer length, char car) {
        return (String.format("%" + length + "s", "").replace(" ", String.valueOf(car)) + str).substring(str.length(), length + str.length());
    }



    public static String getProperties(String property) {

        Properties prop = new Properties();

        // load a properties file
        try {
            prop.load(Functions.class.getResourceAsStream("/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // get the property value and print it out
        return prop.getProperty(property);


    }
    public static String getHostName() {

        try {
         //   String hostname = InetAddress.getLocalHost().getCanonicalHostName();
            String hostname = InetAddress.getLocalHost().getHostName();
            System.out.println("Hostname: " + hostname);
            return hostname;
        } catch (Exception e) {
        }
        return null;
    }



    public static String[] getHostAddresses() {
        Set<String> HostAddresses = new HashSet<>();
        try {
            for (NetworkInterface ni : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (!ni.isLoopback() && ni.isUp() && ni.getHardwareAddress() != null) {
                    for (InterfaceAddress ia : ni.getInterfaceAddresses()) {
                        if (ia.getBroadcast() != null) {  //If limited to IPV4
                            HostAddresses.add(ia.getAddress().getHostAddress());
                        }
                    }
                }
            }
        } catch (SocketException e) { }
        return HostAddresses.toArray(new String[0]);
    }

}


