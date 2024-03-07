import java.sql.Date;
import java.time.LocalDate;

public class Lejer {

    private String navn;
    private String addresse;
    private int postNummer;
    private String city;
    private int countrycode;
    private int telefonNummer;
    private String email;
    private int kørekortnummer;
    private LocalDate kørekortsudstedelsesdato;

    public Lejer(String navn, String addresse,int postNummer,String city, int countrycode, int telefonNummer, String email, int kørekortnummer, LocalDate kørekortsudstedelsesdato) {
        setNavn(navn);
        setAddresse(addresse);
        setPostNummer(postNummer);
        setCity(city);
        setCountrycode(countrycode);
        setTelefonNummer(telefonNummer);
        setEmail(email);
        setKørekortnummer(kørekortnummer);
        setKørekortsudstedelsesdato(kørekortsudstedelsesdato);
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }
    public int getPostNummer() {
        return postNummer;
    }

    public void setPostNummer(int postNummer) {
        this.postNummer = postNummer;
    }
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(int countrycode) {
        this.countrycode = countrycode;
    }

    public int getTelefonNummer() {
        return telefonNummer;
    }

    public void setTelefonNummer(int telefonNummer) {
        this.telefonNummer = telefonNummer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getKørekortnummer() {
        return kørekortnummer;
    }

    public void setKørekortnummer(int kørekortnummer) {
        this.kørekortnummer = kørekortnummer;
    }

    public LocalDate getKørekortsudstedelsesdato() {
        return kørekortsudstedelsesdato;
    }

    public void setKørekortsudstedelsesdato(LocalDate kørekortsudstedelsesdato) {
        this.kørekortsudstedelsesdato = kørekortsudstedelsesdato;
    }


    @Override
    public String toString() {
        return "Lejer{" +
                "navn='" + navn + '\'' +
                ", addresse='" + addresse + '\'' +
                ", city='" + city + '\'' +
                ", countrycode=" + countrycode +
                ", telefonNummer=" + telefonNummer +
                ", email='" + email + '\'' +
                ", kørekortnummer=" + kørekortnummer +
                ", kørekortsudstedelsesdato=" + kørekortsudstedelsesdato +
                '}';
    }
}
