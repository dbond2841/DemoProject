package com.bondarenko;


import com.bondarenko.bo.ClientServiceImpl;
import com.bondarenko.model.Account;
import com.bondarenko.model.Client;
import com.bondarenko.service.InitSessionFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Demo {

    public static void main(String[] args) {
        Demo.fillTheTables();

        ClientServiceImpl service = new ClientServiceImpl();

        //get balance for all clients
        Demo.getAllClients().stream().forEach((client) ->
                System.out.println(String.join(" | ", client.getFirstName(), client.getLastName(), String.valueOf(service.getClientBalance(client))))
        );
        System.out.println("______________________________________________________________________________________");
        //get a client with max balance
        System.out.println(service.getClientWithMaxBalance(Demo.getAllAccounts()));

    }

    private static void fillTheTables() {
        Client client1 = new Client();
        Client client2 = new Client();

        Account account1 = new Account();
        Account account2 = new Account();
        Account account3 = new Account();

        client1.setFirstName("Jack");
        client1.setLastName("Jonson");
        client2.setFirstName("Ivan");
        client2.setLastName("Ivanov");

        account1.setAmount(100);
        account1.setClient(client1);
        account2.setAmount(500);
        account2.setClient(client1);
        account3.setAmount(999);
        account3.setClient(client2);

        client1.getAccounts().add(account1);
        client1.getAccounts().add(account2);
        client2.getAccounts().add(account3);

        Session session = InitSessionFactory.getInstance().openSession();
        Transaction tx = session.beginTransaction();
        session.save(client1);
        session.save(client2);
        tx.commit();
        session.close();
    }


    private static List<Client> getAllClients() {
        List<Client> list = null;
        try{
            String sqlQuery="select c from Client as c";
            Session session=InitSessionFactory.getInstance().openSession();
            Query query=session.createQuery(sqlQuery);
            list=query.list();
            session.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }


    private static List<Account> getAllAccounts() {
        List<Account> list = null;
        try{
            String sqlQuery="select a from Account as a";
            Session session=InitSessionFactory.getInstance().openSession();
            Query query=session.createQuery(sqlQuery);
            list=query.list();
            session.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }



}
