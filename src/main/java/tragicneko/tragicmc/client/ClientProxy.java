package tragicneko.tragicmc.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

import org.lwjgl.input.Keyboard;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.blocks.tileentity.TileEntitySoulChest;
import tragicneko.tragicmc.client.gui.GuiAmuletStatus;
import tragicneko.tragicmc.client.gui.GuiDoom;
import tragicneko.tragicmc.client.model.ModelAbomination;
import tragicneko.tragicmc.client.model.ModelGragul;
import tragicneko.tragicmc.client.model.ModelGreaterStin;
import tragicneko.tragicmc.client.model.ModelHarvester;
import tragicneko.tragicmc.client.model.ModelHunter;
import tragicneko.tragicmc.client.model.ModelInkling;
import tragicneko.tragicmc.client.model.ModelJarra;
import tragicneko.tragicmc.client.model.ModelKragul;
import tragicneko.tragicmc.client.model.ModelLockbot;
import tragicneko.tragicmc.client.model.ModelMinotaur;
import tragicneko.tragicmc.client.model.ModelNanoSwarm;
import tragicneko.tragicmc.client.model.ModelPlague;
import tragicneko.tragicmc.client.model.ModelPsygote;
import tragicneko.tragicmc.client.model.ModelPumpkinhead;
import tragicneko.tragicmc.client.model.ModelRagr;
import tragicneko.tragicmc.client.model.ModelSeeker;
import tragicneko.tragicmc.client.model.ModelSirv;
import tragicneko.tragicmc.client.model.ModelStinKing;
import tragicneko.tragicmc.client.model.ModelStinQueen;
import tragicneko.tragicmc.client.model.ModelTox;
import tragicneko.tragicmc.client.model.ModelTragicNeko;
import tragicneko.tragicmc.client.render.RenderDarkCrystal;
import tragicneko.tragicmc.client.render.RenderDimensionalAnomaly;
import tragicneko.tragicmc.client.render.RenderDirectedLightning;
import tragicneko.tragicmc.client.render.RenderEpicWeapon;
import tragicneko.tragicmc.client.render.RenderGuardianShield;
import tragicneko.tragicmc.client.render.RenderLargeRock;
import tragicneko.tragicmc.client.render.RenderLock;
import tragicneko.tragicmc.client.render.RenderProjectile;
import tragicneko.tragicmc.client.render.RenderSoulChest;
import tragicneko.tragicmc.client.render.RenderStatue;
import tragicneko.tragicmc.client.render.RenderTimeDisruption;
import tragicneko.tragicmc.client.render.alpha.RenderOverlordCocoon;
import tragicneko.tragicmc.client.render.alpha.RenderOverlordCombat;
import tragicneko.tragicmc.client.render.alpha.RenderOverlordCore;
import tragicneko.tragicmc.client.render.boss.RenderAegar;
import tragicneko.tragicmc.client.render.boss.RenderApis;
import tragicneko.tragicmc.client.render.boss.RenderClaymation;
import tragicneko.tragicmc.client.render.boss.RenderDeathReaper;
import tragicneko.tragicmc.client.render.boss.RenderEnyvil;
import tragicneko.tragicmc.client.render.boss.RenderKitsune;
import tragicneko.tragicmc.client.render.boss.RenderMegaCryse;
import tragicneko.tragicmc.client.render.boss.RenderPolaris;
import tragicneko.tragicmc.client.render.boss.RenderTimeController;
import tragicneko.tragicmc.client.render.boss.RenderVoxStellarum;
import tragicneko.tragicmc.client.render.boss.RenderYeti;
import tragicneko.tragicmc.client.render.mob.RenderCryse;
import tragicneko.tragicmc.client.render.mob.RenderErkel;
import tragicneko.tragicmc.client.render.mob.RenderIre;
import tragicneko.tragicmc.client.render.mob.RenderJabba;
import tragicneko.tragicmc.client.render.mob.RenderMob;
import tragicneko.tragicmc.client.render.mob.RenderNorVox;
import tragicneko.tragicmc.client.render.mob.RenderPirah;
import tragicneko.tragicmc.client.render.mob.RenderStin;
import tragicneko.tragicmc.client.render.mob.RenderTox;
import tragicneko.tragicmc.client.render.mob.RenderWisp;
import tragicneko.tragicmc.entity.EntityDarkCrystal;
import tragicneko.tragicmc.entity.EntityDimensionalAnomaly;
import tragicneko.tragicmc.entity.EntityDirectedLightning;
import tragicneko.tragicmc.entity.EntityLock;
import tragicneko.tragicmc.entity.EntityStatue;
import tragicneko.tragicmc.entity.EntityTimeDisruption;
import tragicneko.tragicmc.entity.alpha.EntityOverlordCocoon;
import tragicneko.tragicmc.entity.alpha.EntityOverlordCombat;
import tragicneko.tragicmc.entity.alpha.EntityOverlordCore;
import tragicneko.tragicmc.entity.boss.EntityApis;
import tragicneko.tragicmc.entity.boss.EntityClaymation;
import tragicneko.tragicmc.entity.boss.EntityDeathReaper;
import tragicneko.tragicmc.entity.boss.EntityEnyvil;
import tragicneko.tragicmc.entity.boss.EntityKitsune;
import tragicneko.tragicmc.entity.boss.EntityPolaris;
import tragicneko.tragicmc.entity.boss.EntityTimeController;
import tragicneko.tragicmc.entity.boss.EntityYeti;
import tragicneko.tragicmc.entity.miniboss.EntityAegar;
import tragicneko.tragicmc.entity.miniboss.EntityGreaterStin;
import tragicneko.tragicmc.entity.miniboss.EntityJarra;
import tragicneko.tragicmc.entity.miniboss.EntityKragul;
import tragicneko.tragicmc.entity.miniboss.EntityMagmox;
import tragicneko.tragicmc.entity.miniboss.EntityMegaCryse;
import tragicneko.tragicmc.entity.miniboss.EntityStinKing;
import tragicneko.tragicmc.entity.miniboss.EntityStinQueen;
import tragicneko.tragicmc.entity.miniboss.EntityVoxStellarum;
import tragicneko.tragicmc.entity.mob.EntityAbomination;
import tragicneko.tragicmc.entity.mob.EntityCryse;
import tragicneko.tragicmc.entity.mob.EntityErkel;
import tragicneko.tragicmc.entity.mob.EntityGragul;
import tragicneko.tragicmc.entity.mob.EntityHarvester;
import tragicneko.tragicmc.entity.mob.EntityHunter;
import tragicneko.tragicmc.entity.mob.EntityInkling;
import tragicneko.tragicmc.entity.mob.EntityIre;
import tragicneko.tragicmc.entity.mob.EntityJabba;
import tragicneko.tragicmc.entity.mob.EntityLockbot;
import tragicneko.tragicmc.entity.mob.EntityMinotaur;
import tragicneko.tragicmc.entity.mob.EntityNanoSwarm;
import tragicneko.tragicmc.entity.mob.EntityNorVox;
import tragicneko.tragicmc.entity.mob.EntityPirah;
import tragicneko.tragicmc.entity.mob.EntityPlague;
import tragicneko.tragicmc.entity.mob.EntityPsygote;
import tragicneko.tragicmc.entity.mob.EntityPumpkinhead;
import tragicneko.tragicmc.entity.mob.EntityRagr;
import tragicneko.tragicmc.entity.mob.EntitySeeker;
import tragicneko.tragicmc.entity.mob.EntitySirv;
import tragicneko.tragicmc.entity.mob.EntityStin;
import tragicneko.tragicmc.entity.mob.EntityTox;
import tragicneko.tragicmc.entity.mob.EntityTragicNeko;
import tragicneko.tragicmc.entity.mob.EntityWisp;
import tragicneko.tragicmc.entity.projectile.EntityBanana;
import tragicneko.tragicmc.entity.projectile.EntityCrystalMortor;
import tragicneko.tragicmc.entity.projectile.EntityDarkEnergy;
import tragicneko.tragicmc.entity.projectile.EntityDarkLightning;
import tragicneko.tragicmc.entity.projectile.EntityDarkMortor;
import tragicneko.tragicmc.entity.projectile.EntityGuardianShield;
import tragicneko.tragicmc.entity.projectile.EntityIcicle;
import tragicneko.tragicmc.entity.projectile.EntityIreEnergy;
import tragicneko.tragicmc.entity.projectile.EntityLargePumpkinbomb;
import tragicneko.tragicmc.entity.projectile.EntityLargeRock;
import tragicneko.tragicmc.entity.projectile.EntityNekoClusterBomb;
import tragicneko.tragicmc.entity.projectile.EntityNekoMiniBomb;
import tragicneko.tragicmc.entity.projectile.EntityNekoRocket;
import tragicneko.tragicmc.entity.projectile.EntityNekoStickyBomb;
import tragicneko.tragicmc.entity.projectile.EntityOverlordMortor;
import tragicneko.tragicmc.entity.projectile.EntityPitchBlack;
import tragicneko.tragicmc.entity.projectile.EntityPoisonBarb;
import tragicneko.tragicmc.entity.projectile.EntityPumpkinbomb;
import tragicneko.tragicmc.entity.projectile.EntitySolarBomb;
import tragicneko.tragicmc.entity.projectile.EntitySpiritCast;
import tragicneko.tragicmc.entity.projectile.EntitySpore;
import tragicneko.tragicmc.entity.projectile.EntityStarShard;
import tragicneko.tragicmc.entity.projectile.EntityThrowingRock;
import tragicneko.tragicmc.entity.projectile.EntityTimeBomb;
import tragicneko.tragicmc.entity.projectile.EntityWebBomb;
import tragicneko.tragicmc.events.KeyInputEvents;
import tragicneko.tragicmc.events.MouseEvents;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class ClientProxy extends CommonProxy {

	//public static final String[] multicolors = new String[] {"black", "red", "green", "brown", "blue", "purple", "cyan", "lightGray", "darkGray", "pink", "lime", "yellow", "lightBlue",
	//	"magenta", "orange", "white"};

	//public static final String moddir = "tragicmc:";

	public static KeyBinding useSpecial = new KeyBinding("Special Use", Keyboard.KEY_R, TragicMC.MODNAME);
	public static KeyBinding openAmuletGui = new KeyBinding("Open Amulet Gui", Keyboard.KEY_Y, TragicMC.MODNAME);	

	@Override
	public void registerRenders()
	{
		Minecraft mc = Minecraft.getMinecraft();
		//ItemModelMesher mesher = mc.getRenderItem().getItemModelMesher();
		//this.registerItemBlockRenders(mesher);

		//Gui event registration
		if (TragicConfig.showDoomGui) MinecraftForge.EVENT_BUS.register(new GuiDoom(mc));
		if (TragicConfig.showAmuletStatus) MinecraftForge.EVENT_BUS.register(new GuiAmuletStatus(mc));

		//Keybinding registrations
		ClientRegistry.registerKeyBinding(useSpecial);
		ClientRegistry.registerKeyBinding(openAmuletGui);

		FMLCommonHandler.instance().bus().register(new KeyInputEvents());
		MinecraftForge.EVENT_BUS.register(new KeyInputEvents());
		MinecraftForge.EVENT_BUS.register(new MouseEvents(mc));
		
		//Tile Entity render registration (shouldn't be used too often)
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySoulChest.class, new RenderSoulChest());

		//Weapon models
		if (TragicConfig.allowWeaponModels)
		{
			MinecraftForgeClient.registerItemRenderer(TragicItems.ReaperScythe, new RenderEpicWeapon(0, mc));
			MinecraftForgeClient.registerItemRenderer(TragicItems.Butcher, new RenderEpicWeapon(1, mc));
			MinecraftForgeClient.registerItemRenderer(TragicItems.DragonFang, new RenderEpicWeapon(2, mc));
			MinecraftForgeClient.registerItemRenderer(TragicItems.Thardus, new RenderEpicWeapon(3, mc));
			MinecraftForgeClient.registerItemRenderer(TragicItems.Splinter, new RenderEpicWeapon(4, mc));
			MinecraftForgeClient.registerItemRenderer(TragicItems.Paranoia, new RenderEpicWeapon(5, mc));
			MinecraftForgeClient.registerItemRenderer(TragicItems.CelestialAegis, new RenderEpicWeapon(6, mc));
			MinecraftForgeClient.registerItemRenderer(TragicItems.Titan, new RenderEpicWeapon(7, mc));
		}

		//Projectile renders
		RenderingRegistry.registerEntityRenderingHandler(EntityThrowingRock.class, new RenderProjectile(0));
		RenderingRegistry.registerEntityRenderingHandler(EntityPumpkinbomb.class, new RenderProjectile(2));
		RenderingRegistry.registerEntityRenderingHandler(EntityLargePumpkinbomb.class, new RenderProjectile(3));
		RenderingRegistry.registerEntityRenderingHandler(EntityPoisonBarb.class, new RenderProjectile(4));
		RenderingRegistry.registerEntityRenderingHandler(EntityNekoRocket.class, new RenderProjectile(5));
		RenderingRegistry.registerEntityRenderingHandler(EntityNekoStickyBomb.class, new RenderProjectile(6));
		RenderingRegistry.registerEntityRenderingHandler(EntityNekoClusterBomb.class, new RenderProjectile(7));
		RenderingRegistry.registerEntityRenderingHandler(EntityNekoMiniBomb.class, new RenderProjectile(8));
		RenderingRegistry.registerEntityRenderingHandler(EntitySolarBomb.class, new RenderProjectile(9));
		RenderingRegistry.registerEntityRenderingHandler(EntitySpiritCast.class, new RenderProjectile(10));
		RenderingRegistry.registerEntityRenderingHandler(EntitySpore.class, new RenderProjectile(11));
		RenderingRegistry.registerEntityRenderingHandler(EntityBanana.class, new RenderProjectile(12));
		RenderingRegistry.registerEntityRenderingHandler(EntityLargeRock.class, new RenderLargeRock());
		RenderingRegistry.registerEntityRenderingHandler(EntityIcicle.class, new RenderProjectile(14));
		RenderingRegistry.registerEntityRenderingHandler(EntityTimeBomb.class, new RenderProjectile(15));
		RenderingRegistry.registerEntityRenderingHandler(EntityStarShard.class, new RenderProjectile(16));
		RenderingRegistry.registerEntityRenderingHandler(EntityDarkLightning.class, new RenderProjectile(17));
		RenderingRegistry.registerEntityRenderingHandler(EntityPitchBlack.class, new RenderProjectile(18));
		RenderingRegistry.registerEntityRenderingHandler(EntityDarkEnergy.class, new RenderProjectile(19));
		RenderingRegistry.registerEntityRenderingHandler(EntityDarkMortor.class, new RenderProjectile(20));
		RenderingRegistry.registerEntityRenderingHandler(EntityWebBomb.class, new RenderProjectile(21));
		RenderingRegistry.registerEntityRenderingHandler(EntityCrystalMortor.class, new RenderProjectile(22));
		RenderingRegistry.registerEntityRenderingHandler(EntityOverlordMortor.class, new RenderProjectile(23));
		RenderingRegistry.registerEntityRenderingHandler(EntityIreEnergy.class, new RenderProjectile(24));

		//Non projectile renders
		RenderingRegistry.registerEntityRenderingHandler(EntityStatue.class, new RenderStatue());
		RenderingRegistry.registerEntityRenderingHandler(EntityTimeDisruption.class, new RenderTimeDisruption());
		RenderingRegistry.registerEntityRenderingHandler(EntityDarkCrystal.class, new RenderDarkCrystal());
		RenderingRegistry.registerEntityRenderingHandler(EntityGuardianShield.class, new RenderGuardianShield());
		RenderingRegistry.registerEntityRenderingHandler(EntityDimensionalAnomaly.class, new RenderDimensionalAnomaly());
		RenderingRegistry.registerEntityRenderingHandler(EntityLock.class, new RenderLock());
		RenderingRegistry.registerEntityRenderingHandler(EntityDirectedLightning.class, new RenderDirectedLightning());

		//Mob renders
		RenderingRegistry.registerEntityRenderingHandler(EntityJabba.class, new RenderJabba());
		RenderingRegistry.registerEntityRenderingHandler(EntityJarra.class, new RenderMob(new ModelJarra(), 0.655F, "Jarra", 1.585F));
		RenderingRegistry.registerEntityRenderingHandler(EntityPlague.class, new RenderMob(new ModelPlague(), 0.115F, "Plague"));
		RenderingRegistry.registerEntityRenderingHandler(EntityGragul.class, new RenderMob(new ModelGragul(), 0.115F, "Gragul"));
		RenderingRegistry.registerEntityRenderingHandler(EntityKragul.class, new RenderMob(new ModelKragul(), 0.115F, "Kragul", 2.115F));
		RenderingRegistry.registerEntityRenderingHandler(EntityMinotaur.class, new RenderMob(new ModelMinotaur(), 0.337F, "Minotaur"));
		RenderingRegistry.registerEntityRenderingHandler(EntityRagr.class, new RenderMob(new ModelRagr(), 0.435F, "Ragr"));
		RenderingRegistry.registerEntityRenderingHandler(EntityInkling.class, new RenderMob(new ModelInkling(), 0.175F, "Inkling"));
		RenderingRegistry.registerEntityRenderingHandler(EntityPumpkinhead.class, new RenderMob(new ModelPumpkinhead(), 0.375F, "Pumpkinhead"));
		RenderingRegistry.registerEntityRenderingHandler(EntityTragicNeko.class, new RenderMob(new ModelTragicNeko(), 0.295F, "TragicNeko"));
		RenderingRegistry.registerEntityRenderingHandler(EntityTox.class, new RenderTox());
		RenderingRegistry.registerEntityRenderingHandler(EntityMagmox.class, new RenderMob(new ModelTox(), 0.565F, "Magmox2", 1.625F));
		RenderingRegistry.registerEntityRenderingHandler(EntityCryse.class, new RenderCryse());
		RenderingRegistry.registerEntityRenderingHandler(EntityMegaCryse.class, new RenderMegaCryse());
		RenderingRegistry.registerEntityRenderingHandler(EntityNorVox.class, new RenderNorVox());
		RenderingRegistry.registerEntityRenderingHandler(EntityVoxStellarum.class, new RenderVoxStellarum());
		RenderingRegistry.registerEntityRenderingHandler(EntityPirah.class, new RenderPirah());
		RenderingRegistry.registerEntityRenderingHandler(EntityStin.class, new RenderStin());
		RenderingRegistry.registerEntityRenderingHandler(EntityGreaterStin.class, new RenderMob(new ModelGreaterStin(), 0.675F, "GreaterStin"));
		RenderingRegistry.registerEntityRenderingHandler(EntityStinKing.class, new RenderMob(new ModelStinKing(), 0.675F, "StinKing", 1.625F));
		RenderingRegistry.registerEntityRenderingHandler(EntityStinQueen.class, new RenderMob(new ModelStinQueen(), 0.675F, "StinQueen", 1.225F));
		RenderingRegistry.registerEntityRenderingHandler(EntityWisp.class, new RenderWisp());
		RenderingRegistry.registerEntityRenderingHandler(EntityAbomination.class, new RenderMob(new ModelAbomination(), 0.475F, "Abomination"));
		RenderingRegistry.registerEntityRenderingHandler(EntityErkel.class, new RenderErkel());
		RenderingRegistry.registerEntityRenderingHandler(EntitySirv.class, new RenderMob(new ModelSirv(), 0.245F, "Sirv"));
		RenderingRegistry.registerEntityRenderingHandler(EntityPsygote.class, new RenderMob(new ModelPsygote(), 0.565F, "Psygote"));
		RenderingRegistry.registerEntityRenderingHandler(EntityNanoSwarm.class, new RenderMob(new ModelNanoSwarm(), 0.215F, "NanoSwarm", 1.545F));
		RenderingRegistry.registerEntityRenderingHandler(EntityAegar.class, new RenderAegar());
		RenderingRegistry.registerEntityRenderingHandler(EntityHunter.class, new RenderMob(new ModelHunter(), 0.565F, "Hunter"));
		RenderingRegistry.registerEntityRenderingHandler(EntityHarvester.class, new RenderMob(new ModelHarvester(), 0.785F, "Harvester", 1.555F));
		RenderingRegistry.registerEntityRenderingHandler(EntityLockbot.class, new RenderMob(new ModelLockbot(), 0.335F, "Lockbot"));
		RenderingRegistry.registerEntityRenderingHandler(EntitySeeker.class, new RenderMob(new ModelSeeker(), 0.475F, "Seeker"));
		RenderingRegistry.registerEntityRenderingHandler(EntityIre.class, new RenderIre());

		//Boss renders
		RenderingRegistry.registerEntityRenderingHandler(EntityApis.class, new RenderApis());
		RenderingRegistry.registerEntityRenderingHandler(EntityDeathReaper.class, new RenderDeathReaper());
		RenderingRegistry.registerEntityRenderingHandler(EntityKitsune.class, new RenderKitsune());
		RenderingRegistry.registerEntityRenderingHandler(EntityPolaris.class, new RenderPolaris());
		RenderingRegistry.registerEntityRenderingHandler(EntityYeti.class, new RenderYeti());
		RenderingRegistry.registerEntityRenderingHandler(EntityTimeController.class, new RenderTimeController());
		RenderingRegistry.registerEntityRenderingHandler(EntityEnyvil.class, new RenderEnyvil());
		RenderingRegistry.registerEntityRenderingHandler(EntityClaymation.class, new RenderClaymation());

		//Alpha renders TODO update the first form when I finish the model
		RenderingRegistry.registerEntityRenderingHandler(EntityOverlordCocoon.class, new RenderOverlordCocoon());
		RenderingRegistry.registerEntityRenderingHandler(EntityOverlordCombat.class, new RenderOverlordCombat());
		RenderingRegistry.registerEntityRenderingHandler(EntityOverlordCore.class, new RenderOverlordCore());
	}
	/*
	public void registerItemBlockRenders(ItemModelMesher mesher)
	{
		Item ib; //Itemblock for block registrations
	} */

	@Override
	public EntityPlayer getPlayerFromMessageCtx(MessageContext ctx)
	{
		return Minecraft.getMinecraft().thePlayer;
	}

	/*//These are just being kept here until I update to 1.8, assuming there is no changes to forge until then, if you're wondering, these are for the new block/item rendering	
	public static String[] getColoredVariantsForBlock(Item ib)
	{
		String[] colors = new String[16];
		for (int i = 0; i < 16; i++)
		{
			colors[i] = moddir + ib.getUnlocalizedName() + multicolors[i];
		}

		return colors;
	}

	public static void registerItemToMesher(Item item, int meta, String location)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, new ModelResourceLocation(moddir + location, "inventory"));
	}

	public static void registerBlockToMesher(Block block, int meta, String location)
	{
		registerItemToMesher(Item.getItemFromBlock(block), meta, location);
	}

	public static void registerBlockToBakery(Block block, String... names)
	{
		registerItemToBakery(Item.getItemFromBlock(block), names);
	}

	public static void registerItemToBakery(Item item, String... names)
	{
		ModelBakery.registerVariantNames(item, names);
	}
	 */
}
