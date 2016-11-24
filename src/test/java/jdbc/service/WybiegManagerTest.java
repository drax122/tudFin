package jdbc.service;
import java.util.ArrayList;
import jdbc.domain.Wybieg;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by draxeer on 2016-11-24.
 */
public class WybiegManagerTest {
    public Wybieg dodajWybiega(){
        Wybieg w = new Wybieg();
        w.setWybieg_ID("2");
        w.setTyp_wybiegu("Imprezowy");
        w.setPowierzchnia("2354m2");
        w.setNazwa_wybiegu("Zdzichu");
        return w;
    }
    public Wybieg dodajWybiega22(){
        Wybieg w = new Wybieg();
        w.setWybieg_ID("1");
        w.setTyp_wybiegu("DzieciecyXD");
        w.setPowierzchnia("234m2");
        w.setNazwa_wybiegu("Marian");
        return w;
    }

    @Test
    public void dodajWybieg() throws Exception {
        WybiegManager.polaczenie();
        WybiegManager.delTableWybieg();
        ArrayList<Wybieg> wy = new ArrayList<Wybieg>();
        assertEquals(WybiegManager.DodajWybieg(dodajWybiega22()), 1);
        assertEquals(WybiegManager.WyswietlWybiegi().size(), 1);
    }

    @Test
    public void wyswietlWybiegi() throws Exception {

    }

    @Test
    public void zaktualizujWybieg() throws Exception {
        WybiegManager.polaczenie();
        WybiegManager.delTableWybieg();
        WybiegManager.DodajWybieg(dodajWybiega());
        Wybieg update = new Wybieg();
        update.setWybieg_ID(WybiegManager.WyswietlWybiegi().get(0).getWybieg_ID());
        update.setNazwa_wybiegu("Polszy");
        update.setPowierzchnia("213");
        update.setTyp_wybiegu("Palarnia");

        assertEquals(WybiegManager.ZaktualizujWybieg(update), 1);
        assertEquals(WybiegManager.WyswietlWybiegi().get(0).getNazwa_wybiegu(), "Polszy");
        assertEquals(WybiegManager.WyswietlWybiegi().get(0).getPowierzchnia(), "213");
        assertEquals(WybiegManager.WyswietlWybiegi().get(0).getTyp_wybiegu(), "Palarnia");

    }

    @Test
    public void usunWybiegi() throws Exception {

        WybiegManager.polaczenie();
        WybiegManager.delTableWybieg();
        assertEquals(WybiegManager.DodajWybieg(dodajWybiega22()), 1);
        assertEquals(WybiegManager.DodajWybieg(dodajWybiega()), 1);
        assertEquals(WybiegManager.WyswietlWybiegi().size(), 1);
        WybiegManager.delTableWybieg();
        assertEquals(WybiegManager.WyswietlWybiegi().get(0).getWybieg_ID(), null);

    }
    @Test
    public void usunWybieg() throws Exception {

        WybiegManager.polaczenie();
        WybiegManager.delTableWybieg();
        assertEquals(WybiegManager.DodajWybieg(dodajWybiega()), 1);
        assertEquals(WybiegManager.WyswietlWybiegi().size(), 1);
        assertEquals(WybiegManager.UsunWybieg(dodajWybiega()) , 1);
        assertFalse(WybiegManager.WyswietlWybiegi().contains(dodajWybiega()));

    }

}