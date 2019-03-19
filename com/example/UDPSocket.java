package com.example;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPSocket implements AutoCloseable {

    DatagramSocket socket;
    InetAddress address;
    int port;

    public UDPSocket() throws SocketException {
        this(new DatagramSocket());
    }

    public UDPSocket(int port) throws SocketException {
        this(new DatagramSocket());
    }

    public UDPSocket(DatagramSocket datagramSocket) {
        this.socket = datagramSocket;
    }

    public void send(String s, InetAddress rcvrAdress, int rcvrPort) throws IOException {
        byte[] outBuffer = s.getBytes();
        DatagramPacket outPacket = new DatagramPacket(outBuffer, outBuffer.length, rcvrAdress, rcvrPort);
        socket.send(outPacket);
    }

    public String receive(int maxBytes) throws IOException {
        byte[] inBuffer = new byte[maxBytes];
        DatagramPacket inPacket = new DatagramPacket(inBuffer, inBuffer.length);
        socket.receive(inPacket);
        address = inPacket.getAddress();
        port = inPacket.getPort();

        return new String();
    }

    public void reply(String s) throws IOException {
        if (address == null) {
            throw new IOException("no one to reply.");
        }
        send(s, address, port);
    }

    public InetAddress getSenderAddress() {
        return address;
    }

    public int getSenderPort() {
        return port;
    }

    public void setTimeout(int timeout) throws SocketException {
        socket.setSoTimeout(timeout);
    }

    @Override
    public void close() throws Exception {
        socket.close();
    }
}
