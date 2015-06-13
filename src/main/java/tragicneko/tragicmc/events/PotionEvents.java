package tragicneko.tragicmc.events;

import static tragicneko.tragicmc.TragicMC.rand;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityPortalFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.FoodStats;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent.Start;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent.Tick;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.client.CommonProxy;
import tragicneko.tragicmc.entity.boss.TragicBoss;
import tragicneko.tragicmc.entity.mob.TragicMob;
import tragicneko.tragicmc.network.MessageFlight;
import tragicneko.tragicmc.properties.PropertyDoom;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PotionEvents {

	@SubscribeEvent
	public void flightSync(EntityJoinWorldEvent event)
	{
		if (event.entity instanceof EntityPlayerMP)
		{
			TragicMC.net.sendTo(new MessageFlight(TragicConfig.allowFlight), (EntityPlayerMP) event.entity);
		}
	}

	@SubscribeEvent
	public void tragicPotionEffect(LivingUpdateEvent event)
	{
		EntityLivingBase entity = event.entityLiving;
		World world = event.entityLiving.worldObj;

		if (entity.getActivePotionEffects().isEmpty()) return;

		if (TragicConfig.allowClarity && entity.isPotionActive(TragicPotion.Clarity.id))
		{
			if (TragicConfig.allowDisorientation && event.entityLiving.isPotionActive(TragicPotion.Disorientation))
			{
				event.entityLiving.removePotionEffect(TragicPotion.Disorientation.id);
			}

			if (event.entityLiving.isPotionActive(Potion.confusion))
			{
				event.entityLiving.removePotionEffect(Potion.confusion.id);
			}

			if (event.entityLiving.isPotionActive(Potion.blindness))
			{
				event.entityLiving.removePotionEffect(Potion.blindness.id);
			}

			if (TragicConfig.allowFear && event.entityLiving.isPotionActive(TragicPotion.Fear))
			{
				event.entityLiving.removePotionEffect(TragicPotion.Fear.id);
			}
		}

		if (TragicConfig.allowImmunity && event.entityLiving.isPotionActive(TragicPotion.Immunity))
		{
			if (TragicConfig.allowInhibit && event.entityLiving.isPotionActive(TragicPotion.Inhibit))
			{
				event.entityLiving.removePotionEffect(TragicPotion.Immunity.id);
			}

			if (TragicConfig.allowStun && event.entityLiving.isPotionActive(TragicPotion.Stun))
			{
				event.entityLiving.removePotionEffect(TragicPotion.Stun.id);
			}

			if (event.entityLiving.isPotionActive(Potion.poison))
			{
				event.entityLiving.removePotionEffect(Potion.poison.id);
			}

			if (event.entityLiving.isPotionActive(Potion.wither))
			{
				event.entityLiving.removePotionEffect(Potion.wither.id);
			}

			if (TragicConfig.allowCorruption && event.entityLiving.isPotionActive(TragicPotion.Corruption))
			{
				event.entityLiving.removePotionEffect(TragicPotion.Corruption.id);
			}
		}

		if (TragicConfig.allowInhibit && event.entityLiving.isPotionActive(TragicPotion.Inhibit))
		{
			if (event.entityLiving.isPotionActive(Potion.regeneration))
			{
				event.entityLiving.removePotionEffect(Potion.regeneration.id);
			}

			if (event.entityLiving.isPotionActive(Potion.damageBoost))
			{
				event.entityLiving.removePotionEffect(Potion.damageBoost.id);
			}

			if (event.entityLiving.isPotionActive(Potion.digSpeed))
			{
				event.entityLiving.removePotionEffect(Potion.digSpeed.id);
			}

			if (event.entityLiving.isPotionActive(Potion.moveSpeed))
			{
				event.entityLiving.removePotionEffect(Potion.moveSpeed.id);
			}
		}

		if (TragicConfig.allowMalnourish && event.entityLiving.isPotionActive(TragicPotion.Malnourish))
		{
			if (event.entityLiving.isPotionActive(Potion.field_76443_y)) event.entityLiving.removePotionEffect(TragicPotion.Malnourish.id);
			if (event.entityLiving.isPotionActive(Potion.hunger.id)) event.entityLiving.removePotionEffect(Potion.hunger.id);
		}

		if (TragicConfig.allowCripple && event.entityLiving.isPotionActive(TragicPotion.Cripple))
		{
			if (event.entityLiving.isPotionActive(Potion.field_76434_w)) event.entityLiving.removePotionEffect(TragicPotion.Cripple.id);
		}

		if (TragicConfig.allowSubmission && event.entityLiving.isPotionActive(TragicPotion.Submission))
		{
			if (event.entityLiving.isPotionActive(Potion.field_76434_w)) event.entityLiving.removePotionEffect(TragicPotion.Submission.id);
		}

		if (TragicConfig.allowDivinity && entity.isPotionActive(TragicPotion.Divinity))
		{
			if (entity.getHealth() < entity.getMaxHealth() && rand.nextBoolean() && entity.ticksExisted % 10 == 0) entity.heal(1.0F);
			if (TragicConfig.allowHacked && entity.isPotionActive(TragicPotion.Hacked)) entity.removePotionEffect(TragicPotion.Hacked.id);
		}

		if (TragicConfig.allowAquaSuperiority && entity.isPotionActive(TragicPotion.AquaSuperiority.id))
		{
			if (entity instanceof EntityPlayer && entity.isInWater())
			{
				if (Math.abs(entity.motionX) <= 0.8115D) entity.motionX *= 1.2115;
				if (Math.abs(entity.motionZ) <= 0.815D) entity.motionZ *= 1.2115;
				((EntityPlayer)entity).setAir(300);

				if (entity.motionY > 0.0)
				{
					if (Math.abs(entity.motionY) <= 0.8115D) entity.motionY *= 1.2115;
				}
				else
				{
					if (entity.isSneaking())
					{
						entity.motionY -= 0.1;
					}
					else
					{
						entity.motionY *= 0.8;
					}
				}
			}
		}

		if (TragicConfig.allowFlight && entity.isPotionActive(TragicPotion.Flight.id))
		{
			if (entity instanceof EntityPlayer && !entity.isInWater() && !entity.onGround)
			{
				if (rand.nextInt(128) == 0 && entity.motionY > 0.0)
				{
					((EntityPlayer) entity).addExhaustion(0.5F);
				}

				if (entity.motionY <= 0.0)
				{
					if (entity.isSneaking())
					{
						entity.motionY = -0.0212;
					}
					else
					{
						entity.motionY -= 0.0215D;
					}
				}
				else if (entity.motionY <= 0.4115)
				{
					entity.motionY *= 1.298647D;
				}

				if (!entity.isSneaking())
				{
					if (Math.abs(entity.motionX) <= 0.4115) entity.motionX *= 1.075D;
					if (Math.abs(entity.motionZ) <= 0.4115) entity.motionZ *= 1.075D;
				}

				entity.fallDistance = 0.0F;

			}
		}

		if (world.isRemote) return;

		if (TragicConfig.allowCorruption && entity.isPotionActive(TragicPotion.Corruption.id))
		{
			if (TragicConfig.allowCorruptionDamage)
			{
				if (entity instanceof EntityPlayer && entity.ticksExisted % 40 == 0 && rand.nextBoolean())
				{
					if (!((EntityPlayer)entity).capabilities.isCreativeMode)
					{
						entity.attackEntityFrom(DamageSource.magic, 1.0F);
					}
				}
				else if (entity instanceof EntityAnimal && entity.ticksExisted % 20 == 0 && rand.nextInt(8) == 0)
				{
					entity.attackEntityFrom(DamageSource.magic, 1.0F);
				}
			}

			if (entity.ticksExisted % 4 == 0)
			{
				List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(entity, entity.boundingBox.expand(4.0D, 4.0D, 4.0D));
				boolean flag = list.isEmpty();
				PotionEffect temp = entity.getActivePotionEffect(TragicPotion.Corruption);

				for (Entity target : list)
				{
					if (target instanceof EntityLivingBase)
					{
						if (entity.getDistanceToEntity(target) <= 4.0D && entity.canEntityBeSeen(target))
						{
							flag = true;
							if (rand.nextBoolean() && !((EntityLivingBase) target).isPotionActive(TragicPotion.Corruption.id)) ((EntityLivingBase) target).addPotionEffect(new PotionEffect(TragicPotion.Corruption.id, temp.getDuration() / 2, temp.getAmplifier()));
						}
					}
				}

				NBTTagCompound tag = null;
				boolean flag2 = false;

				if (!flag && !(entity instanceof TragicMob))
				{
					list = world.getEntitiesWithinAABBExcludingEntity(entity, entity.boundingBox.expand(12.0D, 12.0D, 12.0D));

					for (int i = 0; i < list.size(); i++)
					{
						if (entity.canEntityBeSeen(list.get(i)) && entity.getDistanceToEntity(list.get(i)) <= 8.0F)
						{
							flag2 = true;
						}
					}

					tag = CommonProxy.getEntityData(entity.getUniqueID());
					if (tag == null) tag = new NBTTagCompound();

					if (!flag2)
					{
						tag.setInteger("recoveryTicks", tag.hasKey("recoveryTicks") ? tag.getInteger("recoveryTicks") + 1 : 0);

						if (tag.hasKey("recoveryTicks") && tag.getInteger("recoveryTicks") >= 120)
						{
							entity.removePotionEffect(TragicPotion.Corruption.id);
							tag.setInteger("recoveryTicks", 0);
						}

						CommonProxy.storeEntityData(entity.getUniqueID(), tag);
					}
					else
					{
						tag.setInteger("recoveryTicks", 0);
						CommonProxy.storeEntityData(entity.getUniqueID(), tag);
					}
				}
				else if (flag && !(entity instanceof TragicMob))
				{
					tag = CommonProxy.getEntityData(entity.getUniqueID());
					if (tag == null) tag = new NBTTagCompound();
					tag.setInteger("recoveryTicks", 0);
					CommonProxy.storeEntityData(entity.getUniqueID(), tag);
				}
			}
		}		

		if (TragicConfig.allowFear && entity.isPotionActive(TragicPotion.Fear.id))
		{
			if (entity instanceof EntityCreature)
			{
				((EntityCreature) entity).setTarget(null);
				((EntityCreature) entity).setAttackTarget(null);

				if (!((EntityCreature) entity).getNavigator().noPath())
				{
					PathEntity path = ((EntityCreature) entity).getNavigator().getPath();
					int x = path.getFinalPathPoint().xCoord;
					int y = path.getFinalPathPoint().yCoord;
					int z = path.getFinalPathPoint().zCoord;

					if (!entity.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, entity.boundingBox.copy().offset(x, y, z)).isEmpty())
					{
						((EntityCreature) entity).getNavigator().clearPathEntity();
					}
				}

				for (int i = 0; i < 10; i++)
				{
					if (((EntityCreature) entity).getNavigator().noPath())
					{
						double x = entity.posX + (rand.nextDouble() * 5) - (rand.nextDouble() * 5);
						double y = entity.posY + (rand.nextDouble() * 5) - (rand.nextDouble() * 5);
						double z = entity.posZ + (rand.nextDouble() * 5) - (rand.nextDouble() * 5);
						if (((EntityCreature) entity).getNavigator().tryMoveToXYZ(x, y, z, 1.0)) break;
					}
				}
			}
		}

		if (TragicConfig.allowHarmony && event.entityLiving.isPotionActive(TragicPotion.Harmony))
		{
			if (event.entityLiving instanceof EntityEnderman || event.entityLiving instanceof EntityDragon
					|| event.entityLiving instanceof EntityWither || event.entityLiving instanceof TragicBoss)
			{
				event.entityLiving.removePotionEffect(TragicPotion.Harmony.id);
			}
		}

		if (TragicConfig.allowHarmony && entity.isPotionActive(TragicPotion.Harmony.id))
		{
			if (entity instanceof EntityCreature)
			{
				((EntityCreature) entity).setPathToEntity(null);
				((EntityCreature) entity).setAttackTarget(null);
			}

			if (entity.getActivePotionEffect(TragicPotion.Harmony).getDuration() == 0)
			{
				entity.removePotionEffect(TragicPotion.Harmony.id);
			}
		}

		if (TragicConfig.allowMalnourish && entity.isPotionActive(TragicPotion.Malnourish.id))
		{
			if (entity instanceof EntityPlayerMP)
			{
				int amp = entity.getActivePotionEffect(TragicPotion.Malnourish).getAmplifier() * 2;
				FoodStats food = ((EntityPlayerMP)entity).getFoodStats();
				entity.worldObj.getGameRules().getGameRuleBooleanValue("naturalRegeneration");

				if (amp > 10)
				{
					amp = 10;
				}

				if (amp <= 0)
				{
					amp = 1;
				}

				int f = food.getFoodLevel();
				int limit = amp >= 10 ? 9 : (amp > 6 ? 13 : (amp > 3 ? 15 : 19));

				if (entity.getHealth() >= entity.getMaxHealth() - amp)
				{
					if (f >= limit)
					{
						if (f - amp < 0) f = amp;
						food.addStats(-amp, -amp);
					}

					int chance = Math.abs(MathHelper.ceiling_double_int(16 / amp) * 20);
					if (chance < 8) chance = 8;
					if (Math.abs(amp) >= 2 && rand.nextInt(chance) == 0) food.addExhaustion(0.5F);
				}
			}
		}

		if (TragicConfig.allowCripple && event.entityLiving.isPotionActive(TragicPotion.Cripple))
		{
			if (event.entityLiving.getHealth() > event.entityLiving.getMaxHealth())
			{
				event.entityLiving.setHealth(event.entityLiving.getMaxHealth());
			}
		}

		if (TragicConfig.allowLeadFoot && event.entityLiving.isPotionActive(TragicPotion.LeadFoot))
		{
			event.entityLiving.motionY = -0.1D;
			if (TragicConfig.allowFlight && event.entityLiving.isPotionActive(TragicPotion.Flight)) event.entityLiving.removePotionEffect(TragicPotion.Flight.id);
		}

		if (TragicConfig.allowConvergence && event.entityLiving.isPotionActive(TragicPotion.Convergence) && event.entityLiving instanceof EntityPlayerMP && TragicConfig.allowDoom)
		{
			PropertyDoom doom = PropertyDoom.get((EntityPlayer) event.entityLiving);
			if (doom != null && doom.getCurrentCooldown() > 0) doom.setCooldown(0);
			if (doom != null && event.entityLiving.ticksExisted % 5 == 0 && rand.nextBoolean())
			{
				doom.increaseDoom(rand.nextInt(4));
			}
		}

		if (!TragicConfig.allowAnimalGolemCorruption && TragicConfig.allowCorruption && event.entityLiving.isPotionActive(TragicPotion.Corruption.id) && (event.entityLiving instanceof EntityGolem || event.entityLiving instanceof EntityAnimal))
		{
			event.entityLiving.removePotionEffect(TragicPotion.Corruption.id);
		}
	}

	@SubscribeEvent
	public void denyJump(LivingJumpEvent event)
	{
		if (TragicConfig.allowLeadFoot && event.entityLiving.isPotionActive(TragicPotion.LeadFoot)) event.entityLiving.motionY = -0.1D;
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void clarifyInvisibleEnemies(LivingUpdateEvent event)
	{
		EntityLivingBase entity = event.entityLiving;
		World world = event.entityLiving.worldObj;

		if (TragicConfig.allowClarity && entity.isPotionActive(TragicPotion.Clarity))
		{
			double d0 = 16.0D + (8.0D * entity.getActivePotionEffect(TragicPotion.Clarity).getAmplifier());

			List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(entity, entity.boundingBox.expand(d0, d0, d0));
			EntityLivingBase target;

			for (int i = 0; i < list.size(); i++)
			{
				if (list.get(i) instanceof EntityLivingBase)
				{
					target = (EntityLivingBase) list.get(i);
					if (target.isInvisibleToPlayer(Minecraft.getMinecraft().thePlayer))
					{
						for (int j = 0; j < 4; j++)
						{
							double d1 = target.width * (rand.nextDouble() - rand.nextDouble());
							double d2 = target.height * rand.nextDouble();
							double d3 = target.width * (rand.nextDouble() - rand.nextDouble());
							EntityPortalFX fx = new EntityPortalFX(target.worldObj, target.posX + d1, target.posY + d2, target.posZ + d3, 0.0, 0.0, 0.0);
							fx.setRBGColorF(1.0F, 1.0F, 1.0F);
							Minecraft.getMinecraft().effectRenderer.addEffect(fx);
						}
					}
				}
			}
		}

		if (TragicConfig.allowDisorientation && event.entityLiving.isPotionActive(TragicPotion.Disorientation))
		{
			if (event.entityLiving.ticksExisted % 60 == 0)
			{
				float strafe = rand.nextFloat() * MathHelper.getRandomIntegerInRange(rand, -2, 2);
				float forward = rand.nextFloat() * MathHelper.getRandomIntegerInRange(rand, -2, 2);
				event.entityLiving.moveEntityWithHeading(strafe, forward);
			}

			if (event.entityLiving instanceof EntityPlayer)
			{
				((EntityPlayer)event.entityLiving).cameraYaw = (rand.nextFloat() - rand.nextFloat()) * 2.25F;
				((EntityPlayer)event.entityLiving).cameraPitch = (rand.nextFloat() - rand.nextFloat()) * 2.25F;
			}
		}
	}

	@SubscribeEvent
	public void livingJump(LivingJumpEvent event)
	{
		Boolean flag1 = false;
		Boolean flag2 = false;

		if (TragicConfig.allowStun)
		{
			flag1 = event.entityLiving.isPotionActive(TragicPotion.Stun.id);
		}

		if (TragicConfig.allowCripple)
		{
			flag2 = event.entityLiving.isPotionActive(TragicPotion.Cripple.id);
		}

		if (flag1)
		{
			event.entityLiving.motionY = 0.0;
			event.entityLiving.motionZ = 0.0;
			event.entityLiving.motionX = 0.0;
			event.entityLiving.onGround = true;

			if (event.entityLiving instanceof EntityPlayer) ((EntityPlayer) event.entityLiving).capabilities.isFlying = false;
		}

		if (flag2 && !flag1)
		{
			event.entityLiving.motionY *= 0.9;
		}
	}

	@SubscribeEvent
	public void potionFallEvent(LivingFallEvent event)
	{
		if (TragicConfig.allowFlight && event.entityLiving.isPotionActive(TragicPotion.Flight.id))
		{
			if (event.isCancelable()) event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void potionDeathEvent(LivingDeathEvent event)
	{
		if (event.entity.worldObj.isRemote) return;

		if (event.entityLiving instanceof EntityPlayer && TragicConfig.allowResurrection)
		{
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			if (player.isPotionActive(TragicPotion.Resurrection.id))
			{
				if (event.isCancelable())
				{
					event.setCanceled(true);
				}

				float amp = player.getActivePotionEffect(TragicPotion.Resurrection).getAmplifier();

				if (amp > 3.0F)
				{
					amp = 3.0F;
				}

				float percent = (amp + 1) / 4;
				player.setHealth((player.getMaxHealth() * percent));

				for (int i = 0; i < 50; i++)
				{
					if (player.isPotionActive(i))
					{
						player.removePotionEffect(i);
					}
				}
			}
		}
		else if (TragicConfig.allowCorruption)
		{
			if (CommonProxy.extendedEntityData.containsKey(event.entityLiving.getUniqueID()))
			{
				CommonProxy.extendedEntityData.remove(event.entityLiving.getUniqueID());
			}
		}
	}

	@SubscribeEvent
	public void onItemUsed(Start event)
	{
		if (TragicConfig.allowMalnourish && event.entityPlayer.isPotionActive(TragicPotion.Malnourish) && event.item.getItem() instanceof ItemFood)
		{
			int amp = event.entityPlayer.getActivePotionEffect(TragicPotion.Malnourish).getAmplifier() * 2;

			if (amp > 20)
			{
				amp = 20;
			}

			if (amp <= 0)
			{
				amp = 1;
			}

			if (event.entityPlayer.getHealth() >= event.entityPlayer.getMaxHealth() - amp)
			{
				if (event.isCancelable()) event.setCanceled(true);
			}
		}

		if (TragicConfig.allowStun && event.entityPlayer.isPotionActive(TragicPotion.Stun) || TragicConfig.allowFear && event.entityPlayer.isPotionActive(TragicPotion.Fear) ||
				TragicConfig.allowHacked && event.entityPlayer.isPotionActive(TragicPotion.Hacked))
		{
			if (event.isCancelable()) event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void whileUsingItem(Tick event)
	{
		if (TragicConfig.allowStun && event.entityPlayer.isPotionActive(TragicPotion.Stun) || TragicConfig.allowFear && event.entityPlayer.isPotionActive(TragicPotion.Fear) ||
				TragicConfig.allowHacked && event.entityPlayer.isPotionActive(TragicPotion.Hacked))
		{
			if (event.isCancelable()) event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void onDig(BreakEvent event)
	{
		if (event.getPlayer() != null && TragicConfig.allowStun && event.getPlayer().isPotionActive(TragicPotion.Stun) || TragicConfig.allowFear && event.getPlayer() != null && event.getPlayer().isPotionActive(TragicPotion.Fear) ||
				TragicConfig.allowHacked && event.getPlayer() != null && event.getPlayer().isPotionActive(TragicPotion.Hacked))
		{
			if (event.isCancelable()) event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void onBreaking(BreakSpeed event)
	{
		if (TragicConfig.allowStun && event.entityPlayer.isPotionActive(TragicPotion.Stun) || TragicConfig.allowFear && event.entityPlayer.isPotionActive(TragicPotion.Fear) ||
				TragicConfig.allowHacked && event.entityPlayer.isPotionActive(TragicPotion.Hacked))
		{
			if (event.isCancelable()) event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void onAttack(LivingAttackEvent event)
	{
		if (event.source.getEntity() != null && event.source.getEntity() instanceof EntityLivingBase)
		{
			if (TragicConfig.allowHarmony && ((EntityLivingBase) event.source.getEntity()).isPotionActive(TragicPotion.Harmony))
			{
				if (event.isCancelable()) event.setCanceled(true);
			}

			if (TragicConfig.allowStun && ((EntityLivingBase) event.source.getEntity()).isPotionActive(TragicPotion.Stun))
			{
				if (event.isCancelable()) event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent(priority=EventPriority.LOW)
	public void submissiveHurt(LivingHurtEvent event)
	{
		if (TragicConfig.allowSubmission && event.entityLiving.isPotionActive(TragicPotion.Submission))
		{
			double x = (event.entityLiving.getActivePotionEffect(TragicPotion.Submission).getAmplifier() + 2) / 3;

			if (x <= 0)
			{
				x = 0.1;
			}
			if (x > 3.0)
			{
				x = 3.0;
			}

			float f = (float) (event.ammount * x);
			event.ammount += f;

			event.entityLiving.motionX *= 1 + x;
			event.entityLiving.motionZ *= 1 + x;
			event.entityLiving.motionY *= 1 + x;
		}

		if (!(event.entityLiving instanceof EntityPlayer))
		{
			if (event.entityLiving.isPotionActive(Potion.confusion) || event.entityLiving.isPotionActive(Potion.blindness)
					|| TragicConfig.allowDisorientation && event.entityLiving.isPotionActive(TragicPotion.Disorientation)
					|| TragicConfig.allowFear && event.entityLiving.isPotionActive(TragicPotion.Fear))
			{
				event.ammount *= 1.05;
			}
		}
	}

	@SubscribeEvent(priority=EventPriority.HIGHEST)
	public void invulnerableHurt(LivingHurtEvent event)
	{
		if (TragicConfig.allowInvulnerability && event.entityLiving.isPotionActive(TragicPotion.Invulnerability) && !event.source.canHarmInCreative())
		{
			if (event.isCancelable()) event.setCanceled(true);
			if (event.source.getSourceOfDamage() != null) event.entityLiving.applyEntityCollision(event.source.getSourceOfDamage());
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void renderHandWhileStunned(RenderHandEvent event)
	{
		if (TragicConfig.allowStun && Minecraft.getMinecraft().thePlayer.isPotionActive(TragicPotion.Stun))
		{
			if (event.isCancelable()) event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void ignoreTargetsWithHarmony(LivingSetAttackTargetEvent event)
	{
		if (!event.entityLiving.worldObj.isRemote && event.target != null && (TragicConfig.allowHarmony && event.entityLiving.isPotionActive(TragicPotion.Harmony) || TragicConfig.allowFear && event.entityLiving.isPotionActive(TragicPotion.Fear)))
		{
			if (event.entityLiving instanceof EntityCreature && !(event.entityLiving instanceof EntityWither) && !(event.entityLiving instanceof EntityDragon)
					&& !(event.entityLiving instanceof TragicBoss))
			{
				((EntityCreature) event.entityLiving).setTarget(null);
				((EntityLiving) event.entityLiving).setAttackTarget(null);
			}
		}
	}

	@SubscribeEvent
	public void onCorruptedAttack(LivingHurtEvent event)
	{
		if (event.entityLiving.worldObj.isRemote || !TragicConfig.allowCorruption) return;

		if (!event.entityLiving.isPotionActive(TragicPotion.Corruption.id))
		{
			if (event.source.getEntity() instanceof EntityLivingBase)
			{
				EntityLivingBase source = (EntityLivingBase)event.source.getEntity();

				if (source.isPotionActive(TragicPotion.Corruption.id))
				{
					PotionEffect effect = source.getActivePotionEffect(TragicPotion.Corruption);
					event.entityLiving.addPotionEffect(new PotionEffect(effect.getPotionID(), effect.getDuration()));
				}
			}
		}
	}

	@SubscribeEvent
	public void onCorruptedInteract(EntityInteractEvent event)
	{
		if (event.entityPlayer.worldObj.isRemote || !(event.target instanceof EntityLivingBase) || !TragicConfig.allowCorruption) return;

		EntityLivingBase target = (EntityLivingBase) event.target;
		ItemStack stack = event.entityPlayer.getCurrentEquippedItem();
		if (target.isPotionActive(TragicPotion.Corruption) && stack != null)
		{
			if (stack.getItem() == TragicItems.NastyFruit)
			{
				target.removePotionEffect(TragicPotion.Corruption.id);
				if (TragicConfig.allowImmunity) target.addPotionEffect(new PotionEffect(TragicPotion.Immunity.id, 6000));
				--stack.stackSize;
			}
			else if (stack.getItem() == TragicItems.GoldenSushi)
			{
				target.removePotionEffect(TragicPotion.Corruption.id);
				if (TragicConfig.allowImmunity) target.addPotionEffect(new PotionEffect(TragicPotion.Immunity.id, 6000));
				target.addPotionEffect(new PotionEffect(Potion.field_76444_x.id, 2400, 4));
				target.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 1200, 1));
				target.addPotionEffect(new PotionEffect(Potion.field_76443_y.id, 2400, 1));
				target.addPotionEffect(new PotionEffect(Potion.regeneration.id, 2400, 1));
				if (TragicConfig.allowClarity) target.addPotionEffect(new PotionEffect(TragicPotion.Clarity.id, 2400, 1));
				--stack.stackSize;
			}
		}
	}
}
