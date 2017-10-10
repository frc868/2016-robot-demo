package org.usfirst.frc.team868.robot;

import org.usfirst.frc.team868.robot.commands.CollectorMotor;
import org.usfirst.frc.team868.robot.commands.CollectorToggle;
import org.usfirst.frc.team868.robot.commands.DisableSideSeat;
import org.usfirst.frc.team868.robot.commands.DriveTele;
import org.usfirst.frc.team868.robot.commands.LaunchBall;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// kill me pleas

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	public static ControllerMap driver;
	public static ControllerMap sideSeat;
	private static SendableChooser driverControlChoice;
	private static SendableChooser sideSeatControlChoice;
	public static boolean sideSeatDisabled = false;
	
	private static int collecter;     static Button col;
	private static int launchBall;    static Button LB;
	private static int collecterMoter;static Button CM;
	private static int disableSide;   static Button DS;
	
	public OI(){
		initSmartDashboard();
		driverControlChoice = createDriverChoices();
		sideSeatControlChoice = createSideSeatChoices();
		driverController();
		sideSeatController();
	}
	
	public static void driverController(){
		
		int choice = (int) driverControlChoice.getSelected();
		
		if(choice == 0)driver = new ControllerMap(new Joystick(0), ControllerMap.LOGITECH, true);
		else if(choice == 1)driver = new ControllerMap(new Joystick(0), ControllerMap.XBOX1, true);
		else if(choice == 2)driver = new ControllerMap(new Joystick(0), ControllerMap.XBOX360, true);
		else driver = new ControllerMap(new Joystick(0), ControllerMap.XBOX1, true);
		initDriver();
	}
	
	public static void sideSeatController(){
		
		int choice = (int) sideSeatControlChoice.getSelected();
		
		if(choice == 0)sideSeat = new ControllerMap(new Joystick(1), ControllerMap.LOGITECH, true);
		else if(choice == 1)sideSeat = new ControllerMap(new Joystick(1), ControllerMap.XBOX1, true);
		else if(choice == 2)sideSeat = new ControllerMap(new Joystick(1), ControllerMap.XBOX360, true);
		else sideSeat = new ControllerMap(new Joystick(1), ControllerMap.XBOX1, true);
		initSideSeat();
	}
	
	public static void initDriver(){
		collecter = ControllerMap.RB;
		launchBall = ControllerMap.Y;
		collecterMoter = ControllerMap.B;
		disableSide = ControllerMap.START;
		
		col = driver.createButton(collecter);
		col.whenPressed(new CollectorToggle());
		
		LB = driver.createButton(launchBall);
		LB.whenPressed(new LaunchBall());
		
		CM = driver.createButton(collecterMoter);
		CM.whileHeld(new CollectorMotor());
		
		DS = driver.createButton(disableSide);
		DS.whenPressed(new DisableSideSeat());
	}
	
	public static void initSideSeat(){
		collecter = ControllerMap.RB;
		launchBall = ControllerMap.Y;
		collecterMoter = ControllerMap.B;
		
		col = sideSeat.createButton(collecter);
		col.whenPressed(new CollectorToggle());
		
		LB = sideSeat.createButton(launchBall);
		LB.whenPressed(new LaunchBall());
		
		CM = sideSeat.createButton(collecterMoter);
		CM.whileHeld(new CollectorMotor());
	}
	
	public static double getDriverLeftStickX(){
		return driver.getLeftStickX();
	}
	
	public static double getDriverLeftStickY(){
		return driver.getLeftStickY();
	}
	
	public static double getDriverRightStickX(){
		return driver.getRightStickX();
	}
	
	public static double getDriverRightStickY(){
		return driver.getRightStickY();
	}
	
	public static double getDriverLeftTrigger(){
		return driver.getLeftTrigger();
	}
	
	public static double getDriverRightTrigger(){
		return driver.getRightTrigger();
	}
	
	public static double getSideSeatLeftStickX(){
		return sideSeat.getLeftStickX();
	}
	
	public static double getSideSeatLeftStickY(){
		return sideSeat.getLeftStickY();
	}
	
	public static double getSideSeatRightStickX(){
		return sideSeat.getRightStickX();
	}
	
	public static double getSideSeatRightStickY(){
		return sideSeat.getRightStickY();
	}
	
	public static double getSideSeatLeftTrigger(){
		return sideSeat.getLeftTrigger();
	}
	
	public static double getSideSeatRightTrigger(){
		return sideSeat.getRightTrigger();
	}
	
	public static void setDriverRumble(boolean rumble, int rumbleType){
		if(rumble)driver.StartRumble(rumbleType);
		else if(!rumble)driver.stopRumble(rumbleType);
	}
	
	public static void setSideSeatRumble(boolean rumble, int rumbleType){
		if(rumble)sideSeat.StartRumble(rumbleType);
		else if(!rumble)sideSeat.stopRumble(rumbleType);
	}
	
	public void initSmartDashboard(){
		
	}
	
	public static void updateSmartDashboard(){
		SmartDashboard.putBoolean("side seat disabled?", sideSeatDisabled);
		SmartDashboard.putNumber("power right input %", DriveTele.getPowerR());
		SmartDashboard.putNumber("power left input %", DriveTele.getPowerL());
	}
	
	//displaying controller choices for Driver and SideSeat
	
	private SendableChooser createDriverChoices(){
		SendableChooser send = new SendableChooser();
		send.addDefault("XBOX1", new Integer(1));
		send.addObject("XBOX360", new Integer(2));
		send.addObject("Logitech", new Integer(0));
		SmartDashboard.putData("driver controller type", send);
		return send;
	}
	
	private SendableChooser createSideSeatChoices(){
		SendableChooser SSsend = new SendableChooser();
		SSsend.addDefault("XBOX1", new Integer(1));
		SSsend.addObject("XBOX360", new Integer(2));
		SSsend.addObject("Logitech", new Integer(0));
		SmartDashboard.putData("driver controller type", SSsend);
		return SSsend;
	}
	
}
