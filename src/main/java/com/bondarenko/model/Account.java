package com.bondarenko.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Account")
public class Account implements Serializable {

    private int id;
    private double amount;
    private Client client;

    public Account() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    public int getId() {
        return id;
    }

    @Column(name = "amount")
    public double getAmount() {
        return amount;
    }

    @ManyToOne
    @JoinColumn(name="client_id")
    public Client getClient() {
        return client;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", amount=" + amount +
                ", client=" + client +
                '}';
    }
}
