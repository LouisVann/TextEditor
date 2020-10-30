import command.AbstractModifyCommand;
import command.MacroCommand;
import exception.AbstractCommandException;
import exception.ClientException;
import exception.TextException;
import receiver.Text;
import command.Command;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Client
 * Create a concrete command object and
 * Set its receiver (model)
 */
public class Client {
    private Invoker invoker;
    private Text text;
    private Map<String, Class<?>> cmd2Class;

    public Client(Map<String, Class<?>> cmd2Class) {
        invoker = new Invoker();
        text = new Text();
        this.cmd2Class = cmd2Class;
    }

    /**
     * run the editor according to the command
     * @param cmdStr command string typed in by the user
     * @param paramStr parameter string of this command, which can be null sometimes
     * @return string that should be output to the console
     */
    public String run(String cmdStr, String paramStr) throws AbstractCommandException, TextException, ClientException,
            NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, CloneNotSupportedException {
        if (cmdStr == null || cmdStr.equals(""))
            return null;
        switch (cmdStr) {
            case "u":
                invoker.undo();
                return text.toString();
            case "r":
                invoker.redo();
                return text.toString();
            case "l":
                int k = Integer.parseInt(paramStr);
                return invoker.getRecentModifyCommandsInString(k);
            case "m":
                addNewCommand(paramStr);
                break;
            default: // for supported commands in cmd2Class
                Command command = createCommand(cmdStr, paramStr);
                String ret = invoker.call(command); // return updated text if modifiable, else null
                if ((ret != null && !ret.equals("")) || command instanceof AbstractModifyCommand) {
                    return ret;
                }
        }
        return null;
    }

    private Command createCommand(String cmdStr, String paramStr) throws ClientException,
            NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, CloneNotSupportedException {
        if (!cmd2Class.containsKey(cmdStr)) {
            throw new ClientException("Command " + cmdStr + " does not exist.");
        }
        Class<?> cmdClass = cmd2Class.get(cmdStr);
        if (cmdClass == MacroCommand.class) {
            String macroName = cmdStr.substring(1);
            ArrayList<AbstractModifyCommand> commands = invoker.getMacro(macroName);
            return (Command)cmdClass.getDeclaredConstructor(String.class, List.class, Text.class).newInstance(macroName, commands, text);
        }
        return (Command)cmdClass.getDeclaredConstructor(String.class, String.class, Text.class).
                newInstance(cmdStr, paramStr, text); // receiver is set here

    }

    private void addNewCommand(String param) throws ClientException, CloneNotSupportedException {
        String[] params;
        if (param == null || (params = param.split(" ")).length < 2) {
            throw new ClientException("Wrong format params.");
        }
        int k = Integer.parseInt(params[0]);
        String macroName = params[1];
        String macroOrder = "$" + params[1];

        invoker.setNewMacro(macroName, k);

        if (cmd2Class.containsKey(macroOrder)) {
            throw new ClientException("Command name already exists.");
        }
        cmd2Class.put(macroOrder, MacroCommand.class);
    }

}
