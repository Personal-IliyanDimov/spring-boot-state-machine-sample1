package org.imd.statemachine;
import org.imd.statemachine.config.ex1.Events;
import org.imd.statemachine.config.ex1.Ex1Service;
import org.imd.statemachine.config.ex1.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.statemachine.StateMachine;

@SpringBootApplication
public class StateMachineSample1Application {

	public static void main(String[] args) {
		SpringApplication.run(StateMachineSample1Application.class, args);
	}

	@Autowired
	private Ex1Service ex1Service;

	@EventListener(ApplicationReadyEvent.class)
	public void playEx1() {
		ex1Service.runExample();
	}

}