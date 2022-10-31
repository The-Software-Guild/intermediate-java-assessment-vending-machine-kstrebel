
package com.sal.vendingmachine.dto;

import com.sal.vendingmachine.service.VendingMachineInsufficientFundsException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;

/**
 *
 * @author salajrawi
 */
public class Change {
    private HashMap<Coins,Integer> coinChangeMap=new HashMap<>();

    public Change() {
    }
    
    public HashMap<Coins,Integer> getChange(BigDecimal funds) throws VendingMachineInsufficientFundsException{
      final Coins[] coinList =
      { Coins.QUARTERS, Coins.DIME, Coins.NICKLE, Coins.PENNY };

      for (Coins coin : coinList) {
        coinChangeMap.put(coin, funds.divide(coin.getValue()).intValue());

        funds = funds.remainder(coin.getValue());
      }

      return coinChangeMap;
    }
}
