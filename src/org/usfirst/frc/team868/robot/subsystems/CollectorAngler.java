package org.usfirst.frc.team868.robot.subsystems;

import org.usfirst.frc.team868.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CollectorAngler extends Subsystem {
	
	public static CollectorAngler instance;
	
	private TalonSRX angler;
	private PIDController pid;
	
	private double P = 0.004, I = 0, D = 0.005;
	private double TOLERENCE = 10;
	
	public void collectorAngler(){
		
		angler = new WPI_TalonSRX(RobotMap.ANGLER_MOTOR);
		angler.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 0);
		
		pid = new PIDController(P, I, D, new PIDSource() {
			@Override
			public void setPIDSourceType(PIDSourceType pidSource){
			}
			
			@Override
			public PIDSourceType getPIDSourceType() {
				return PIDSourceType.kDisplacement;
			}

			@Override
			public double pidGet() {
				return Math.max(Math.min(angler.getSensorCollection().getAnalogInRaw(), 828), 478);
			}

		}, new PIDOutput() {

			@Override
			public void pidWrite(double output) {
				setPower(output < 0 ? output - .1 : output);
			}

		}, 1 / 200.0);
		
		pid.setOutputRange(-0.45, 0.45);
		pid.setInputRange(478, 828);
		pid.setAbsoluteTolerance(TOLERENCE);
		
	}
	
	public void setPosition(double position){
		pid.setSetpoint(Math.max(Math.min(position, 828), 478));
		pid.enable();
	}
	
	public double getSetPoint() {
		return pid.getSetpoint();
	}

	public boolean onTarget() {
		return pid.onTarget();
	}
	
	public void disableControl(){
		pid.disable();
	}
	
	public double getPosition() {
		return angler.getSensorCollection().getAnalogIn();
	}

	public boolean reachedTarget(double tolerance) {
		return Math.abs(pid.getError()) < tolerance;
	}

	public double getError() {
		return pid.getError();
	}
	
	public void setPower(double power){
		angler.set(ControlMode.PercentOutput, power);
	}
	
	public void stopPower(){
		pid.disable();
		angler.set(ControlMode.PercentOutput, 0);
	}
	
	public static CollectorAngler getInstance() {
		return instance == null ? instance = new CollectorAngler() : instance;
	}

    public void initDefaultCommand() {
        
    }
}