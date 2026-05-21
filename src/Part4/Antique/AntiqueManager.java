package Part4.Antique;

import java.util.*;

class AntiqueManager {
    private List<Antique> antiques;

    public AntiqueManager() {
        this.antiques = new ArrayList<>();
    }

    public void addAntique(Antique antique) {
        antiques.add(antique);
    }

    public List<Antique> getAllAntiques() {
        return antiques;
    }

    /**
     * Tìm món đồ theo mã.
     * Đầu vào: itemId
     * Đầu ra:
     * - trả về Antique nếu tìm thấy
     * - trả về null nếu không tìm thấy
     */
    public Antique findAntiqueById(String itemId) {
        for (Antique at : antiques){
            if(at.getItemId().equalsIgnoreCase(itemId)) return at;
        }
        return null;
    }

    /**
     * Tìm các món đồ có tên chứa từ khóa.
     * Đầu vào: keyword
     * Đầu ra: danh sách món đồ phù hợp, nếu không có thì trả về danh sách rỗng
     */
    public List<Antique> searchAntiquesByName(String keyword) {
        if (keyword == null) {
            return List.of();
        }

        String lowerKeyword = keyword.toLowerCase();

        return antiques.stream()
                .filter(m -> m.getItemName().toLowerCase().contains(lowerKeyword))
                .toList();
    }


    /**
     * Lấy danh sách món đồ đủ điều kiện trưng bày.
     * Đầu ra: danh sách món đồ thỏa điều kiện, nếu không có thì trả về danh sách rỗng
     */
    public List<Antique> getAntiquesCanDisplay() {
        return antiques.stream()
                .filter(Antique::canDisplayInExhibition)
                .toList();

    }

    /**
     * Lấy top n món đồ có độ hiếm cao nhất.
     * Đầu vào: n
     * Đầu ra: danh sách tối đa n món đồ
     */
    public List<Antique> getTopAntiques(int n) {
        java.util.List<Antique> sorted = sortByRarityScore(false);
        return sorted.subList(0, Math.min(n, sorted.size()));

    }

    /**
     * Sắp xếp món đồ theo độ hiếm.
     * Đầu vào:
     * - ascending = true: tăng dần
     * - ascending = false: giảm dần
     * Đầu ra: danh sách mới đã sắp xếp
     */
    public List<Antique> sortByRarityScore(boolean ascending) {
        List<Antique> sorted = new ArrayList<>(antiques);

        sorted.sort((a, b) -> ascending
                ? Double.compare(a.getRarityScore(), b.getRarityScore())
                : Double.compare(b.getRarityScore(), a.getRarityScore()));

        return sorted;
    }


    /**
     * Sắp xếp món đồ theo tên A-Z.
     * Đầu ra: danh sách mới đã sắp xếp
     */
    public List<Antique> sortByName() {
        List<Antique> sorted = new ArrayList<>(antiques);
        sorted.sort((a,b) -> a.getItemName().compareTo(b.getItemName()));
        return sorted;
    }

    /**
     * Tính độ hiếm trung bình.
     * Đầu ra:
     * - trả về độ hiếm trung bình
     * - nếu danh sách rỗng thì trả về 0
     */
    public double calculateAverageRarityScore() {
        double sum =0;
        for (Antique at : antiques){
            sum += at.getRarityScore();
        }
        return sum/antiques.size();
    }

    /**
     * Tính tổng giá trị của tất cả món đồ.
     * Đầu ra: tổng giá trị
     */
    public double calculateTotalValue() {
        double total = 0;
        for (Antique aat : antiques){
            total += aat.calculateTotalValue();
        }
        return total;
    }
}