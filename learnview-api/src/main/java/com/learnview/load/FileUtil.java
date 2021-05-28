package com.learnview.load;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import lombok.extern.java.Log;

@Log
public class FileUtil {

    public static String convertFile2String(String filepath) {

        String returnStr = "";
        try {
            Path path = Paths.get(filepath);
            returnStr = Files.readAllLines(path).stream().collect(Collectors.joining("\n"));
        } catch (Exception e) {
            log.info("Error reading file [" + filepath + "]: " + e.getMessage());
        }
        return returnStr;
    }
}
