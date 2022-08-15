package co.marcuss.acct.cmd.api.commands;

import co.marcuss.acct.commons.dto.AccountType;
import co.marcuss.cqrs.core.commands.BaseCommand;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OpenAccountCommand extends BaseCommand {

    private String accountHolder;
    private AccountType accountType;
    private BigDecimal openingBalance;

}
