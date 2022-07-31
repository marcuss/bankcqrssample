package co.marcuss.acct.cmd.api.commands;

import co.marcuss.cqrs.core.commands.BaseCommand;

public class CloseAccountCommand extends BaseCommand {

    public CloseAccountCommand(String id) {
        super(id);
    }
}
