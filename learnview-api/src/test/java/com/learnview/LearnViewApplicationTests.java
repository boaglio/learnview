package com.learnview;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.learnview.load.FileUtil;

class LearnViewApplicationTests {

    @Test
    public void testReadTmpFiLe() throws IOException {

        String tmpdir = System.getProperty("java.io.tmpdir");
        String filename = tmpdir + File.separator + "test-file.txt";

        List<String> lines = Arrays.asList("1st line", "2nd line");
        Files.write(Paths.get(filename), lines, StandardCharsets.UTF_8, StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);

        String fileTest = FileUtil.convertFile2String(filename);

        assertThat(fileTest).isNotEmpty();
        assertThat(fileTest).contains("1st line");
    }

}
