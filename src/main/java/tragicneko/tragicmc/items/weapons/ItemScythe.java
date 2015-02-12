package tragicneko.tragicmc.items.weapons;

import java.util.List;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.doomsday.Doomsday.EnumDoomType;
import tragicneko.tragicmc.util.LoreHelper;

import com.google.common.collect.Sets;

import cpw.mods.fml.common.eventhandler.Event.Result;

public class ItemScythe extends ItemTool {

	public Doomsday doomsday;

	private static final Set blocksEffectiveAgainst = Sets.newHashSet(new Block[] {Blocks.tallgrass,
			Blocks.brown_mushroom_block, Blocks.cactus, Blocks.red_mushroom_block, Blocks.carrots,
			Blocks.cocoa, Blocks.deadbush, Blocks.double_plant, Blocks.hay_block, Blocks.leaves, Blocks.leaves2,
			Blocks.melon_block, Blocks.melon_stem, Blocks.nether_wart, Blocks.potatoes, Blocks.pumpkin_stem,
			Blocks.red_flower, Blocks.reeds, Blocks.sapling, Blocks.sponge, Blocks.tripwire, Blocks.vine,
			Blocks.waterlily, Blocks.web, Blocks.wheat, Blocks.yellow_flower, TragicBlocks.CarrotBlock, TragicBlocks.PotatoBlock,
			TragicBlocks.AshenTallGrass, TragicBlocks.DriedGrass, TragicBlocks.StarlitTallGrass, TragicBlocks.PaintedTallGrass,
			TragicBlocks.GlowVine, TragicBlocks.DeadBush, TragicBlocks.AshenBush, TragicBlocks.TragicFlower, TragicBlocks.TragicSapling});

	public ItemScythe(ToolMaterial par2Material) {
		super(3.0F, par2Material, blocksEffectiveAgainst);
		this.setHarvestLevel("scythe", 3);
		this.setCreativeTab(TragicMC.Survival);
	}
	
	public Doomsday getDoomsday()
	{
		return this.doomsday;
	}

	public EnumDoomType doomsdayType()
	{
		return this.doomsday.doomsdayType;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		if (TragicConfig.allowRandomWeaponLore && LoreHelper.getRarityFromStack(stack) > 0)
		{
			String lore = LoreHelper.getDescFromStack(stack);
			EnumChatFormatting loreFormat = LoreHelper.getFormatForRarity(LoreHelper.getRarityFromStack(stack));
			
			if (lore != null)
			{
				String[] subs = LoreHelper.splitDesc(lore);
				if (subs != null) for (String sub : subs) par2List.add(loreFormat + sub);
			}
		}

		if (TragicConfig.allowDoomsdays && this.doomsday != null)
		{
			EnumChatFormatting format = doomsday.getDoomsdayType().getFormat();
			par2List.add(format + doomsday.getLocalizedType() + ": " + doomsday.getLocalizedName());
			par2List.add(EnumChatFormatting.GOLD + "Doom Cost: " + doomsday.getScaledDoomRequirement(par2EntityPlayer.worldObj));
			par2List.add(""); //extra space
		}
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int numb, boolean flag)
	{
		TragicWeapon.updateAsWeapon(stack, world, entity, numb, flag);
	}

	@Override
	public float func_150893_a(ItemStack stack, Block block)
	{
		Material material = block.getMaterial();
		return material == Material.plants || material == Material.vine || material == Material.coral || material == Material.leaves || material == Material.gourd ? this.toolMaterial.getEfficiencyOnProperMaterial() : super.func_150893_a(stack, block);
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int meta, float f, float f1, float f2)
    {
        if (!player.canPlayerEdit(x, y, z, meta, stack))
        {
            return false;
        }
        else
        {
            UseHoeEvent event = new UseHoeEvent(player, stack, world, x, y, z);
            if (MinecraftForge.EVENT_BUS.post(event))
            {
                return false;
            }

            if (event.getResult() == Result.ALLOW)
            {
                stack.damageItem(1, player);
                return true;
            }

            Block block = world.getBlock(x, y, z);

            if (meta != 0 && world.getBlock(x, y + 1, z).isAir(world, x, y + 1, z) && (block == Blocks.grass || block == Blocks.dirt))
            {
                Block block1 = Blocks.farmland;
                world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, block1.stepSound.getStepResourcePath(), (block1.stepSound.getVolume() + 1.0F) / 2.0F, block1.stepSound.getPitch() * 0.8F);

                if (world.isRemote)
                {
                    return true;
                }
                else
                {
                    world.setBlock(x, y, z, block1);
                    stack.damageItem(1, player);
                    return true;
                }
            }
            else
            {
                return false;
            }
        }
    }
}
