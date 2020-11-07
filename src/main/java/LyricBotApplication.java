
import listeners.LyricSearchListener;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LyricBotApplication {

    private static Logger log = LoggerFactory.getLogger(LyricBotApplication.class);

    public static  void main(String[] args) {

        log.info("Initializing Bot");
        DiscordApi api = new DiscordApiBuilder().setToken("Nzc0NzM2NzE5ODkwMzUwMDgw.X6cHyw._aZttAL58KQUhiWRraZegwTLgUA").login().join();
        log.info("Bot Joined to server");

        api.addMessageCreateListener(event -> {
            if (event.getMessageContent().matches("!ping")) {
                log.info("Bot received a ping request from : {}",event.getMessage().getAuthor().getDisplayName());
                event.getChannel().sendMessage("Pong!");
            }
        });

        api.addListener(new LyricSearchListener());

        log.info("You can invite the bot by using the following url: {}" , api.createBotInvite());
    }

}
