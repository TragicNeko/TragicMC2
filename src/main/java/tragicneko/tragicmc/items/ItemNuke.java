package tragicneko.tragicmc.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.EntityNuke;

public class ItemNuke extends Item {

	public ItemNuke()
	{
		super();
		this.setMaxStackSize(1);
		this.setCreativeTab(TragicMC.Survival);
		this.setUnlocalizedName("tragicmc.nuke");
	}
	
	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		par2List.add(EnumChatFormatting.RED + "Nuke it!");
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		MovingObjectPosition mop = this.getMovingObjectPositionFromPlayer(world, player, false);

		if (mop == null) return stack;

		int x = mop.blockX;
		int y = mop.blockY;
		int z = mop.blockZ;

		if (World.doesBlockHaveSolidTopSurface(world, x, y, z) && !world.isRemote)
		{
			EntityNuke nuke = new EntityNuke(world);

			double x2 = x + 0.5D;
			double y2 = y;
			double z2 = z + 0.5D;
			float rotation = ((MathHelper.floor_float((player.rotationYaw * 8.0F / 360.0F) + 0.5F)) * 45.0F) + 180F;

			switch (mop.sideHit)
			{
			case 0: //bottom
				y2 -= 1.0D;
				break;
			case 1: //top
				y2 += 1.0D;
				break;
			case 2: //east
				z2 -= 1.0D;
				break;
			case 3: //west
				z2 += 1.0D;
				break;
			case 4: //north
				x2 -= 1.0D;
				break;
			case 5: //south
				x2 += 1.0D;
				break;
			}

			nuke.setPosition(x2, y2, z2);
			world.spawnEntityInWorld(nuke);
			if (!player.capabilities.isCreativeMode) stack.stackSize--;
		}

		return stack;
	}
}
