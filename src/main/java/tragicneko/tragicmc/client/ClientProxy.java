package tragicneko.tragicmc.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraftforge.common.MinecraftForge;

import org.lwjgl.input.Keyboard;

import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.client.gui.GuiDoom;
import tragicneko.tragicmc.client.model.ModelAbomination;
import tragicneko.tragicmc.client.model.ModelDeathReaper;
import tragicneko.tragicmc.client.model.ModelGragul;
import tragicneko.tragicmc.client.model.ModelGreaterStin;
import tragicneko.tragicmc.client.model.ModelInkling;
import tragicneko.tragicmc.client.model.ModelJabba;
import tragicneko.tragicmc.client.model.ModelJarra;
import tragicneko.tragicmc.client.model.ModelKragul;
import tragicneko.tragicmc.client.model.ModelMinotaur;
import tragicneko.tragicmc.client.model.ModelNorVox;
import tragicneko.tragicmc.client.model.ModelPirah;
import tragicneko.tragicmc.client.model.ModelPlague;
import tragicneko.tragicmc.client.model.ModelPumpkinhead;
import tragicneko.tragicmc.client.model.ModelRagr;
import tragicneko.tragicmc.client.model.ModelStin;
import tragicneko.tragicmc.client.model.ModelStinBaby;
import tragicneko.tragicmc.client.model.ModelStinKing;
import tragicneko.tragicmc.client.model.ModelStinQueen;
import tragicneko.tragicmc.client.model.ModelTox;
import tragicneko.tragicmc.client.model.ModelTragicNeko;
import tragicneko.tragicmc.client.render.RenderProjectile;
import tragicneko.tragicmc.client.render.RenderStatue;
import tragicneko.tragicmc.client.render.boss.RenderApis;
import tragicneko.tragicmc.client.render.boss.RenderDeathReaper;
import tragicneko.tragicmc.client.render.boss.RenderKitsune;
import tragicneko.tragicmc.client.render.boss.RenderMegaCryse;
import tragicneko.tragicmc.client.render.boss.RenderPolaris;
import tragicneko.tragicmc.client.render.boss.RenderTimeController;
import tragicneko.tragicmc.client.render.boss.RenderYeti;
import tragicneko.tragicmc.client.render.mob.RenderCryse;
import tragicneko.tragicmc.client.render.mob.RenderMob;
import tragicneko.tragicmc.client.render.mob.RenderStarCryse;
import tragicneko.tragicmc.client.render.mob.RenderStarVox;
import tragicneko.tragicmc.client.render.mob.RenderWisp;
import tragicneko.tragicmc.entity.EntityStatue;
import tragicneko.tragicmc.entity.boss.EntityApis;
import tragicneko.tragicmc.entity.boss.EntityDeathReaper;
import tragicneko.tragicmc.entity.boss.EntityGreaterStin;
import tragicneko.tragicmc.entity.boss.EntityJarra;
import tragicneko.tragicmc.entity.boss.EntityKitsune;
import tragicneko.tragicmc.entity.boss.EntityKragul;
import tragicneko.tragicmc.entity.boss.EntityMagmox;
import tragicneko.tragicmc.entity.boss.EntityMegaCryse;
import tragicneko.tragicmc.entity.boss.EntityPolaris;
import tragicneko.tragicmc.entity.boss.EntityStinKing;
import tragicneko.tragicmc.entity.boss.EntityStinQueen;
import tragicneko.tragicmc.entity.boss.EntityTimeController;
import tragicneko.tragicmc.entity.boss.EntityYeti;
import tragicneko.tragicmc.entity.mob.EntityAbomination;
import tragicneko.tragicmc.entity.mob.EntityCryse;
import tragicneko.tragicmc.entity.mob.EntityDeathReaperClone;
import tragicneko.tragicmc.entity.mob.EntityGragul;
import tragicneko.tragicmc.entity.mob.EntityInkling;
import tragicneko.tragicmc.entity.mob.EntityJabba;
import tragicneko.tragicmc.entity.mob.EntityJanna;
import tragicneko.tragicmc.entity.mob.EntityLavaPirah;
import tragicneko.tragicmc.entity.mob.EntityMinotaur;
import tragicneko.tragicmc.entity.mob.EntityNorVox;
import tragicneko.tragicmc.entity.mob.EntityPirah;
import tragicneko.tragicmc.entity.mob.EntityPlague;
import tragicneko.tragicmc.entity.mob.EntityPox;
import tragicneko.tragicmc.entity.mob.EntityPumpkinhead;
import tragicneko.tragicmc.entity.mob.EntityRagr;
import tragicneko.tragicmc.entity.mob.EntityStarCryse;
import tragicneko.tragicmc.entity.mob.EntityStarVox;
import tragicneko.tragicmc.entity.mob.EntityStin;
import tragicneko.tragicmc.entity.mob.EntityStinBaby;
import tragicneko.tragicmc.entity.mob.EntityTox;
import tragicneko.tragicmc.entity.mob.EntityTragicNeko;
import tragicneko.tragicmc.entity.mob.EntityWisp;
import tragicneko.tragicmc.entity.projectile.EntityBanana;
import tragicneko.tragicmc.entity.projectile.EntityIcicle;
import tragicneko.tragicmc.entity.projectile.EntityLargePumpkinbomb;
import tragicneko.tragicmc.entity.projectile.EntityLargeRock;
import tragicneko.tragicmc.entity.projectile.EntityNekoClusterBomb;
import tragicneko.tragicmc.entity.projectile.EntityNekoMiniBomb;
import tragicneko.tragicmc.entity.projectile.EntityNekoRocket;
import tragicneko.tragicmc.entity.projectile.EntityNekoStickyBomb;
import tragicneko.tragicmc.entity.projectile.EntityPoisonBarb;
import tragicneko.tragicmc.entity.projectile.EntityPumpkinbomb;
import tragicneko.tragicmc.entity.projectile.EntitySolarBomb;
import tragicneko.tragicmc.entity.projectile.EntitySpiritCast;
import tragicneko.tragicmc.entity.projectile.EntitySpore;
import tragicneko.tragicmc.entity.projectile.EntityThrowingRock;
import tragicneko.tragicmc.entity.projectile.EntityTimeBomb;
import tragicneko.tragicmc.entity.projectile.EntityTimeDisruption;
import tragicneko.tragicmc.main.TragicItems;
import tragicneko.tragicmc.main.TragicNewConfig;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class ClientProxy extends CommonProxy {

	public static KeyBinding useSpecial;
	public static KeyBinding openAmuletGui;	

	@Override
	public void registerRenders()
	{
		//Gui event registration
		if (TragicNewConfig.showDoomGui)
		{
			MinecraftForge.EVENT_BUS.register(new GuiDoom(Minecraft.getMinecraft()));
		}

		//Keybinding registrations
		useSpecial = new KeyBinding("Special Use", Keyboard.KEY_R, TragicMC.MODNAME);
		ClientRegistry.registerKeyBinding(useSpecial);

		openAmuletGui = new KeyBinding("Open Amulet Gui", Keyboard.KEY_P, TragicMC.MODNAME);
		ClientRegistry.registerKeyBinding(openAmuletGui);

		//Projectile and non-mob entity renders
		RenderingRegistry.registerEntityRenderingHandler(EntityThrowingRock.class, new RenderProjectile(TragicItems.Rock));
		RenderingRegistry.registerEntityRenderingHandler(EntityPumpkinbomb.class, new RenderProjectile(TragicItems.Pumpkinbomb));
		RenderingRegistry.registerEntityRenderingHandler(EntityLargePumpkinbomb.class, new RenderProjectile(TragicItems.Pumpkinbomb));
		RenderingRegistry.registerEntityRenderingHandler(EntityPoisonBarb.class, new RenderProjectile(TragicItems.PoisonBarb));

		RenderingRegistry.registerEntityRenderingHandler(EntityNekoRocket.class, new RenderProjectile(TragicItems.NekoRocket));
		RenderingRegistry.registerEntityRenderingHandler(EntityNekoStickyBomb.class, new RenderProjectile(TragicItems.NekoStickyBomb));
		RenderingRegistry.registerEntityRenderingHandler(EntityNekoClusterBomb.class, new RenderProjectile(TragicItems.NekoClusterBomb));
		RenderingRegistry.registerEntityRenderingHandler(EntityNekoMiniBomb.class, new RenderProjectile(TragicItems.NekoClusterBomb));

		RenderingRegistry.registerEntityRenderingHandler(EntitySolarBomb.class, new RenderProjectile(TragicItems.SolarBomb));
		RenderingRegistry.registerEntityRenderingHandler(EntitySpiritCast.class, new RenderProjectile(TragicItems.SpiritCast));
		RenderingRegistry.registerEntityRenderingHandler(EntitySpore.class, new RenderProjectile(TragicItems.Spore));
		RenderingRegistry.registerEntityRenderingHandler(EntityBanana.class, new RenderProjectile(TragicItems.Banana));

		RenderingRegistry.registerEntityRenderingHandler(EntityLargeRock.class, new RenderProjectile(TragicItems.IceOrb));
		RenderingRegistry.registerEntityRenderingHandler(EntityIcicle.class, new RenderProjectile(TragicItems.CrushedIce));

		RenderingRegistry.registerEntityRenderingHandler(EntityTimeDisruption.class, new RenderProjectile(Items.clock));
		RenderingRegistry.registerEntityRenderingHandler(EntityTimeBomb.class, new RenderProjectile(Items.clock));

		RenderingRegistry.registerEntityRenderingHandler(EntityStatue.class, new RenderStatue());

		//Mob renders
		if (TragicNewConfig.allowJabba) RenderingRegistry.registerEntityRenderingHandler(EntityJabba.class, new RenderMob(new ModelJabba(), 0.355F, "Jabba_lowRes"));
		if (TragicNewConfig.allowJarra) RenderingRegistry.registerEntityRenderingHandler(EntityJarra.class, new RenderMob(new ModelJarra(), 0.355F, "Jarra_lowRes", 1.585F));
		if (TragicNewConfig.allowJanna) RenderingRegistry.registerEntityRenderingHandler(EntityJanna.class, new RenderMob(new ModelJarra(), 0.355F, "Janna_lowRes", 0.825F));

		if (TragicNewConfig.allowPlague) RenderingRegistry.registerEntityRenderingHandler(EntityPlague.class, new RenderMob(new ModelPlague(), 0.115F, "Plague_lowRes"));

		if (TragicNewConfig.allowGragul) RenderingRegistry.registerEntityRenderingHandler(EntityGragul.class, new RenderMob(new ModelGragul(), 0.115F, "Gragul_lowRes"));
		if (TragicNewConfig.allowKragul) RenderingRegistry.registerEntityRenderingHandler(EntityKragul.class, new RenderMob(new ModelKragul(), 0.115F, "Kragul_lowRes", 2.115F));

		if (TragicNewConfig.allowMinotaur) RenderingRegistry.registerEntityRenderingHandler(EntityMinotaur.class, new RenderMob(new ModelMinotaur(), 0.337F, "Minotaur_lowRes"));
		if (TragicNewConfig.allowRagr)	RenderingRegistry.registerEntityRenderingHandler(EntityRagr.class, new RenderMob(new ModelRagr(), 0.435F, "Ragr_lowRes"));
		if (TragicNewConfig.allowInkling) RenderingRegistry.registerEntityRenderingHandler(EntityInkling.class, new RenderMob(new ModelInkling(), 0.175F, "Inkling_lowRes"));
		if (TragicNewConfig.allowPumpkinhead) RenderingRegistry.registerEntityRenderingHandler(EntityPumpkinhead.class, new RenderMob(new ModelPumpkinhead(), 0.375F, "Pumpkinhead_lowRes"));
		if (TragicNewConfig.allowTragicNeko) RenderingRegistry.registerEntityRenderingHandler(EntityTragicNeko.class, new RenderMob(new ModelTragicNeko(), 0.295F, "TragicNeko_lowRes"));

		if (TragicNewConfig.allowTox) RenderingRegistry.registerEntityRenderingHandler(EntityTox.class, new RenderMob(new ModelTox(), 0.565F, "Tox_lowRes"));
		if (TragicNewConfig.allowMagmox) RenderingRegistry.registerEntityRenderingHandler(EntityMagmox.class, new RenderMob(new ModelTox(), 0.565F, "Magmox_lowRes", 1.625F));
		if (TragicNewConfig.allowPox) RenderingRegistry.registerEntityRenderingHandler(EntityPox.class, new RenderMob(new ModelTox(), 0.565F, "Pox_lowRes", 0.635F));

		if (TragicNewConfig.allowCryse) RenderingRegistry.registerEntityRenderingHandler(EntityCryse.class, new RenderCryse());
		if (TragicNewConfig.allowMegaCryse) RenderingRegistry.registerEntityRenderingHandler(EntityMegaCryse.class, new RenderMegaCryse());
		if (TragicNewConfig.allowStarCryse) RenderingRegistry.registerEntityRenderingHandler(EntityStarCryse.class, new RenderStarCryse());

		if (TragicNewConfig.allowNorVox) RenderingRegistry.registerEntityRenderingHandler(EntityNorVox.class, new RenderMob(new ModelNorVox(), 0.625F, "NorVox_lowRes", 1.455F));
		if (TragicNewConfig.allowStarVox) RenderingRegistry.registerEntityRenderingHandler(EntityStarVox.class, new RenderStarVox());

		if (TragicNewConfig.allowPirah) RenderingRegistry.registerEntityRenderingHandler(EntityPirah.class, new RenderMob(new ModelPirah(), 0.225F, "Pirah_lowRes"));
		if (TragicNewConfig.allowLavaPirah) RenderingRegistry.registerEntityRenderingHandler(EntityLavaPirah.class, new RenderMob(new ModelPirah(), 0.225F, "LavaPirah_lowRes", 1.225F));

		if (TragicNewConfig.allowStin) RenderingRegistry.registerEntityRenderingHandler(EntityStin.class, new RenderMob(new ModelStin(), 0.665F, "Stin_lowRes"));
		if (TragicNewConfig.allowStinBaby) RenderingRegistry.registerEntityRenderingHandler(EntityStinBaby.class, new RenderMob(new ModelStinBaby(), 0.255F, "Stin_lowRes"));
		if (TragicNewConfig.allowGreaterStin) RenderingRegistry.registerEntityRenderingHandler(EntityGreaterStin.class, new RenderMob(new ModelGreaterStin(), 0.675F, "GreaterStin_lowRes"));
		if (TragicNewConfig.allowStinKing) RenderingRegistry.registerEntityRenderingHandler(EntityStinKing.class, new RenderMob(new ModelStinKing(), 0.675F, "StinKing_lowRes", 1.625F));
		if (TragicNewConfig.allowStinQueen) RenderingRegistry.registerEntityRenderingHandler(EntityStinQueen.class, new RenderMob(new ModelStinQueen(), 0.675F, "StinQueen_lowRes", 1.225F));

		if (TragicNewConfig.allowWisp) RenderingRegistry.registerEntityRenderingHandler(EntityWisp.class, new RenderWisp());
		if (TragicNewConfig.allowAbomination) RenderingRegistry.registerEntityRenderingHandler(EntityAbomination.class, new RenderMob(new ModelAbomination(), 0.35F, "Abomination_lowRes"));

		//Boss renders
		if (TragicNewConfig.allowApis) RenderingRegistry.registerEntityRenderingHandler(EntityApis.class, new RenderApis());
		if (TragicNewConfig.allowDeathReaper)
		{
			RenderingRegistry.registerEntityRenderingHandler(EntityDeathReaper.class, new RenderDeathReaper());
			RenderingRegistry.registerEntityRenderingHandler(EntityDeathReaperClone.class, new RenderMob(new ModelDeathReaper(), 0.375F, "DeathReaper_lowRes", 0.875F));
		}
		if (TragicNewConfig.allowKitsune) RenderingRegistry.registerEntityRenderingHandler(EntityKitsune.class, new RenderKitsune());
		if (TragicNewConfig.allowPolaris) RenderingRegistry.registerEntityRenderingHandler(EntityPolaris.class, new RenderPolaris());
		if (TragicNewConfig.allowYeti) RenderingRegistry.registerEntityRenderingHandler(EntityYeti.class, new RenderYeti());
		if (TragicNewConfig.allowTimeController) RenderingRegistry.registerEntityRenderingHandler(EntityTimeController.class, new RenderTimeController());

	}

	@Override
	public EntityPlayer getPlayerFromMessageCtx(MessageContext ctx)
	{
		return Minecraft.getMinecraft().thePlayer;
	}

}
