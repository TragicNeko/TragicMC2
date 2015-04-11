package tragicneko.tragicmc.blocks.itemblocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public class ItemBlockStructureSeeds extends TragicItemBlock {

	public static String[] subNames = new String[] {"apisTemple", "tower", "deathCircle", "obsidianCavern", "kitsuneDen", "celestialTemple",
		"timeAltar", "soulTomb", "corruptedSpire"};

	public ItemBlockStructureSeeds(Block p_i45326_1_) {
		super(p_i45326_1_, subNames);
		this.setUnlocalizedName("tragicmc.structureSeed");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2)
	{
		int color = 0x000000;

		switch (par1ItemStack.getItemDamage())
		{
		case 0: //Apis Temple
			color = 0xEAD739;
			break;
		case 1: //Forest tower
			color = 0xC3E799;
			break;
		case 2: //Death Circle
			color = 0x770300;
			break;
		case 3: //Obsidian Cavern
			color = 0xAAAAAA;
			break;
		case 4: //Kitsune Den
			color = 0xAF0000;
			break;
		case 5: //Celestial Temple
			color = 0xAA23AA;
			break;
		case 6: //Time Altar
			color = 0x23FF23;
			break;
		case 7: //Soul Tomb
			color = 0x333333;
			break;
		case 8: //Aegar Tower
			color = 0xFFFFFF;
		default:
			break;
		}
		return color;
	}
}
