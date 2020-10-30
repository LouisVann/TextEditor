package command;

import exception.AbstractCommandException;
import exception.RemoveLastKCommandException;
import receiver.Text;

import static exception.ExceptionUtils.WRONG_VAL_PARAMS;

public class RemoveLastKCommand extends AbstractModifyCommand {
    int k;
    String tail;

    public RemoveLastKCommand(String cmdStr, String paramStr, Text text) {
        super(cmdStr, paramStr, text);
    }

    @Override
    public String execute() throws AbstractCommandException {
        try {
            k = Integer.parseInt(paramStr);
        } catch (Exception e) {
            throw new RemoveLastKCommandException(WRONG_VAL_PARAMS);
        }
        tail = text.removeLastK(k);
        return text.toString();
    }

    @Override
    public void undo() {
        text.addLast(tail);
    }

    @Override
    public void redo() {
        text.removeLastK(k);
    }
}
