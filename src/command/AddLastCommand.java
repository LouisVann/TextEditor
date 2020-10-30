package command;

import exception.AbstractCommandException;
import exception.AddLastCommandException;
import exception.TextException;
import receiver.Text;

import static exception.ExceptionUtils.WRONG_FORMAT_PARAMS;

public class AddLastCommand extends AbstractModifyCommand {
    String tail;

    public AddLastCommand(String cmdStr, String paramStr, Text text) {
        super(cmdStr, paramStr, text);
    }

    @Override
    public String execute() throws AbstractCommandException {
        int start = paramStr.indexOf("\"");
        int end = paramStr.lastIndexOf("\"");
        if (start < 0 || start == end)
            throw new AddLastCommandException(WRONG_FORMAT_PARAMS);
        tail = paramStr.substring(start + 1, end);
        text.addLast(tail);
        return text.toString();
    }

    @Override
    public void undo() throws TextException {
        text.removeLast(tail);
    }

    @Override
    public void redo() {
        text.addLast(tail);
    }
}
