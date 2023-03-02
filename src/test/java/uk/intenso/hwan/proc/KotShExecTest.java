package uk.intenso.hwan.proc;

import org.junit.jupiter.api.*;
import uk.intenso.hwan.shell.ShExec;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class KotShExecTest {

    String quote(String data) {
        return "\"" + data + "\"";
    }

    @Test
    void execTest() {
        String cmd = "sh -c " + Stream.of("ls","-la","/home/$s", "|", "grep", "Do"
                        .formatted(System.getenv("USER"))).map(this::quote)
                .collect(Collectors.joining(" "));
        var result = ShExec.exec(cmd);
    }


    @Test
    void arrayTest() {
        try {
            String line;
            String[] cmd = { "sh","-c", "ls -la /home/ | grep Do".formatted(System.getenv("USER")) };
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

