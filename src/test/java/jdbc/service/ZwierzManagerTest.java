package jdbc.service;

import jdbc.domain.Wybieg;
import jdbc.domain.Zwierz;
import jdbc.service.WybiegManager;
import jdbc.service.ZwierzManager;
import org.junit.Test;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

/**
 * Created by draxeer on 2016-11-23.
 */
public class ZwierzManagerTest {
    @Test
    public void zaktualizujZwierza() throws Exception {
        ZwierzManager.polaczenie();
        ZwierzManager.delTableZwierz();
        ZwierzManager.DodajZwierza(dodajZwierze());
        Zwierz nowyzw= new Zwierz();
        nowyzw.setZwierz_ID(ZwierzManager.WyswietlZwierza().get(0).getZwierz_ID());
        nowyzw.setRasa("Trol Gorski");
        nowyzw.setGatunek("Trolowate");
        nowyzw.setCo_szama("gluty");
        nowyzw.setWybieg_ID(ZwierzManager.WyswietlZwierza().get(0).getWybieg_ID());

        assertEquals(ZwierzManager.ZaktualizujZwierza(nowyzw), 1);

        assertEquals(ZwierzManager.WyswietlZwierza().get(0).getRasa(), "Trol Gorski");
        assertEquals(ZwierzManager.WyswietlZwierza().get(0).getGatunek(), "Trolowate");
        assertEquals(ZwierzManager.WyswietlZwierza().get(0).getCo_szama(), "gluty");
    }

    @Test
    public void usunZwierza() throws Exception {
        ZwierzManager.polaczenie();
        ZwierzManager.delTableZwierz();
        assertEquals(ZwierzManager.DodajZwierza(dodajZwierze()), 1);
        assertEquals(ZwierzManager.DodajZwierza(dodajZwierze2()), 1);
        assertEquals(ZwierzManager.WyswietlZwierza().size(), 2);
        assertEquals(ZwierzManager.UsunZwierza(dodajZwierze()) , 1);
        assertEquals(ZwierzManager.WyswietlZwierza().size(), 1);	//spr czy usunal sie tylko jeden rekord
        assertFalse(ZwierzManager.WyswietlZwierza().contains(dodajZwierze()));  //spr ze nie ma tego rekordu ktory usunelismy
    }
    @Test
    public void usunZwierze() throws Exception{
        ZwierzManager.polaczenie();
        ZwierzManager.delTableZwierz();
        assertEquals(ZwierzManager.DodajZwierza(dodajZwierze()), 1);
        assertEquals(ZwierzManager.WyswietlZwierza().size(), 1);
        ZwierzManager.delTableZwierz();
        assertEquals(ZwierzManager.WyswietlZwierza().size(), 0);
    }

    @Test
    public void wyswietlZwierzeZWybiegu() throws Exception {
        ZwierzManager.delTableZwierz();
        ZwierzManager.DodajZwierza(dodajZwierze2());    //id wybiegu = 2

        ArrayList<Zwierz> zw = new ArrayList<Zwierz>();
        zw = ZwierzManager.WyswietlZwierzeZWybiegu(dodajZwierze2());
        int rozmiar = zw.size();

        int i = 0;
        while (i < rozmiar) {
            assertEquals(zw.get(i).getWybieg_ID(), "2");
            i++;
        }
    }

    @Test
    public void zmianaWybieguDlaZwierza() throws Exception {
        ZwierzManager.polaczenie();
        ZwierzManager.delTableZwierz();
        ZwierzManager.DodajZwierza(dodajZwierze());

        Zwierz nowyzw = new Zwierz();
        nowyzw.setZwierz_ID(ZwierzManager.WyswietlZwierza().get(0).getZwierz_ID());
        nowyzw.setWybieg_ID("2");

        assertEquals(ZwierzManager.ZmianaWybieguDlaZwierza(nowyzw), 1);
        assertEquals(ZwierzManager.WyswietlZwierza().get(0).getZwierz_ID(), "1");
        assertEquals(ZwierzManager.WyswietlZwierza().get(0).getWybieg_ID(), "2");
    }

    @Test
    public void dodajZwierza() throws Exception {
        ZwierzManager.polaczenie();
        ZwierzManager.delTableZwierz();
        assertEquals(ZwierzManager.DodajZwierza(dodajZwierze()), 1);
        assertEquals(ZwierzManager.WyswietlZwierza().size(), 1);
    }

    @org.junit.Test
    public void polaczenie() throws Exception {
        assertNotNull(ZwierzManager.getConnection());
    }

    public Zwierz dodajZwierze(){
        Zwierz zw = new Zwierz();
        zw.setZwierz_ID("1");
        zw.setWybieg_ID("1");
        zw.setGatunek("Wilk");
        zw.setCo_szama("Miesozerny");
        zw.setRasa("Tunder");
        return zw;
    }
    public Zwierz dodajZwierze2(){
        Zwierz zw = new Zwierz();
        zw.setZwierz_ID("2");
        zw.setWybieg_ID("2");
        zw.setGatunek("Slon");
        zw.setCo_szama("Roslinozerny");
        zw.setRasa("Slon XD");
        return zw;
    }
}