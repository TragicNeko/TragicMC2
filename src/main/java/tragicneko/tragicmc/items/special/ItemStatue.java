package tragicneko.tragicmc.items.special;

import java.util.List;
import java.util.Random;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.EntityStatue;
import tragicneko.tragicmc.main.TragicTabs;

public class ItemStatue extends Item {

	private String[] subNames = new String[] {"apis", "kitsune", "deathReaper", "timeController", "yeti", "polaris", "jarra", "kragul", "magmox", "megaCryse", "stinKing",
			"stinQueen", "greaterStin"};
	private String[] textureNames = new String[] {"Apis", "Kitsune", "DeathReaper", "TimeController", "Yeti", "Polaris", "Jarra", "Kragul", "Magmox", "MegaCryse", "StinKing",
			"StinQueen", "GreaterStin"};
	private IIcon[] iconArray = new IIcon[subNames.length];

	public ItemStatue()
	{
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.setCreativeTab(TragicTabs.Survival);
		this.setUnlocalizedName("tragicmc.mobStatue");
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list)
	{
		for (int i = 0; i < subNames.length; i++)
		{
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public IIcon getIconFromDamage(int damage)
	{
		if (damage < this.iconArray.length) return this.iconArray[damage];
		
		damage %= this.iconArray.length;

		if (damage >= this.iconArray.length)
		{
			damage = this.iconArray.length - 1;
		}

		return this.iconArray[damage];
	}

	@Override
	public void registerIcons(IIconRegister register)
	{
		for (int i = 0; i < subNames.length; i++)
		{
			this.iconArray[i] = register.registerIcon("tragicmc:Statue" + textureNames[i] + "_lowRes");
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		int damage = itemstack.getItemDamage();
		
		if (damage < subNames.length) return getUnlocalizedName() + "." + subNames[damage];
		
		int var = damage % subNames.length;

		if (var >= subNames.length)
		{
			var = subNames.length - 1;
		}
		return getUnlocalizedName() + "." + subNames[var];
	}
	
	public String getItemStackDisplayName(ItemStack stack)
    {
		String s = ("" + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
		if (stack.getItemDamage() < subNames.length) return s;
        return s + " " + StatCollector.translateToLocal(this.getExtraStringName(stack.getItemDamage()));
    }

	private String getExtraStringName(int damage) {
		int var = 0;

		for (int i = 1; i < 16; i++)
		{
			if (damage >= subNames.length * i && damage < (i * subNames.length) + subNames.length)
			{
				var = i;
			}
		}
		
		switch(var)
		{
		case 1:
			return "tragicmc.mobTexture.iron";
		case 2:
			return "tragicmc.mobTexture.gold";
		case 3:
			return "tragicmc.mobTexture.diamond";
		case 4:
			return "tragicmc.mobTexture.stone";
		case 5:
			return "tragicmc.mobTexture.wood";
		case 6:
			return "tragicmc.mobTexture.emerald";
		case 7:
			return "tragicmc.mobTexture.redMercury";
		case 8:
			return "tragicmc.mobTexture.tungsten";
		case 9:
			return "tragicmc.mobTexture.ruby";
		case 10:
			return "tragicmc.mobTexture.sapphire";
		case 11:
			return "tragicmc.mobTexture.redstone";
		case 12:
			return "tragicmc.mobTexture.coal";
		case 13:
			return "tragicmc.mobTexture.lapis";
		case 14:
			return "tragicmc.mobTexture.obsidian";
		case 15:
			return "tragicmc.mobTexture.checkered";
		default:
		case 0:
			return "";
		}
	}

	private byte getTextureIDFromDamage(int damage)
	{		
		for (byte i = 1; i < 16; i++)
		{
			if (damage >= subNames.length * i && damage < (i * subNames.length) + subNames.length) return i;
		}

		return 0;
	}

	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		par2List.add(EnumChatFormatting.GOLD + "A trophy for your triumphs!");
		par2List.add(EnumChatFormatting.DARK_RED + "These are WIP and may cause problems");
	}

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) 
	{
		Random random = TragicMC.rand;
		MovingObjectPosition mop = this.getMovingObjectPositionFromPlayer(world, player, false);

		if (mop == null) return stack;

		int x = mop.blockX;
		int y = mop.blockY;
		int z = mop.blockZ;

		EntityStatue statue;

		if (World.doesBlockHaveSolidTopSurface(world, x, y, z) && !world.isRemote)
		{
			statue = new EntityStatue(world);
			
			double x2 = x + 0.5D;
			double y2 = y;
			double z2 = z + 0.5D;
			
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
			
			statue.setPosition(x2, y2, z2);
			if (!world.getCollidingBoundingBoxes(statue, statue.boundingBox).isEmpty() || world.isAnyLiquid(statue.boundingBox)) return stack;
			statue.setMobID(stack.getItemDamage() % subNames.length);
			statue.setTextureID(this.getTextureIDFromDamage(stack.getItemDamage()));
			world.spawnEntityInWorld(statue);
			if (!player.capabilities.isCreativeMode) stack.stackSize--;
		}

		return stack;
	}
}
