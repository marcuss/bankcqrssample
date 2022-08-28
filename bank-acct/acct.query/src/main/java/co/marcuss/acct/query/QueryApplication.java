package co.marcuss.acct.query;

import co.marcuss.acct.query.api.queries.FindAccountByHolderQuery;
import co.marcuss.acct.query.api.queries.FindAccountByIdQuery;
import co.marcuss.acct.query.api.queries.FindAccountWithBalanceQuery;
import co.marcuss.acct.query.api.queries.FindAllAccountsQuery;
import co.marcuss.acct.query.api.queries.QueryHandler;
import co.marcuss.cqrs.core.infrastructure.QueryDispatcher;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QueryApplication {

    private final QueryDispatcher queryDispatcher;

    private final QueryHandler queryHandler;

    @Autowired
    public QueryApplication(QueryDispatcher queryDispatcher, QueryHandler queryHandler) {
        this.queryDispatcher = queryDispatcher;
        this.queryHandler = queryHandler;
    }

    public static void main(String[] args) {
        SpringApplication.run(QueryApplication.class, args);
    }

    @PostConstruct
    private void registerHandlers() {
        queryDispatcher.registerHandler(FindAllAccountsQuery.class, queryHandler::handle);
        queryDispatcher.registerHandler(FindAccountByIdQuery.class, queryHandler::handle);
        queryDispatcher.registerHandler(FindAccountByHolderQuery.class, queryHandler::handle);
        queryDispatcher.registerHandler(FindAccountWithBalanceQuery.class, queryHandler::handle);
    }
}
