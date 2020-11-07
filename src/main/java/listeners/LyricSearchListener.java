package listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.MusicSearchService;
import org.javacord.api.entity.message.Message;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;


public class LyricSearchListener implements MessageCreateListener {

    public static final Logger log = LoggerFactory.getLogger(LyricSearchListener.class);


    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        Message message = event.getMessage();
        if (message.getContent().startsWith("!artist ")) {
            log.info("Service to search music by artist , Message Author : {} ",message.getAuthor().getDisplayName());
            MusicSearchService musicSearchService = new MusicSearchService();
                musicSearchService.searchByArtist(message.getContent().substring(7),event);
        }

        if (message.getContent().startsWith("!lyric ")) {
            log.info("Service to search lyric by artist , Message Author : {}",message.getAuthor().getDisplayName());
            MusicSearchService musicSearchService = new MusicSearchService();
            musicSearchService.getLyricByArtistAndTrack(message.getContent().substring(7),event);
        }
    }
}
