package commands;

import lombok.Data;
import lombok.ToString;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@ToString
public class Command {

    public static final Pattern PATTERN = Pattern.compile("!(.*?)(\\s+(.*)|$)");

    private String command;
    private String payload;

    public Command(String message) {

        Matcher matcher = PATTERN.matcher(message);

        if (matcher.find()) {

            command = matcher.group(1);
            payload = matcher.group(2);

        }

    }

}
