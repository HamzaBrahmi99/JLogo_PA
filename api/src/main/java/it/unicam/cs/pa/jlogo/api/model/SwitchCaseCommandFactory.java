package it.unicam.cs.pa.jlogo.api.model;

import it.unicam.cs.pa.jlogo.api.model.commands.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * This class has the responsibility to create commands.
 *
 * Nota per il prof: questa classe avrebbe dovuto utilizzare la Reflection per creare dinamicamente il comando corretto, però per una serie di
 * problemi personali non ho avuto tempo per implementarla ed ho optato per una implementazione meno astratta, e quindi meno estendibile,
 * attraverso un semplice switch.
 * Ho lasciato in fondo l'implementazione incompleta commentata, la quale funziona per tutti i comandi tranne che per il RIPETI.
 * @param <L> type of Location.
 */
public class SwitchCaseCommandFactory<L extends Location<L>> implements CommandFactory {
    private final Canvas<L> canvas;
    private final Cursor<L> cursor;

    /**
     * Creates a factory of methods using a switch case.
     * @param canvas to use,
     * @param cursor to use.
     */
    public SwitchCaseCommandFactory(Canvas<L> canvas, Cursor<L> cursor) {
        this.canvas = canvas;
        this.cursor = cursor;
    }

    /**
     * crea un Command in base al nome e usa i parametri passati per istanziarlo.
     * @param commandName name of the command.
     * @param parameters list of the parameters.
     * @return the correct Command.
     */
    public Command createCommand(String commandName, List<String> parameters){
        return switch (commandName) {
            case "FORWARD" -> new FORWARD<>(cursor, Integer.parseInt(parameters.get(0)), canvas);
            case "BACKWARD" -> new BACKWARD<>(cursor, Integer.parseInt(parameters.get(0)), canvas);
            case "CLEARSCREEN" -> new CLEARSCREEN<>(canvas);
            case "HOME" -> new HOME<>(cursor, canvas);
            case "LEFT" -> new LEFT<>(cursor, Integer.parseInt(parameters.get(0)));
            case "RIGHT" -> new RIGHT<>(cursor, Integer.parseInt(parameters.get(0)));
            case "PENDOWN" -> new PENDOWN<>(cursor);
            case "PENUP" -> new PENUP<>(cursor);
            case "SETFILLCOLOR" -> new SETFILLCOLOR<>(cursor, new Color(Integer.parseInt(parameters.get(0)), Integer.parseInt(parameters.get(1)), Integer.parseInt(parameters.get(2))));
            case "SETPENCOLOR" -> new SETPENCOLOR<>(cursor, new Color(Integer.parseInt(parameters.get(0)), Integer.parseInt(parameters.get(1)), Integer.parseInt(parameters.get(2))));
            case "SETPENSIZE" -> new SETPENSIZE<>(cursor, Integer.parseInt(parameters.get(0)));
            case "SETSCREENCOLOR" -> new SETSCREENCOLOR<>(canvas, new Color(Integer.parseInt(parameters.get(0)), Integer.parseInt(parameters.get(1)), Integer.parseInt(parameters.get(2))));
            case "RIPETI" -> new RIPETI<>(Integer.parseInt(parameters.remove(0)),stringsToCommands(parameters));
            default -> throw new RuntimeException("That command does not exist...");
        };
    }

    /**
     * This method transform a list of string into a list of Commands.
     * @param stringsFromFile list of strings to parse into Commands,
     * @return list of commands.
     */
    @Override
    public List<Command> stringsToCommands(List<String> stringsFromFile){
        List<Command> commands = new LinkedList<>();
        List<String> params = new LinkedList<>();
        for (Iterator<String> iterator = stringsFromFile.iterator(); iterator.hasNext(); ) {
            String startingString = iterator.next();
            CommandsAvailable commandName = CommandsAvailable.valueOf(startingString);
            if(commandName.toString().equals("RIPETI") && iterator.hasNext()) {
                readParametersForRIPETICommand(params, iterator);
            } else{
                for (int j = 0; j < commandName.getNumberOfParameters(); j++) {
                    if (iterator.hasNext())
                        params.add(iterator.next());
                }
            }
            commands.add(createCommand(commandName.toString(), params));
            params.clear();
        }
        return commands;
    }

    /**
     * This utility method is used to read commands to pass to the RIPETI command as parameters.
     * @param params list of parameters for the RIPETI command.
     * @param iterator over the strings read from the file.
     */
    private void readParametersForRIPETICommand(List<String> params, Iterator<String> iterator) {
        params.add(iterator.next());
        iterator.next();
        List<String> commandsToRepeat = new LinkedList<>();
        String f = iterator.next();
        while(!f.equals("]")&& iterator.hasNext()) {
            commandsToRepeat.add(f);
            f = iterator.next();
        }
        params.addAll(commandsToRepeat);
    }
///**
// * Nota per il prof: Ho lasciato questa parte riguardante la reflection commentata giusto per farle dare un'occhiata
//   all'implementazione che avevo in mente.
// */
//    public Command createCommand(String commandName, List<String> parameters) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
//        Class<?> commandClass = createCommands(commandName);
//        for (Constructor<?> ctor : commandClass.getConstructors()) {
//            Class<?>[] paramTypes = ctor.getParameterTypes();//3
//            Object[] convertedArgs = new Object[paramTypes.length];//1
//            int realArgs = parameters.length;
//            for (int i = 0; i < paramTypes.length; i++) {
//                if (paramTypes[i].isAssignableFrom(Canvas.class)) {
//                    convertedArgs[i] = this.canvas;
//                } else if (paramTypes[i].isAssignableFrom(Cursor.class)) {
//                    convertedArgs[i] = this.cursor;
//                } else {
//                    convertedArgs[i] = parseArgsType(paramTypes[i], parameters[i - realArgs]);
//                }
//            }
//            return (Command) ctor.newInstance(convertedArgs);
//        }
//    }
//    private Class<?> createCommands(String commandName) throws ClassNotFoundException {
//        return getCommandClass(DefaultCommandParser.class.getClassLoader(),"it.unicam.cs.pa.jlogo.api.model.commands."+commandName);
//    }
//    private Class<?> getCommandClass(ClassLoader classLoader, String className) throws ClassNotFoundException {
//        return classLoader.loadClass(className);
//    }
//
//    /**
//     * This method parses the given string to an Object of the given Class.
//     * @param target class of the expected parameter.
//     * @param s the parameter to check.
//     * @return an Object of the correct class for the expected parameter.
//     */
//    private Object parseArgsType(Class<?> target, String s) {
//        if (target == Object.class || target == String.class || sFixed == null) {
//            return sFixed;
//        }
//        if (target == Character.class || target == char.class) {
//            return sFixed.charAt(0);
//        }
//        if (target == Byte.class || target == byte.class) {
//            return Byte.parseByte(sFixed);
//        }
//        if (target == Short.class || target == short.class) {
//            return Short.parseShort(sFixed);
//        }
//        if (target == Integer.class || target == int.class) {
//            return Integer.parseInt(sFixed);
//        }
//        if (target == Long.class || target == long.class) {
//            return Long.parseLong(sFixed);
//        }
//        if (target == Float.class || target == float.class) {
//            return Float.parseFloat(sFixed);
//        }
//        if (target == Double.class || target == double.class) {
//            return Double.parseDouble(sFixed);
//        }
//        if (target == Boolean.class || target == boolean.class) {
//            return Boolean.parseBoolean(sFixed);
//        }
//        throw new IllegalArgumentException("Don't know how to convert to " + target);
//    }
}
