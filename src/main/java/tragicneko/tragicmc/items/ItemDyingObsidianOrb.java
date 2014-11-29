package tragicneko.tragicmc.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

public class ItemDyingObsidianOrb extends Item {

	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		par2List.add("When Right-Clicked, will teleport you");
		par2List.add("to your last spawn point in the overworld");
		par2List.add("no matter what dimension you're in");
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (!par2World.isRemote)
		{
			int dim = par2World.provider.dimensionId;

			if (dim != 0)
			{
				par3EntityPlayer.travelToDimension(0);
			}

			ChunkCoordinates cc = par3EntityPlayer.getBedLocation(0);

			if (cc != null)
			{
				par3EntityPlayer.setPositionAndUpdate(cc.posX, cc.posY, cc.posZ);
			}
			else
			{
				ChunkCoordinates cc2 = par2World.getSpawnPoint();
				par3EntityPlayer.setPositionAndUpdate(cc2.posX, par2World.getTopSolidOrLiquidBlock(cc2.posX, cc2.posZ), cc2.posZ);
			}

			par1ItemStack.stackSize--;
		}
		return par1ItemStack;
	}
}
