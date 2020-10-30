package command;

import receiver.Text;

public abstract class AbstractShowCommand extends Command {
    public AbstractShowCommand(String cmdStr, String paramStr, Text text) {
        super(cmdStr, paramStr, text);
    }
}
