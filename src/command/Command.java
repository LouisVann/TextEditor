package command;

import exception.AbstractCommandException;
import receiver.Text;

public abstract class Command {
    String cmdStr;
    String paramStr;
    Text text;

    public Command(String cmdStr, String paramStr, Text text) {
        this.cmdStr = cmdStr;
        this.paramStr = paramStr;
        this.text = text;
    }

    public abstract String execute() throws AbstractCommandException;

    @Override
    public String toString() {
        return cmdStr + ((paramStr != null && !paramStr.isEmpty()) ? " " + paramStr : "");
    }
}
