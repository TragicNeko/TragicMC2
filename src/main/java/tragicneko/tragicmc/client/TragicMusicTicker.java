package tragicneko.tragicmc.client;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.MusicTicker.MusicType;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.client.event.sound.PlaySoundEvent17;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.dimension.SynapseWorldProvider;
import tragicneko.tragicmc.dimension.TragicWorldProvider;
import tragicneko.tragicmc.entity.alpha.EntityOverlordCore;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class TragicMusicTicker implements IUpdatePlayerListBox {

	private final Minecraft mc;
	private Random rand = new Random();

	private ISound currentTrack;
	private int buffer = 100;

	public static TragicMusic collisionTrack = new TragicMusic(new ResourceLocation("tragicmc:music.dimension.bog"), 200, 800);
	public static TragicMusic collisionCreative = new TragicMusic(new ResourceLocation("tragicmc:music.dimension.catacombs"), 1200, 3600);
	public static TragicMusic synapseTrack = new TragicMusic(new ResourceLocation("tragicmc:music.dimension.sanctuary"), 200, 600);
	public static TragicMusic synapseOverlord = new TragicMusic(new ResourceLocation("tragicmc:music.dimension.prime"), 0, 0);

	public TragicMusicTicker(Minecraft mc)
	{
		this.mc = mc;
	}

	@Override
	public void update() {
		MusicType musictype = mc.func_147109_W();
		if (mc.theWorld != null)
		{
			WorldProvider prov = mc.theWorld.provider;
			TragicMusic music = null; 
			/*
			if (this.currentTrack != null && !this.currentTrack.getPositionedSoundLocation().equals(synapseOverlord.loc) && mc.thePlayer != null && !mc.thePlayer.isPotionActive(TragicPotion.Nightmare) )
			{
				this.mc.getSoundHandler().stopSound(this.currentTrack);
			}

			if (mc.thePlayer != null && mc.thePlayer.isPotionActive(TragicPotion.Nightmare))
			{
				music = synapseOverlord;
			} */

			if (TragicConfig.allowDimensionalMusic)
			{
				if (prov instanceof TragicWorldProvider) // && mc.thePlayer != null && !mc.thePlayer.isPotionActive(TragicPotion.Nightmare))
				{
					music = musictype == MusicType.GAME ? collisionTrack : collisionCreative;
				}
				else if (prov instanceof SynapseWorldProvider) // && mc.thePlayer != null && !mc.thePlayer.isPotionActive(TragicPotion.Nightmare))
				{
					music = mc.thePlayer != null && !mc.theWorld.getEntitiesWithinAABB(EntityOverlordCore.class, mc.thePlayer.boundingBox.expand(120, 128, 120)).isEmpty() ? synapseOverlord : (musictype == MusicType.GAME ? synapseTrack : collisionCreative);
				}
			}

			if (/*mc.thePlayer != null && mc.thePlayer.isPotionActive(TragicPotion.Deafening) ||*/ music == null) return;

			if (this.currentTrack != null)
			{					
				if (!this.currentTrack.getPositionedSoundLocation().equals(music.loc))
				{
					this.mc.getSoundHandler().stopSound(this.currentTrack);
					this.buffer = MathHelper.getRandomIntegerInRange(this.rand, 0, music.min / 2);
				}

				if (!this.mc.getSoundHandler().isSoundPlaying(this.currentTrack))
				{
					this.currentTrack = null;
					this.buffer = Math.min(MathHelper.getRandomIntegerInRange(this.rand, music.min, music.max), this.buffer);
				}
			}

			if (this.currentTrack == null && /*(mc.thePlayer != null && mc.thePlayer.isPotionActive(TragicPotion.Nightmare) ||*/ this.buffer-- <= 0/*)*/ && mc.gameSettings.getSoundLevel(SoundCategory.MUSIC) > 0F && mc.gameSettings.getSoundLevel(SoundCategory.MASTER) > 0F)
			{
				//if (music != synapseOverlord && mc.thePlayer != null && !mc.thePlayer.isPotionActive(TragicPotion.Nightmare)) return;
				this.currentTrack = PositionedSoundRecord.func_147673_a(music.loc);
				this.mc.getSoundHandler().playSound(this.currentTrack);
				this.buffer = Integer.MAX_VALUE;
			}
		}
	}

	@SubscribeEvent
	public void overrideSound(PlaySoundEvent17 event)
	{
		MusicType musictype = mc.func_147109_W();

		if (event.sound.getPositionedSoundLocation().equals(musictype.getMusicTickerLocation()))
		{
			if (mc.theWorld != null && (mc.theWorld.provider instanceof TragicWorldProvider || mc.theWorld.provider instanceof SynapseWorldProvider))
			{
				event.result = null;
			}
		}
		/*
		if (mc.thePlayer != null)
		{
			if (mc.thePlayer.isPotionActive(TragicPotion.Deafening.id))
			{
				event.result = null;
				mc.getSoundHandler().stopSounds();
			}
		} */
	}

	public static class TragicMusic {
		public final ResourceLocation loc;
		public final int min;
		public final int max;

		public TragicMusic(ResourceLocation loc, int minBuf, int maxBuf)
		{
			this.loc = loc;
			this.min = minBuf;
			this.max = maxBuf;
		}
	}

}
