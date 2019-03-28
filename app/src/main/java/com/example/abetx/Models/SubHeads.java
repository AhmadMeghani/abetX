package com.example.abetx.Models;

public class SubHeads {
    public String head;

    public SubHeads(String head) {
        this.head = head;
    }

    public SubHeads() {
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    @Override
    public String toString() {
        return "SubHeads{" +
                "head='" + head + '\'' +
                '}';
    }
}
