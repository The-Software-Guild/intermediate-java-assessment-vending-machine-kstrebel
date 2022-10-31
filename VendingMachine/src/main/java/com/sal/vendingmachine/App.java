
package com.sal.vendingmachine;

import com.sal.vendingmachine.controller.VendingMachineController;
// import com.sal.vendingmachine.dao.VendingMachineDao;
// import com.sal.vendingmachine.dao.VendingMachineDaoImpl;
import com.sal.vendingmachine.dao.VendingMachineException;
import com.sal.vendingmachine.service.VendingMachineInsufficientFundsException;
import com.sal.vendingmachine.service.VendingMachineItemInventoryException;
import com.sal.vendingmachine.service.VendingMachineService;
import com.sal.vendingmachine.service.VendingMachineServiceImpl;
import com.sal.vendingmachine.ui.UserIO;
import com.sal.vendingmachine.ui.UserIOImpl;
import com.sal.vendingmachine.ui.VendingMachineView;

/**
 *
 * @author salajrawi
 */
public class App {
    public static void main(String[] args) throws VendingMachineException
    {
        UserIO io = new UserIOImpl();

        // VendingMachineDao dao = new VendingMachineDaoImpl();
        VendingMachineView view = new VendingMachineView(io);
        VendingMachineService service = new VendingMachineServiceImpl();

        VendingMachineController controller = new VendingMachineController(view, service);

        controller.run();
    }
}
