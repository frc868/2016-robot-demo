package org.usfirst.frc.team868.robot.commands;

import org.usfirst.frc.team868.robot.subsystems.CollectorMotor;

import edu.wpi.first.wpilibj.command.Command;

public class Collect extends Command {
	
	CollectorMotor motor;
	
	private double power;

    public Collect(double power) {
        motor = CollectorMotor.getInstance();
        requires(motor);
        this.power = power;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	motor.setPower(power);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
