/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bank;

/**
 *
 * @author Konrad Sobczyk
 */
abstract public class Osoba {
    String Imie;
    String Nazwisko;

    Osoba(String imie, String nazwisko)
    {
        this.Imie = imie;
        this.Nazwisko = nazwisko;
    }
    Osoba(){Imie = ""; Nazwisko = "";}
}

