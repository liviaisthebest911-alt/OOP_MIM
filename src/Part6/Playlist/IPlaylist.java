package Part6.Playlist;

interface IPlaylist {
    boolean addSong(ISong song);
    boolean removeSong(String songId);
    ISong getSong(String songId);
    java.util.List<ISong> getAllSongs();
    int getTotalSongs();
    int getTotalDuration();
    void removeByGenre(String genre);
    void removeSongsLongerThan(int maxDuration);
    java.util.List<ISong> findSongsByArtist(String artist);
    void printAllSongs();
    void printSongsWithIterator();
}