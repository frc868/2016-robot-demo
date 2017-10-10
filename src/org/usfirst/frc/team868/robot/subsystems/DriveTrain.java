package org.usfirst.frc.team868.robot.subsystems;

import org.usfirst.frc.team868.robot.RobotMap;
import org.usfirst.frc.team868.robot.commands.DriveTele;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

    private Spark left;
    private Spark right;
    private static DriveTrain instance;
    
    private DriveTrain(){
    	left = new Spark(RobotMap.DRIVE_LEFT_MOTOR);
    	right = new Spark(RobotMap.DRIVE_RIGHT_MOTOR);
    	right.setInverted(true);
    }
    
    public void setPower(double Lpower, double Rpower){
    	left.setSpeed(rangeCheck(Lpower));
    	right.setSpeed(rangeCheck(Rpower));
    }
    
    public double rangeCheck(double power){
    	power = power > 1 ? power : 1;
    	power = power < -1 ? power : -1;
    	return power;
    }

    public void initDefaultCommand() {
        setDefaultCommand(new DriveTele());
    }
    
    public static DriveTrain getInstance() {
    	if (instance == null)
    		instance = new DriveTrain();
    	return instance;
    }
    
}

