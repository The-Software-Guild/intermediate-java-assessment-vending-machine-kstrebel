
package com.sal.vendingmachine.dao;

import com.sal.vendingmachine.dao.AuditDao;
import com.sal.vendingmachine.dao.VendingMachineException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author salajrawi
 */
public class AuditDaoImpl implements AuditDao
{

    public static final String AUDIT_FILE = "audit.txt";

    @Override
    public void writeAuditEntry(String entry) throws VendingMachineException
    {
        try
        {
            PrintWriter output = new PrintWriter(new FileWriter(AUDIT_FILE, true));

            output.println(LocalDateTime.now().toString() + " : " + entry);
            output.flush();

            output.close();
        } 
        catch (IOException e)
        {
            throw new VendingMachineException("Cound not write audit information to audit.txt");
        }
    }
}
