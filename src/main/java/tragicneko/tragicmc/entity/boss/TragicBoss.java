package tragicneko.tragicmc.entity.boss;

import java.util.ArrayList;

import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.dimension.TragicWorldProvider;
import tragicneko.tragicmc.util.EntityDropHelper;
import tragicneko.tragicmc.util.WorldHelper;

public abstract class TragicBoss extends EntityMob implements IBossDisplayData
{
	public TragicBoss(World par1World) {
		super(par1World);
		this.experienceValue = 100;
	}

	@Override
	public boolean isAIEnabled()
	{
		return true;
	}

	@Override
	public void onDeath(DamageSource par1)
	{
		super.onDeath(par1);

		if (this.worldObj.isRemote || !this.getAllowLoot()) return;

		ArrayList<int[]> list = WorldHelper.getBlocksInSphericalRange(this.worldObj, 4.0D, this.posX, this.posY, this.posZ);
		int[] coords;

		for (int i = 0; i < list.size(); i++)
		{
			coords= list.get(i);
			if (this.worldObj.getBlock(coords[0], coords[1], coords[2]).getMaterial() == Material.fire) this.worldObj.setBlockToAir(coords[0], coords[1], coords[2]);
		}

		int x = 3;

		if (par1.getEntity() != null && par1.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) par1.getEntity();

			if (player.getCurrentEquippedItem() != null)
			{
				ItemStack weapon = player.getCurrentEquippedItem();
				x += EnchantmentHelper.getEnchantmentLevel(Enchantment.looting.effectId, weapon);
			}
		}

		int amt = 0;

		if (TragicConfig.allowExtraBossLoot)
		{
			int amount = rand.nextInt(6) + 4 * x;

			for (int i = 0; i < amount; i++)
			{
				if (rand.nextBoolean())
				{
					ItemStack luxuryDrop = EntityDropHelper.getLuxuryDropForBoss();
					if (luxuryDrop != null) this.entityDropItem(luxuryDrop, 0.4F);
					amt++;
				}

				if (amt >= 6 + x) break;
			}
		}

		int total = 0;

		for (int i = 0; i < x + 3; i++)
		{
			if (rand.nextInt(100) <= TragicConfig.commonDropRate + (x * 4))
			{
				ItemStack drop = EntityDropHelper.getDropFromEntity(this.getClass(), true);
				if (drop != null) this.entityDropItem(drop, 0.4F);
				total += 1;
			}

			if (this.recentlyHit > 0 && rand.nextInt(25) <= TragicConfig.rareDropRate + x)
			{
				ItemStack drop = EntityDropHelper.getDropFromEntity(this.getClass(), false);
				if (drop != null) this.entityDropItem(drop, 0.4F);
				total += 1;
			}

			if (total >= x * 2.5) break;
		}

		if (this.recentlyHit > 0)
		{
			ItemStack drop = EntityDropHelper.getDropFromEntity(this.getClass(), false);
			if (drop != null) this.entityDropItem(drop, 0.4F);
		}
	}

	@Override
	public void onLivingUpdate()
	{
		if (TragicConfig.allowCorruption && this.isPotionActive(TragicPotion.Corruption)) this.removePotionEffect(TragicPotion.Corruption.id);
		super.onLivingUpdate();
		if (this.getAttackTarget() != null && this.getAttackTarget().isDead) this.setAttackTarget(null);
		if (this.worldObj.difficultySetting == EnumDifficulty.EASY && !TragicConfig.allowEasyBosses || this.posY <= -30 || this.posY > 280) this.setDead();
	}

	@Override
	public boolean getCanSpawnHere()
	{
		if (rand.nextInt(10) != 0) return false;

		if (this.posY <= 63)
		{
			switch (this.worldObj.provider.dimensionId)
			{
			case 0:
				return false;
			case 1:
				return rand.nextInt(4) == 0 ? false : super.getCanSpawnHere();
			case -1:
				return false;
			default:
				if (this.worldObj.provider instanceof TragicWorldProvider)
				{
					return super.getCanSpawnHere();
				}
				else
				{
					return false;
				}
			}
		}
		return super.getCanSpawnHere();
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (TragicConfig.allowStun && this.isPotionActive(TragicPotion.Stun)) return false;
		return super.attackEntityAsMob(par1Entity);
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		if (this.worldObj.isRemote) return false;

		if (par1DamageSource.getEntity() != null)
		{
			if (par1DamageSource.getEntity() instanceof EntityPlayer && par2 >= TragicConfig.bossDamageCap)
			{
				EntityPlayer player = (EntityPlayer) par1DamageSource.getEntity();
				boolean flag = player.getCurrentEquippedItem() == null ? false : (player.getCurrentEquippedItem().getItem() == TragicItems.BowOfJustice || player.getCurrentEquippedItem().getItem() == TragicItems.SwordOfJustice);

				if (!player.capabilities.isCreativeMode || !flag) par2 = MathHelper.clamp_float(par2, 0.0F, TragicConfig.bossDamageCap);
			}

			if (rand.nextBoolean() && this.getAttackTarget() != null && par1DamageSource.getEntity() instanceof EntityLivingBase && this.getAttackTarget() != par1DamageSource.getEntity()) this.setAttackTarget((EntityLivingBase) par1DamageSource.getEntity());
		}

		return super.attackEntityFrom(par1DamageSource, par2);
	}

	@Override
	public int getMaxSpawnedInChunk()
	{
		return 1;
	}

	public boolean getMobGriefing()
	{
		return this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
	}

	public boolean getAllowLoot()
	{
		return this.worldObj.getGameRules().getGameRuleBooleanValue("doMobLoot");
	}

	public boolean isEntityInRange(Entity entity, float min, float max)
	{
		float f = this.getDistanceToEntity(entity);
		return f >= min && f <= max;
	}

	public int getIntegerInRange(int min, int max)
	{
		int cand = MathHelper.getRandomIntegerInRange(rand, min, max);
		return rand.nextBoolean() ? cand : -cand;
	}

	public int getPlayersNearby() {
		return this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, boundingBox.expand(64.0, 64.0, 64.0)).size();
	}

	public int getPlayersNearby(int min, int max)
	{
		return MathHelper.clamp_int(getPlayersNearby(), min, max);
	}

	public int getPlayersNearby(double range)
	{
		return this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, boundingBox.expand(range, range, range)).size();
	}

	public int getPlayersNearby(double range, int min, int max)
	{
		return MathHelper.clamp_int(getPlayersNearby(range), min, max);
	}

	public void healByFactor(float factor)
	{
		int i = this.getPlayersNearby();
		this.heal(factor * i);
	}

	public void healByFactorRanged(float factor, float min, float max)
	{
		int i = this.getPlayersNearby();
		float f = MathHelper.clamp_float(factor * i, min, max);
		this.heal(f);
	}

	public int getHighestSolidBlock(int posX, int posY, int posZ) {
		while(this.worldObj.getBlock(posX, posY, posZ).getMaterial() == Material.air && posY > 0)
		{
			--posY;
		}
		return posY;
	}
}
