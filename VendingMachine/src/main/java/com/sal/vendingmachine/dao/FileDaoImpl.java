
package com.sal.vendingmachine.dao;

import com.sal.vendingmachine.dao.FileDao;
import com.sal.vendingmachine.dao.VendingMachineException;
import com.sal.vendingmachine.dto.Item;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author salajrawi
 */
public class FileDaoImpl implements FileDao{

    private static final String ITEM_FILE = "items.txt";
    private static final String DELIMITER = ",";
    
    @Override
    public Item unmarshallItem(String line) {
         //implement  }

    @Override
    public String marshallItem(Item item) {
        return item.getName() + DELIMITER + item.getCost() + DELIMITER + item.getNumInventoryItems() + "\n";
    }

    @Override
    public void wirteFile(List<Item> list) throws VendingMachineException{
         try {
            //implement
        }
        catch(IOException e)
        {
            throw new VendingMachineException("Could not save item data", e);
        }
    }

    @Override
    public Map<String,Item> readFile(String file) throws VendingMachineException{
        try {
             //implement}
            return itemMap;
        }
        catch(FileNotFoundException e)
        {
            throw new VendingMachineException("File not found", e);
        } 
        
    }
    
}
