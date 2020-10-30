import command.AbstractModifyCommand;
import command.Command;
import exception.AbstractCommandException;
import exception.TextException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Invoker
 * Ask the command to carry out the request
 */
public class Invoker {
    private List<AbstractModifyCommand> commandHistory;
    private int currIdx;
    private HashMap<String, ArrayList<AbstractModifyCommand>> macros;

    public Invoker() {
        commandHistory = new ArrayList<>();
        currIdx = -1;
        macros = new HashMap<>();
    }

    public String call(Command command) throws AbstractCommandException {
        String ret = command.execute();
        if (command instanceof AbstractModifyCommand) {
            if (currIdx + 1 < commandHistory.size()) { // clear the rest part
                commandHistory.subList(currIdx + 1, commandHistory.size()).clear();
            }
            commandHistory.add((AbstractModifyCommand)command);
            currIdx++;
        }
        return ret;
    }

    public void undo() throws TextException {
        if (currIdx < 0)
            return;
        commandHistory.get(currIdx).undo();
        currIdx--;
    }

    public void redo() throws TextException {
        if (currIdx + 1 >= commandHistory.size())
            return;
        currIdx++;
        commandHistory.get(currIdx).redo();
    }

    public String getRecentModifyCommandsInString(int k) {
        int min = Math.min(k, currIdx + 1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < min; i++) {
            sb.append(i + 1).append(" ").append(commandHistory.get(currIdx - i).toString());
            if (i != min - 1)
                sb.append("\n");
        }
        return sb.toString();
    }

    public void setNewMacro(String macroName, int k) throws CloneNotSupportedException {
        ArrayList<AbstractModifyCommand> recentCommands = new ArrayList<>();
        for (int i = Math.max(0, currIdx - k + 1); i <= currIdx; i++) {
            recentCommands.add(commandHistory.get(i).clone());
        }
        macros.put(macroName, recentCommands);
    }

    public ArrayList<AbstractModifyCommand> getMacro(String macroName) throws CloneNotSupportedException {
        ArrayList<AbstractModifyCommand> cloned = new ArrayList<>();
        for (AbstractModifyCommand command : macros.get(macroName)) {
            cloned.add(command.clone());
        }
        return cloned;
    }
}
