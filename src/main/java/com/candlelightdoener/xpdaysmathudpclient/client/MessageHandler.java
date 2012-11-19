package com.candlelightdoener.xpdaysmathudpclient.client;

import java.util.LinkedList;
import java.util.List;

/**
* Changes when textual format of message changes.
*/
public class MessageHandler {

    private final String DELIMITER = ":";
    private final String NULL_CHARACTER = "\u0000";
    private final String NOTHING = "";

    private Function function;
    private String uuid;
    private List<Integer> parameters = new LinkedList<Integer>();

    public MessageHandler(byte[] incomingMessage) {
        parse(incomingMessage);
    }

    private void parse(byte[] message) {
        String[] exploded = explodeMessage(message);

        function = Function.valueOf(exploded[0]);

        if(exploded.length > 1)
            uuid = exploded[1];

        for (int i = 2; i < exploded.length; i++) {
            parameters.add(Integer.parseInt(exploded[i]));
        }
    }

    private String[] explodeMessage(byte[] message) {
        String trimmed = new String(message).replace(NULL_CHARACTER, NOTHING);
        return trimmed.split(DELIMITER);
    }

    public boolean isShutdownMessage() {
        return function.equals(Function.__SHUTDOWN__);
    }

    public byte[] createResponseMessage() {
        int calculationResult = function.calculate(parameters);

        String message = uuid + DELIMITER + calculationResult;
        return message.getBytes();
    }
}