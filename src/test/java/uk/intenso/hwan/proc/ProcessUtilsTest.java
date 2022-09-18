package uk.intenso.hwan.proc;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class ProcessUtilsTest {

    String quote(String data) {
        return "\"" + data + "\"";
    }

    @Test
    void execTest() {
        String cmd = "sh -c " + Stream.of("ls","-la","/home/manjaro", "|", "grep", "Do").map(this::quote)
                .collect(Collectors.joining(" "));
        var result = ProcessUtils.exec(cmd);
    }


    @Test
    void arrayTest() {
        try {
            String line;
            String[] cmd = { "sh","-c", "ls -la /home/manjaro | grep Do" };
            Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader in =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

