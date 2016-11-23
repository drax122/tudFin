package jdbc.domain;

/**
 * Created by draxeer on 2016-11-23.
 */
public class Zwierz {

    private String zwierz_ID;
    private String wybieg_ID;
    private String rasa;
    private String gatunek;
    private String co_szama;

    public Zwierz(){};

    public Zwierz(String wybieg_ID, String rasa, String gatunek, String co_szama) {
        super();
        this.wybieg_ID = wybieg_ID;
        this.rasa = rasa;
        this.gatunek = gatunek;
        this.co_szama = co_szama;
    }

    public String getZwierz_ID() {
        return zwierz_ID;
    }

    public void setZwierz_ID(String zwierz_ID) {
        zwierz_ID = zwierz_ID;
    }

    public String getWybieg_ID() {
        return wybieg_ID;
    }

    public void setWybieg_ID(String wybieg_ID) {
        this.wybieg_ID = wybieg_ID;
    }

    public String getRasa() {
        return rasa;
    }

    public void setRasa(String rasa) {
        this.rasa = rasa;
    }

    public String getGatunek() {
        return gatunek;
    }

    public void setGatunek(String gatunek) {
        this.gatunek = gatunek;
    }

    public String getCo_szama() {
        return co_szama;
    }

    public void setCo_szama(String co_szama) {
        this.co_szama = co_szama;
    }
}
