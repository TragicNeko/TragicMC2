package tragicneko.tragicmc.doomsday;

import java.util.Random;

import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayEffect {

	public final Doomsday dday;
	public int timeBetweenUpdates;

	public final boolean isInstant;
	public final boolean isCommandActivated;
	public final PropertyDoom doom;

	public boolean isActive;
	public boolean crucMoment;

	public int iterations;
	
	public Random rand = new Random();

	public DoomsdayEffect(int id, PropertyDoom doom)
	{
		this(id, doom, false);
	}

	public DoomsdayEffect(int id, PropertyDoom doom, boolean commandActive)
	{
		this.dday = Doomsday.getDoomsdayFromId(id);
		this.doom = doom;
		this.isInstant = dday instanceof IExtendedDoomsday ? false : true;
		this.timeBetweenUpdates = dday.getWaitTime();
		this.isActive = true;
		this.crucMoment = false;
		this.isCommandActivated = commandActive;
		this.iterations = 0;
	}
	
	public DoomsdayEffect inheritIterations(DoomsdayEffect effect)
	{
		this.iterations = effect.iterations;
		return this;
	}

	public void onDoomsdayUpdate()
	{		
		if (this.iterations >= dday.getMaxIterations() || doom.getPlayer().isSneaking() && !this.isInstant)
		{
			this.isActive = false;
			return;
		}
		
		if (this.timeBetweenUpdates > 0 && !this.isInstant)
		{
			this.timeBetweenUpdates--;
		}

		if (this.timeBetweenUpdates == 0)
		{
			if (TragicNewConfig.allowCrucialMoments) crucMoment = rand.nextInt(100) <= TragicNewConfig.crucialMomentChance; //chance for crucial moment per iteration rather than overall
			if (this.iterations == 0) dday.doInitialEffects(doom, doom.getPlayer(), crucMoment);
			
			if (this.isCommandActivated)
			{
				this.dday.useDoomsday(doom, doom.getPlayer(), crucMoment);
				iterations++;
				this.timeBetweenUpdates = dday.getWaitTime();
			}
			else
			{
				if (this.dday.doesCurrentDoomMeetRequirement(doom, iterations == 0 ? 1 : iterations))
				{
					this.dday.useDoomsday(doom, doom.getPlayer(), crucMoment);
					if (this.isInstant) this.isActive = false;
					iterations++;
					this.timeBetweenUpdates = dday.getWaitTime();
				}
				else
				{
					this.isActive = false;
				}
				
			}
		}
	}
	
	public boolean equals(DoomsdayEffect effect)
	{
		return this == effect ? true : (effect.dday == this.dday ? true : false);
	}
}
