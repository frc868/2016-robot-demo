package org.usfirst.frc.team868.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

public class ControllerMap {
	
	public static final int A = 0, B = 1, X = 2, Y = 3, RB = 4, RT = 5, LB = 6, LT = 7, START = 8, BACK = 9,
			LEFT_X = 10, RIGHT_X = 11,  LEFT_Y = 12, RIGHT_Y = 13, RIGHT_STICK = 14, LEFT_STICK = 15;
	
	public static final int RIGHT = 24, LEFT = 25, UP = 26, DOWN = 27, UP_RIGHT = 28, UP_LEFT = 29, DOWN_RIGHT = 30, DOWN_LEFT = 31;
	
	public static final int LOGITECH = 0, XBOX1 = 1, XBOX360 = 2, PLAYSTATION4 = 3;
		
	protected static final int[] logitech = {2, 3, 1, 4, 6, 8, 5, 7, 10, 9, 0, 2, 1, 3, 11, 12};
	
	protected static final int[] xbox1 = {1, 2, 3, 4, 6, 3, 5, 2, 8, 7, 0, 4, 1, 5, 10, 9};
	
	protected static final int[] xbox360 = {1, 2, 3, 4, 6, 3, 5, 2, 8, 7, 0, 4, 1, 5, 10, 9};
	
	protected static final int[] playstation4 = {};
	
	protected static double DEADZONE = 0.05;
	
	protected int type;
	
	public boolean eightDirectional;
	protected int[] buttonSet;
	public Joystick joystick;
	
	public ControllerMap(Joystick joystick, int type, boolean isEightDirectional){
		this.type = type;
		this.joystick = joystick;
		if(type == LOGITECH)
			buttonSet = logitech;
		else if(type == XBOX1)
			buttonSet = xbox1;
		else if(type == PLAYSTATION4)
			buttonSet = playstation4;
		else
			buttonSet = xbox360;
		
		this.eightDirectional = isEightDirectional;
		
	}
	
	public int getType(){
		return type;
	}
	
	public double getLeftStickX(){
		return checkDeadZone(joystick.getRawAxis(buttonSet[LEFT_X]));
	}
	public double getLeftStickY(){
		return checkDeadZone(joystick.getRawAxis(buttonSet[LEFT_Y]));
	}
	public double getRightStickX(){
		return checkDeadZone(joystick.getRawAxis(buttonSet[RIGHT_X]));
	}
	public double getRightStickY(){
		return checkDeadZone(joystick.getRawAxis(buttonSet[RIGHT_Y]));
	}
	public double getRightTrigger(){
		if(type == LOGITECH || type == PLAYSTATION4){
			JoystickButton Rt = new JoystickButton(joystick, RT);
			boolean rt = Rt.get();
			if(rt){
				double rT = 1;
				return rT;
			}else{
				double rT = 0;
				return rT;
			}
		}else{
		return checkDeadZone(joystick.getRawAxis(buttonSet[RT]));
		}
	}

	public double getLeftTrigger(){
		if(type == LOGITECH || type == PLAYSTATION4){
			JoystickButton Lt = new JoystickButton(joystick, LT);
			boolean lt = Lt.get();
			if(lt){
				double lT = 1;
				return lT;
			}else{
				double lT = 0;
				return lT;
			}
		}else{
		return checkDeadZone(joystick.getRawAxis(buttonSet[LT]));
		}
	}
	
	public void setDeadZone(double deadZone){
		DEADZONE = deadZone;
	}
	
	public static double checkDeadZone(double val){
		if(Math.abs(val) < DEADZONE)
			return 0;
		return val;
	}
	
	public void StartRumble(int rumbleType){
		if(buttonSet == logitech){
			return;
		}
		if(rumbleType == 1){
		joystick.setRumble(edu.wpi.first.wpilibj.GenericHID.RumbleType.kLeftRumble, 1);
		}else{
		joystick.setRumble(edu.wpi.first.wpilibj.GenericHID.RumbleType.kRightRumble, 1);
		}
	}
	
	public void stopRumble(int rumbleType){
		if(buttonSet == logitech){
			return;
		}
		if(rumbleType == 1){
		joystick.setRumble(edu.wpi.first.wpilibj.GenericHID.RumbleType.kLeftRumble, 0);
		}else{
		joystick.setRumble(edu.wpi.first.wpilibj.GenericHID.RumbleType.kRightRumble, 0);
		}
	}
	public Button createButton(int buttonID){
		if (buttonID == UP || buttonID == DOWN || buttonID == LEFT || buttonID == RIGHT ||
	    		buttonID == DOWN_LEFT || buttonID == DOWN_RIGHT || buttonID == UP_LEFT|| buttonID == UP_RIGHT)
	    		return new DPadButton(buttonID);
		
		
		return new JoystickButton(joystick, buttonSet[buttonID]);
	}
	
	protected class DPadButton extends Button{
		
		private int button;
		
		private DPadButton(int button){
			this.button = button;
		}
		
		public boolean get(){
			int angle = joystick.getPOV();
			if(!eightDirectional){
					if (button == ControllerMap.LEFT) {
		                return angle == 270 || angle == 315 || angle == 225;
		            } else if (button == ControllerMap.RIGHT) {
		                return angle == 90 || angle == 45 || angle== 135;
		            } else if (button == ControllerMap.UP) {
		                return angle == 0 || angle == 45 || angle == 315;
		            } else if (button == ControllerMap.DOWN) {
		                return angle == 180 || angle == 135 || angle == 225;
		            } else {
		                return false;
		            }
				}else{
		            	if(button == ControllerMap.UP){
		        			return angle == 0;
		        		}else if(button == ControllerMap.UP_LEFT){
		        			return angle == 45;
		        		}else if(button == ControllerMap.LEFT){
		        			return angle == 90;
		        		}else if(button == ControllerMap.DOWN_LEFT){
		        			return angle == 135;
		        		}else if(button == ControllerMap.DOWN){
		        			return angle == 180;
		        		}else if(button == ControllerMap.DOWN_RIGHT){
		        			return angle == 225;
		        		}else if(button == ControllerMap.RIGHT){
		        			return angle == 270;
		        		}else if(button == ControllerMap.UP_RIGHT){
		        			return angle == 315;
		        		}else{
		        			return false;
		        		}
		            }
				}
		}
	}