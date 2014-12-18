package tragicneko.tragicmc.items;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.ShapelessOreRecipe;

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

		for (int i = 0; i < crafting.getSizeInventory(); ++i)
		{
			ItemStack stack2 = crafting.getStackInSlot(i);

			if (stack2 != null && stack2.hasTagCompound())
			{
				if (stack2.getTagCompound().hasKey("amuletLevel"))
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
			}
		}

		if (level > 3) level = 3;
		if (!stack.hasTagCompound()) stack.stackTagCompound = new NBTTagCompound();
		stack.stackTagCompound.setInteger("amuletLevel", level);

		return stack;
	}
}
