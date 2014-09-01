package tragicneko.tragicmc.doomsday;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomThread extends Thread {

	private PropertyDoom playerDoom;
	private Doomsday doomsday;
	private EntityPlayer player;
	private boolean crucMoment;
	private boolean griefCheck;
	private boolean commandActivated;
	private boolean shouldRun;

	public DoomThread(IThreadedDoomsday dd, PropertyDoom doom, boolean crucial, boolean grief)
	{
		this(dd, doom, crucial, grief, false);
	}

	public DoomThread(IThreadedDoomsday dd, PropertyDoom doom, boolean crucial, boolean grief, boolean command)
	{
		this.doomsday = (Doomsday) dd;
		this.playerDoom = doom;
		this.player = doom.getPlayer();
		this.crucMoment = crucial;
		this.griefCheck = grief;
		this.commandActivated = command;
	}

	@Override
	public void run()
	{
		int timesRan = 0;

		for (int i = 0; i < 5000; i++)
		{
			long tick = MinecraftServer.getSystemTimeMillis();

			if (tick % 50L == 0)
			{
				this.shouldRun = false;
			}
			else
			{
				this.shouldRun = true;
			}

			if (this.shouldRun)
			{
				timesRan++;

				if (timesRan > 5 && this.doomsday instanceof DoomsdaySuicidalTendencies)
				{
					if (this.isAlive())
					{
						this.interrupt();
					}
					break;
				}

				if (timesRan > 21)
				{
					if (this.isAlive())
					{
						this.interrupt();
					}
					break;
				}

				if (!doomsday.doesCurrentDoomMeetRequirement(playerDoom) && !this.commandActivated)
				{
					if (this.isAlive())
					{
						this.interrupt();
					}
					break;
				}

				if (this.player.isSneaking())
				{
					if (this.isAlive())
					{
						this.interrupt();
					}
					break;
				}

				if (this.commandActivated)
				{
					((IThreadedDoomsday) doomsday).useDoomsdayFromThreadThroughCommand(playerDoom, player, crucMoment, griefCheck);
				}
				else
				{
					((IThreadedDoomsday) doomsday).useDoomsdayFromThread(playerDoom, player, crucMoment, griefCheck);
				}

				long time = 1000L;

				if (doomsday instanceof DoomsdayRapidFire)
				{
					time = 200L;
				}

				if (doomsday instanceof DoomsdaySuicidalTendencies)
				{
					time = 400L;
				}

				if (doomsday instanceof DoomsdayReaperLaugh)
				{
					time = 500L;
				}

				try 
				{
					this.sleep(time);
				}
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}

				if (this.doomsday instanceof DoomsdayPulse)
				{
					if (this.commandActivated)
					{
						((DoomsdayPulse) doomsday).useDoomsdayFromThreadThroughCommand2(playerDoom, player, crucMoment, griefCheck);
					}
					else
					{
						((DoomsdayPulse) doomsday).useDoomsdayFromThread2(playerDoom, player, crucMoment, griefCheck);
					}

					try
					{
						this.sleep(1200L);
					}
					catch (InterruptedException e) 
					{
						e.printStackTrace();
						break;
					}
				}
			}
			else
			{
				try 
				{
					this.sleep(1L);
				}
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
}
