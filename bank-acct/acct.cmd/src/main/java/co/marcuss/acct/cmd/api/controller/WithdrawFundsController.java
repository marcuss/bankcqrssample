package co.marcuss.acct.cmd.api.controller;

import co.marcuss.acct.cmd.api.commands.WithdrawFundsCommand;
import co.marcuss.acct.cmd.api.dto.OpenAccountResponse;
import co.marcuss.acct.commons.dto.BaseResponse;
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
@RequestMapping(path = "api/v1/withdraw-funds")
public class WithdrawFundsController {

    private final CommandDispatcher commandDispatcher;

    @Autowired
    public WithdrawFundsController(CommandDispatcher commandDispatcher) {
        this.commandDispatcher = commandDispatcher;
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<BaseResponse> withdrawFunds(@PathVariable(name = "id") String id,
                                                      @RequestBody WithdrawFundsCommand command) {

        try {
            command.setId(id);
            commandDispatcher.send(command);
            return new ResponseEntity<>(
                    new BaseResponse("Withdraw funds request completed successfully"),
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
            log.error("Error in withdraw funds request.", e);
            log.error("Error in withdraw fund request. id: {}", id);
            return new ResponseEntity<>(
                    new OpenAccountResponse("Server failed to fulfill the request.", null),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

    }

}
