
import Service.MusicSearchService;
import listeners.LyricSearchListener;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.jmusixmatch.MusixMatchException;


import java.util.logging.Logger;


public class LyricBotApplication {

    public static Logger log = Logger.getLogger(LyricBotApplication.class.getName());

    public static  void main(String[] args) throws MusixMatchException {


        DiscordApi api = new DiscordApiBuilder().setToken("Nzc0NzM2NzE5ODkwMzUwMDgw.X6cHyw._aZttAL58KQUhiWRraZegwTLgUA").login().join();

        api.addMessageCreateListener(event -> {
            if (event.getMessageContent().matches("!ping")) {
                event.getChannel().sendMessage("Pong!");
            }
        });

        api.addListener(new LyricSearchListener());

        log.info("You can invite the bot by using the following url: " + api.createBotInvite());
    }

}
