package co.marcuss.acct.cmd.api.commands;

public interface CommandHandler {

    void handle(OpenAccountCommand command);

    void handle(DepositFundsCommand command);

    void handle(WithdrawFundsCommand command);

    void handle(CloseAccountCommand command);
}
