package tragicneko.tragicmc.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

public class ItemCryingObsidianOrb extends Item {

	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		par2List.add("When Right-Clicked, will set your");
		par2List.add("spawn point, only affects the dimension");
		par2List.add("you're currently in");
	}
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (!par2World.isRemote)
		{
			ChunkCoordinates cc = par2World.getSpawnPoint();
			int x = (int) par3EntityPlayer.posX;
			int z = (int) par3EntityPlayer.posZ;
			int y = (int) par3EntityPlayer.posY;
			
			ChunkCoordinates newCC = new ChunkCoordinates(x, y, z);
			
			int dim = par2World.provider.dimensionId;
			
			par3EntityPlayer.setSpawnChunk(newCC, true, dim);
			
			par1ItemStack.stackSize--;
		}
		return par1ItemStack;
	}
}
