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

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.blocks.BlockGenericLeaves;
import tragicneko.tragicmc.client.ClientProxy;
import tragicneko.tragicmc.network.MessageGui;
import tragicneko.tragicmc.network.MessageUseDoomsday;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;

public class ClientEvents extends Gui {

	private static ResourceLocation hackedTexture = new ResourceLocation("tragicmc:textures/environment/collisionSky.png");
	private static ResourceLocation divinityTexture = new ResourceLocation("tragicmc:textures/environment/divinity.png");

	private static final float[][] rgb = new float[][] {{1F, 1F, 1F}, {1F, 0.3F, 0.3F}, {0.3F, 0.9F, 0.9F}, {0F, 0.8F, 0F}, {1F, 0.3F, 1F}, {0.8F, 0F, 0F}, {0F, 0F, 0.8F},
		{0.8F, 0.3F, 0.5F}, {0.6F, 0.3F, 0.9F}, {0.3F, 0.3F, 0.3F}, {0.6F, 0.6F, 0.9F}, {0F, 0F, 0F}};
	private static int counter;
	private static int color;
	private static int buffer;

	private static final String[] sounds = new String[] {"mob.enderdragon.growl", "random.fizz", "mob.enderdragon.wings", "mob.endermen.portal", "mob.zombie.hurt",
			"mob.skeleton.hurt", "random.bow", "random.explode", "random.chestopen", "mob.wither.hurt", "mob.wither.idle", "random.door_open",
			"game.hostile.hurt", "creeper.primed", "random.break", "random.wood_click", "mob.endermen.scream", "mob.endermen.stare"};
	
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

		Minecraft mc = Minecraft.getMinecraft();
		BlockGenericLeaves.fancyGraphics = mc.isFancyGraphicsEnabled();
		EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
		
		if (player != null && TragicConfig.allowFlight && player.isPotionActive(TragicPotion.Flight.id))
		{
			boolean flag = !TragicConfig.allowStun || TragicConfig.allowStun && !player.isPotionActive(TragicPotion.Stun.id);
			
			if (flag && Keyboard.isCreated() && player.ticksExisted % 2 == 0)
			{
				PotionEffect effect = player.getActivePotionEffect(TragicPotion.Flight);
				int dur = effect.getDuration();

				if (Keyboard.isKeyDown(Keyboard.KEY_SPACE))
				{
					player.motionY = player.isSprinting() ? 0.245D : 0.165D;
				}
				else if (player.isSneaking())
				{
					player.motionY = -0.455D;
				}
			}
		}

		if (player != null && TragicConfig.allowHacked && player.isPotionActive(TragicPotion.Hacked.id) && player.ticksExisted % 2 == 0)
		{
			PotionEffect effect = player.getActivePotionEffect(TragicPotion.Hacked);

			ItemStack current = player.getCurrentEquippedItem();
			if (current != null && rand.nextInt(1048) == 0 && rand.nextInt(1048) == 42) player.dropOneItem(true);
			if (player.swingProgress == 1.0F) player.swingProgress = 0.0F;
			MovementInput input = new MovementInput();
			if (rand.nextInt(4) == 0) input.jump = true;
			if (rand.nextInt(4) == 0) input.moveForward = rand.nextFloat() * 1.4F - rand.nextFloat() * 1.4F;
			if (rand.nextInt(4) == 0) input.moveStrafe = rand.nextFloat() * 1.4F - rand.nextFloat() * 1.4F;
			if (rand.nextInt(32) == 0) input.sneak = true;
			player.movementInput = input;
		}

		if (player != null && !(player.movementInput instanceof MovementInputFromOptions) && TragicConfig.allowHacked && !player.isPotionActive(TragicPotion.Hacked)) player.movementInput = new MovementInputFromOptions(mc.gameSettings);

		if (player != null && TragicConfig.allowDisorientation && player.isPotionActive(TragicPotion.Disorientation) && player.ticksExisted % 2 == 0)
		{
			player.rotationPitch += (rand.nextFloat() - rand.nextFloat()) * 2.25F;
			player.rotationYaw += (rand.nextFloat() - rand.nextFloat()) * 2.25F;
		}
		
		if (player != null && TragicConfig.allowStun && player.isPotionActive(TragicPotion.Stun))
		{
			player.prevRotationPitch = player.rotationPitch;
			player.prevRotationYaw = player.rotationYaw;
			player.rotationYawHead = player.prevRotationYawHead;
			player.movementInput.jump = false;
			player.movementInput.sneak = false;
		}

		if (player != null && TragicConfig.allowFear && player.isPotionActive(TragicPotion.Fear))
		{
			buffer++;
			if (player.ticksExisted % 120 == 0 && rand.nextBoolean() && buffer >= 1000)
			{
				buffer = 0;
				player.playSound(sounds[rand.nextInt(sounds.length)], rand.nextFloat() * 0.3F + 0.7F, 1.0F);
			}
			player.swingItem();
		}
	}

	@SubscribeEvent
	public void renderHackedEffects(RenderGameOverlayEvent event)
	{
		if (event.type != ElementType.PORTAL) return;

		Minecraft mc = Minecraft.getMinecraft();

		boolean flag = TragicConfig.allowHacked && mc.thePlayer.isPotionActive(TragicPotion.Hacked);
		boolean flag2 = TragicConfig.allowDivinity && mc.thePlayer.isPotionActive(TragicPotion.Divinity);
		boolean flag3 = TragicConfig.allowConvergence && mc.thePlayer.isPotionActive(TragicPotion.Convergence);

		if (!flag && !flag2 && !flag3) return;

		mc.renderEngine.bindTexture(flag ? hackedTexture : divinityTexture);

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		float f = 2.25F;
		float trans = flag ? MathHelper.cos(event.partialTicks / f) * 2.625F - f : 0.0725F;

		float r = rgb[color][0];
		float g = flag3 ? 0F : rgb[color][1];
		float b = flag3 ? 0F : rgb[color][2];
		GL11.glColor4f(r, g, b, trans);
		GL11.glDisable(GL11.GL_ALPHA_TEST);

		drawTexturedModalRect(0, 0, 0, 0, mc.displayWidth, mc.displayHeight);

		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(true);

		counter++;
		if (counter > 60 + rand.nextInt(20))
		{
			counter = 0;
			color = 0; //rand.nextInt(12);
		}
	}
}
