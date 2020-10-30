package command;

import exception.AbstractCommandException;
import exception.AddFirstCommandException;
import exception.TextException;
import receiver.Text;

import static exception.ExceptionUtils.WRONG_FORMAT_PARAMS;

public class AddFirstCommand extends AbstractModifyCommand {
    String head;

    public AddFirstCommand(String cmdStr, String paramStr, Text text) {
        super(cmdStr, paramStr, text);
    }

    @Override
    public String execute() throws AbstractCommandException {
        int start = paramStr.indexOf("\"");
        int end = paramStr.lastIndexOf("\"");
        if (start < 0 || start == end)
            throw new AddFirstCommandException(WRONG_FORMAT_PARAMS);
        head = paramStr.substring(start + 1, end);
        text.addFirst(head);
        return text.toString();
    }

    @Override
    public void undo() throws TextException {
        text.removeFirst(head);
    }

    @Override
    public void redo() {
        text.addFirst(head);
    }

}
