package command;

import exception.AbstractCommandException;
import exception.ShowCommandException;
import receiver.Text;

import static exception.ExceptionUtils.UNEXPECTED_PARAMS;

public class ShowCommand extends AbstractShowCommand {
    public ShowCommand(String cmdStr, String paramStr, Text text) {
        super(cmdStr, paramStr, text);
    }

    @Override
    public String execute() throws AbstractCommandException {
        if (paramStr != null && !paramStr.equals("")) {
            throw new ShowCommandException(UNEXPECTED_PARAMS);
        }
        return text.toString();
    }

}
