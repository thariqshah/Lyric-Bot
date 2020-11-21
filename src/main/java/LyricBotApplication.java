
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

import java.util.logging.Logger;


public class LyricBotApplication {

    public static Logger log = Logger.getLogger(LyricBotApplication.class.getName());

    public static  void main(String[] args) {



        DiscordApi api = new DiscordApiBuilder().setToken("bot token here").login().join();
        //Add a listener which answers with "Pong!" if someone writes "!ping"
        api.addMessageCreateListener(event -> {
            if (event.getMessageContent().matches("!ping")) {
                event.getChannel().sendMessage("Pong!");
            }
        });
        log.info("You can invite the bot by using the following url: " + api.createBotInvite());
    }

}
