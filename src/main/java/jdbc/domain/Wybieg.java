package jdbc.domain;

/**
 * Created by draxeer on 2016-11-23.
 */
public class Wybieg {

    private String wybieg_ID;
    private String nazwa_wybiegu;
    private String powierzchnia;
    private String typ_wybiegu;

    public Wybieg() {
    }

    public Wybieg(String nazwa_wybiegu, String powierzchnia, String typ_wybiegu){
        super();
        this.nazwa_wybiegu = nazwa_wybiegu;
        this.powierzchnia = powierzchnia;
        this.typ_wybiegu = typ_wybiegu;


    }

    public String getWybieg_ID() {
        return wybieg_ID;
    }

    public void setWybieg_ID(String wybieg_ID) {
        this.wybieg_ID = wybieg_ID;
    }

    public String getNazwa_wybiegu() {
        return nazwa_wybiegu;
    }

    public void setNazwa_wybiegu(String nazwa_wybiegu) {
        this.nazwa_wybiegu = nazwa_wybiegu;
    }

    public String getPowierzchnia() {
        return powierzchnia;
    }

    public void setPowierzchnia(String powierzchnia) {
        this.powierzchnia = powierzchnia;
    }

    public String getTyp_wybiegu() {
        return typ_wybiegu;
    }

    public void setTyp_wybiegu(String typ_wybiegu) {
        this.typ_wybiegu = typ_wybiegu;
    }
}
