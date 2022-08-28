package co.marcuss.acct.cmd;

import co.marcuss.acct.cmd.api.commands.CloseAccountCommand;
import co.marcuss.acct.cmd.api.commands.CommandHandler;
import co.marcuss.acct.cmd.api.commands.DepositFundsCommand;
import co.marcuss.acct.cmd.api.commands.OpenAccountCommand;
import co.marcuss.acct.cmd.api.commands.WithdrawFundsCommand;
import co.marcuss.cqrs.core.infrastructure.CommandDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class CommandApplication {

    private final CommandDispatcher commandDispatcher;

    private final CommandHandler commandHandler;

    @Autowired //Todo: change project to use @Inject
    public CommandApplication(CommandDispatcher commandDispatcher, CommandHandler commandHandler) {
        this.commandDispatcher = commandDispatcher;
        this.commandHandler = commandHandler;
    }

    public static void main(String[] args) {
        SpringApplication.run(CommandApplication.class, args);
    }

    @PostConstruct
    public void registerHandlers() {
        commandDispatcher.registerHandler(OpenAccountCommand.class, commandHandler::handle);
        commandDispatcher.registerHandler(DepositFundsCommand.class, commandHandler::handle);
        commandDispatcher.registerHandler(WithdrawFundsCommand.class, commandHandler::handle);
        commandDispatcher.registerHandler(CloseAccountCommand.class, commandHandler::handle);
    }
}
