import java.time.LocalDateTime;

public class Lejekontrakt {
    private int lejerId;
    private int kørekortnummer;
    private LocalDateTime fraDate;
    private LocalDateTime tilDate;
    private int maxKm;
    private String rigistreingsnummer;

    public Lejekontrakt(int lejerId, int kørekortnummer, LocalDateTime fraDate, LocalDateTime tilDate, int maxKm, String rigistreingsnummer) {
        this.lejerId = lejerId;
        this.kørekortnummer = kørekortnummer;
        this.fraDate = fraDate;
        this.tilDate = tilDate;
        this.maxKm = maxKm;
        this.rigistreingsnummer = rigistreingsnummer;
    }

    // Getter and Setter for lejerId
    public int getLejerId() {
        return lejerId;
    }

    public void setLejerId(int lejerId) {
        this.lejerId = lejerId;
    }

    // Getter and Setter for kørekortnummer
    public int getKørekortnummer() {
        return kørekortnummer;
    }

    public void setKørekortnummer(int kørekortnummer) {
        this.kørekortnummer = kørekortnummer;
    }

    // Getter and Setter for fraDate
    public LocalDateTime getFraDate() {
        return fraDate;
    }

    public void setFraDate(LocalDateTime fraDate) {
        this.fraDate = fraDate;
    }

    // Getter and Setter for tilDate
    public LocalDateTime getTilDate() {
        return tilDate;
    }

    public void setTilDate(LocalDateTime tilDate) {
        this.tilDate = tilDate;
    }

    // Getter and Setter for maxKm
    public int getMaxKm() {
        return maxKm;
    }

    public void setMaxKm(int maxKm) {
        this.maxKm = maxKm;
    }

    // Getter and Setter for rigistreingsnummer
    public String getRigistreingsnummer() {
        return rigistreingsnummer;
    }

    public void setRigistreingsnummer(String rigistreingsnummer) {
        this.rigistreingsnummer = rigistreingsnummer;
    }

    @Override
    public String toString() {
        return "Lejekontrakt{" +
                "lejerId=" + lejerId +
                ", kørekortnummer=" + kørekortnummer +
                ", fraDate=" + fraDate +
                ", tilDate=" + tilDate +
                ", maxKm=" + maxKm +
                ", rigistreingsnummer='" + rigistreingsnummer + '\'' +
                '}';
    }
}