package uk.intenso.hwan.shell;

public interface ShellCmdExecutor {


    String query(String cmd);

    boolean exec(String cmd);
}
