package co.marcuss.cqrs.core.infrastructure;

import co.marcuss.cqrs.core.commands.BaseCommand;
import co.marcuss.cqrs.core.commands.CommandHandlerMethod;

public interface CommandDispatcher {
    <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);
    void send(BaseCommand command);
}
