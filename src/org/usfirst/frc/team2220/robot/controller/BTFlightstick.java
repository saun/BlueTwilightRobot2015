package org.usfirst.frc.team2220.robot.controller;

import org.usfirst.frc.team2220.robot.controller.modules.BTIConAxis;
import org.usfirst.frc.team2220.robot.controller.modules.BTIConButton;
import org.usfirst.frc.team2220.robot.controller.modules.BTJoyAxis;
import org.usfirst.frc.team2220.robot.controller.modules.BTJoyButton;

import edu.wpi.first.wpilibj.Joystick;

public class BTFlightstick implements BTIController
{
	private final BTJoyAxis JOYSTICK_X;
	private final BTJoyAxis JOYSTICK_Y;
	private final BTJoyAxis JOYSTICK_Z_ROTATE;
	
	private Joystick joy;
	
	public BTFlightstick(int port)
	{
		joy = new Joystick(port);
		
		JOYSTICK_X = new BTJoyAxis(joy, 0);
		JOYSTICK_Y = new BTJoyAxis(joy, 1);
		JOYSTICK_Z_ROTATE = new BTJoyAxis(joy, 5);
	}

	@Override
	public BTIConAxis getAxis(int port)
	{
		return new BTJoyAxis(joy, port);
	}

	@Override
	public BTIConButton getButton(int port)
	{
		return new BTJoyButton(joy, port);
	}

	@Override
	public BTIConAxis getLeftDriveFrontBack()
	{
		return null;
	}

	@Override
	public BTIConAxis getRightDriveFrontBack()
	{
		return null;
	}

	@Override
	public BTIConAxis getDriveLeftRight()
	{
		return JOYSTICK_X;
	}

	@Override
	public BTIConAxis getDriveFrontBack()
	{
		return JOYSTICK_Y;
	}

	@Override
	public BTIConAxis getDriveRotate()
	{
		return JOYSTICK_Z_ROTATE;
	}
	
	@Override
	public BTIConButton getOctoSwitch()
	{
		return null;
	}



}