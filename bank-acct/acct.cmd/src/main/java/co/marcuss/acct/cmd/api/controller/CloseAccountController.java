package co.marcuss.acct.cmd.api.controller;

import co.marcuss.acct.cmd.api.commands.CloseAccountCommand;
import co.marcuss.acct.cmd.api.dto.OpenAccountResponse;
import co.marcuss.acct.commons.dto.BaseResponse;
import co.marcuss.cqrs.core.infrastructure.CommandDispatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j(topic = "CloseAccount")
@RestController
@RequestMapping(path = "api/v1/close-account")
public class CloseAccountController {

    private final CommandDispatcher commandDispatcher;

    @Autowired
    public CloseAccountController(CommandDispatcher commandDispatcher) {
        this.commandDispatcher = commandDispatcher;
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<BaseResponse> closeAccount(@PathVariable(name = "id") String id) {

        try {
            commandDispatcher.send(new CloseAccountCommand(id));
            return new ResponseEntity<>(
                    new BaseResponse("Close account request completed successfully"),
                    HttpStatus.OK
            );
        }
        catch (IllegalStateException e) {
            log.warn("Bad Request Caused By: {}", e.getMessage());
            return new ResponseEntity<>(
                    new OpenAccountResponse("Bad Request Caused By: " + e.getMessage(), id),
                    HttpStatus.BAD_REQUEST
            );
        }
        catch (Exception e) {
            log.error("Error in close account request.", e);
            log.error("Error in close account request. id: {}", id);
            return new ResponseEntity<>(
                    new OpenAccountResponse("Server failed to fulfill the request.", null),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

    }
}
