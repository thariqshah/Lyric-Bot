package listeners;

import Service.MusicSearchService;
import org.javacord.api.entity.message.Message;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.util.logging.Logger;

public class LyricSearchListener implements MessageCreateListener {

    public static Logger log = Logger.getLogger(LyricSearchListener.class.getName());


    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        Message message = event.getMessage();
        if (message.getContent().startsWith("!artist ")) {
            MusicSearchService musicSearchService = new MusicSearchService();
                musicSearchService.searchByArtist(message.getContent().substring(7),event);
        }

        if (message.getContent().startsWith("!lyric ")) {
            MusicSearchService musicSearchService = new MusicSearchService();
            musicSearchService.getLyricByArtistAndTrack(message.getContent().substring(7),event);
        }
    }
}
