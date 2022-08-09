package co.marcuss.acct.cmd.domain;

import co.marcuss.acct.cmd.api.commands.OpenAccountCommand;
import co.marcuss.acct.commons.events.AccountClosedEvent;
import co.marcuss.acct.commons.events.AccountOpenedEvent;
import co.marcuss.acct.commons.events.FundsDepositedEvent;
import co.marcuss.acct.commons.events.FundsWithdrawnEvent;
import co.marcuss.cqrs.core.domain.AggregateRoot;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
public class AccountAggregate extends AggregateRoot {

    private Boolean active;
    private BigDecimal balance;

    public AccountAggregate(OpenAccountCommand command) {
        raiseEvent(AccountOpenedEvent.builder()
                .id(command.getId())
                .accountHolder(command.getAccountHolder())
                .createdDate(LocalDate.now())
                .acctType(command.getAcctType())
                .openingBalance(command.getOpeningBalance())
                .build());
    }

    public void apply(AccountOpenedEvent event) {
        this.id = event.getId();
        this.active = true;
        this.balance = event.getOpeningBalance();
    }

    public void depositFunds(BigDecimal amount) {
        if (!active) {
            throw new IllegalStateException("Funds can not be added to closed accounts");
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalStateException("Deposit must be greater tha zero");
        }

        raiseEvent(FundsDepositedEvent.builder()
                .id(this.getId())
                .amount(amount)
                .build()
        );
    }

    public void apply(FundsDepositedEvent event) {
        this.id = event.getId();
        this.balance.add(event.getAmount());
    }

    public void withdrawFunds(BigDecimal amount) {
        if (!active) {
            throw new IllegalStateException("Funds can not be withdrawn from closed accounts");
        }
        raiseEvent(FundsWithdrawnEvent.builder()
                .id(this.getId())
                .amount(amount)
                .build());
    }

    public void apply(FundsWithdrawnEvent event) {
        this.id = event.getId();
        this.balance.subtract(event.getAmount());
    }

    public void closeAccount() {
        if (!active) {
            throw new IllegalStateException("Account can not be close");
        }
        raiseEvent(AccountClosedEvent.builder()
                .id(this.getId())
                .build());
    }

    public void apply(AccountClosedEvent event) {
        this.id = event.getId();
        this.active = false;
    }
}
