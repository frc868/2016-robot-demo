package org.usfirst.frc.team868.robot;

import org.usfirst.frc.team868.robot.commands.Collect;
import org.usfirst.frc.team868.robot.commands.CollectorToggle;
import org.usfirst.frc.team868.robot.commands.DisableSideSeat;
import org.usfirst.frc.team868.robot.commands.DriveTele;
import org.usfirst.frc.team868.robot.commands.LaunchBall;
import org.usfirst.frc.team868.robot.commands.ShooterMotor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OI {
	
	public static ControllerMap driver;
	public static ControllerMap sideSeat;
	private static SendableChooser<Integer> driverControlChoice;
	private static SendableChooser<Integer> sideSeatControlChoice;
	public static boolean sideSeatDisabled = false;
	
	private static int DCollecter;     static Button Dcol;
	private static int DLaunchBall;    static Button DLB;
	private static int DCollecterMoter;static Button DCM;
	private static int DDisableSide;   static Button DDS;
	private static int DShooter;       static Button DSM;
	
	private static int SSCollecter;     static Button SScol;
	private static int SSLaunchBall;    static Button SSLB;
	private static int SSCollecterMoter;static Button SSCM;
	private static int SSShooter;       static Button SSSM;
	
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
		DCollecter = ControllerMap.RB;
		DLaunchBall = ControllerMap.Y;
		DCollecterMoter = ControllerMap.B;
		DDisableSide = ControllerMap.START;
		DShooter = ControllerMap.A;
		
		Dcol = driver.createButton(DCollecter);
		Dcol.whenPressed(new CollectorToggle());
		
		DLB = driver.createButton(DLaunchBall);
		DLB.whenPressed(new LaunchBall());
		
		DCM = driver.createButton(DCollecterMoter);
		DCM.whenPressed(new Collect(0.5));
		
		DDS = driver.createButton(DDisableSide);
		DDS.whenPressed(new DisableSideSeat());
		
		DSM = driver.createButton(DShooter);
		DSM.whenPressed(new ShooterMotor(5));
		DSM.whenReleased(new ShooterMotor(0));
	}
	
	public static void initSideSeat(){
		SSCollecter = ControllerMap.RB;
		SSLaunchBall = ControllerMap.Y;
		SSCollecterMoter = ControllerMap.B;
		SSShooter = ControllerMap.A;
		
		SScol = sideSeat.createButton(SSCollecter);
		SScol.whenPressed(new Collect(0.5));
		
		SSLB = sideSeat.createButton(SSLaunchBall);
		SSLB.whenPressed(new LaunchBall());
		
		SSCM = sideSeat.createButton(SSCollecterMoter);
		SSCM.whenPressed(new Collect(0.5));

		SSSM = driver.createButton(SSShooter);
		SSSM.whenPressed(new ShooterMotor(5));
		SSSM.whenReleased(new ShooterMotor(0));
	}
	
	public static double getDriverLeftStickX(){return driver.getLeftStickX();}
	
	public static double getDriverLeftStickY(){return driver.getLeftStickY();}
	
	public static double getDriverRightStickX(){return driver.getRightStickX();}
	
	public static double getDriverRightStickY(){return driver.getRightStickY();}
	
	public static double getDriverLeftTrigger(){return driver.getLeftTrigger();}
	
	public static double getDriverRightTrigger(){return driver.getRightTrigger();}
	
	public static double getSideSeatLeftStickX(){return sideSeat == null ? sideSeat.getLeftStickX() : 0;}
	
	public static double getSideSeatLeftStickY(){return sideSeat == null ? sideSeat.getLeftStickY() : 0;}
	
	public static double getSideSeatRightStickX(){return sideSeat == null ? sideSeat.getRightStickX() : 0;}
	
	public static double getSideSeatRightStickY(){return sideSeat == null ? sideSeat.getRightStickY() : 0;}
	
	public static double getSideSeatLeftTrigger(){return sideSeat == null ? sideSeat.getLeftTrigger() : 0;}
	
	public static double getSideSeatRightTrigger(){return sideSeat == null ? sideSeat.getRightTrigger() : 0;}
	
	public static void setDriverRumble(boolean rumble, int rumbleType){
		if(rumble)driver.StartRumble(rumbleType);
		else if(!rumble)driver.stopRumble(rumbleType);
	}
	
	public static void setSideSeatRumble(boolean rumble, int rumbleType){
		if(rumble)sideSeat.StartRumble(rumbleType);
		else if(!rumble)sideSeat.stopRumble(rumbleType);
	}
	
	public static void disableSideSeat(){
		sideSeatDisabled = true;
		SScol = null;
		SSLB = null;
		SSCM = null;
		SSSM = null;
		sideSeat = null;
	}
	
	public static void enableSideSeat(){
		sideSeatController();
		sideSeatDisabled = false;
	}
	
	public void initSmartDashboard(){
		
	}
	
	public static void updateSmartDashboard(){
		SmartDashboard.putBoolean("side seat disabled?", sideSeatDisabled);
		SmartDashboard.putNumber("power right input %", DriveTele.getPowerR());
		SmartDashboard.putNumber("power left input %", DriveTele.getPowerL());
	}
	
	//displaying controller choices for Driver and SideSeat
	
	private SendableChooser<Integer> createDriverChoices(){
		SendableChooser<Integer> send = new SendableChooser<Integer>();
		send.addDefault("XBOX1", new Integer(1));
		send.addObject("XBOX360", new Integer(2));
		send.addObject("Logitech", new Integer(0));
		SmartDashboard.putData("driver controller type", send);
		return send;
	}
	
	private SendableChooser<Integer> createSideSeatChoices(){
		SendableChooser<Integer> SSsend = new SendableChooser<Integer>();
		SSsend.addDefault("XBOX1", new Integer(1));
		SSsend.addObject("XBOX360", new Integer(2));
		SSsend.addObject("Logitech", new Integer(0));
		SmartDashboard.putData("driver controller type", SSsend);
		return SSsend;
	}
	
}
