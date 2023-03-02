package uk.intenso.hwan.shell;

public interface ShellCmdExecutor {


    /**
     *  Runs a native command and return the ootput value
     * @param cmd The native Comamnd
     * @return The value written to STDOUT
     */
    String query(String cmd);

    /**
     * Executes a native command and returns the exit code
     * @param cmd The command to execute
     * @return The exit code - if 0 the command was successful, otherwise an error occurred.
     */
    int exec(String cmd);
}
