package pl.arkani.LZ_2022301_LX.utils;


import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;

import java.util.*;


import java.io.IOException;

public class Functions {

    public static String[] getHostAddresses2() {
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




    private static List<String> getFieldNamesForClass(Class<?> clazz) throws Exception {
        List<String> fieldNames = new ArrayList<String>();
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fieldNames.add(fields[i].getName());
        }
        return fieldNames;
    }
//    public String getDeviceName() {
//        String manufacturer = Build.MANUFACTURER;
//        String model = Build.MODEL;
//
//        /*
//        String ip="" ;
//        try {
//        ip= InetAddress.getLocalHost().getHostAddress().toString() ;
//        } catch  (Exception e){
//            e.printStackTrace();
//            Log.d("# Err:",e.getMessage());
//        }
//*/
//        if (model.toLowerCase().startsWith(manufacturer.toLowerCase())) {
//            return capitalize(model);
//        } else {
//
//            return capitalize(manufacturer) + " " + model ;
//        }
//    }


    private String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }


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
            //system.out.println("Hostname: " + hostname);
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


