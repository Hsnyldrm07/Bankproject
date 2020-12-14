/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bank;

/**
 *
 * @author Konrad Sobczyk
 */

import java.util.ArrayList;
public class Bank {
    private ArrayList<Konto> konta = new ArrayList<Konto>();

    public int NumerKont()
    {
        return konta.size();
    }
    
    public int DodajKono(int numer_konta, String haslo)
    {
        if(WyszukajKonto(numer_konta)==null)
        {
            konta.add(new Konto(numer_konta, haslo));
            return 1;
        }

        return -1;
    }

    public void UsunKonto(int numer_konta)
    {        
        for(int i=0;i<konta.size();i++)
            if(konta.get(i).GetNumer() == numer_konta)
                konta.remove(i);
    }

    public Konto WyszukajKonto(int numer_konta)
    {
        for(int i=0;i<konta.size();i++)
            if(konta.get(i).GetNumer() == numer_konta)
                return konta.get(i);

        return null;
    }
    
    public int GetNumer(int poz)
    {
        if(poz<konta.size()) 
            return konta.get(poz).GetNumer();

        return -1;
    }
}
