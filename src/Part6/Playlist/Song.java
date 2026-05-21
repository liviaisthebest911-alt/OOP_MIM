package Part6.Playlist;

/**
 * Class Song - Đại diện cho một bài hát
 *
 * Mô tả:
 * - Class này đại diện cho một bài hát trong playlist
 * - Cần implements interface ISong
 *
 * Thuộc tính:
 * - songId: String - Mã bài hát (unique)
 * - title: String - Tên bài hát
 * - artist: String - Nghệ sĩ
 * - duration: int - Thời lượng (giây)
 * - genre: String - Thể loại nhạc
 *
 * Yêu cầu:
 * 1. Tạo constructor với đầy đủ 5 tham số
 * 2. Implement tất cả methods từ interface ISong
 * 3. Override toString() để in thông tin bài hát theo format:
 *    "Song[id='<id>', title='<title>', artist='<artist>', duration=<dur>s, genre='<genre>']"
 */
class Song implements ISong {
    private String songId;
    private String title;
    private String artist;
    private int duration;
    private String genre;

    public Song(String songId, String title, String artist, int duration, String genre) {
        this.songId = songId;
        this.title=title;
        this.artist=artist;
        this.duration=duration;
        this.genre=genre;
    }

    @Override
    public String getSongId() {
        return songId;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getArtist() {
        return artist;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public String getGenre() {
        return genre;
    }

    @Override
    public String toString(){
        return "Song[id='"+songId+"', title='"+title+"', artist='"+artist+"', duration="+duration+"s, genre='"+genre+"']";
    }

}