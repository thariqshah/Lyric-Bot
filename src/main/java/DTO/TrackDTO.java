package DTO;

public class TrackDTO {

    public String trackName;
    public String albumName;
    public String artistName;

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    @Override
    public String toString() {
        return "Track Name : "+this.trackName+"\n"+
                "Album Name : "+this.albumName+"\n"+
                "Artist Name : "+this.artistName+"\n";
    }
}
