/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bank;

/**
 *
 * @author Konrad Sobczyk
 */
public class Uzytkownik extends Osoba{
    private int numer_konta;
    private Konto konto = null;
    private boolean zalogowany = false;

    Uzytkownik(String imie, String nazwisko)
    {
        super(imie, nazwisko);
    }

    @Override
    public String toString(){return Imie+" "+Nazwisko+" numer konta: "+numer_konta;}

    private boolean Zalogowany()
    {
        if((zalogowany)&&(konto!=null)) return true;

        return false;
    }

    public void PrzypiszKonto(int numer)
    {
        numer_konta = numer;
    }
    
    public void ZmienHaslo(String stare_haslo, String nowe_haslo)
    {
        if(Zalogowany())
            konto.ZmienHaslo(stare_haslo, nowe_haslo, hashCode());
    }

    public int ZalogujNaKonto(Bank bank, String haslo)
    {
        konto = bank.WyszukajKonto(numer_konta);
        if(konto!=null)
        {
            int result = konto.Zaloguj(this, haslo);
            if(result>0)
                zalogowany = true;
            return result;
        }
        return -1;
    }

    public int Wplata(float kwota)
    {
        if(Zalogowany())
            return konto.Wplata(kwota, hashCode());

        return -1;
    }

    public int Wyplata(float kwota)
    {
        if(Zalogowany())
            return konto.Wyplata(kwota, hashCode());

        return -1;
    }

    public int WyswietlStanKonta()
    {
        if(Zalogowany())
        {
            konto.WyswietlStan();
            return 1;
        }

        return -1;
    }
    
    public float GetSaldo()
    {
        if(Zalogowany())
        {            
            return konto.GetSaldo(hashCode());
        }

        return -0.0f;        
    }

    public boolean czyHistoria()
    {
        return konto.czyHistoria();
    }

    public String PobierzTransakcje()
    {
        if(Zalogowany())
            return konto.PobierzTransakcje();

        return null;
    }

    public void WyswietlTransakcje()
    {
        if(Zalogowany())
            konto.WyswietlTransakcje();
    }

    public void Wyloguj()
    {
        if(Zalogowany())
        {
            konto.Wyloguj();
            konto =  null;
            System.out.println("Wylogowano.");
        }
    }

}
