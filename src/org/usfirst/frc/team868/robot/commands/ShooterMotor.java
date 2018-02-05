package org.usfirst.frc.team868.robot.commands;

import org.usfirst.frc.team868.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.command.Command;

public class ShooterMotor extends Command {
	
	Shooter shooterMotor;
	public double shooterSpeed;
	
    public ShooterMotor(double shooterSpeed) {
        shooterMotor = Shooter.getInstance();
        requires(shooterMotor);
        this.shooterSpeed = shooterSpeed;
    }
    
    protected void initialize() {
    	shooterMotor.setSpeed(shooterSpeed);
    }
    
    protected void execute() {
    }
    
    protected boolean isFinished() {
        return false;
    }
    
    protected void end() {
    }
    
    protected void interrupted() {
    }
}
