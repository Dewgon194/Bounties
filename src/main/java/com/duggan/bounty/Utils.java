package com.duggan.bounty;

import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;

import java.math.BigDecimal;

public class Utils {

    public static BigDecimal getBalance(String player) {
        BigDecimal money;
        try {
            money = Economy.getMoneyExact(player);
        } catch (UserDoesNotExistException e) {
            e.printStackTrace();
            return BigDecimal.valueOf(-1);
        }

        return money;

    }

    public static boolean hasEnoughMoney(String player, double amount) {
        if (getBalance(player).compareTo(BigDecimal.valueOf(amount)) >= 0) {
            return true;
        }
        return false;
    }

    public static boolean takeMoney(String player, double amount) {
        try {
            Economy.substract(player, BigDecimal.valueOf(amount));
            return true;
        } catch (UserDoesNotExistException | NoLoanPermittedException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean giveMoney(String player, double amount) {
        try {
            Economy.add(player, BigDecimal.valueOf(amount));
            return true;
        } catch (UserDoesNotExistException | NoLoanPermittedException e) {
            e.printStackTrace();
            return false;
        }
    }


}
