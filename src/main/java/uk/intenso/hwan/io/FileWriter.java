package uk.intenso.hwan.io;


import uk.intenso.hwan.hwtry.statictry.TryUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

interface FileWriter {
    static void writeStringToFile(String data, String path) {
        TryUtils.orThrow(() -> Files.writeString(Path.of(path), data));
    }

    static void writeInputToStdOut(InputStream is) {
        writeToStream(is, System.out);
    }

    static void writeToStream(InputStream is, OutputStream os) {

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = in.readLine()) != null) {
                os.write(line.getBytes(StandardCharsets.UTF_8));
                os.write("%n".getBytes(StandardCharsets.UTF_8));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
