package tragicneko.tragicmc.events;

import static tragicneko.tragicmc.TragicMC.rand;
import static tragicneko.tragicmc.TragicNewConfig.modifierAmts;

import java.util.List;
import java.util.UUID;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicNewConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.entity.mob.EntityMinotaur;
import tragicneko.tragicmc.entity.mob.TragicMob;
import tragicneko.tragicmc.properties.PropertyDoom;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class VanillaChangingEvents {

	private static AttributeModifier ghastHealthBuff = new AttributeModifier(UUID.fromString("cb92285c-f0b5-44b5-b500-3ddd7e08ceae"), "ghastHealthBuff", modifierAmts[14], 0);
	private static AttributeModifier normalHealthBuff = new AttributeModifier(UUID.fromString("d72b0471-d23a-4a9a-a7f8-e2a54018a4ee"), "zombieSkeletonCreeperHealthBuff", modifierAmts[15], 0);
	private static AttributeModifier endermanHealthBuff = new AttributeModifier(UUID.fromString("883e8a02-2f76-43d0-b7ee-de412b0c352d"), "endermanHealthBuff", 20.0, 0);
	private static AttributeModifier spiderHealthBuff = new AttributeModifier(UUID.fromString("e4cec251-fce7-4cbb-9784-eba58a140c30"), "spiderHealthBuff", 8.0, 0);
	private static AttributeModifier mobBlindnessDebuff = new AttributeModifier(UUID.fromString("6a73b2cb-c791-4b10-849c-6817ec3eab22"), "mobBlindnessFollowRangeDebuff", -16.0, 0);

	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event)
	{		
		if (event.entityLiving.getEquipmentInSlot(0) != null && event.entityLiving instanceof EntityZombie)
		{
			if (event.entityLiving.getEquipmentInSlot(0).getItem() == Items.egg)
			{
				((EntityMob)event.entityLiving).setCurrentItemOrArmor(0, null);
			}
		}

		if (event.entityLiving instanceof EntityEnderman && TragicNewConfig.allowVanillaMobBuffs)
		{
			if (event.entityLiving.ticksExisted % 120 == 0 && event.entityLiving.getHealth() < event.entityLiving.getMaxHealth())
			{
				event.entityLiving.heal(3.0F);
			}
		}
	}

	@SubscribeEvent
	public void onEntityDeath(LivingDeathEvent event)
	{
		if (event.entityLiving instanceof EntityGhast && !event.entityLiving.worldObj.isRemote)
		{
			if (event.source.damageType == "fireball" && event.source.getEntity() != null && event.source.getEntity() instanceof EntityPlayer)
			{
				event.entityLiving.entityDropItem(new ItemStack(Items.ghast_tear, 1 + rand.nextInt(2)), rand.nextFloat());
			}
		}

		if (event.entityLiving.worldObj.difficultySetting == EnumDifficulty.HARD && TragicNewConfig.allowAnimalRetribution && rand.nextInt(16) == 0)
		{
			if (event.entityLiving instanceof EntityPig && !event.entityLiving.worldObj.isRemote)
			{
				if (event.source.getEntity() != null && event.source.getEntity() instanceof EntityPlayer && event.entityLiving.worldObj.difficultySetting == EnumDifficulty.HARD)
				{
					List<Entity> list = event.entityLiving.worldObj.getEntitiesWithinAABBExcludingEntity(event.entityLiving, event.entityLiving.boundingBox.expand(16.0, 16.0, 16.0));
					if (list.size() > 0 && list.size() <= 4 && rand.nextInt(100) == 0)
					{
						for (int i = 0; i < list.size(); i++)
						{
							Entity entity = list.get(i);
							if (entity instanceof EntityPig && rand.nextInt(4) == 0 || entity instanceof EntityPlayer)
							{
								double x = entity.posX;
								double y = entity.posY;
								double z = entity.posZ;

								EntityLightningBolt lightning = new EntityLightningBolt(entity.worldObj, x, y, z);
								entity.worldObj.spawnEntityInWorld(lightning);
							}
						}
					}
				}
			}

			if (event.entityLiving instanceof EntityChicken && !event.entityLiving.worldObj.isRemote)
			{
				if (event.source.getEntity() != null && event.source.getEntity() instanceof EntityPlayer && event.entityLiving.worldObj.difficultySetting == EnumDifficulty.HARD)
				{
					List<Entity> list = event.entityLiving.worldObj.getEntitiesWithinAABBExcludingEntity(event.entityLiving, event.entityLiving.boundingBox.expand(16.0, 16.0, 16.0));
					if (list.size() > 0 && list.size() <= 5 && rand.nextInt(100) == 0)
					{
						for (int i = 0; i < list.size(); i++)
						{
							Entity entity = list.get(i);
							if (entity instanceof EntityChicken && rand.nextInt(4) == 0 || entity instanceof EntityPlayer)
							{
								double x = entity.posX;
								double y = entity.posY;
								double z = entity.posZ;

								EntityLightningBolt lightning = new EntityLightningBolt(entity.worldObj, x, y, z);
								entity.worldObj.spawnEntityInWorld(lightning);
							}
						}
					}
				}
			}

			if (event.entityLiving instanceof EntityCow && !event.entityLiving.worldObj.isRemote)
			{
				if (event.source.getEntity() != null && event.source.getEntity() instanceof EntityPlayer && event.entityLiving.worldObj.difficultySetting == EnumDifficulty.HARD)
				{
					List<Entity> list = event.entityLiving.worldObj.getEntitiesWithinAABBExcludingEntity(event.entityLiving, event.entityLiving.boundingBox.expand(16.0, 16.0, 16.0));
					if (list.size() > 0 && list.size() <= 5 && rand.nextInt(100) == 0)
					{
						for (int i = 0; i < list.size(); i++)
						{
							Entity entity = list.get(i);
							if (entity instanceof EntityCow && rand.nextInt(4) == 0 || entity instanceof EntityPlayer)
							{
								double x = entity.posX;
								double y = entity.posY;
								double z = entity.posZ;

								EntityLightningBolt lightning = new EntityLightningBolt(entity.worldObj, x, y, z);
								entity.worldObj.spawnEntityInWorld(lightning);
							}
						}
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onEntityJoin(EntityJoinWorldEvent event)
	{
		if (event.entity.worldObj.difficultySetting == EnumDifficulty.HARD)
		{
			if (TragicNewConfig.allowVanillaMobBuffs)
			{
				if (event.entity instanceof EntityGhast)
				{
					((EntityGhast)event.entity).getEntityAttribute(SharedMonsterAttributes.maxHealth).removeModifier(ghastHealthBuff);
					((EntityGhast)event.entity).getEntityAttribute(SharedMonsterAttributes.maxHealth).applyModifier(ghastHealthBuff);
					((EntityGhast)event.entity).heal(((EntityGhast)event.entity).getMaxHealth());
				}
				else if (event.entity instanceof EntityZombie || event.entity instanceof EntitySkeleton || event.entity instanceof EntityCreeper)
				{
					((EntityMob)event.entity).getEntityAttribute(SharedMonsterAttributes.maxHealth).removeModifier(normalHealthBuff);
					((EntityMob)event.entity).getEntityAttribute(SharedMonsterAttributes.maxHealth).applyModifier(normalHealthBuff);
					((EntityMob)event.entity).heal(((EntityMob)event.entity).getMaxHealth());
				}
				else if (event.entity instanceof EntityEnderman)
				{
					((EntityMob)event.entity).getEntityAttribute(SharedMonsterAttributes.maxHealth).removeModifier(endermanHealthBuff);
					((EntityMob)event.entity).getEntityAttribute(SharedMonsterAttributes.maxHealth).applyModifier(endermanHealthBuff);
					((EntityMob)event.entity).heal(((EntityMob)event.entity).getMaxHealth());
				}

				else if (event.entity instanceof EntitySpider)
				{
					((EntityMob)event.entity).getEntityAttribute(SharedMonsterAttributes.maxHealth).removeModifier(spiderHealthBuff);
					((EntityMob)event.entity).getEntityAttribute(SharedMonsterAttributes.maxHealth).applyModifier(spiderHealthBuff);
					((EntityMob)event.entity).heal(((EntityMob)event.entity).getMaxHealth());
				}
				else if (event.entity instanceof EntityPigZombie)
				{
					((EntityMob)event.entity).getEntityAttribute(SharedMonsterAttributes.maxHealth).removeModifier(normalHealthBuff);
					((EntityMob)event.entity).getEntityAttribute(SharedMonsterAttributes.maxHealth).applyModifier(normalHealthBuff);
					((EntityMob)event.entity).heal(((EntityMob)event.entity).getMaxHealth());
				}
			}

			if (TragicNewConfig.allowMobModdedArmorAndEnchants && !event.entity.worldObj.isRemote)
			{
				if (event.entity instanceof EntityZombie || event.entity instanceof EntitySkeleton)
				{
					for (int i = 0; i < 4; i++)
					{
						ItemStack stack = ((EntityMob)event.entity).getEquipmentInSlot(i);

						if (stack == null && rand.nextInt(6) == 0)
						{
							switch(i)
							{
							case 0:
								switch(rand.nextInt(8))
								{
								case 0:
									((EntityMob)event.entity).setCurrentItemOrArmor(i, new ItemStack(TragicItems.Scythe));
									break;
								case 1:
									((EntityMob)event.entity).setCurrentItemOrArmor(i, new ItemStack(TragicItems.MercuryDagger));
									break;
								case 2:
									((EntityMob)event.entity).setCurrentItemOrArmor(i, new ItemStack(TragicItems.TungstenJack));
									break;
								case 3:
									((EntityMob)event.entity).setCurrentItemOrArmor(i, new ItemStack(Items.iron_sword));
									break;
								case 4:
									((EntityMob)event.entity).setCurrentItemOrArmor(i, new ItemStack(Items.golden_sword));
									break;
								case 5:
									((EntityMob)event.entity).setCurrentItemOrArmor(i, new ItemStack(Items.wooden_sword));
									break;
								}
								break;
							case 1:
								switch(rand.nextInt(6))
								{
								case 0:
									((EntityMob)event.entity).setCurrentItemOrArmor(i, new ItemStack(TragicItems.SkullHelmet));
									break;
								case 1:
									((EntityMob)event.entity).setCurrentItemOrArmor(i, new ItemStack(TragicItems.MercuryHelm));
									break;
								case 2:
									((EntityMob)event.entity).setCurrentItemOrArmor(i, new ItemStack(TragicItems.SkullHelmet));
									break;
								case 3:
									((EntityMob)event.entity).setCurrentItemOrArmor(i, new ItemStack(Items.iron_helmet));
									break;
								case 4:
									((EntityMob)event.entity).setCurrentItemOrArmor(i, new ItemStack(Items.golden_helmet));
									break;
								case 5:
									((EntityMob)event.entity).setCurrentItemOrArmor(i, new ItemStack(Items.leather_helmet));
									break;
								}
								break;
							case 2:
								switch(rand.nextInt(6))
								{
								case 0:
									((EntityMob)event.entity).setCurrentItemOrArmor(i, new ItemStack(TragicItems.SkullPlate));
									break;
								case 1:
									((EntityMob)event.entity).setCurrentItemOrArmor(i, new ItemStack(TragicItems.MercuryPlate));
									break;
								case 2:
									((EntityMob)event.entity).setCurrentItemOrArmor(i, new ItemStack(TragicItems.SkullPlate));
									break;
								case 3:
									((EntityMob)event.entity).setCurrentItemOrArmor(i, new ItemStack(Items.iron_chestplate));
									break;
								case 4:
									((EntityMob)event.entity).setCurrentItemOrArmor(i, new ItemStack(Items.golden_chestplate));
									break;
								case 5:
									((EntityMob)event.entity).setCurrentItemOrArmor(i, new ItemStack(Items.leather_chestplate));
									break;
								}
								break;
							case 3:
								switch(rand.nextInt(6))
								{
								case 0:
									((EntityMob)event.entity).setCurrentItemOrArmor(i, new ItemStack(TragicItems.SkullLegs));
									break;
								case 1:
									((EntityMob)event.entity).setCurrentItemOrArmor(i, new ItemStack(TragicItems.MercuryLegs));
									break;
								case 2:
									((EntityMob)event.entity).setCurrentItemOrArmor(i, new ItemStack(TragicItems.SkullLegs));
									break;
								case 3:
									((EntityMob)event.entity).setCurrentItemOrArmor(i, new ItemStack(Items.iron_leggings));
									break;
								case 4:
									((EntityMob)event.entity).setCurrentItemOrArmor(i, new ItemStack(Items.golden_leggings));
									break;
								case 5:
									((EntityMob)event.entity).setCurrentItemOrArmor(i, new ItemStack(Items.leather_leggings));
									break;
								}
								break;
							case 4:
								switch(rand.nextInt(6))
								{
								case 0:
									((EntityMob)event.entity).setCurrentItemOrArmor(i, new ItemStack(TragicItems.SkullBoots));
									break;
								case 1:
									((EntityMob)event.entity).setCurrentItemOrArmor(i, new ItemStack(TragicItems.MercuryBoots));
									break;
								case 2:
									((EntityMob)event.entity).setCurrentItemOrArmor(i, new ItemStack(TragicItems.SkullBoots));
									break;
								case 3:
									((EntityMob)event.entity).setCurrentItemOrArmor(i, new ItemStack(Items.iron_boots));
									break;
								case 4:
									((EntityMob)event.entity).setCurrentItemOrArmor(i, new ItemStack(Items.golden_boots));
									break;
								case 5:
									((EntityMob)event.entity).setCurrentItemOrArmor(i, new ItemStack(Items.leather_boots));
									break;
								}
								break;
							}
						}

						stack = ((EntityMob)event.entity).getEquipmentInSlot(i);

						if (stack != null && !stack.isItemEnchanted())
						{
							float f = event.entity.worldObj.func_147462_b(event.entity.posX, event.entity.posY, event.entity.posZ);
							EnchantmentHelper.addRandomEnchantment(rand, stack, (int)(5.0F + f * (float)rand.nextInt(18)));
						}
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onEntityAttack(LivingAttackEvent event)
	{		
		if (event.entityLiving.worldObj.isRemote) return;

		if (event.entityLiving instanceof EntityEnderman || event.entityLiving instanceof EntityWitch)
		{
			if (event.source == DamageSource.magic && event.isCancelable()) event.setCanceled(true);
		}

		if (event.source.getEntity() != null && event.source.getEntity() instanceof EntityLivingBase && !event.source.isMagicDamage()
				&& event.source.isExplosion() && !event.source.isProjectile() && rand.nextInt(4) == 0 && TragicNewConfig.allowExtraExplosiveEffects)
		{
			if (event.entityLiving instanceof EntityPlayer && !((EntityPlayer)event.entityLiving).capabilities.isCreativeMode)
			{
				if (rand.nextBoolean())
				{
					event.entityLiving.addPotionEffect(new PotionEffect(Potion.confusion.id, rand.nextInt(80) + 60));
				}
				else
				{
					if (TragicNewConfig.allowDisorientation) event.entityLiving.addPotionEffect(new PotionEffect(TragicPotion.Disorientation.id, rand.nextInt(80) + 60));
				}
			}
		}

		if (event.entityLiving.worldObj.difficultySetting == EnumDifficulty.HARD && event.source.getEntity() != null && TragicNewConfig.allowExtraMobEffects)
		{
			if (event.source.getEntity() instanceof EntityLivingBase && event.source.getEntity().isBurning() && !event.source.isMagicDamage()
					&& !event.source.isExplosion() && !event.source.isProjectile() && rand.nextInt(4) == 0)
			{
				event.entityLiving.setFire(4 + rand.nextInt(4));
			}

			if (event.source.getEntity() instanceof EntityZombie)
			{
				if (((EntityZombie)event.source.getEntity()).isChild() && rand.nextInt(4) == 0 && TragicNewConfig.allowMalnourish)
				{
					event.entityLiving.addPotionEffect(new PotionEffect(TragicPotion.Malnourish.id, rand.nextInt(160) + 40, rand.nextInt(4)));
				}

				if (rand.nextInt(8) == 0) event.entityLiving.addPotionEffect(new PotionEffect(Potion.hunger.id, rand.nextInt(160) + 40, rand.nextInt(4)));
			}

			if (event.source.getEntity() instanceof EntitySlime)
			{
				if (event.entityLiving instanceof EntityPlayer)
				{
					PropertyDoom property = PropertyDoom.get((EntityPlayer) event.entityLiving);

					if (property != null && TragicNewConfig.allowDoom)
					{
						property.increaseDoom(-(2 + rand.nextInt(4)));

						if (event.source.getEntity() instanceof EntityMagmaCube)
						{
							property.increaseDoom(-(2 + rand.nextInt(4)));
						}
					}

					((EntitySlime)event.source.getEntity()).heal(event.ammount / 2);

				}
			}

			if (event.source.getEntity() instanceof EntityMagmaCube)
			{
				if (rand.nextInt(8) == 0) event.entityLiving.setFire(rand.nextInt(12));
				if (rand.nextInt(16) == 0 && TragicNewConfig.allowSubmission) event.entityLiving.addPotionEffect(new PotionEffect(TragicPotion.Submission.id, rand.nextInt(160) + 40, rand.nextInt(4)));
				if (rand.nextInt(32) == 0 && TragicNewConfig.allowCripple) event.entityLiving.addPotionEffect(new PotionEffect(TragicPotion.Cripple.id, rand.nextInt(160) + 40, rand.nextInt(4)));
				if (rand.nextInt(64) == 0 && TragicNewConfig.allowStun) event.entityLiving.addPotionEffect(new PotionEffect(TragicPotion.Stun.id, rand.nextInt(20) + 40, rand.nextInt(4)));
			}

			if (event.source.getEntity() instanceof EntityPigZombie)
			{
				if (rand.nextInt(4) == 0) event.entityLiving.addPotionEffect(new PotionEffect(Potion.weakness.id, rand.nextInt(160) + 40, rand.nextInt(4)));
				if (rand.nextInt(8) == 0 && TragicNewConfig.allowSubmission) event.entityLiving.addPotionEffect(new PotionEffect(TragicPotion.Submission.id, rand.nextInt(60) + 40, rand.nextInt(6)));
			}

			if (event.source.getEntity() instanceof EntitySkeleton)
			{
				if (event.source.getSourceOfDamage() instanceof EntityArrow)
				{
					if (rand.nextInt(16) == 0)
					{
						event.entityLiving.addPotionEffect(new PotionEffect(Potion.poison.id, rand.nextInt(160) + 40, rand.nextInt(4)));
					}
					else if (rand.nextInt(16) == 0 && TragicNewConfig.allowSubmission)
					{
						event.entityLiving.addPotionEffect(new PotionEffect(TragicPotion.Submission.id, rand.nextInt(160) + 40, rand.nextInt(6)));
					}

					else if (rand.nextInt(16) == 0)
					{
						event.entityLiving.addPotionEffect(new PotionEffect(Potion.confusion.id, rand.nextInt(160) + 40, rand.nextInt(6)));
					}

					else if (rand.nextInt(16) == 0)
					{
						event.entityLiving.addPotionEffect(new PotionEffect(Potion.weakness.id, rand.nextInt(160) + 40, rand.nextInt(6)));
					}

					else if (rand.nextInt(32) == 0 && TragicNewConfig.allowStun)
					{
						event.entityLiving.addPotionEffect(new PotionEffect(TragicPotion.Stun.id, rand.nextInt(40) + 20, rand.nextInt(4)));
					}
				}
				else
				{
					if (rand.nextInt(32) == 0)
					{
						event.entityLiving.addPotionEffect(new PotionEffect(Potion.blindness.id, rand.nextInt(60) + 80, rand.nextInt(4)));
					}
				}

				if (event.source.getEntity() instanceof EntitySilverfish)
				{
					if (rand.nextInt(4) == 0 && TragicNewConfig.allowStun)
					{
						event.entityLiving.addPotionEffect(new PotionEffect(TragicPotion.Stun.id, rand.nextInt(40) + 20, rand.nextInt(4)));
					}
				}

				if (event.source.getEntity() instanceof EntityEnderman)
				{
					if (rand.nextInt(16) == 0 && TragicNewConfig.allowSubmission)
					{
						event.entityLiving.addPotionEffect(new PotionEffect(TragicPotion.Submission.id, rand.nextInt(160) + 40, rand.nextInt(6)));
					}

					if (rand.nextInt(16) == 0 && TragicNewConfig.allowDisorientation)
					{
						event.entityLiving.addPotionEffect(new PotionEffect(TragicPotion.Disorientation.id, rand.nextInt(160) + 40, rand.nextInt(6)));
					}

					if (rand.nextInt(32) == 0 && TragicNewConfig.allowCripple)
					{
						event.entityLiving.addPotionEffect(new PotionEffect(TragicPotion.Cripple.id, rand.nextInt(160) + 40, rand.nextInt(4)));
					}

					if (rand.nextInt(16) == 0)
					{
						event.entityLiving.addPotionEffect(new PotionEffect(Potion.confusion.id, rand.nextInt(160) + 40, rand.nextInt(6)));
					}

					if (rand.nextInt(4) == 0)
					{
						event.entityLiving.addPotionEffect(new PotionEffect(Potion.blindness.id, rand.nextInt(80) + 40, rand.nextInt(6)));
					}

				}
			}

			if (event.source.getEntity() instanceof EntityBlaze)
			{
				if (rand.nextInt(8) == 0 && TragicNewConfig.allowDisorientation) event.entityLiving.addPotionEffect(new PotionEffect(TragicPotion.Disorientation.id, rand.nextInt(80) + 40, rand.nextInt(4)));
			}
		}

		if (event.entityLiving instanceof EntityEnderman)
		{
			if (event.entityLiving.isBurning()) event.entityLiving.extinguish();
		}

		if (event.entityLiving instanceof EntityCreature && TragicNewConfig.allowMobBlindnessDebuff)
		{
			event.entityLiving.getEntityAttribute(SharedMonsterAttributes.followRange).removeModifier(mobBlindnessDebuff);

			if (event.entityLiving.isPotionActive(Potion.blindness.id)|| event.entityLiving.isPotionActive(Potion.confusion.id) 
					|| TragicNewConfig.allowDisorientation && event.entityLiving.isPotionActive(TragicPotion.Disorientation.id))
			{
				event.entityLiving.getEntityAttribute(SharedMonsterAttributes.followRange).applyModifier(mobBlindnessDebuff);
			}
		}


	}

	@SubscribeEvent
	public void denyFallEvent(LivingFallEvent event)
	{
		if (event.entityLiving instanceof EntityEnderman || event.entityLiving instanceof EntitySpider)
		{
			event.entityLiving.fallDistance = 0.0F;
			if (event.isCancelable()) event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void onThunderstruck(EntityStruckByLightningEvent event)
	{
		if (event.entity instanceof EntityCow && TragicNewConfig.allowMinotaur)
		{
			TragicMob mob = new EntityMinotaur(event.entity.worldObj);

			mob.copyLocationAndAnglesFrom(event.entity);
			mob.onSpawnWithEgg((IEntityLivingData)null);
			event.entity.worldObj.removeEntity(event.entity);
			event.entity.worldObj.spawnEntityInWorld(mob);
			if (TragicNewConfig.allowInvulnerability) mob.addPotionEffect(new PotionEffect(TragicPotion.Invulnerability.id, 80));
		}

		if (rand.nextInt(4) == 0 && !event.lightning.worldObj.isRemote)
		{
			event.lightning.entityDropItem(new ItemStack(TragicItems.LightningOrb, 1), rand.nextFloat() - rand.nextFloat());
		}
	}
}
