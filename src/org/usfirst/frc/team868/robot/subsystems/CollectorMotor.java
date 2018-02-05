package org.usfirst.frc.team868.robot.subsystems;

import org.usfirst.frc.team868.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

//edit to update

public class CollectorMotor extends Subsystem {
	
	private static CollectorMotor instance;
	
	private TalonSRX collect;
	
	public void collectorMotor(){
		collect = new WPI_TalonSRX(RobotMap.COLLECTOR_MOTOR);
	}
	
	public void setPower(double power){
		collect.set(ControlMode.PercentOutput, power);
	}
	
	public void stopMotor(){
		collect.set(ControlMode.PercentOutput, 0);
	}
	
	public static CollectorMotor getInstance(){
		return instance == null ? instance = new CollectorMotor() : instance;
	}
	
    public void initDefaultCommand() {
        
    }
}

