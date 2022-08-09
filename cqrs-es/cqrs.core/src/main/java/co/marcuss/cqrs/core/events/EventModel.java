package co.marcuss.cqrs.core.events;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@Builder
@Document(collation = "eventStore")
public class EventModel {

    @Id
    private String id;

    private LocalDateTime timeStamp;

    private String aggregateIdentifier;

    private String aggregateTye;

    private int version;

    private BaseEvent eventDate;

}
