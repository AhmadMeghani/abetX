package com.example.abetx.Models;

public class Transactions {
    private String date;
    private String debit_head;
    private String credit_head;
    private int debit;
    private int credit;

    public Transactions(String date, String debit_head, String credit_head, int debit, int credit) {
        this.date = date;
        this.debit_head = debit_head;
        this.credit_head = credit_head;
        this.debit = debit;
        this.credit = credit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDebit_head() {
        return debit_head;
    }

    public void setDebit_head(String debit_head) {
        this.debit_head = debit_head;
    }

    public String getCredit_head() {
        return credit_head;
    }

    public void setCredit_head(String credit_head) {
        this.credit_head = credit_head;
    }

    public int getDebit() {
        return debit;
    }

    public void setDebit(int debit) {
        this.debit = debit;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "date='" + date + '\'' +
                ", debit_head='" + debit_head + '\'' +
                ", credit_head='" + credit_head + '\'' +
                ", debit=" + debit +
                ", credit=" + credit +
                '}';
    }
}
