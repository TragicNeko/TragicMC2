package tragicneko.tragicmc.items;

import java.util.Iterator;
import java.util.Map.Entry;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.Tuple;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.util.AmuletHelper;

import com.google.common.collect.Multimap;

public class RecipeAmulets extends ShapelessOreRecipe {

	public RecipeAmulets(ItemStack result, Object...recipe) {
		super(result, recipe);
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting crafting)
	{
		ItemStack stack = this.getRecipeOutput().copy();
		int level = 0;
		boolean flag = false;
		Tuple[] tuples = new Tuple[5];

		for (int i = 0; i < crafting.getSizeInventory(); i++)
		{
			ItemStack stack2 = crafting.getStackInSlot(i);

			if (stack2 != null && stack2.getItem() instanceof ItemAmulet)
			{
				if (stack2.hasTagCompound() && stack2.getTagCompound().hasKey("amuletLevel"))
				{
					int level2 = stack2.getTagCompound().getInteger("amuletLevel");
					if (level2 < 1) level2 = 1;

					if (!flag)
					{
						level = level2;
						flag = true;
					}
					else
					{
						level += 1;
						break;
					}
				}

				Multimap mmap = stack2.getAttributeModifiers();
				Iterator ite = mmap.entries().iterator();
				int j = 0;

				while (ite.hasNext())
				{
					Entry entry = (Entry) ite.next();
					AttributeModifier mod = (AttributeModifier) entry.getValue();
					if (j < 5) tuples[j++] = new Tuple(entry.getKey(), mod);
				}
			}
		}

		if (level > 3) level = 3;
		if (!stack.hasTagCompound()) stack.stackTagCompound = new NBTTagCompound();
		applyModifiersToItemStack(stack, tuples);
		stack.stackTagCompound.setInteger("amuletLevel", level);

		return stack;
	}

	public static void applyModifiersToItemStack(ItemStack stack, Tuple[] tuples)
	{
		NBTTagList taglist = new NBTTagList();
		NBTTagCompound tag;
		boolean flag = false;

		for (int i = 0; i < 3; i++)
		{
			Tuple tup = tuples[i];
			if (tup == null) continue;
			
			try
			{
				AttributeModifier mod = (AttributeModifier) tup.getSecond();
				String atrName = (String) tup.getFirst();
				
				tag = AmuletHelper.writeAttributeModifierToNBT(AmuletHelper.getAttributeFromEntity(Minecraft.getMinecraft().thePlayer, atrName), mod);
				taglist.appendTag(tag);
				flag = true;
			}
			catch (Exception e)
			{
				TragicMC.logError("Crafting errored while trying to apply modifiers to Amulet itemstack.", e);
				return;
			}
		}

		if (stack.hasTagCompound() && flag) stack.getTagCompound().setTag("AttributeModifiers", taglist);
	}
}
