package Service;

import DTO.TrackDTO;
import org.javacord.api.event.message.MessageCreateEvent;
import org.jmusixmatch.MusixMatch;
import org.jmusixmatch.MusixMatchException;
import org.jmusixmatch.entity.lyrics.Lyrics;
import org.jmusixmatch.entity.track.Track;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class MusicSearchService {

    public static Logger log = Logger.getLogger(MusicSearchService.class.getName());


    static String apiKey = "8649d9e1007a735e550a0fd1cf31ccef";
    static MusixMatch musixMatch = new MusixMatch(apiKey);

    public void searchByArtist(String artist, MessageCreateEvent event)  {
        try {
            List<Track> tracks = musixMatch.searchTracks("", artist, "", 0, 5, true);
            List<Track> sortedTrack =tracks.stream().sorted(Comparator.comparing(track -> track.getTrack().getTrackRating())).collect(Collectors.toList());
            for (Track trk : sortedTrack) {
                this.sendArtistResult(trk,event);
            }
        }catch (MusixMatchException x){
            log.info(x.getMessage());
        }

    }

    public void sendArtistResult(Track object, MessageCreateEvent event){
        TrackDTO trackDTO = new TrackDTO();
        trackDTO.setTrackName(object.getTrack().getTrackName());
        trackDTO.setAlbumName(object.getTrack().getAlbumName());
        trackDTO.setArtistName(object.getTrack().getArtistName());
        event.getChannel().sendMessage(trackDTO.toString());
    }

    public void getLyricByArtistAndTrack(String query,MessageCreateEvent event){
        try {
            int id=0;
            String[] artistTrackArray =query.split(",");
            String artist = artistTrackArray[0];
            String trackName = artistTrackArray[1];
            Track track = musixMatch.getMatchingTrack(trackName, artist);
            id = track.getTrack().getTrackId();
            if(id!=0){
                Lyrics lyrics = musixMatch.getLyrics(id);
                event.getChannel().sendMessage(lyrics.getLyricsBody());
            }else
                event.getChannel().sendMessage("Sorry, No Lyric was Found");

        } catch (MusixMatchException e) {
            e.printStackTrace();
        }

    }
}
