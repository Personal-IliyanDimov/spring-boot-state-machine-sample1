package org.imd.statemachine.config.ex2;

import jakarta.annotation.PostConstruct;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.stereotype.Service;

import java.util.EnumSet;

@Service
public class Ex2Service {

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
            .initial(States.S1)
            .state(States.S2)
            .and()
                .withStates()
                .parent(States.S2)
                .initial(States.S2I)
                .state(States.S21)
                .end(States.S2F)
            .and()
                .withStates()
                .parent(States.S2)
                .initial(States.S3I)
                .state(States.S31)
                .end(States.S3F);

        builder.configureTransitions()
            .withExternal()
            .source(States.S1).target(States.S2).event(Events.E1)
            .and()
            .withExternal()
            .source(States.S2I).target(States.S21).event(Events.E2)
            .and()
            .withExternal()
            .source(States.S21).target(States.S2F).event(Events.E2)
            .and()
            .withExternal()
            .source(States.S3I).target(States.S31).event(Events.E3)
            .and()
            .withExternal()
            .source(States.S31).target(States.S3F).event(Events.E3);

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

            @Override
            public void stateEntered(State<States, Events> state) {
                System.out.println(String.format("State(s) entered: %s" ,
                        state.getIds() != null ? state.getIds() : "null"));
            }

            @Override
            public void stateExited(State<States, Events> state) {
                System.out.println(String.format("State(s) exited: %s" ,
                        state.getIds() != null ? state.getIds() : "null"));
            }
        };
    }

    public void runExample() {
        System.out.println("E1");
        stateMachine.sendEvent(Events.E1);
        System.out.println("E2");
        stateMachine.sendEvent(Events.E2);
        System.out.println("E3");
        stateMachine.sendEvent(Events.E3);
        System.out.println("E2");
        stateMachine.sendEvent(Events.E2);
        System.out.println("E3");
        stateMachine.sendEvent(Events.E3);
    }
}
