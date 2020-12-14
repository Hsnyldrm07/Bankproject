/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bank;

/**
 *
 * @author Konrad Sobczyk
 */
import java.util.Calendar;

public class Konto {
    private float saldo = 0.0f;
    private int numer;
    private String haslo;
    private boolean aktywne = false;
    private int kod_weryfikacji;
    private boolean zablokowane = false;

    public enum typ_transakcji { WPLATA, WYPLATA, NONE }

    private String czas_transakcji = null;
    private typ_transakcji transakcja = typ_transakcji.NONE;
    private float kwota_transakcji = -0.0f;

    Konto(int numer_konta, String haslo)
    {
        numer = numer_konta;
        this.haslo = haslo;
    }

    @Override
    public String toString(){return "Konto numer: " + numer + ", saldo: " + saldo +", aktywne: " + aktywne;}
    
    private boolean Weryfikacja(int kod)
    {
        if((!zablokowane)&&(aktywne)&&(kod==kod_weryfikacji)) return true;

        System.out.println("Weryfikacja nie powiodła się.");
        return false;
    }
    
    public int GetNumer(){ return numer; }
    
    public float GetSaldo(int kod)
    {
        if(Weryfikacja(kod)) return saldo;
        
        return 0.0f;
    }
    
    public void ZmienHaslo(String stare_haslo, String nowe_haslo, int kod)
    {
        if(Weryfikacja(kod)&&(haslo.equals(stare_haslo))) haslo = nowe_haslo;
    }
    
    public int Zaloguj(Osoba osoba, String haslo_do_konta)
    {
        if((!zablokowane)&&(osoba instanceof Uzytkownik)&&(haslo.equals(haslo_do_konta)))
        {
            aktywne = true;
            kod_weryfikacji = osoba.hashCode();

            return 1;
        }
        else return -1;
    }

    public void Wyloguj()
    {
        aktywne = false;
        kod_weryfikacji = -1;
    }
    
    public void Zablokuj(Osoba osoba)
    {
        if(osoba instanceof Pracownik)
        {
            aktywne = false;            
            kod_weryfikacji = -1;
            
            zablokowane = true;
        }
    }

    public int Wplata(float kwota, int kod)
    {
        if(Weryfikacja(kod))
        {
            saldo += kwota;
            
            transakcja = typ_transakcji.WPLATA;
            kwota_transakcji = kwota;
            czas_transakcji = OstatniaTransakcja();
            return 1;
        }

        return -1;
    }

    public int Wyplata(float kwota, int kod)
    {
        if((Weryfikacja(kod))&&(kwota<=saldo))
        {
            saldo -= kwota;
            
            transakcja = typ_transakcji.WYPLATA;
            kwota_transakcji = kwota;
            czas_transakcji = OstatniaTransakcja();
            
            return 1;
        }

        return -1;
    }
    
    public boolean czyHistoria()
    {
        return (czas_transakcji!=null);
    }

    public void WyswietlStan()
    {
        System.out.println("Numer konta: "+ numer+" saldo: "+ saldo);
        System.out.println("");
    }
    
    public String OstatniaTransakcja()
    {
        String result;
        
        result = "\nOstatnia operacja:";
      
        int h = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int m = Calendar.getInstance().get(Calendar.MINUTE);
        //int s = Calendar.getInstance().get(Calendar.SECOND);

        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int year = Calendar.getInstance().get(Calendar.YEAR);

        result += "\nData: "+Integer.toString(day)+"."+Integer.toString(month)+"."+Integer.toString(year)
                +"\nGodzina: "+Integer.toString(h)+":"+Integer.toString(m)+":"+Integer.toString(h);

        if(transakcja.equals(typ_transakcji.WPLATA))
            result += "\nTyp transakcji: WPŁATA";
        else 
            result += "\nTyp transakcji: WYPŁATA";
        
        result += "\nKwota: "+kwota_transakcji+" PLN";

        return result;
    }

    public String PobierzTransakcje()
    {
        return czas_transakcji;
    }

    public void WyswietlTransakcje()
    {
        System.out.print(czas_transakcji);
    }
}
