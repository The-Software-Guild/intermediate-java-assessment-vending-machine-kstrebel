
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
      //implement
    }
    
}
