package bot;

import bot.common.Command;
import bot.common.DynamicCommandBase;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

public class BotListener extends ListenerAdapter {

    //
    // Searches the bot.commands package for classes.
    //
    private final Reflections reflections = new Reflections("bot");

    //
    // Called when a new message is received.
    //
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        //
        // Create a Command Object which parses out the command name and options.
        //
        Command command = new Command(event);

        System.out.printf("[COMMAND] %s", command.toString());

        //
        // Ensure the bot is not talking to itself.
        //
        if (!event.getAuthor().isBot()) {

            boolean found = false;

            //
            // Using Reflection we will search for all classes that inherit from the DynamicCommandBase Class.
            //
            Set<Class<? extends DynamicCommandBase>> subTypes = reflections.getSubTypesOf(DynamicCommandBase.class);

            //
            // Loop through all of the matching classes, instantiating and invoking the "run" method.
            //
            for (Class<? extends DynamicCommandBase> dynamicCommand : subTypes) {

                try {

                    //
                    // Instantiate the class matched with the @DynamicCommand Annotation dynamically.
                    //
                    DynamicCommandBase dynamicCommandBase = dynamicCommand.newInstance();

                    //
                    // Check to see if the command name exists in this classes commands list.
                    //
                    // This will loop through all classes trying to find a match in the event
                    // there are more than one handling method to be called.
                    //
                    if (dynamicCommandBase.getCommands().contains(command.getCommand())) {

                        Method method = dynamicCommandBase.getClass().getMethod("run", Command.class);

                        //
                        // Call the "run" method on this dynamically instantiated class.
                        //
                        method.invoke(dynamicCommandBase, command);

                        //
                        // Determine that we've found a matching method preventing the command not found
                        // message from being returned (below);
                        //
                        found = true;

                    }

                } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {

                    e.printStackTrace();

                    event.getChannel().sendMessage("The following error has occurred: " + e.getStackTrace()[0]).queue();

                }

            }

            //
            // No classes having the command name in it's commands list was found.
            //
            if (!found) {

                event.getChannel().sendMessage("The command \"" + command.getCommand() + "\" was not found :sob:").queue();

            }

        }

    }

}
