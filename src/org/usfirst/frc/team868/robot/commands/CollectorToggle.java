package org.usfirst.frc.team868.robot.commands;

import org.usfirst.frc.team868.robot.subsystems.CollectorAngler;

import edu.wpi.first.wpilibj.command.Command;

public class CollectorToggle extends Command {
	
	CollectorAngler angler;
	boolean collect;
	boolean isDone;
	
	public CollectorToggle(boolean collect){
		angler = CollectorAngler.getInstance();
		requires(angler);
		
		this.collect = collect;
	}
	
	public CollectorToggle(){
		angler = CollectorAngler.getInstance();
		requires(angler);
		
		collect = angler.getSetPoint() > 600 ? true : false;
	}

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(collect)angler.setPosition(719);
    	else if(!collect)angler.setPosition(478);
    	isDone = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isDone;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	angler.setPower(0);
    }
}