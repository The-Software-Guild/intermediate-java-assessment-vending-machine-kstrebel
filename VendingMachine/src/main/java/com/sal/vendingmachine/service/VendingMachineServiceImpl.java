
package com.sal.vendingmachine.service;

import com.sal.vendingmachine.dao.AuditDao;
import com.sal.vendingmachine.dao.AuditDaoImpl;
import com.sal.vendingmachine.dao.FileDao;
import com.sal.vendingmachine.dao.FileDaoImpl;
import com.sal.vendingmachine.dao.VendingMachineDao;
import com.sal.vendingmachine.dao.VendingMachineDaoImpl;
import com.sal.vendingmachine.dao.VendingMachineException;
import com.sal.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author salajrawi
 */
public class VendingMachineServiceImpl implements VendingMachineService{

    private VendingMachineDao dao;
    private AuditDao adao;

    public VendingMachineServiceImpl() throws VendingMachineException
    {
        this.dao = new VendingMachineDaoImpl();
        this.adao = new AuditDaoImpl();
    }
    
    public VendingMachineServiceImpl(VendingMachineDao dao, AuditDao adao) {
        this.dao = dao;
        this.adao = adao;
    }
    
    @Override
    public Item getItem(String name) throws VendingMachineException, VendingMachineItemInventoryException
    {
        return dao.getItem(name);
    }

    @Override
    public List<Item> listAllItems() throws VendingMachineException{
        return dao.listAllItems()
                .stream()
                .filter(item->item.getNumInventoryItems()>0)
                .collect(Collectors.toList());
    }

    @Override
    public Item addItem(Item item) throws VendingMachineException{
        return dao.addItem(item);
    }

    @Override
    public Item removeItem(Item item) throws VendingMachineException
    {
        return dao.removeItem(item);
    }

    @Override
    public Item changeInventoryCount(Item item, int newCount) throws VendingMachineException{
        item.setNumInventoryItems(newCount);
        dao.changeInventoryCount(item, newCount);

        return item;
    }

    @Override
    public BigDecimal sellItem(BigDecimal totalFunds, Item item) throws VendingMachineException,
            VendingMachineItemInventoryException, VendingMachineInsufficientFundsException
    {
        int itemCount = item.getNumInventoryItems();
        BigDecimal itemCost = item.getCost();

        if (itemCount <= 0)
        {
            throw new VendingMachineItemInventoryException("No " + item.getName() + " available to buy");

            // return totalFunds;
        } else if (itemCost.compareTo(totalFunds) > 0)
        {
            throw new VendingMachineInsufficientFundsException("Not enough money to buy " + item.getName());

            // return totalFunds;
        } else
        {
            changeInventoryCount(item, itemCount - 1);

            adao.writeAuditEntry("Item "+item.getName()+" has been sold. Number in Inventory: "+item.getNumInventoryItems());

            return totalFunds.subtract(itemCost);
        }
    }
}
