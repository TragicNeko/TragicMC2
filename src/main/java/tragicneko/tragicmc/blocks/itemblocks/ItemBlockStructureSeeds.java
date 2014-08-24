package tragicneko.tragicmc.blocks.itemblocks;

import java.awt.Color;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockStructureSeeds extends TragicItemBlock {

	private static String[] subNames = new String[] {"apisTemple", "desertTower", "forestTower", "mesaTower", "netherTower", "deathCircle", "iceTower", "obsidianCavern.random",
		"obsidianCavern.lootStash", "obsidianCavern.deathTrap", "obsidianCavern.mobSpawners", "obsidianCavern.bossChallenge", "kitsuneDen", "celestialTemple", "timeAltar"};

	public ItemBlockStructureSeeds(Block p_i45326_1_) {
		super(p_i45326_1_, subNames);
		this.setUnlocalizedName("tragicmc.structureSeed");
	}

	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2)
	{
		Color color = new Color(0x00, 0x00, 0x00);

		switch (par1ItemStack.getItemDamage())
		{
		case 0: //Apis Temple
			color = new Color(0xEA, 0xD7, 0x39);
			break;
		case 1: //Desert Tower
			color = new Color(0xEA, 0xD2, 0xAD);
			break;
		case 2: //Forest tower
			color = new Color(0xC3, 0xE7, 0x99);
			break;
		case 3: //Mesa tower
			color = new Color(0xEF, 0xBC, 0x59);
			break;
		case 4: //Nether tower
			color = new Color(0xCD, 0x47, 0x2D);
			break;
		case 5: //Death Circle
			color = new Color(0x77, 0x03, 0x00);
			break;
		case 6: //Ice Tower
			color = new Color(0xD0, 0xFF, 0xEF);
			break;
		case 7:
		case 8:
		case 9:
		case 10:
		case 11:
			color = new Color(0x56, 0x56, 0x56);
			break;
		case 12: //Kitsune Den
			color = new Color(0xAF, 0x00, 0x00);
			break;
		case 13: //Celestial Temple
			color = new Color(0xAA, 0x23, 0xAA);
			break;
		case 14: //Time Altar
			color = new Color(0x23, 0xFF, 0x23);
			break;
		default:
			break;
		}
		return color.getRGB();
	}

	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		switch (par1ItemStack.getItemDamage())
		{
		case 0:
			return EnumRarity.epic;
		case 4:
			return EnumRarity.uncommon;
		case 5:
			return EnumRarity.epic;
		case 7:
			return EnumRarity.rare;
		case 8:
		case 10:
		case 11:
			return EnumRarity.uncommon;
		case 9:
			return EnumRarity.epic;
		case 12:
			return EnumRarity.uncommon;
		case 13:
			return EnumRarity.rare;
		case 14:
			return EnumRarity.epic;
		}
		return EnumRarity.common;
	}
}
