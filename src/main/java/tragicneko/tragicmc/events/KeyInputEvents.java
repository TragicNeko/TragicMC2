package tragicneko.tragicmc.events;

import static tragicneko.tragicmc.TragicMC.rand;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.Gui;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovementInput;
import net.minecraft.util.MovementInputFromOptions;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.client.ClientProxy;
import tragicneko.tragicmc.network.MessageGui;
import tragicneko.tragicmc.network.MessageUseDoomsday;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;

public class KeyInputEvents extends Gui {
	
	private static ResourceLocation hackedTexture = new ResourceLocation("tragicmc:textures/environment/collisionSky.png");

	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event)
	{
		if (Minecraft.getMinecraft().inGameHasFocus)
		{
			EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;

			if (player == null) return;

			if (ClientProxy.openAmuletGui.getIsKeyPressed() && TragicConfig.allowAmulets)
			{
				TragicMC.net.sendToServer(new MessageGui(TragicMC.idAmuletGui));
			}

			if (ClientProxy.useSpecial.getIsKeyPressed() && TragicConfig.allowDoomsdays)
			{
				TragicMC.net.sendToServer(new MessageUseDoomsday(player.getCurrentEquippedItem()));
			}
		}
	}

	@SubscribeEvent
	public void onClientUpdate(LivingUpdateEvent event)
	{
		if (!Minecraft.getMinecraft().inGameHasFocus) return;

		EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;

		if (player != null && TragicConfig.allowFlight && Keyboard.isCreated() && player.isPotionActive(TragicPotion.Flight.id) && player.ticksExisted % 2 == 0)
		{
			PotionEffect effect = player.getActivePotionEffect(TragicPotion.Flight);

			if (effect.getDuration() >= 40)
			{
				if (Keyboard.isKeyDown(Keyboard.KEY_SPACE))
				{
					if (player.isSprinting())
					{
						player.motionY = 0.215D;
					}
					else
					{
						player.motionY = 0.135D;
					}
				}
				else if (player.isSneaking())
				{
					player.motionY = -0.455D;
				}
			}
		}
		else if (player != null && TragicConfig.allowHacked && player.isPotionActive(TragicPotion.Hacked.id) && player.ticksExisted % 2 == 0)
		{
			PotionEffect effect = player.getActivePotionEffect(TragicPotion.Hacked);

			if (effect.getDuration() >= 40)
			{
				ItemStack current = player.getCurrentEquippedItem();
				if (current != null && rand.nextInt(128) == 0) player.dropOneItem(true);
				if (player.swingProgress == 1.0F) player.swingProgress = 0.0F;
				MovementInput input = new MovementInput();
				if (rand.nextInt(16) == 0) input.jump = true;
				if (rand.nextInt(4) == 0) input.moveForward = rand.nextFloat() * 1.4F - rand.nextFloat() * 1.4F;
				if (rand.nextInt(4) == 0) input.moveStrafe = rand.nextFloat() * 1.4F - rand.nextFloat() * 1.4F;
				if (rand.nextInt(32) == 0) input.sneak = true;
				player.movementInput = input;
			}
			else
			{
				player.movementInput = new MovementInputFromOptions(Minecraft.getMinecraft().gameSettings);
			}
		}
		
		boolean flag = TragicConfig.allowHacked ? player != null && player.isPotionActive(TragicPotion.Hacked) : false;
		if (!flag && !(player.movementInput instanceof MovementInputFromOptions)) player.movementInput = new MovementInputFromOptions(Minecraft.getMinecraft().gameSettings);
	}
	
	@SubscribeEvent
	public void renderHackedEffects(RenderGameOverlayEvent event)
	{
		if (event.type != ElementType.PORTAL || !TragicConfig.allowHacked) return;
		
		Minecraft mc = Minecraft.getMinecraft();
		
		if (!mc.thePlayer.isPotionActive(TragicPotion.Hacked)) return;
		
		mc.renderEngine.bindTexture(hackedTexture);
		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		float trans = MathHelper.cos(event.partialTicks / 2.25F) * 2.625F - 2.25F;
		GL11.glColor4f(1.0F, 1.0F, 1.0F, trans);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		
		drawTexturedModalRect(0, 0, 0, 0, mc.displayWidth, mc.displayHeight);
		
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(true);
	}
}
