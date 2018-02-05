package org.usfirst.frc.team868.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class LaunchBall extends CommandGroup {
	
	private static LaunchBall instance;

    public LaunchBall() {
    	addSequential(new Collect(1));
    	addSequential(new WaitCommand(1));
    	addSequential(new Collect(0));
    }
    
    public static LaunchBall getInstance(){
    	return instance == null? instance = new LaunchBall() : instance;
    }
    
    public void cancel(){
    	super.cancel();
    	(new ShooterMotor(0)).start();
    	(new Collect(0)).start();
    }
}
