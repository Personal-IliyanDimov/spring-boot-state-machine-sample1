package org.imd.statemachine.config.ex1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

@Service
public class Ex1Service {

    @Autowired
    private StateMachine<States, Events> stateMachine;

    public void runExample() {
        stateMachine.sendEvent(Events.E1);
        stateMachine.sendEvent(Events.E2);
    }
}
