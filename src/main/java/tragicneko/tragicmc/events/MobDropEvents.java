package tragicneko.tragicmc.events;

import static tragicneko.tragicmc.TragicMC.rand;

import java.util.Random;

import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.main.TragicItems;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class MobDropEvents {

	@SubscribeEvent
	public void onVanillaMobDeath(LivingDeathEvent event)
	{
		if (event.source == null || event.entityLiving.worldObj.isRemote) return;
		
		if (event.entityLiving instanceof EntityCow)
		{
			if (rand.nextInt(4) == 0)
			{
				event.entityLiving.entityDropItem(new ItemStack(TragicItems.Horn, 1 + rand.nextInt(2)), rand.nextFloat());
			}
		}

		if (event.entityLiving instanceof EntityCreeper)
		{
			if (((EntityCreeper)event.entityLiving).getDataWatcher().getWatchableObjectByte(17) == 1)
			{
				event.entityLiving.entityDropItem(new ItemStack(TragicItems.LightningOrb, 1 + rand.nextInt(2)), rand.nextFloat());
			}
		}

		if (event.entityLiving instanceof EntityZombie || event.entityLiving instanceof EntitySkeleton || event.entityLiving instanceof EntityBat || event.entityLiving instanceof EntityWither)
		{
			if (event.entityLiving.isBurning())
			{
				event.entityLiving.entityDropItem(new ItemStack(TragicItems.Ash, rand.nextInt(3)), rand.nextFloat());
			}

			int x = 8;
			int y = 5;

			if (event.entityLiving instanceof EntityZombie)
			{
				x = 25;
				y = 3;

				if (rand.nextInt(256) == 0)
				{
					switch(rand.nextInt(3))
					{
					case 0:
						event.entityLiving.entityDropItem(new ItemStack(TragicItems.Tungsten, 1), rand.nextFloat());
						break;
					case 1:
						event.entityLiving.entityDropItem(new ItemStack(TragicItems.RedMercury, 1), rand.nextFloat());
						break;
					case 2:
						event.entityLiving.entityDropItem(new ItemStack(TragicItems.Quicksilver, 1), rand.nextFloat());
						break;
					}
				}
			}

			if (event.entityLiving instanceof EntityWither)
			{
				x = 2;
				y = 6;
			}

			if (rand.nextBoolean() && rand.nextInt(x) == 0 && !(event.entityLiving instanceof EntityBat))
			{
				event.entityLiving.entityDropItem(new ItemStack(TragicItems.BoneMarrow, rand.nextInt(y)), rand.nextFloat());
			}
		}

		if (event.entityLiving instanceof EntitySlime)
		{
			int wubwub = 25;

			if (event.entityLiving instanceof EntityMagmaCube)
			{
				wubwub = 10;
			}

			if (rand.nextInt(wubwub) == 0)
			{
				event.entityLiving.entityDropItem(new ItemStack(TragicItems.Ectoplasm, rand.nextInt(3)), rand.nextFloat());
			}
		}

		if (event.entityLiving instanceof EntityEnderman || event.entityLiving instanceof EntityDragon)
		{			
			int x = 5;
			int y = 3;

			if (event.entityLiving instanceof EntityDragon)
			{
				x = 2;
				y = 10;
			}

			if (rand.nextBoolean() && rand.nextInt(x) == 0)
			{
				event.entityLiving.entityDropItem(new ItemStack(TragicItems.DarkParticles, rand.nextInt(y)), rand.nextFloat());
			}

			if (event.entityLiving instanceof EntityEnderman)
			{
				x *= 10;
				y = 1;
			}

			if (rand.nextInt(x * 2) == 0)
			{
				event.entityLiving.entityDropItem(new ItemStack(TragicItems.ObsidianOrb, rand.nextInt(y)), rand.nextFloat());
			}
		}

		if (event.entityLiving instanceof EntityPigZombie)
		{
			if (rand.nextInt(16) == 0)
			{
				switch(rand.nextInt(5))
				{
				case 0:
					if (rand.nextInt(100) == 0)
					{
						event.entityLiving.entityDropItem(new ItemStack(TragicItems.Ruby, 1), rand.nextFloat());
					}
					break;
				case 1:
					if (rand.nextInt(100) == 0)
					{
						event.entityLiving.entityDropItem(new ItemStack(TragicItems.Sapphire, 1), rand.nextFloat());
					}
					break;
				case 2:
					event.entityLiving.entityDropItem(new ItemStack(TragicItems.Ash, rand.nextInt(3)), rand.nextFloat());
					break;
				case 3:
					event.entityLiving.entityDropItem(new ItemStack(Items.bone, rand.nextInt(3)), rand.nextFloat());
					break;
				case 4:
					event.entityLiving.entityDropItem(new ItemStack(TragicItems.BoneMarrow, rand.nextInt(2)), rand.nextFloat());
					break;
				}
			}
		}

		if (event.entityLiving instanceof EntityWitch)
		{
			if (rand.nextInt(8) == 0)
			{
				switch(rand.nextInt(6))
				{
				case 0:
					event.entityLiving.entityDropItem(new ItemStack(TragicItems.Sap, rand.nextInt(3)), rand.nextFloat());
					break;
				case 1:
					event.entityLiving.entityDropItem(new ItemStack(TragicItems.Honey, rand.nextInt(3)), rand.nextFloat());
					break;
				case 2:
					event.entityLiving.entityDropItem(new ItemStack(TragicItems.Ash, rand.nextInt(3)), rand.nextFloat());
					break;
				case 3:
					event.entityLiving.entityDropItem(new ItemStack(TragicItems.Spore, rand.nextInt(3)), rand.nextFloat());
					break;
				case 4:
					event.entityLiving.entityDropItem(new ItemStack(TragicItems.BoneMarrow, rand.nextInt(2)), rand.nextFloat());
					break;
				case 5:
					event.entityLiving.entityDropItem(new ItemStack(TragicItems.Ectoplasm, rand.nextInt(2)), rand.nextFloat());
					break;
				}
			}
		}
		
		if (event.entityLiving instanceof EntitySpider && rand.nextInt(16) == 0)
		{
			event.entityLiving.entityDropItem(new ItemStack(TragicItems.WovenSilk, 1), rand.nextFloat());
		}
	}
}
