import commands.Command;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

public class Bot extends ListenerAdapter {

    public static void main(String[] args) throws LoginException {

        JDA jda = new JDABuilder(System.getenv("TOKEN")).build();

        jda.addEventListener(new Bot());

    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        Command command = new Command(event.getMessage().getContentDisplay());

        System.out.printf("[COMMAND] %s", command.toString());

        if (!event.getAuthor().isBot()) {

            if (event.isFromType(ChannelType.PRIVATE)) {

                System.out.printf("[PM] %s: %s\n", event.getAuthor().getName(), event.getMessage().getContentDisplay());

            } else {

                System.out.printf("[%s][%s] %s: %s\n", event.getGuild().getName(), event.getTextChannel().getName(), event.getMember().getEffectiveName(), event.getMessage().getContentDisplay());

                if (command.getCommand().equals("ping")) {

                    event.getChannel().sendMessage("pong!").queue();

                } else {

                    event.getChannel().sendMessage("The command \"" + command.getCommand() + "\" was not found :sob:").queue();

                }

            }

        }

    }

}
