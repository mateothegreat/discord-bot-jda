package bot;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

import javax.security.auth.login.LoginException;

public class Bot {

    //
    // Main bootstrap method.
    //
    public static void main(String[] args) throws LoginException {

        //
        // Retrieves the discord token from the environment variable "TOKEN".
        //
        JDA jda = new JDABuilder(System.getenv("TOKEN")).build();

        //
        // Binds the BotListener Class
        //
        jda.addEventListener(new BotListener());

    }

}
