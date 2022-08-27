package co.marcuss.acct.cmd.api.controller;

import co.marcuss.acct.cmd.api.commands.OpenAccountCommand;
import co.marcuss.acct.cmd.api.dto.OpenAccountResponse;
import co.marcuss.acct.commons.dto.BaseResponse;
import co.marcuss.cqrs.core.infrastructure.CommandDispatcher;
import co.marcuss.cqrs.core.utils.UUIDGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j(topic = "OpenAccount")
@RestController
@RequestMapping(path = "api/v1/open-bank-account")
public class OpenAccountController {

    private final CommandDispatcher commandDispatcher;

    @Autowired
    public OpenAccountController(CommandDispatcher commandDispatcher) {
        this.commandDispatcher = commandDispatcher;
    }

    @PostMapping
    public ResponseEntity<BaseResponse> openAccount(@RequestBody OpenAccountCommand command) {
        var id = UUIDGenerator.generateUIID(command.getAccountType().toString());
        command.setId(id);
        try {
            commandDispatcher.send(command);
            return new ResponseEntity<>(
                    new OpenAccountResponse("Account Opened Successfully", id),
                    HttpStatus.CREATED
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
            log.error("Error opening an account.", e);
            log.error("Error opening an account. id: {}", id);
            return new ResponseEntity<>(
                    new OpenAccountResponse("Server failed to fulfill the request.", null),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
