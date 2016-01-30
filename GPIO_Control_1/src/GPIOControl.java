import java.sql.Date;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

/**
 * This example code demonstrates how to perform simple state control of a GPIO
 * pin on the Raspberry Pi.
 * 
 * @author Jens Braun
 */

public class GPIOControl {

	public static void main(String[] args) throws InterruptedException {

		// create gpio controller
		final GpioController gpio = GpioFactory.getInstance();

		// provision gpio pin #18 as an output pin and turn off
		final GpioPinDigitalOutput trigger = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_18, "Trigger", PinState.LOW);

		// provision gpio pin #24 as an input pin
		final GpioPinDigitalInput echo = gpio.provisionDigitalInputPin(RaspiPin.GPIO_24, "Echo");
		
		// define distance between bottom and top of the mailbox
		final int boxheight = 28;
		
		// define state of the mailbox
		boolean empty = true;
		
		// ################
		
		// turn on Trigger
		trigger.high();
		
		// turn off Trigger after 1ms
		Thread.sleep(1);
		trigger.low();
		
		// define date variables
		Date start = new Date(0);
		Date stop = new Date(0);
		
//		/*
//		 * TODO
//		 */
//		
////		// save starting time
////		while (echo.getState() = "LOW") {
////			start = setDate
////		}
////		
////		// save stopping time
////		while (echo.getState() = "HIGH") {
////					stop = setDate
////		}
////			
////		// Time difference between start and stop
////		float timeElapsed = stop - start
//		
//		// Multiply with 34300 cm/s and devide by 2 (with return)
//		float distance = (timeElapsed * 34300) / 2;
		
		// ++++ noch ändern !!! ++++
		int distance = 0;
		
		// Output
		System.out.println(distance);
		
		// ################
		
		
		// ################
		
		if (empty = true) {
			if (distance < boxheight) {
				// ( save in db )
				// send mail: "Post im Briefkasten: " + Date
				empty = false;
			}
		} else if (empty = false) {
			if (distance >= boxheight) {
				// ( save in db )
				// send mail: "Briefkasten geleert: " + Date
				empty = true;
			}
		}
		
		// ################
		
		

		// stop all GPIO activity/threads by shutting down the GPIO controller
		// (this method will forcefully shutdown all GPIO monitoring threads and
		// scheduled tasks)
		gpio.shutdown();
	}
}
