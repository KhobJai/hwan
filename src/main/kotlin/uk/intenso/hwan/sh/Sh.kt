package uk.intenso.hwan.sh

@Deprecated("use IsSh")
class Sh() {

    private val shellPath = arrayOf("sh", "-c")

    private var command:String = ""

    private var isSudo: Boolean = false;
    private var password: String? = null

    private var shellCmd:ShellCmd? = null
    var builder: ProcessBuilder? = null


    fun isSudo(): Boolean {
        return isSudo && password != null;
        command = " echo $password sudo| -S " + command
    }

    fun pipe(prefix:String,suffix:String):Sh {
        command += " $prefix| $suffix "
        return this;
    }



    fun shellCommand(cmd: ShellCmd):Sh {
        this.shellCmd=cmd;
        return this;
    }

    fun sudo(password:String):Sh {
        this.password = password;
        isSudo=true;
        return this;
    }


}

class ShResponse(val exitCode:Boolean,val text:String)


class NotOnPathExcecption(cmd: String) : RuntimeException("Command Not Found: $cmd") {

}


enum class ShellCmd(val cmd: String) {
    LS("ls");

}