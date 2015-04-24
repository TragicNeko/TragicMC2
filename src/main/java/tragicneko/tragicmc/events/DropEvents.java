package tragicneko.tragicmc.events;

import static tragicneko.tragicmc.TragicMC.rand;
import net.minecraft.block.BlockCactus;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockMushroom;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.BlockWeb;
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
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.blocks.BlockGenericLeaves;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class DropEvents {

	@SubscribeEvent
	public void onBlockBreak(HarvestDropsEvent event)
	{
		if (event.harvester == null || event.isSilkTouching || rand.nextBoolean()) return;		

		if (event.block instanceof BlockGrass && rand.nextInt(64) == 0)
		{
			event.drops.add(new ItemStack(TragicItems.Projectile, 1, 11));
		}
		else if (event.block == Blocks.dirt && event.y <= 32 && rand.nextInt(8) == 0)
		{
			event.drops.clear();
			event.drops.add(new ItemStack(Items.glowstone_dust));
		}
		else if (event.block == Blocks.sand && rand.nextInt(128) == 0)
		{
			if (rand.nextInt(32) == 0)
			{
				event.drops.add(new ItemStack(Items.gunpowder));
			}
			else
			{
				event.drops.add(new ItemStack(Items.glowstone_dust));
			}
		}
		else if (event.block == Blocks.cobblestone && rand.nextInt(32) == 0)
		{
			event.drops.clear();
			event.drops.add(new ItemStack(TragicItems.Projectile));
		}
		else if (event.block == Blocks.netherrack && rand.nextInt(48) == 0)
		{
			event.drops.clear();
			event.drops.add(new ItemStack(TragicItems.Projectile, 1, 1));
		}
		else if (event.block instanceof BlockLeaves && rand.nextInt(32) == 0)
		{
			if (rand.nextInt(16) != 0)
			{
				event.drops.add(new ItemStack(Items.stick));
			}
			else
			{
				switch(event.blockMetadata)
				{
				case 0: //Oak
					if (event.world.getBiomeGenForCoords(event.x, event.z) == BiomeGenBase.swampland)
					{
						event.drops.add(new ItemStack(TragicItems.NastyFruit));
					}
					else if (event.world.getBiomeGenForCoords(event.x, event.z) == BiomeGenBase.roofedForest)
					{
						event.drops.add(new ItemStack(TragicItems.GooeyFruit));
					}
					else
					{
						event.drops.add(new ItemStack(TragicItems.ExoticFruit));
					}
					break;
				case 1: //Spruce
					break;
				case 2: //Birch
					event.drops.add(new ItemStack(TragicItems.SkyFruit));
					break;
				case 3: //Jungle
					if (event.world.getBiomeGenForCoords(event.x, event.z) == BiomeGenBase.jungle || event.world.getBiomeGenForCoords(event.x, event.z) == BiomeGenBase.jungleHills)
					{
						event.drops.add(new ItemStack(TragicItems.ExoticFruit));
					}
					break;

				}
			}

		}
		else if (event.block instanceof BlockCactus && rand.nextInt(16) == 0)
		{
			event.drops.clear();
			event.drops.add(new ItemStack(TragicItems.Thorns, 1, rand.nextInt(2) + 1));
		}
		else if (event.block instanceof BlockFlower || event.block instanceof BlockMushroom || event.block instanceof BlockTallGrass)
		{
			if (rand.nextInt(16) == 0) event.drops.add(new ItemStack(TragicItems.Projectile, 1, 10));
		}
		else if (event.block == Blocks.double_plant && rand.nextInt(4) == 0)
		{
			if (event.blockMetadata != 4)
			{
				event.drops.add(new ItemStack(TragicItems.Projectile, 1, 11));
			}
			else
			{
				event.drops.add(new ItemStack(TragicItems.Thorns));
			}
		}
		else if (event.block instanceof BlockWeb && rand.nextInt(16) == 0)
		{
			event.drops.clear();
			event.drops.add(new ItemStack(TragicItems.WovenSilk));
		}
		else if (event.block instanceof BlockGenericLeaves && rand.nextInt(16) == 0)
		{
			event.drops.add(new ItemStack(TragicItems.ExoticFruit));
		}
	}

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
				event.entityLiving.entityDropItem(new ItemStack(TragicItems.BoneMarrow, rand.nextInt(y) + 1), rand.nextFloat());
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
				event.entityLiving.entityDropItem(new ItemStack(TragicItems.DimensionalKeyEnd), rand.nextFloat());
			}

			if (rand.nextBoolean() && rand.nextInt(x) == 0)
			{
				event.entityLiving.entityDropItem(new ItemStack(TragicItems.DarkParticles, rand.nextInt(y) + 1), rand.nextFloat());
			}

			if (event.entityLiving instanceof EntityEnderman)
			{
				x *= 10;
				y = 1;
			}

			if (rand.nextInt(x * 2) == 0)
			{
				event.entityLiving.entityDropItem(new ItemStack(TragicItems.ObsidianOrb, rand.nextInt(y) + 1), rand.nextFloat());
			}
		}

		if (event.entityLiving instanceof EntityPigZombie && rand.nextInt(16) == 0)
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
				event.entityLiving.entityDropItem(new ItemStack(TragicItems.Ash, rand.nextInt(3) + 1), rand.nextFloat());
				break;
			case 3:
				event.entityLiving.entityDropItem(new ItemStack(Items.bone, rand.nextInt(3) + 1), rand.nextFloat());
				break;
			case 4:
				event.entityLiving.entityDropItem(new ItemStack(TragicItems.BoneMarrow, rand.nextInt(2) + 1), rand.nextFloat());
				break;
			}
		}

		if (event.entityLiving instanceof EntityWitch && rand.nextInt(8) == 0)
		{
			switch(rand.nextInt(6))
			{
			case 0:
			case 1:
				event.entityLiving.entityDropItem(new ItemStack(TragicItems.Projectile, 1, rand.nextInt(3) + 2), rand.nextFloat());
				break;
			case 2:
				event.entityLiving.entityDropItem(new ItemStack(TragicItems.Ash, rand.nextInt(3) + 1), rand.nextFloat());
				break;
			case 3:
				event.entityLiving.entityDropItem(new ItemStack(TragicItems.Projectile, rand.nextInt(3) + 1, 10 + rand.nextInt(2)), rand.nextFloat());
				break;
			case 4:
				event.entityLiving.entityDropItem(new ItemStack(TragicItems.BoneMarrow, rand.nextInt(2) + 1), rand.nextFloat());
				break;
			case 5:
				event.entityLiving.entityDropItem(new ItemStack(TragicItems.Ectoplasm, rand.nextInt(2) + 1), rand.nextFloat());
				break;
			}

		}

		if (event.entityLiving instanceof EntitySpider && rand.nextInt(16) == 0)
		{
			event.entityLiving.entityDropItem(new ItemStack(TragicItems.WovenSilk, 1), rand.nextFloat());
		}

		if (event.entityLiving instanceof EntitySquid && rand.nextInt(4) == 0)
		{
			event.entityLiving.entityDropItem(new ItemStack(TragicItems.Tentacle, rand.nextInt(6) + 1), rand.nextFloat());
		}
	}
}
