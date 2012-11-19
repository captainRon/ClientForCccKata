package com.candlelightdoener.xpdaysmathudpclient.client;

import org.apache.log4j.Logger;

import java.net.*;

/**
 * Client-Implementation for Server-Client-UDP-Kata, instructions on https://github.com/lomin/ccc-kata
 *
 * Receives messages and instantly replies.
 *
 * Changes when package handling changes.
 */
public class Client {
    private final static Logger log = Logger.getLogger(Client.class);

    private final static int MAX_INCOMING_PACKET_SIZE = 100;
    private final static int RECEIVE_TIMEOUT_IN_MS = 10000;

    private final InetAddress SERVER_NAME;
    private final int SERVER_PORT;
    private final DatagramSocket socket;

    public Client(String serverName, int serverPort, int clientPort) {
        try {
            SERVER_NAME = InetAddress.getByName(serverName);
            SERVER_PORT = serverPort;

            socket = new DatagramSocket(clientPort);
            socket.setSoTimeout(RECEIVE_TIMEOUT_IN_MS);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Client", e);
        }
    }

    public void run() {
        try {
            loopUntilShutdown();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }

    private void loopUntilShutdown() throws Exception {
        while (true) {
            byte[] incoming = receive();
            MessageHandler handler = new MessageHandler(incoming);

            if (handler.isShutdownMessage())
                break;

            byte[] outgoing = handler.createResponseMessage();
            reply(outgoing);
        }
    }

    private byte[] receive() throws Exception {
        byte[] incomingMessagePlaceholder = new byte[MAX_INCOMING_PACKET_SIZE];
        DatagramPacket incomingPacket = createPacket(incomingMessagePlaceholder);

        socket.receive(incomingPacket);
        log.debug("Received: " + new String(incomingPacket.getData()));

        return incomingPacket.getData();
    }

    private void reply(byte[] outgoingMessage) throws Exception {
        DatagramPacket outgoingPacket = createPacket(outgoingMessage);

        socket.send(outgoingPacket);
        log.debug("Sent: " + new String(outgoingMessage));
    }

    private DatagramPacket createPacket(byte[] data) {
        return new DatagramPacket(data, data.length, SERVER_NAME, SERVER_PORT);
    }
}