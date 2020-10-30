package command;

import exception.AbstractCommandException;
import exception.TextException;
import receiver.Text;

import java.util.List;

public class MacroCommand extends AbstractModifyCommand {
    List<AbstractModifyCommand> commands;

    public MacroCommand(String cmdStr, List<AbstractModifyCommand> commands, Text text) {
        super(cmdStr, null, text);
        this.commands = commands;
    }

    @Override
    public String execute() throws AbstractCommandException {
        for (int i = 0; i < commands.size(); i++) {
            commands.get(i).execute();
        }
        return text.toString();
    }

    @Override
    public void undo() throws TextException {
        for (int i = commands.size() - 1; i >= 0; i--) {
            commands.get(i).undo();
        }
    }

    @Override
    public void redo() throws TextException {
        for (int i = 0; i < commands.size(); i++) {
            commands.get(i).redo();
        }
    }
}
