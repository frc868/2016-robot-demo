package org.usfirst.frc.team868.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.CANTalon;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Shooter extends Subsystem {
	
	private static Shooter instance;
	private final TalonSRX shooter;
	
	public Shooter() {
    	shooter = new WPI_TalonSRX(0);
    }

    public void setSpeed(double rps) {
    	
    }
    
    public void setPower(double V) {
    	shooter.set(ControlMode.PercentOutput,V);
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