package tragicneko.tragicmc.items;

import java.util.ArrayList;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.util.LoreHelper.EnchantEntry;

public class ItemAnomalyAugment extends Item{

	public ItemAnomalyAugment()
	{
		super();
		this.setCreativeTab(TragicMC.Survival);
		this.setMaxStackSize(1);
		this.setMaxDamage(5);
		this.setUnlocalizedName("tragicmc.anomalyAugment");
		this.setHasSubtypes(false);
	}

	@Override
	public int getItemEnchantability(ItemStack stack)
	{
		return 100;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if (stack.hasTagCompound() && stack.isItemEnchanted())
		{
			ArrayList<EnchantEntry> list = new ArrayList<EnchantEntry>();

			for (int i = 0; i < Enchantment.enchantmentsList.length; i++)
			{
				if (Enchantment.enchantmentsList[i] == null) continue;
				int lvl = EnchantmentHelper.getEnchantmentLevel(i, stack);
				EnchantEntry ee = new EnchantEntry(Enchantment.enchantmentsList[i], lvl);
				if (lvl > 0) list.add(ee);
			}

			int totalLevels = 0;
			int totalWeights = 0;
			NBTTagList tags = stack.getEnchantmentTagList();

			stack.stackTagCompound.removeTag("ench");
			if (list.size() <= 0) return stack;

			for (EnchantEntry ee : list)
			{
				totalLevels += ee.getEnchantLevel();
				totalWeights += ee.getEnchantment().getWeight();
				TragicMC.logInfo("Enchantment was " + ee.getEnchantment());
			}

			double avgLevels = totalLevels / list.size();
			double avgWeights = totalWeights / list.size();

			TragicMC.logInfo("Total levels was " + totalLevels + ", average was " + avgLevels + " and total weight was " + totalWeights + ", average was " + avgWeights);
			int r = MathHelper.floor_double(100 * avgLevels * avgWeights);
			TragicMC.logInfo("Total attempts are " + r);
			Item item = Item.getItemById(itemRand.nextInt(42000));
			int attempts = 0;

			while (item == null && attempts < r)
			{
				item = Item.getItemById(itemRand.nextInt(42000));
				attempts++;
			}

			if (item != null && !world.isRemote)
			{
				TragicMC.logInfo("Item chosen was " + item);
				EntityItem ei = new EntityItem(world);
				ei.setEntityItemStack(new ItemStack(item));
				ei.setPosition(player.posX, player.posY, player.posZ);
				world.spawnEntityInWorld(ei);
			}

			if (!player.capabilities.isCreativeMode)
			{
				if (stack.attemptDamageItem(1, itemRand))
				{
					((EntityLivingBase) player).renderBrokenItemStack(stack);
					stack.stackSize--;
					return stack;
				}
			}
		}
		
		if (stack.getItemDamage() >= this.getMaxDamage(stack))
		{
			((EntityLivingBase) player).renderBrokenItemStack(stack);
			stack.stackSize--;
			return stack;
		}
		
		return stack;
	}
}
