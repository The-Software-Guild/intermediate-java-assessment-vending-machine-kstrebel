
package com.sal.vendingmachine.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author salajrawi
 */
public class Item {
    private String name;
    private BigDecimal cost;
    private int numInventoryItems;

    public Item() {
    }

    public Item(String name, BigDecimal cost, int numInventoryItems) {
       //implement
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public int getNumInventoryItems() {
        return numInventoryItems;
    }

    public void setNumInventoryItems(int numInventoryItems) {
        this.numInventoryItems = numInventoryItems;
    }

    @Override
    public String toString() {
        return "Item{" + "name=" + name + ", cost=" + cost + ", numInventoryItems=" + numInventoryItems + '}';
    }

    @Override
    public int hashCode() {
    //implement
    }

    @Override
    public boolean equals(Object obj) {
        //implement
    }
    
    
}
