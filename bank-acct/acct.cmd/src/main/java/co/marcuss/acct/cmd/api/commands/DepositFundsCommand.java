package co.marcuss.acct.cmd.api.commands;

import co.marcuss.cqrs.core.commands.BaseCommand;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DepositFundsCommand extends BaseCommand {

    private BigDecimal amount;
}
