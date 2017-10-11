package org.usfirst.frc.team868.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.CANTalon;

/**
 *
 */
public class Shooter extends Subsystem {
	
	private static Shooter instance;
	
	private final CANTalon shooter; // regret
	
	public Shooter() {
    	shooter = new CANTalon(0);
    	
    	shooter.changeControlMode(CANTalon.TalonControlMode.Voltage);
    }

    public void setSpeed(double rps) {
    	
    }
    
    public void setPower(double V) {
    	shooter.set(V);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public static Shooter getInstance() {
    	if (instance == null)
    		instance = new Shooter();
    	return instance;
    }
}

