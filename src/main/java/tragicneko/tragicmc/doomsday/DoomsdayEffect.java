package tragicneko.tragicmc.doomsday;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import tragicneko.tragicmc.TragicNewConfig;
import tragicneko.tragicmc.doomsday.Doomsday.IExtendedDoomsday;
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
	public int inheritedCooldown;

	public final Random rand;

	public List utilityList;
	public Entity utilityEntity;
	public boolean utilityFlag;
	public int utilityInt;

	public DoomsdayEffect(int id, PropertyDoom doom)
	{
		this(id, doom, false);
	}

	public DoomsdayEffect(int id, PropertyDoom doom, boolean commandActive)
	{
		this.dday = Doomsday.getDoomsdayFromId(id);
		this.rand = dday.rand;
		this.doom = doom;
		this.isInstant = dday instanceof IExtendedDoomsday ? false : true;
		this.timeBetweenUpdates = dday.getWaitTime();
		this.isActive = true;
		this.crucMoment = false;
		this.isCommandActivated = commandActive;
		this.iterations = 0;
		
		this.utilityList = new ArrayList();
		this.utilityFlag = false;
		this.utilityEntity = null;
		this.utilityInt = 0;
	}

	public DoomsdayEffect inheritCooldown(DoomsdayEffect ext, DoomsdayEffect ins)
	{
		this.inheritedCooldown = (ext.iterations * ext.dday.getScaledCooldown(ext.doom.getPlayer().worldObj.difficultySetting)) + ins.dday.cooldown;
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
			if (TragicNewConfig.allowCrucialMoments) crucMoment = rand.nextInt(100) <= TragicNewConfig.crucialMomentChance;
			
			try
			{
				if (this.iterations == 0) dday.doInitialEffects(this, doom, doom.getPlayer(), crucMoment);

				if (this.isCommandActivated)
				{
					this.dday.useDoomsday(this, doom, doom.getPlayer(), crucMoment);
					iterations++;
					this.timeBetweenUpdates = dday.getWaitTime();
				}
				else
				{
					if (this.dday.doesCurrentDoomMeetRequirement(doom))
					{
						this.dday.useDoomsday(this, doom, doom.getPlayer(), crucMoment);
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
			catch (Exception e)
			{
				DoomsdayManager.logger.info("Exception thrown while updating a DoomsdayEffect for Doomsday: " + this.dday + ", it has been aborted to prevent a crash. Displaying stack trace.");
				DoomsdayManager.logger.catching(e);
				this.isActive = false;
			}
		}
	}

	public boolean equals(DoomsdayEffect effect)
	{
		return this.equals(effect) || effect.dday == this.dday;
	}
}
