
package com.sal.vendingmachine.dao;

import com.sal.vendingmachine.dao.FileDao;
import com.sal.vendingmachine.dao.VendingMachineException;
import com.sal.vendingmachine.dto.Item;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
public class FileDaoImpl implements FileDao
{

    private static final String ITEM_FILE = "items.txt";
    private static final String DELIMITER = ",";

    @Override
    public Item unmarshallItem(String line)
    {
        String[] attributes=line.split((DELIMITER));

        Item item = new Item(attributes[0], attributes[1], attributes[2]);

        return item;
    }

    @Override
    public String marshallItem(Item item)
    {
        return item.getName() + DELIMITER + item.getCost() + DELIMITER + item.getNumInventoryItems();
    }

    @Override
    public void writeFile(List<Item> list) throws VendingMachineException
    {
        try
        {
            PrintWriter output = new PrintWriter(new FileWriter(ITEM_FILE));

            String itemText;

            for (Item item : list)
            {
                itemText = marshallItem(item);
                output.println(itemText);
            }

            output.flush();

            output.close();
        } catch (IOException e)
        {
            throw new VendingMachineException("Could not save item data", e);
        }
    }

    @Override
    public Map<String, Item> readFile(String file) throws VendingMachineException
    {
        try
        {
            Scanner input = new Scanner(new BufferedReader(new FileReader(ITEM_FILE)));
            Map<String, Item> itemMap = new HashMap<>();

            while (input.hasNextLine())
            {
                String line = input.nextLine();

                Item item = unmarshallItem(line);

                itemMap.put(item.getName(), item);
            }

            return itemMap;
        } catch (FileNotFoundException e)
        {
            throw new VendingMachineException("File not found", e);
        }

    }

}
