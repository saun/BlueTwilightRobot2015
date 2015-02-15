
package org.usfirst.frc.team2220.robot;


import org.usfirst.frc.team2220.robot.autonomous.BTAuto;
import org.usfirst.frc.team2220.robot.autonomous.BTIAutonomousRoutine;
import org.usfirst.frc.team2220.robot.drivetrain.BTMeca;
import org.usfirst.frc.team2220.robot.drivetrain.BTOcto;
import org.usfirst.frc.team2220.robot.manipulator.BTManipulator;

import edu.wpi.first.wpilibj.SampleRobot;

public class BTMain extends SampleRobot
{
	// Testing folders
	BTMeca meca;
	//BTOcto octo;
	BTStorage storage;
	BTIAutonomousRoutine auto;
	//BTTestClass test;
	BTManipulator manipulator;
	
    public BTMain()
    {
    	
    }
	
	@Override
    public void robotInit()
    {
		storage = new BTStorage();
		if(BTConstants.IS_TEST)
		{
		//	test = new BTTestClass(storage);
			meca = new BTMeca(storage);
		}
		else
		{
			meca = new BTMeca(storage);
			manipulator = new BTManipulator(storage);
			auto = new BTAuto(storage, manipulator);
		}
    	//octo = new BTOcto(storage);   	
		if (storage.robot.getGyro() != null)
		{
			storage.robot.getGyro().reset();
		}
	}
	
	//@Override
    public void autonomous()
    {
		auto.runAutonomous();
    }
	
	//@Override
    public void operatorControl()
    {
    	while(isOperatorControl())
    	{
    		//test.test();
    		meca.drive();
    		manipulator.perform();
		}
    }
	
	//@Override
    public void disabled()
    {
    	
    }
}
