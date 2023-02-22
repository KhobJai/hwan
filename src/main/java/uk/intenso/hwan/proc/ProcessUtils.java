package uk.intenso.hwan.proc;

import uk.intenso.hwan.io.ReadUtils;

import java.io.IOException;

import static java.util.stream.Collectors.joining;

public class ProcessUtils {


    private ProcessUtils() {
    }

    /**
     * Simple native command executiion - returns if success (0) or not.
     *
     * @param cmd the command to execute
     * @return true if return value 0, false if not 0;
     */
    public static String exec(String cmd) {
        return exec(cmd.split(" "));
    }

    public static String exec(String... cmd) {
        System.out.println(String.join(",", cmd));
        var processBuilder = new ProcessBuilder().command(cmd);

        Process proc = null;
        try {
            proc = processBuilder.inheritIO().start();
            var is = proc.getInputStream();
            var waitMillis = 0;
            while (proc.isAlive()) {
                Thread.sleep(10);
                waitMillis += 10;
                if (waitMillis > 1000) {
                    break;
                }
            }
            if (proc.exitValue() != 0) {
                throw new RuntimeException(String.format("Process Failed with value %d: + %s", proc.exitValue(), ReadUtils.streamToString(is)));
            } else {
                return ReadUtils.streamToString(is);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (proc != null) {
                proc.destroyForcibly();
            }
        }
    }
}
