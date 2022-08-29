package co.marcuss.acct.query.api.controller;

import co.marcuss.acct.query.api.dto.AccountLookupResponse;
import co.marcuss.acct.query.api.dto.EqualityType;
import co.marcuss.acct.query.api.queries.FindAccountByHolderQuery;
import co.marcuss.acct.query.api.queries.FindAccountByIdQuery;
import co.marcuss.acct.query.api.queries.FindAccountWithBalanceQuery;
import co.marcuss.acct.query.api.queries.FindAllAccountsQuery;
import co.marcuss.acct.query.domain.BankAccount;
import co.marcuss.cqrs.core.infrastructure.QueryDispatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "api/v1/bank-account")
public class AccountLookupController implements AccountHttpResponseBuilder {


    private final QueryDispatcher queryDispatcher;

    @Autowired
    public AccountLookupController(QueryDispatcher queryDispatcher) {
        this.queryDispatcher = queryDispatcher;
    }

    @GetMapping
    public ResponseEntity<AccountLookupResponse> getAllAccounts() {
        FindAllAccountsQuery query = new FindAllAccountsQuery();
        try {
            List<BankAccount> bankAccounts = queryDispatcher.sendQuery(query);
            return buildSuccessfulResponse(bankAccounts, HttpStatus.NOT_FOUND);
        }
        catch (IllegalStateException e) {
            log.warn("Bad Request Caused By: {}", e.getMessage());
            return buildClientErrorResponse(e);
        }
        catch (Exception e) {
            log.error("Error in account lookup request.", e);
            return buildServerErrorResponse(e);
        }
    }

    @GetMapping(path = "/id/{id}")
    public ResponseEntity<AccountLookupResponse> getAccountsById(@PathVariable String id) {
        FindAccountByIdQuery query = new FindAccountByIdQuery(id);
        try {
            List<BankAccount> bankAccounts = queryDispatcher.sendQuery(query);
            return buildSuccessfulResponse(bankAccounts);
        }
        catch (IllegalStateException e) {
            log.warn("Bad Request Caused By: {}", e.getMessage());
            return buildClientErrorResponse(e);
        }
        catch (Exception e) {
            log.error("Error in account lookup request.", e);
            return buildServerErrorResponse(e);
        }
    }

    @GetMapping(path = "/holder/{holder}")
    public ResponseEntity<AccountLookupResponse> getAccountsByHolder(@PathVariable String holder) {
        FindAccountByHolderQuery query = new FindAccountByHolderQuery(holder);
        try {
            List<BankAccount> bankAccounts = queryDispatcher.sendQuery(query);
            return buildSuccessfulResponse(bankAccounts);
        }
        catch (IllegalStateException e) {
            log.warn("Bad Request Caused By: {}", e.getMessage());
            return buildClientErrorResponse(e);
        }
        catch (Exception e) {
            log.error("Error in account lookup request.", e);
            return buildServerErrorResponse(e);
        }
    }

    @GetMapping(path = "/withBalance/{equalityType}/{balance}")
    public ResponseEntity<AccountLookupResponse> getAccountWithBalance(@PathVariable(value = "equalityType") EqualityType equalityType, @PathVariable(value = "balance") BigDecimal balance) {
        FindAccountWithBalanceQuery query = new FindAccountWithBalanceQuery(equalityType, balance);
        try {
            List<BankAccount> bankAccounts = queryDispatcher.sendQuery(query);
            return buildSuccessfulResponse(bankAccounts);
        }
        catch (IllegalStateException e) {
            log.warn("Bad Request Caused By: {}", e.getMessage());
            return buildClientErrorResponse(e);
        }
        catch (Exception e) {
            log.error("Error in account lookup request.", e);
            return buildServerErrorResponse(e);
        }
    }
}
