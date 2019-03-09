package bot.commands;

import bot.common.Command;
import bot.common.DynamicCommand;
import bot.common.DynamicCommandBase;
import bot.common.DynamicCommandInterface;

import java.time.LocalDateTime;

//
// Annotate this class so it can be picked up dynamically.
//
@DynamicCommand
public class TimeCommand extends DynamicCommandBase<TimeCommand> implements DynamicCommandInterface {

    public TimeCommand() {

        this.getCommands().add("time");

    }

    public void run(Command command) {

        command.getEvent().getChannel().sendMessage(LocalDateTime.now().toString()).queue();

    }

}
