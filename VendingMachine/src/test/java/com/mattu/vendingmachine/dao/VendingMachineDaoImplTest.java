
package com.mattu.vendingmachine.dao;

import com.sal.vendingmachine.dao.VendingMachineDao;
import com.sal.vendingmachine.dao.VendingMachineDaoImpl;
import com.sal.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author salajrawi
 */
public class VendingMachineDaoImplTest {
    
    public static VendingMachineDao testDao;
    private Item item;
    
    public VendingMachineDaoImplTest() {
    }

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
        testDao = new VendingMachineDaoImpl();
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception
    {
        testDao = new VendingMachineDaoImpl();

        item = new Item("TestItem", new BigDecimal(3), 1);

        testDao.addItem(item);
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception
    {
        
        testDao.removeItem(item);
    }
    
//    @BeforeAll
//    public static void setUpClass() {
//    }
//    
//    @AfterAll
//    public static void tearDownClass() {
//    }
//    
//    @BeforeEach
//    public void setUp() {
//    }
//    
//    @AfterEach
//    public void tearDown() {
//    }

    /**
     * Test of getItem method, of class VendingMachineDaoImpl.
     */
    @org.junit.jupiter.api.Test
    public void testGetItem() throws Exception {
        assertEquals(testDao.getItem("TestItem"), item);
    }

    /**
     * Test of listAllItems method, of class VendingMachineDaoImpl.
     */
    @org.junit.jupiter.api.Test
    public void testListAllItems() throws Exception {
        List<Item> testList = testDao.listAllItems();

        assertEquals(testList.size(), 10);
}

    /**
     * Test of changeInventoryCount method, of class VendingMachineDaoImpl.
     */
    @org.junit.jupiter.api.Test
    public void testChangeInventoryCount() throws Exception {
        testDao.changeInventoryCount(item, 5);
        assertEquals(item.getNumInventoryItems(), 5);
    }
    
}
