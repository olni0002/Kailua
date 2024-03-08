import java.time.LocalDate;
import java.time.LocalDateTime;


public class Lejekontrakt {
    private int lejerId;
    private int kørekortnummer;
    private LocalDate fraDate;
    private LocalDate  tilDate;
    private int maxKm;
    private String licensePlate;


    public Lejekontrakt(int lejerId, int kørekortnummer, LocalDateTime fraDate, LocalDateTime tilDate, int maxKm, String licensePlate) {
        this.lejerId = lejerId;
        this.kørekortnummer = kørekortnummer;
        this.fraDate = LocalDate.from(fraDate);
        this.tilDate = LocalDate.from(tilDate);
        this.maxKm = maxKm;
        this.licensePlate = licensePlate;
    }

    public Lejekontrakt(int kørekortnummer, LocalDate fraDate, LocalDate tilDate, int maxKm, String licensePlate) {
        this.kørekortnummer = kørekortnummer;
        this.fraDate = fraDate;
        this.tilDate = tilDate;
        this.maxKm = maxKm;
        this.licensePlate = licensePlate;
    }

    public int getLejerId() {
        return lejerId;
    }

    public void setLejerId(int lejerId) {
        this.lejerId = lejerId;
    }

    public int getKørekortnummer() {
        return kørekortnummer;
    }

    public void setKørekortnummer(int kørekortnummer) {
        this.kørekortnummer = kørekortnummer;
    }

    public LocalDate getFraDate() {
        return fraDate;
    }

    public void setFraDate(LocalDate fraDate) {
        this.fraDate = fraDate;
    }

    public LocalDate getTilDate() {
        return tilDate;
    }

    public void setTilDate(LocalDate tilDate) {
        this.tilDate = tilDate;
    }

    public int getMaxKm() {
        return maxKm;
    }

    public void setMaxKm(int maxKm) {
        this.maxKm = maxKm;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }


    @Override
    public String toString() {
        return "Lejekontrakt{" +
                "lejerId=" + lejerId +
                ", kørekortnummer=" + kørekortnummer +
                ", fraDate=" + fraDate +
                ", tilDate=" + tilDate +
                ", maxKm=" + maxKm +
                ", licensePlate='" + licensePlate + '\'' +
                '}';
    }
}
