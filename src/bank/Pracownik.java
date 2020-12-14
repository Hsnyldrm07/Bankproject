/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

/**
 *
 * @author Konrad Sobczyk
 */
public class Pracownik extends Osoba {

    Pracownik(String imie, String nazwisko) {
        super(imie, nazwisko);
    }

    @Override
    public String toString() {
        return "Pracownik: " + Imie + " " + Nazwisko;
    }

    public int DodajKonto(Bank bank, int numer_konta, String haslo) {
        return bank.DodajKono(numer_konta, haslo);
    }

    public void UsunKonto(Bank bank, int numer_konta) {
        bank.UsunKonto(numer_konta);
    }

    public void ZablokujKonto(Bank bank, int numer_konta) {
        Konto konto = bank.WyszukajKonto(numer_konta);
        konto.Zablokuj(this);
    }
}
