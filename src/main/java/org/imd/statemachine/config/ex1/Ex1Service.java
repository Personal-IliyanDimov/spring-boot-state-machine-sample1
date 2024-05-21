package org.imd.statemachine.config.ex1;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.stereotype.Service;

import java.util.EnumSet;

@Service
public class Ex1Service {

    private StateMachine<States, Events> stateMachine;

    @PostConstruct
    public void postConstruct() throws Exception {
        final StateMachineBuilder.Builder<States, Events> builder
                = StateMachineBuilder.builder();
         builder.configureConfiguration()
                .withConfiguration()
                .autoStartup(true)
                .listener(listener());

        builder.configureStates().withStates()
                .initial(States.SI)
                .states(EnumSet.allOf(States.class));

        builder.configureTransitions()
                .withExternal()
                .source(States.SI).target(States.S1).event(Events.E1)
                .and()
                .withExternal()
                .source(States.S1).target(States.S2).event(Events.E2);

        this.stateMachine = builder.build();
    }

    private StateMachineListener<States, Events> listener() {
        return new StateMachineListenerAdapter<States, Events>() {
            @Override
            public void stateChanged(State<States, Events> from, State<States, Events> to) {
                System.out.println(String.format("State change from %s to %s" ,
                        from != null ? from.getId() : "null",
                        to != null ? to.getId() : "null"));
            }
        };
    }

    public void runExample() {
        stateMachine.sendEvent(Events.E1);
        stateMachine.sendEvent(Events.E2);
    }
}
