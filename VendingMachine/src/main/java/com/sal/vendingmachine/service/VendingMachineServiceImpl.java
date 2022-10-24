
package com.sal.vendingmachine.service;

import com.sal.vendingmachine.dao.AuditDao;
import com.sal.vendingmachine.dao.AuditDaoImpl;
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

    public VendingMachineServiceImpl() throws VendingMachineException {
    //implement
    }
    
    public VendingMachineServiceImpl(VendingMachineDao dao, AuditDao adao) {
        this.dao = dao;
        this.adao = adao;
    }
    
    @Override
    public Item getItem(String name) throws VendingMachineException, VendingMachineItemInventoryException{
        //implement
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
     //implement
    }

    @Override
    public Item removeItem(Item item) throws VendingMachineException{
        //implement
    }

    @Override
    public Item changeInventoryCount(Item item, int newCount) throws VendingMachineException{
         //implement
    }

    @Override
    public BigDecimal sellItem(BigDecimal totalFunds, Item item) throws VendingMachineException, VendingMachineItemInventoryException, VendingMachineInsufficientFundsException{
    //implement
    }
    
}
