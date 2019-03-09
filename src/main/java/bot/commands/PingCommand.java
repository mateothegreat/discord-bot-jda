package bot.commands;

import bot.common.Command;
import bot.common.DynamicCommand;
import bot.common.DynamicCommandBase;
import bot.common.DynamicCommandInterface;

//
// Annotate this class so it can be picked up dynamically.
//
@DynamicCommand
public class PingCommand extends DynamicCommandBase<PingCommand> implements DynamicCommandInterface {

    public PingCommand() {

        this.getCommands().add("ping");

    }

    public void run(Command command) {

        command.getEvent().getChannel().sendMessage("pong!").queue();

    }

}
