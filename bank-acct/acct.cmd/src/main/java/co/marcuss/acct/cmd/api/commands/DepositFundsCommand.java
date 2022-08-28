package co.marcuss.acct.cmd.api.commands;

import co.marcuss.cqrs.core.commands.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class DepositFundsCommand extends BaseCommand {

    private BigDecimal amount;
}
