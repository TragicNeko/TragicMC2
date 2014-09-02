package tragicneko.tragicmc.client.gui;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

import org.lwjgl.opengl.GL11;

import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.properties.PropertyAmulets;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiAmuletStatus extends Gui
{
	private Minecraft mc;
	private int buffer;
	private int width;
	private static final RenderItem itemRender = new RenderItem();

	private static final ResourceLocation texturepath = new ResourceLocation("tragicmc:textures/gui/amulet_status_minimal.png");

	public GuiAmuletStatus(Minecraft mc) {
		super();
		this.mc = mc;
	}

	@SubscribeEvent(priority=EventPriority.NORMAL)
	public void onRenderOverlay(RenderGameOverlayEvent event)
	{
		if (event.isCancelable() || event.type != ElementType.EXPERIENCE || Minecraft.getMinecraft().gameSettings.showDebugInfo) return;

		PropertyAmulets amu = PropertyAmulets.get(this.mc.thePlayer);
		if (amu == null || amu.getSlotsOpen() <= 0) {
			return; 
		}

		int xPos = 2;
		int yPos = 22;
		this.mc.getTextureManager().bindTexture(texturepath);
		int length = 0;
		if (amu.getSlotsOpen() == 1) length = 20;
		if (amu.getSlotsOpen() == 2) length = 40;
		if (amu.getSlotsOpen() == 3) length = 60;

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_ALPHA_TEST);

		drawTexturedModalRect(xPos, yPos, 0, 0, length, 20);

		width++;

		if (width > 30)
		{
			width = 0;
		}

		if (!TragicNewConfig.allowAnimatedGui) width = 0;

		ItemStack stack;

		for (int i = 0; i < 3; i++)
		{
			if (amu.inventory.getStackInSlot(i) != null)
			{
				stack = amu.inventory.getStackInSlot(i);
				
				itemRender.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.getTextureManager(), stack, xPos + 3 + (20 * i), yPos + 2);
				itemRender.renderItemOverlayIntoGUI(mc.fontRenderer, mc.getTextureManager(), stack, xPos + 2 + (20 * i), yPos + 4);
				GL11.glDisable(GL11.GL_LIGHTING);
				String s = "" + (i + 1);
				Color color = new Color(0xAA, 0x57, 0x57);
				//this.mc.fontRenderer.drawString(s.trim(), xPos + 14 + (20 * i), yPos + 2, color.getRGB());
				
			}
		}


		buffer++;

		if (buffer > 20)
		{
			buffer = 0;
		}

		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(true);
	}
}
