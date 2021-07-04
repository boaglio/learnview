package com.learnview.util;

public class IdManager {

    public static String getNext(String exam, String lastId) {

        String id = lastId.substring(exam.length() + 1);

        int intId = Integer.valueOf(id);

        String formattedId = String.format("%03d", ++intId);

        return exam + "-" + formattedId;

    }

}
