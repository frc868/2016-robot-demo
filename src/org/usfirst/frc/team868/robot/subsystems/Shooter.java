package org.usfirst.frc.team868.robot.subsystems;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team868.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

//edit to update

public class Shooter extends Subsystem {
	
	private static Shooter instance;
	private final TalonSRX shooter;
	private Counter count;
	private PIDController pid;
	
	private static final double MAX_SPEED = 100;
	public static final double P = 0.025, I = 0, D = 0.075, F = 0.005;
	
	private double lastSpeed, pidLastSpeed, speedThreshold;
	
	public Shooter() {
    	shooter = new WPI_TalonSRX(0);
    	
    	count = new Counter(new DigitalInput(RobotMap.SHOOTER_DIO));
    	count.setSamplesToAverage(5);
		count.setDistancePerPulse(1);
    	
    	pid = new PIDController(P, I, D, F, new PIDSource() {
    		public void setPIDSourceType(PIDSourceType pidSource){}
    		public PIDSourceType getPIDSourceType(){
    			return PIDSourceType.kRate;
    		}
    		public double pidGet(){
    			double speed = getFilteredSpeed2();
    			return pidLastSpeed = speed;
    		}
    	}, new PIDOutput() {
    		public void pidWrite(double output){
    			if (pidLastSpeed < getSetPoint() * 0.25) {
					shooter.set(ControlMode.PercentOutput, 0.6);
				} else if (pidLastSpeed < getSetPoint() * 0.75) {
					shooter.set(ControlMode.PercentOutput, 0.8);
				} else {
					shooter.set(ControlMode.PercentOutput, output);
				}
    		}
    	}, 1/100.0);
    	pid.setAbsoluteTolerance(2);
    	pid.setOutputRange(0, 1);
    }

    public void setSpeed(double rps) {
    	pidLastSpeed = 0;
    	pid.setSetpoint(rps);
    	setThreshold(rps);
    	pid.enable();
    	
    }
    
    public void disable(){
    	pid.disable();
    }
    
    public void setPower(double V) {
    	pid.disable();
    	shooter.set(ControlMode.PercentOutput,V);
    }
    
    public double getFilteredSpeed2(){
    	double speed = getSpeed();
		if (speed > speedThreshold) {
			speed = lastSpeed;
		}
		lastSpeed = speed;
		return speed;
    }
    
    public void setThreshold(double setPoint) {
		speedThreshold = setPoint * 1.3;
	}
    
    public double getSetPoint(){
    	return pid.getSetpoint();
    }
    
    public double getSpeed(){
    	double speed = count.getRate();
		if (speed == Double.NaN || speed == Double.POSITIVE_INFINITY || speed == Double.NEGATIVE_INFINITY) {
			speed = lastSpeed;
		}
		if (speed > MAX_SPEED) {
			speed = lastSpeed;
		}

		lastSpeed = speed;
		return speed;
    }
    
    public void initDefaultCommand() {
    }
    
    public static Shooter getInstance() {
    	return instance == null ? instance = new Shooter() : instance;
    }
}