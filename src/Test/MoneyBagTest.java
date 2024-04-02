package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Classe.IMoney;
import Classe.Money;
import Classe.MoneyBag;



public class MoneyBagTest {
	public Money f12CHF;
    public Money f14CHF;
    public Money f7USD;
    public Money f21USD;
    public MoneyBag fMB1;
    public MoneyBag fMB2;
    
    @Before
    public void setUp() {
        f12CHF = new Money(12, "CHF");
        f14CHF = new Money(14, "CHF");
        f7USD = new Money(7, "USD");
        f21USD = new Money(21, "USD");
        fMB1 = new MoneyBag(f12CHF, f7USD);
        fMB2 = new MoneyBag(f14CHF, f21USD);
    }
    
    @Test
    public void testBagEquals() {
        assertFalse(fMB1.equals(null)); 
        assertTrue(fMB1.equals(new MoneyBag(f12CHF, f7USD))); 
        assertTrue(fMB1.equals(new MoneyBag(f7USD, f12CHF))); 
        assertFalse(fMB1.equals(fMB2)); 
    }

    @Test
    public void testSimpleAdd() {
        Money expectedMoney = new Money(19, "CHF"); 
        MoneyBag expectedBag = new MoneyBag(expectedMoney);
        assertNotSame(expectedBag, fMB1.add(f12CHF));
        

    }

    @Test
    public void testComplexAdd() {
        // Validation de l'ajout de deux MoneyBag
        Money expectedMoney1 = new Money(26, "CHF"); // 12 CHF + 14 CHF
        Money expectedMoney2 = new Money(28, "USD"); // 7 USD + 21 USD
        MoneyBag expectedBag = new MoneyBag(expectedMoney1, expectedMoney2);
        assertNotSame(expectedBag, fMB1.add(fMB2));
    }

    
    @Test
    public void testSimplifyMoneyBag() {
        Money m1 = new Money(12, "CHF");
        Money m2 = new Money(-12, "CHF");
        Money m3 = new Money(7, "USD");
        MoneyBag bag1 = new MoneyBag(m1, m3);
        MoneyBag bag2 = new MoneyBag(m2, m3);

        assertNotSame(new Money(7, "USD"), bag1.add(m2));
        assertNotSame(new Money(0, "CHF"), bag1.add(m1));
        assertNotSame(new Money(7, "USD"), bag2.add(m1));
        assertNotSame(new Money(7, "USD"), bag2.add(m2));
    }




}

