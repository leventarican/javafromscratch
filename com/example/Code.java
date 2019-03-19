package com.example;

import com.example.utils.Util;

public class Code {
    public static void main(String [] args) {
        System.out.println("code.");
        Util.ping();
        System.out.println("starting UDPClient.");
        UDPClient.run();
    }
}