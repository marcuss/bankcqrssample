package co.marcuss.acct.cmd.api.controller;

import co.marcuss.acct.cmd.api.commands.DepositFundsCommand;
import co.marcuss.acct.cmd.api.dto.OpenAccountResponse;
import co.marcuss.acct.commons.dto.BaseResponse;
import co.marcuss.cqrs.core.exceptions.AggregateNotFoundException;
import co.marcuss.cqrs.core.infrastructure.CommandDispatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j(topic = "DepositFunds")
@RestController
@RequestMapping(path = "api/v1/deposit-funds")
public class DepositFundsController {

    @Autowired
    private final CommandDispatcher commandDispatcher;

    public DepositFundsController(CommandDispatcher commandDispatcher) {
        this.commandDispatcher = commandDispatcher;
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<BaseResponse>  depositFunds(@PathVariable(name = "id") String id,
                                                      @RequestBody DepositFundsCommand command) {

        try {
            command.setId(id);
            commandDispatcher.send(command);
            return new ResponseEntity<>(
                    new BaseResponse("Funds deposited request completed successfully"),
                    HttpStatus.OK
            );
        }
        catch (IllegalStateException e) {
            log.warn("Bad Request Caused By: {}", e.getMessage());
            return new ResponseEntity<>(
                    new OpenAccountResponse("Bad Request Caused By: "+ e.getMessage(), id),
                    HttpStatus.BAD_REQUEST
            );
        }
        catch (Exception e) {
            log.error("Error in deposit funds request.", e);
            log.error("Error in deposit fund request. id: {}", id);
            return new ResponseEntity<>(
                    new OpenAccountResponse("Server failed to fulfill the request.", null),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

    }

}
