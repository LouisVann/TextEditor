package command;

import exception.AbstractCommandException;
import exception.RemoveFirstKCommandException;
import receiver.Text;

import static exception.ExceptionUtils.WRONG_VAL_PARAMS;

public class RemoveFirstKCommand extends AbstractModifyCommand {
    int k;
    String head;

    public RemoveFirstKCommand(String cmdStr, String paramStr, Text text) {
        super(cmdStr, paramStr, text);
    }

    @Override
    public String execute() throws AbstractCommandException {
        try {
            k = Integer.parseInt(paramStr);
        } catch (Exception e) {
            throw new RemoveFirstKCommandException(WRONG_VAL_PARAMS);
        }
        head = text.removeFirstK(k);
        return text.toString();
    }

    @Override
    public void undo() {
        text.addFirst(head);
    }

    @Override
    public void redo() {
        text.removeFirstK(k);
    }
}
