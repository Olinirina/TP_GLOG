package Classe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

public class MoneyBag implements IMoney{
	private Vector<Money> fMonies = new Vector<Money>(); 
	 private List<IMoney> fMonie = new ArrayList<>();
	public MoneyBag(Money m1, Money m2) { 
			appendMoney(m1); 
			appendMoney(m2); 
	} 
	
	
	public MoneyBag(Money bag[]) { 
	for (int i = 0; i < bag.length; i++) 
	appendMoney(bag[i]); 
	} 
	
	private void appendMoney(Money m) {
	    if (fMonies.isEmpty()) {
	        fMonies.add(m);
	    } else {
	        boolean added = false;
	        for (int i = 0; i < fMonies.size(); i++) {
	            Money money = fMonies.get(i);
	            if (money.currency().equals(m.currency())) {
	                // Si trouvé, ajouter les montants
	                money.add(m.amount());
	                added = true;
	                break;
	            }
	        }
	        if (!added) {
	            fMonies.add(m);
	        }
	    }
	}
	

	    public MoneyBag(IMoney... monies) {
	        fMonies.addAll((Collection<? extends Money>) Arrays.asList(monies));
	    }

	    @Override
	    public IMoney add(IMoney m) {
	        return m.addMoneyBag(this);
	    }
	    @Override
	    public IMoney addMoney(Money m) {	      
	        for (Money money : fMonies) {
	            if (money.currency().equals(m.currency())) {
	                // Si trouvé, ajoutez les montants
	                money.add(m.amount());
	                return simplify();
	            }
	        }
	        fMonies.add(m);
	        return simplify();
	    }

	    @Override
	    public IMoney addMoneyBag(MoneyBag mb) {
	        List<IMoney> combinedMonies = new ArrayList<>(this.fMonies);
	        combinedMonies.addAll(mb.fMonies);
	        MoneyBag result = new MoneyBag(combinedMonies.toArray(new IMoney[0]));
	        return result.simplify();
	    }

	
	    @Override
	    public String toString() {
	        StringBuilder sb = new StringBuilder();
	        sb.append("MoneyBag{");
	        for (IMoney money : fMonies) {
	            sb.append(money.toString()).append(", ");
	        }
	        if (!fMonies.isEmpty()) {
	            sb.setLength(sb.length() - 2); 
	        }
	        sb.append("}");
	        return sb.toString();
	    }
	    public IMoney simplify() {
	        if (fMonies.size() == 1) {
	            return fMonies.get(0);
	        }

	        return this;
	    }


	public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        MoneyBag moneyBag = (MoneyBag) obj;

        if (fMonies.size() != moneyBag.fMonies.size()) return false;

        for (Money money : fMonies) {
            if (!moneyBag.fMonies.contains(money)) {
                return false;
            }
        }

        for (Money money : moneyBag.fMonies) {
            if (!fMonies.contains(money)) {
                return false;
            }
        }

        return true;
    }
	
}
