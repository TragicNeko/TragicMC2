package tragicneko.tragicmc.client.render.item;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import tragicneko.tragicmc.client.model.ModelBlock;

public class RenderEpicWeapon implements IItemRenderer {

	private final String path = "tragicmc:textures/items/";
	private final Minecraft mc;
	public final ModelBase model;
	public final ResourceLocation texture;
	
	public ModelBase[] models = new ModelBase[] {new ModelBlock(), new ModelBlock(), new ModelBlock(), new ModelBlock(), new ModelBlock(), new ModelBlock(),
		new ModelBlock(), new ModelBlock(), new ModelBlock()};
	
	public ResourceLocation[] textures = new ResourceLocation[] {new ResourceLocation(path + "Butcher_lowRes.png"),
		new ResourceLocation(path + "DragonFang_lowRes.png"), new ResourceLocation(path + "Thardus_lowRes.png"), new ResourceLocation(path + "Splinter_lowRes.png"),
		new ResourceLocation(path + "Paranoia_lowRes.png"), new ResourceLocation(path + "CelestialAegis_lowRes.png"), new ResourceLocation(path + "Titan_lowRes.png"),
		new ResourceLocation(path + "TragicHellraiser_lowRes.png"), new ResourceLocation(path + "OverlordSentinel_lowRes.png")};

	public RenderEpicWeapon(int i, Minecraft mc)
	{
		this.model = this.getModelFromItem(i);
		this.texture = this.getTextureFromItem(i);
		this.mc = mc;
	}

	private ResourceLocation getTextureFromItem(int i) {
		return textures[i];
	}

	private ModelBase getModelFromItem(int i) {
		return models[i];
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		GL11.glPushMatrix();
		mc.renderEngine.bindTexture(texture);
		model.render((Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.625F);
		GL11.glPopMatrix();
	}

}
