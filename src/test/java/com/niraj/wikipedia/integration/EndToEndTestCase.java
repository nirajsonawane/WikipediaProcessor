package com.niraj.wikipedia.integration;

import com.niraj.wikipedia.steps.AcceptFileName;
import com.niraj.wikipedia.Main;
import com.niraj.wikipedia.util.Factory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EndToEndTestCase {

    protected final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    protected final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    protected final PrintStream originalOut = System.out;
    protected final PrintStream originalErr = System.err;

    private AcceptFileName acceptFileName;


    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void shouldReadInputWikiFileAndPrintResult(){

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("testInputFile.txt").getFile());
        String filePath = file.getAbsolutePath();
        Factory factory = new Factory();
        Main.StartProcessing(filePath,factory.getWikiFileParser(),factory.getWikiFileReader(),factory.getWikiQuestionAnswerMatcher(),factory.getPrintResult());
        String s = outContent.toString();
        assertTrue(s.contains("WHICH  SUBGENUS DO THE PLAINS ZEBRA AND THE MOUNTAIN ZEBRA BELONG TO ?"));
        assertTrue(s.contains("SUBGENUS HIPPOTIGRIS"));

        assertTrue(s.contains("WHAT IS  THE AIM OF THE QUAGGA PROJECT ?"));
        assertTrue(s.contains("AIMS TO BREED ZEBRA THAT ARE PHENOTYPICALLY SIMILAR TO THE QUAGGA"));

        assertTrue(s.contains("WHICH ARE  THE THREE SPECIES OF ZEBRA ?"));
        assertTrue(s.contains("THE PLAINS ZEBRA, THE GRÉVY'S ZEBRA AND THE MOUNTAIN ZEBRA"));

        assertTrue(s.contains("WHICH  ANIMALS ARE SOME OF THEIR CLOSEST RELATIVES"));
        assertTrue(s.contains("HORSES AND DONKEYS"));

        assertTrue(s.contains("WHICH  ZEBRA ARE ENDANGERED"));
        assertTrue(s.contains("GRÉVY'S ZEBRA AND THE MOUNTAIN ZEBRA"));

    }
}
