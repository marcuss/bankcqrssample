package co.marcuss.acct.query.api.controller;

import co.marcuss.acct.query.api.dto.AccountLookupResponse;
import co.marcuss.acct.query.domain.BankAccount;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AccountHttpResponseBuilder {

    default ResponseEntity<AccountLookupResponse> buildSuccessfulResponse(List<BankAccount> bankAccounts) {
        return buildSuccessfulResponse(bankAccounts, HttpStatus.OK);
    }

    default ResponseEntity<AccountLookupResponse> buildSuccessfulResponse(List<BankAccount> bankAccounts, HttpStatus notFoundStatus) {
        if (bankAccounts.isEmpty()) {
            return new ResponseEntity<>(new AccountLookupResponse("No accounts found"), notFoundStatus);
        }
        return new ResponseEntity<>(
                AccountLookupResponse.builder().
                        accounts(bankAccounts)
                        .message("Successfully returned {0} accounts.", bankAccounts.size())
                        .build(),
                HttpStatus.OK);
    }

    default ResponseEntity<AccountLookupResponse> buildClientErrorResponse(IllegalStateException e) {
        return new ResponseEntity<>(
                AccountLookupResponse.builder()
                        .message("Bad Request Caused By: {}", e.getMessage())
                        .build(),
                HttpStatus.BAD_REQUEST);
    }

    default ResponseEntity<AccountLookupResponse> buildServerErrorResponse(Exception e) {
        return new ResponseEntity<>(
                new AccountLookupResponse("Server failed to fulfill the request."),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
