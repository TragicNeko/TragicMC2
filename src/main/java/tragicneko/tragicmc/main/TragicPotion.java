package tragicneko.tragicmc.main;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class TragicPotion extends Potion {
	
	private ItemStack icon;

	public TragicPotion(int par1, boolean par2, int par3) {
		super(par1, par2, par3);
	}
	
	public void setIcon(ItemStack icon)
	{
		this.icon = icon;
	}
	
	@Override
	public void renderInventoryEffect(int x, int y, PotionEffect effect, net.minecraft.client.Minecraft mc)
	{
		x += 6;
		y += 7;
		net.minecraft.client.renderer.entity.RenderItem itemRender = new net.minecraft.client.renderer.entity.RenderItem();
		if (this.icon == null) this.icon = new ItemStack(Items.apple);
		itemRender.renderItemIntoGUI(mc.fontRenderer, mc.getTextureManager(), this.icon, x, y);
	}
}
