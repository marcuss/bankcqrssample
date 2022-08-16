package co.marcuss.acct.query.infrastructure.handlers;

import co.marcuss.acct.commons.events.AccountClosedEvent;
import co.marcuss.acct.commons.events.AccountOpenedEvent;
import co.marcuss.acct.commons.events.FundsDepositedEvent;
import co.marcuss.acct.commons.events.FundsWithdrawnEvent;
import co.marcuss.acct.query.domain.BankAccount;
import co.marcuss.acct.query.domain.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AccountEventHandler implements EventHandler {
    @Autowired
    private AccountRepository repository;

    @Override
    public void on(AccountOpenedEvent event) {
        var bankAccount = BankAccount.builder()
                .id(event.getId())
                .accountHolder(event.getAccountHolder())
                .balance(event.getOpeningBalance())
                .creationDate(LocalDate.now())
                .accountType(event.getAccountType())
                .build();
        repository.save(bankAccount);
    }

    @Override
    public void on(FundsDepositedEvent event) {
        var bankAccount = repository.findById(event.getId());
        if (bankAccount.isPresent()){
            bankAccount.ifPresent(e -> e.setBalance(
                    e.getBalance().add(event.getAmount())
            ));
            repository.save(bankAccount.get());
        }
    }

    @Override
    public void on(FundsWithdrawnEvent event) {
        var bankAccount = repository.findById(event.getId());
        if (bankAccount.isPresent()){
            bankAccount.ifPresent(e -> e.setBalance(
                    e.getBalance().subtract(event.getAmount())
            ));
        }
    }

    @Override
    public void on(AccountClosedEvent event) {
        repository.deleteById(event.getId());
    }
}