package Part6.Playlist;

/**
 * Class Playlist - Quản lý danh sách bài hát
 *
 * Mô tả:
 * - Class này quản lý playlist sử dụng ArrayList
 * - Cần implements interface IPlaylist
 * - SỬ DỤNG ITERATOR để duyệt và xử lý danh sách
 *
 * Thuộc tính:
 * - songs: List<ISong> - Danh sách bài hát (sử dụng ArrayList)
 *
 * Yêu cầu:
 * 1. Tạo constructor khởi tạo ArrayList rỗng
 * 2. Implement các methods:
 *    - addSong(): Thêm bài hát vào playlist
 *    - removeSong(): Xóa bài hát theo ID
 *    - getSong(): Lấy bài hát theo ID
 *    - getAllSongs(): Trả về List tất cả bài hát
 *    - getTotalSongs(): Trả về tổng số bài hát
 *    - getTotalDuration(): Trả về tổng thời lượng của playlist (giây)
 *    - removeByGenre(): Xóa tất cả bài hát của một thể loại (DÙNG ITERATOR)
 *    - removeSongsLongerThan(): Xóa bài hát có duration > maxDuration (DÙNG ITERATOR)
 *    - findSongsByArtist(): Tìm tất cả bài hát của một nghệ sĩ (DÙNG ITERATOR)
 *    - printAllSongs(): In tất cả bài hát bằng for-each loop
 *    - printSongsWithIterator(): In tất cả bài hát DÙNG ITERATOR với hasNext()
 * 3. Override toString() để in danh sách bài hát
 *
 * LƯU Ý QUAN TRỌNG:
 * - Các method removeByGenre() và removeSongsLongerThan() BẮT BUỘC dùng Iterator.remove()
 * - Method findSongsByArtist() và printSongsWithIterator() BẮT BUỘC dùng iterator()
 * - Phải sử dụng hasNext() và next() trong vòng lặp iterator
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Playlist implements IPlaylist {

    // CHÚ Ý: removeByGenre() và removeSongsLongerThan() PHẢI dùng Iterator.remove()
    // CHÚ Ý: findSongsByArtist() và printSongsWithIterator() PHẢI dùng iterator()

    private List<ISong> songs;

    public Playlist() {
        this.songs = new ArrayList<>();
    }

    @Override
    public boolean addSong(ISong song) {
        songs.add(song);
        return true;
    }

    @Override
    public boolean removeSong(String songId) {
        Iterator<ISong> it = songs.iterator();
        while (it.hasNext()) {
            ISong s = it.next();
            if (s.getSongId().equals(songId)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public ISong getSong(String songId) {
        for(ISong iSong : songs){
            if(iSong.getSongId().equals(songId)){
                return iSong;
            }
        }
        return null;
    }

    @Override
    public List<ISong> getAllSongs() {
        List<ISong> list = new ArrayList<>();
        for(ISong iSong : songs){
            list.add(iSong);
        }
        return list;
    }

    @Override
    public int getTotalSongs() {
        return songs.size();
    }

    @Override
    public int getTotalDuration() {
        int sum=0;
        for(ISong iSong : songs){
            sum += iSong.getDuration();
        }
        return sum;
    }
    @Override
    public void removeByGenre(String genre) {
        Iterator<ISong> it = songs.iterator();
        while (it.hasNext()) {
            ISong s = it.next();
            if (s.getGenre().equalsIgnoreCase(genre)) {
                it.remove();
            }
        }
    }

    @Override
    public void removeSongsLongerThan(int maxDuration) {
        Iterator<ISong> it = songs.iterator();
        while (it.hasNext()) {
            ISong s = it.next();
            if (s.getDuration() > maxDuration) {
                it.remove();
            }
        }
    }

    @Override
    public List<ISong> findSongsByArtist(String artist) {
        List<ISong> result = new ArrayList<>();
        Iterator<ISong> it = songs.iterator();

        while (it.hasNext()) {
            ISong s = it.next();
            if (s.getArtist().equalsIgnoreCase(artist)) {
                result.add(s);
            }
        }
        return result;
    }

    @Override
    public void printSongsWithIterator() {
        Iterator<ISong> it = songs.iterator();
        while (it.hasNext()) {
            System.out.println("  " + it.next()); //
        }
    }

    @Override
    public void printAllSongs() {
        for (ISong s : songs) {
            System.out.println("  " + s);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ISong s : songs) {
            sb.append(s).append("\n");
        }
        return sb.toString();
    }


}
