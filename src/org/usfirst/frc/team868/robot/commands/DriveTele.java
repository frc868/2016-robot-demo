package org.usfirst.frc.team868.robot.commands;

import org.usfirst.frc.team868.robot.OI;
import org.usfirst.frc.team868.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTele extends Command {
	
	DriveTrain drive;
	public double powerL = 0;
	public double powerR = 0;

    public DriveTele() {
        drive = DriveTrain.getInstance();
        requires(drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(OI.sideSeatDisabled){
    		powerL = (Math.pow(OI.getDriverRightTrigger(), 2) 
        			- Math.pow(OI.getDriverLeftTrigger(), 2))
        			- Math.pow(OI.getDriverLeftStickX(), 2);
    		powerR =(Math.pow(OI.getDriverRightTrigger(), 2) 
        			- Math.pow(OI.getDriverLeftTrigger(), 2)) 
        			+ Math.pow(OI.getDriverLeftStickX(), 2);
    	}
    	drive.setPower(0, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
