package org.idea.hackaton1.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

@Getter
public class NewEmailCreated extends ApplicationEvent {
    public NewEmailCreated(Object source, Clock clock) {
        super(source, clock);
    }
}
