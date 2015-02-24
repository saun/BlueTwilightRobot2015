package org.usfirst.frc.team2220.robot.autonomous;

import org.usfirst.frc.team2220.robot.BTStorage;
import org.usfirst.frc.team2220.robot.BTConstants;
import org.usfirst.frc.team2220.robot.manipulator.BTManipulator;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BTAuto implements BTIAutonomousRoutine
{
	BTStorage storage;
	BTManipulator manipulator;
	
	double fl;
	double bl;
	double fr;
	double br;
	
	public BTAuto(BTStorage storage, BTManipulator manipulator)
	{
		this.storage = storage;
		this.manipulator = manipulator;
	}
	
	@Override
	public void runAutonomous()
	{
		switch (BTConstants.ACTIVE_AUTONOMOUS)
		{
		case 5: SmartDashboard.putString(BTConstants.AUTONOMOUS_METHOD_KEY, "Running Test Autonomous");
				runAutonomousCoop();
				break;
		case 4: SmartDashboard.putString(BTConstants.AUTONOMOUS_METHOD_KEY, "Running Autonomous 4");
				runAutonomous4();
				break;
		case 3: SmartDashboard.putString(BTConstants.AUTONOMOUS_METHOD_KEY, "Running Autonomous 3");
				runAutonomous3();
				break;
		case 2: SmartDashboard.putString(BTConstants.AUTONOMOUS_METHOD_KEY, "Running Autonomous 2");
				runAutonomous2();
				break;
		case 1:	SmartDashboard.putString(BTConstants.AUTONOMOUS_METHOD_KEY, "Running Autonomous 1");
				runAutonomous1();
				break;
		default:	break;
		
		}
	}
	
	public void runAutonomousCoop()
	{
		manipulator.liftSecondary();
		long startTime = System.currentTimeMillis();
		while(System.currentTimeMillis() - startTime < 6500){}	
		manipulator.stopBarrelMotors();
		moveRight(3750);
		startTime = System.currentTimeMillis();
		while(System.currentTimeMillis() - startTime < 3750){}	
		rotateRight(500);
	}

	public void runAutonomous4()
	{
		SmartDashboard.putString(BTConstants.AUTONOMOUS_STAGE_KEY, "Autonomous: Completing right staging zone");
		completeZone();
		runAutonomous3();
	}
	
	public void runAutonomous3()
	{
		SmartDashboard.putString(BTConstants.AUTONOMOUS_STAGE_KEY, "Autonomous: Completing center staging zone");
		completeZone();
		runAutonomous2();
	}
	
	public void runAutonomous2()
	{
		SmartDashboard.putString(BTConstants.AUTONOMOUS_STAGE_KEY, "Autonomous: Completing left staging zone");
		// start collection motors
//		manipulator.startCollectorMotors();
		// move forward slowly
		moveForward(BTConstants.MOVE_FORWARD_TIME_SHORT);
		manipulator.collectTote();
		runAutonomous1();
//		manipulator.releaseTotes();
	}
	
	public void runAutonomous1()
	{
		SmartDashboard.putString(BTConstants.AUTONOMOUS_STAGE_KEY, "Autonomous: Moving to auto zone");
		moveBack(BTConstants.MOVE_BACK_TIME_LONG);
	}
	
	public void completeZone()
	{
		// start collection motors
//		manipulator.startCollectorMotors();
		// move forward slowly
		moveForward(BTConstants.MOVE_FORWARD_TIME_SHORT);
		manipulator.collectTote();
		// move back just enough to collect the next tote
		moveBack(BTConstants.MOVE_BACK_TIME_SHORT);
		strafeRight(BTConstants.STRAFE_RIGHT_TIME);
	}
	
	public void strafeRight(int time)
	{
		long startTime = System.currentTimeMillis();
		startStrafingRight();
		while(System.currentTimeMillis() - startTime < time){}
		stopMotors();
	}
	
	public void moveBack(int time)
	{
		long startTime = System.currentTimeMillis();
		startMovingBack();
		while(System.currentTimeMillis() - startTime < time){}
		stopMotors();
	}
	
	public void moveForward(int time)
	{
		long startTime = System.currentTimeMillis();
		startMovingForward();
		//while(storage.data.TOTE_SWITCH.getValue() == false){}
		while(System.currentTimeMillis() - startTime < time){}
		stopMotors();
	}
	
	public void startMovingBack()
	{
		double speed = BTConstants.STRAFE_SPEED;
		
		fl = speed;
		bl = -speed;
		fr = -speed;
		br = speed;
		
		invertMotors();
		
		storage.robot.getFrontLeftMotor().setX(fl);
		storage.robot.getBackLeftMotor().setX(bl);
		storage.robot.getFrontRightMotor().setX(fr);
		storage.robot.getBackRightMotor().setX(br);
	}
	
	public void startMovingForward()
	{
		double speed = BTConstants.FORWARD_SPEED;
		
		fl = -speed;
		bl = speed;
		fr = speed;
		br = -speed;
		
		invertMotors();
		
		storage.robot.getFrontLeftMotor().setX(fl);
		storage.robot.getBackLeftMotor().setX(bl);
		storage.robot.getFrontRightMotor().setX(fr);
		storage.robot.getBackRightMotor().setX(br);
	}
	
	public void startStrafingRight()
	{
		double speed = BTConstants.STRAFE_SPEED;
		
		fl = -speed;
		bl = -speed;
		fr = -speed;
		br = -speed;
		
		invertMotors();
		
		storage.robot.getFrontLeftMotor().setX(fl);
		storage.robot.getBackLeftMotor().setX(bl);
		storage.robot.getFrontRightMotor().setX(fr);
		storage.robot.getBackRightMotor().setX(br);
	}
	
	public void invertMotors()
	{
		if (BTConstants.FRONT_LEFT_REVERSED)
		{
			fl = -fl;
		}
		if (BTConstants.BACK_LEFT_REVERSED)
		{
			bl = -bl;
		}
		if (BTConstants.FRONT_RIGHT_REVERSED)
		{
			fr = -fr;
		}
		if (BTConstants.BACK_RIGHT_REVERSED)
		{
			br = -br;
		}
	}
	
	public void rotate180()
	{
		storage.robot.getFrontLeftMotor().setX(0);
		storage.robot.getBackLeftMotor().setX(0);
		storage.robot.getFrontRightMotor().setX(0);
		storage.robot.getBackRightMotor().setX(0);
		
		long currentTime = System.currentTimeMillis();
		
	}
	
	public void stopMotors()
	{
		storage.robot.getFrontLeftMotor().setX(0);
		storage.robot.getBackLeftMotor().setX(0);
		storage.robot.getFrontRightMotor().setX(0);
		storage.robot.getBackRightMotor().setX(0);
	}
	
	public void rotateRight(double rotateRightTime)
	{
		fl = BTConstants.ROTATE_RIGHT_SPEED;
		fr = -BTConstants.ROTATE_RIGHT_SPEED;
		bl = BTConstants.ROTATE_RIGHT_SPEED;
		br = -BTConstants.ROTATE_RIGHT_SPEED;
		
		invertMotors();
		
		storage.robot.getFrontLeftMotor().setX(fl);
		storage.robot.getBackLeftMotor().setX(fr);
		storage.robot.getFrontRightMotor().setX(bl);
		storage.robot.getBackRightMotor().setX(br);
		
		long startTime = System.currentTimeMillis();
		while(System.currentTimeMillis() - startTime < rotateRightTime){}
		stopMotors();
		
	}
	
	public void moveRight(double rotateRightTime)
	{
		fl = BTConstants.MOVE_RIGHT_SPEED;
		fr = -BTConstants.MOVE_RIGHT_SPEED;
		bl = -BTConstants.MOVE_RIGHT_SPEED;
		br = BTConstants.MOVE_RIGHT_SPEED;
		
		invertMotors();
		
		storage.robot.getFrontLeftMotor().setX(fl);
		storage.robot.getBackLeftMotor().setX(fr);
		storage.robot.getFrontRightMotor().setX(bl);
		storage.robot.getBackRightMotor().setX(br);
		
		long startTime = System.currentTimeMillis();
		while(System.currentTimeMillis() - startTime < rotateRightTime){}
		stopMotors();
		
	}
}
