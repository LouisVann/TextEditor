import command.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Class<?>> cmd2Class = getCommand2Class();
        Client client = new Client(cmd2Class);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            int splitIndex = line.indexOf(" ");
            String cmdStr, paramStr;
            if (splitIndex < 0) {
                cmdStr = line;
                paramStr = null;
            } else {
                cmdStr = line.substring(0, splitIndex);
                paramStr = line.substring(splitIndex + 1);
            }
            try {
                String ans = client.run(cmdStr, paramStr);
                if (ans != null) {
                    printContent(ans); // "" should also be printed
                }
            } catch (Exception e) {
                printException(e.toString());
            }
        }
    }

    public static Map<String, Class<?>> getCommand2Class() {
        // all the commands supported are added here
        Map<String, Class<?>> cmd2Class = new HashMap<>();
        cmd2Class.put("s", ShowCommand.class);
        cmd2Class.put("A", AddLastCommand.class);
        cmd2Class.put("a", AddFirstCommand.class);
        cmd2Class.put("D", RemoveLastKCommand.class);
        cmd2Class.put("d", RemoveFirstKCommand.class);
        return cmd2Class;
    }

    private static void printException(String err) {
        System.err.println(err);
    }

    private static void printContent(String s) {
        System.out.println(s);
    }
}
