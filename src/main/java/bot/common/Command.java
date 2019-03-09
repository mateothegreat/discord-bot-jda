package bot.common;

import lombok.Data;
import lombok.ToString;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@ToString
public class Command {

    public static final Pattern PATTERN = Pattern.compile("!(.*?)(\\s+(.*)|$)");

    private String command;
    private String payload;

    private MessageReceivedEvent event;

    public Command(MessageReceivedEvent event) {

        Matcher matcher = PATTERN.matcher(event.getMessage().getContentRaw());

        if (matcher.find()) {

            command = matcher.group(1);
            payload = matcher.group(2);

        }

        this.event = event;

    }

}

