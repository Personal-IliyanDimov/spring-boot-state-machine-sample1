package org.imd.statemachine;

import org.imd.statemachine.config.ex1.Ex1Service;
import org.imd.statemachine.config.ex2.Ex2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class StateMachineSample1Application {

	public static void main(String[] args) {
		SpringApplication.run(StateMachineSample1Application.class, args);
	}

	@Autowired
	private Ex1Service ex1Service;

	@Autowired
	private Ex2Service ex2Service;


	@EventListener(ApplicationReadyEvent.class)
	public void play() {
		ex2Service.runExample();
	}

}