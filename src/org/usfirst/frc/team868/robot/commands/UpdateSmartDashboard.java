package org.usfirst.frc.team868.robot.commands;

import org.usfirst.frc.team868.robot.OI;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class UpdateSmartDashboard extends Command {
	
	private Timer time;
	private int refresh = 20;

    public UpdateSmartDashboard(int refresh) {
        setRunWhenDisabled(true);
        time = new Timer();
        this.refresh = refresh;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	time.reset();
    	time.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(time.get() > (1/refresh)){
    		//place update SDs here
    		OI.updateSmartDashboard();
    	}
    	time.reset();
    }
    
    public void setRefreshRate(int refresh){
    	this.refresh = refresh;
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
