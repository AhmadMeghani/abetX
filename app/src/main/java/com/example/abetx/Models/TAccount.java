package com.example.abetx.Models;

import java.util.ArrayList;
import java.util.List;

public class TAccount {
    public String accountName;
    public List<Integer> debitValues;
    public List<Integer> creditValues;
    public int debitTotal;
    public int creditTotal;
    public int debitLeft;
    public int creditLeft;

    public TAccount(String accountName, ArrayList<Integer> debitValues, ArrayList<Integer> creditValues, int debitTotal, int creditTotal, int debitLeft, int creditLeft) {
        this.accountName = accountName;
        this.debitValues = debitValues;
        this.creditValues = creditValues;
        this.debitTotal = debitTotal;
        this.creditTotal = creditTotal;
        this.debitLeft = debitLeft;
        this.creditLeft = creditLeft;
    }

    public TAccount() {

    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public List<Integer> getDebitValues() {
        return debitValues;
    }

    public void setDebitValues(ArrayList<Integer> debitValues) {
        this.debitValues = debitValues;
    }

    public List<Integer> getCreditValues() {
        return creditValues;
    }

    public void setCreditValues(ArrayList<Integer> creditValues) {
        this.creditValues = creditValues;
    }

    public int getDebitTotal() {
        return debitTotal;
    }

    public void setDebitTotal(int debitTotal) {
        this.debitTotal = debitTotal;
    }

    public int getCreditTotal() {
        return creditTotal;
    }

    public void setCreditTotal(int creditTotal) {
        this.creditTotal = creditTotal;
    }

    public int getDebitLeft() {
        return debitLeft;
    }

    public void setDebitLeft(int debitLeft) {
        this.debitLeft = debitLeft;
    }

    public int getCreditLeft() {
        return creditLeft;
    }

    public void setCreditLeft(int creditLeft) {
        this.creditLeft = creditLeft;
    }

    @Override
    public String toString() {
        return "TAccount{" +
                "accountName='" + accountName + '\'' +
                ", debitValues=" + debitValues +
                ", creditValues=" + creditValues +
                ", debitTotal=" + debitTotal +
                ", creditTotal=" + creditTotal +
                ", debitLeft=" + debitLeft +
                ", creditLeft=" + creditLeft +
                '}';
    }
}
