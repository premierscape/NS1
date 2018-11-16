package com.mayhem.rs2.content.event.impl;

import com.mayhem.rs2.content.event.Event;
import com.mayhem.rs2.entity.World;

/**
 * @author Andy || ReverendDread Apr 12, 2017
 */
public class DoubleExperience extends Event {
	
	private int duration;

	@Override
	public boolean start() {
		//World.sendGlobalMessage("<img=1><col=ff0000>[EVENT]: Double Experience is now active for 30 minutes.");
		return true;
	}

	@Override
	public boolean preStartupCheck() {
		return true;
	}

	@Override
	public int process() {	
		duration++;
		if (duration >= 3000)
			stop();
			return -1;
	}

	@Override
	public void stop() {
		//World.sendGlobalMessage("<img=1><col=ff0000>[EVENT]: Double Experience has now ended.");
	}

}
