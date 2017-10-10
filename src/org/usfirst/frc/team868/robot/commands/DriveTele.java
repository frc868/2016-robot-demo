package org.usfirst.frc.team868.robot.commands;

import org.usfirst.frc.team868.robot.OI;
import org.usfirst.frc.team868.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTele extends Command {
	
	DriveTrain drive;
	public static double powerL = 0;
	public static double powerR = 0;

    public DriveTele() {
        drive = DriveTrain.getInstance();
        requires(drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double brake = Math.pow(OI.getDriverLeftTrigger(), 2);
    	double gas = Math.pow(OI.getDriverRightTrigger(), 2);
    	double turn = OI.getDriverLeftStickX() >= 0 ? -Math.pow(OI.getDriverLeftStickX(), 2) : Math.pow(OI.getDriverLeftStickX(), 2);
    	double ssBrake = Math.pow(OI.getSideSeatLeftTrigger(), 2);
    	double ssGas = Math.pow(OI.getSideSeatRightTrigger(), 2);
    	double ssTurn = OI.getSideSeatLeftStickX() >= 0 ? -Math.pow(OI.getSideSeatLeftStickX(), 2) : Math.pow(OI.getSideSeatLeftStickX(), 2);
    	if(!OI.sideSeatDisabled){
    		brake = brake > ssBrake ? ssBrake : brake;
    		gas = gas > ssGas ? ssGas : gas;
    		turn = Math.abs(turn) >= Math.abs(ssTurn) ? ssTurn : turn;
    	}
    	powerL = turn > 0 ? gas - brake : (gas - brake) + turn;
    	powerR = turn < 0 ? gas - brake : (gas - brake) - turn;
    	drive.setPower(powerL, powerR);
    }
    
    public static double getPowerL(){
    	return powerL;
    }
    
    public static double getPowerR(){
    	return powerR;
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
