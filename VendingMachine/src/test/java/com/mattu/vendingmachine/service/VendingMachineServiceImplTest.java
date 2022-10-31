
package com.mattu.vendingmachine.service;

import com.sal.vendingmachine.service.VendingMachineServiceImpl;
import com.sal.vendingmachine.service.VendingMachineItemInventoryException;
import com.sal.vendingmachine.service.VendingMachineService;
import com.sal.vendingmachine.service.VendingMachineInsufficientFundsException;
import com.sal.vendingmachine.dao.AuditDao;
import com.sal.vendingmachine.dao.AuditDaoImpl;
import com.sal.vendingmachine.dao.VendingMachineDao;
import com.sal.vendingmachine.dao.VendingMachineDaoImpl;
import com.sal.vendingmachine.dao.VendingMachineException;
import com.sal.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.rmi.server.ServerCloneException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author salajrawi
 */
public class VendingMachineServiceImplTest {
    
    public static VendingMachineService service;
    private Item item;
    
    public VendingMachineServiceImplTest()
    {
        try{
        VendingMachineDao dao = new VendingMachineDaoImpl();
        AuditDao auditDao = new AuditDaoImpl();

        service = new VendingMachineServiceImpl(dao, auditDao);
        }
        catch (VendingMachineException e)
        {
            //displayErrorMessage(e.getMessage());
        }
    }
    
    @BeforeAll
    public static void setUpClass() throws VendingMachineException{
        //implement
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws VendingMachineException
    {
        item = new Item("TestItem", new BigDecimal(3), 1);

        service.addItem(item);
    }
    
    @AfterEach
    public void tearDown() throws VendingMachineException{
        service.removeItem(item);
    }

    /**
     * Test of getItem method, of class VendingMachineServiceImpl.
     */
    @Test
    public void testGetItem() throws Exception
    {
        assertEquals(service.getItem("TestItem"), item);
        //Assertions.assertThrows(NullPointerException.class, () -> service.getItem("Fail"));

        // service.removeItem(item);
    }

    /**
     * Test of listAllItems method, of class VendingMachineServiceImpl.
     */
    @Test
    public void testListAllItems() throws Exception {
        assertEquals(9, service.listAllItems().size(), "9 items");
    }

    /**
     * Test of changeInventoryCount method, of class VendingMachineServiceImpl.
     */
    @Test
    public void testChangeInventoryCount(){
        Item testItem = new Item("Cheetos",  new BigDecimal(2.99).setScale(2,RoundingMode.FLOOR), 18);
        try{
            service.changeInventoryCount(testItem, 100);
            assertNotNull(testItem, "Item should not be null");
            assertEquals(100, testItem.getNumInventoryItems(), "Inventory item should be 100");
        }catch(VendingMachineException e){
            fail("No way it will go wrong");
        }

        try{
            service.changeInventoryCount(testItem, -100);
        }catch(VendingMachineException e){
            System.out.println("the value should not be negative");
        }
    }

    /**
     * Test of sellItem method, of class VendingMachineServiceImpl.
     */
    @Test
    public void testSellItem() throws Exception
    {
        Assertions.assertThrows(VendingMachineInsufficientFundsException.class,
                () -> service.sellItem(new BigDecimal(2), item));

        assertEquals(service.sellItem(new BigDecimal(5), item), new BigDecimal(2));

        Assertions.assertThrows(VendingMachineItemInventoryException.class,
                () -> service.sellItem(new BigDecimal(5), item));
    }
}
