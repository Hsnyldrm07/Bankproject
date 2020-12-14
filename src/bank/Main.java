// nie używany w GUI
package bank;

/**
 *
 * @author Konrad Sobczyk
 */

public class Main {

    public static void main(String[] args) {
        
        Bank bank = new Bank();

        Pracownik pracownik = new Pracownik("Zenon", "Malinowski");

        pracownik.DodajKonto(bank, 12345678, "xczbcx56bn");
        pracownik.DodajKonto(bank, 24661773, "hjytfujd54");
        pracownik.DodajKonto(bank, 25645634, "dfy546ery7");


        Uzytkownik uzytkownik = new Uzytkownik("Jan", "Kowalski");

        uzytkownik.PrzypiszKonto(24661773);
        uzytkownik.ZalogujNaKonto(bank, "hjytfujd54");

        int test=0;
        
        test = uzytkownik.Wplata(1800);    
        if(test<0) System.out.println("Operacja nie powiodła się.");
        uzytkownik.WyswietlTransakcje();
        uzytkownik.WyswietlStanKonta();
        
        
        test = uzytkownik.Wyplata(600); 
        if(test<0) System.out.println("Operacja nie powiodła się.");  
        uzytkownik.WyswietlTransakcje();
        uzytkownik.WyswietlStanKonta();
        uzytkownik.Wyloguj();
        
    }

}
