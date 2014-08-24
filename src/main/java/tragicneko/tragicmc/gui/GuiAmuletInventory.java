package tragicneko.tragicmc.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import tragicneko.tragicmc.client.ClientProxy;
import tragicneko.tragicmc.inventory.ContainerAmulet;
import tragicneko.tragicmc.inventory.InventoryAmulet;
import tragicneko.tragicmc.properties.PropertyAmulets;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiAmuletInventory extends GuiContainer
{
	/** x size of the inventory window in pixels. Defined as float, passed as int */
	private float xSize_lo;
	/** y size of the inventory window in pixels. Defined as float, passed as int. */
	private float ySize_lo;

	private static final ResourceLocation iconLocation = new ResourceLocation("tragicmc:textures/gui/amulet.png");
	private final InventoryAmulet inventory;

	private final PropertyAmulets amulets;

	public GuiAmuletInventory(EntityPlayer player, InventoryPlayer invPlayer, InventoryAmulet inventoryCustom) 
	{
		super(new ContainerAmulet(player, invPlayer, inventoryCustom));
		this.inventory = inventoryCustom;
		this.amulets = PropertyAmulets.get(player);
	}

	protected void keyTyped(char c, int keyCode) {
		super.keyTyped(c, keyCode);
		if (c == 1 || keyCode == ClientProxy.openAmuletGui.getKeyCode()) {
			mc.thePlayer.closeScreen();
		}
	}

	/**
	 * Draws the screen and all the components in it.
	 */
	public void drawScreen(int mouseX, int mouseY, float f) {
		super.drawScreen(mouseX, mouseY, f);
		xSize_lo = mouseX;
		ySize_lo = mouseY;
	}

	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of the items)
	 */
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {

		String s = inventory.hasCustomInventoryName() ? inventory.getInventoryName() : I18n.format(inventory.getInventoryName());
		
		//fontRendererObj.drawString(s, xSize - fontRendererObj.getStringWidth(s) - 40, 46, 4210752);
	}

	/**
	 * Draw the background layer for the GuiContainer (everything behind the items)
	 */
	protected void drawGuiContainerBackgroundLayer(float f, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(iconLocation);
		drawTexturedModalRect(guiLeft + 16, guiTop - 2, 0, 0, xSize, ySize);
	}

}
