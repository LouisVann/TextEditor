package command;

import exception.TextException;
import receiver.Text;

public abstract class AbstractModifyCommand extends Command implements Cloneable {
    public AbstractModifyCommand(String cmdStr, String paramStr, Text text) {
        super(cmdStr, paramStr, text);
    }

    public abstract void undo() throws TextException;

    public abstract void redo() throws TextException;

    @Override
    public AbstractModifyCommand clone() throws CloneNotSupportedException {
        return (AbstractModifyCommand)super.clone();
    }
}
