package org.usfirst.frc.team2220.robot.electronics;

import edu.wpi.first.wpilibj.Compressor;

public class BTCompressor
{
	private final Compressor comp;
	
	public BTCompressor()
	{
		comp = new Compressor(1);
	}

}
