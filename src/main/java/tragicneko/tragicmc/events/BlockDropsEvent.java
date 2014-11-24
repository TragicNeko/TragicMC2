package tragicneko.tragicmc.events;

import static tragicneko.tragicmc.TragicMC.rand;

import java.util.Random;

import net.minecraft.block.BlockCactus;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockMushroom;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.BlockWeb;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.blocks.BlockGenericLeaves;
import tragicneko.tragicmc.main.TragicItems;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class BlockDropsEvent {

	@SubscribeEvent
	public void onBlockBreak(HarvestDropsEvent event)
	{
		if (event.harvester == null || event.isSilkTouching || rand.nextBoolean()) return;		

		if (event.block instanceof BlockGrass && rand.nextInt(64) == 0)
		{
			event.drops.add(new ItemStack(TragicItems.Spore));
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
		else if (event.block == Blocks.log && rand.nextInt(64) == 0)
		{
			event.drops.add(new ItemStack(TragicItems.Sap, 1, 1));
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
			if (rand.nextInt(16) == 0) event.drops.add(new ItemStack(TragicItems.Spore));
		}
		else if (event.block == Blocks.double_plant && rand.nextInt(4) == 0)
		{
			if (event.blockMetadata != 4)
			{
				event.drops.add(new ItemStack(TragicItems.Spore));
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

}
