package co.marcuss.acct.cmd.infrastructure;

import co.marcuss.cqrs.core.commands.BaseCommand;
import co.marcuss.cqrs.core.commands.CommandHandlerMethod;
import co.marcuss.cqrs.core.infrastructure.CommandDispatcher;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AccountCommandDispatcher implements CommandDispatcher {
    private final Map<Class<? extends BaseCommand>, Optional<List<CommandHandlerMethod>>> routes = new HashMap<>();

    @Override
    public <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler) {
        var handlers =
                routes.computeIfAbsent(type, e -> Optional.of(new LinkedList<>()));
        //noinspection OptionalGetWithoutIsPresent
        handlers.get().add(handler);
    }

    @Override
    public void send(BaseCommand command) {
        var handlers = routes.get(command.getClass())
                .orElseThrow(() -> new RuntimeException("No command handler was registered!"));
        if (handlers.size() > 1) {
            throw new RuntimeException("We can not send the command to more than one handler");
        }
        handlers.get(0).handle(command);
    }
}
