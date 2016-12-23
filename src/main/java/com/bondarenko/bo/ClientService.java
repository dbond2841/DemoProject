package com.bondarenko.bo;

import com.bondarenko.model.Account;
import com.bondarenko.model.Client;
import java.util.List;

public interface ClientService {

    double getClientBalance(Client client);

    Client getClientWithMaxBalance(List<Account> accounts);
}
