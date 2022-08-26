package co.marcuss.cqrs.core.domain;

import co.marcuss.cqrs.core.events.BaseEvent;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
public class AggregateRoot {

    protected String id;
    @Setter
    private int version = -1;

    private final List<BaseEvent> changes = new ArrayList<>();

    public List<BaseEvent> getUncommittedChanges() {
        return changes;
    }

    public void markChangesAsCommitted() {
        this.changes.clear();
    }

    protected void applyChange(BaseEvent event, Boolean isNew) {
        try {
            var method = getClass().getDeclaredMethod("apply", event.getClass());
            method.setAccessible(true);
            method.invoke(this, event);
        }
        catch (NoSuchMethodException nsme) {
            log.warn("No apply method present in: {}", this.getClass().getName());
        }
        catch (InvocationTargetException | IllegalAccessException e) {
            log.error("Error applying event to aggregate {}, Exception: {}", this.getClass().getName(), e);
        }
        finally {
            if (isNew) {
                changes.add(event);
            }
        }
    }

    public void raiseEvent(BaseEvent event) {
        applyChange(event, true);
    }

    public void replyEvents(List<BaseEvent> events) {
        events.stream().forEach(e-> applyChange(e, false));
    }
}
