package com.bondarenko.bo;

import com.bondarenko.model.Account;
import com.bondarenko.model.Client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ClientServiceImpl implements ClientService {

    public double getClientBalance(Client client) {
       return client.getAccounts().stream().mapToDouble((a) -> a.getAmount()).sum();

    }

    public Client getClientWithMaxBalance(List<Account> accounts) {
        Map<Client, Double> balances = new HashMap<>();

        for(Account account : accounts){
            if (!balances.containsKey(account.getClient())){
                balances.put(account.getClient(), account.getAmount());
            }
            else{
                double newAmount = account.getAmount() + balances.get(account.getClient());
                balances.put(account.getClient(), newAmount);
            }
        }
        Map.Entry<Client, Double> maxEntry = null;

        for (Map.Entry<Client, Double> entry : balances.entrySet())
        {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
            {
                maxEntry = entry;
            }
        }
        return maxEntry.getKey();
    }
}
