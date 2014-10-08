package tragicneko.tragicmc.entity.boss;

import java.util.UUID;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.dimension.TragicWorldProvider;
import tragicneko.tragicmc.main.TragicBlocks;
import tragicneko.tragicmc.main.TragicItems;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import tragicneko.tragicmc.util.EntityDropHelper;

public class TragicBoss extends EntityMob implements IBossDisplayData
{
	private static ItemStack[] luxuryDrops = new ItemStack[] {new ItemStack(Items.diamond, TragicMC.rand.nextInt(4) + 1), new ItemStack(Items.emerald, TragicMC.rand.nextInt(4) + 1),
		new ItemStack(Items.iron_ingot, TragicMC.rand.nextInt(4) + 1), new ItemStack(Items.gold_ingot, TragicMC.rand.nextInt(4) + 1), new ItemStack(Items.diamond, TragicMC.rand.nextInt(4) + 1, TragicMC.rand.nextInt(2)),
		new ItemStack(TragicItems.Ruby, TragicMC.rand.nextInt(4) + 1), new ItemStack(TragicItems.Sapphire, TragicMC.rand.nextInt(4) + 1),
		new ItemStack(TragicItems.Tungsten, TragicMC.rand.nextInt(4) + 1), new ItemStack(TragicItems.RedMercury, TragicMC.rand.nextInt(4) + 1),
		new ItemStack(TragicBlocks.StorageBlock, TragicMC.rand.nextInt(2) + 1, TragicMC.rand.nextInt(5)), new ItemStack(Blocks.gold_block, TragicMC.rand.nextInt(2) + 1),
		new ItemStack(Blocks.diamond_block, TragicMC.rand.nextInt(2) + 1), new ItemStack(Blocks.iron_block, TragicMC.rand.nextInt(2) + 1),
		new ItemStack(TragicItems.AmuletRelease, 1), new ItemStack(TragicItems.AwakeningStone, 1), new ItemStack(TragicItems.CooldownDefuse, 1 + TragicMC.rand.nextInt(4)),
		new ItemStack(TragicItems.DoomConsume, 1), new ItemStack(TragicItems.Titan), new ItemStack(TragicItems.Paranoia), new  ItemStack(TragicItems.Splinter),
		new ItemStack(TragicItems.Butcher), new ItemStack(TragicItems.Thardus), new ItemStack(TragicItems.DragonFang), new ItemStack(TragicItems.Talisman)};

	public TragicBoss(World par1World) {
		super(par1World);
		this.experienceValue = 100;
	}

	public void onDeath(DamageSource par1)
	{
		super.onDeath(par1);

		if (this.worldObj.isRemote || !this.getAllowLoot()) return;
		
		int amt = 0;

		if (TragicNewConfig.allowExtraBossLoot)
		{
			int amount = rand.nextInt(6) + 4;

			for (int i = 0; i < amount; i++)
			{
				if (rand.nextBoolean())
				{
					this.entityDropItem(luxuryDrops[rand.nextInt(luxuryDrops.length)].copy(), 0.4F);
					amt++;
				}

				if (amt >= 6) break;
			}
		}

		if (!this.worldObj.isRemote)
		{
			int x = 1;

			if (par1.getEntity() != null && par1.getEntity() instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer) par1.getEntity();

				if (player.getCurrentEquippedItem() != null)
				{
					ItemStack weapon = player.getCurrentEquippedItem();
					x += EnchantmentHelper.getEnchantmentLevel(Enchantment.looting.effectId, weapon);
				}
			}

			int y = TragicNewConfig.commonDropRate;
			int z = TragicNewConfig.rareDropRate;
			int total = 0;

			for (int i = 0; i < x; i++)
			{
				if (rand.nextInt(100) <= y + (x * 4))
				{
					ItemStack drop = EntityDropHelper.getCommonDropFromEntity(this.getClass());
					if (drop != null) this.entityDropItem(drop, 0.4F);
					total += 1;
				}

				if (this.recentlyHit > 0 && rand.nextInt(25) <= z + x)
				{
					ItemStack drop = EntityDropHelper.getRareDropFromEntity(this.getClass());
					if (drop != null) this.entityDropItem(drop, 0.4F);
					total += 1;
				}

				if (total >= x * 2) break;
			}

			if (this.recentlyHit > 0)
			{
				ItemStack drop = EntityDropHelper.getRareDropFromEntity(this.getClass());
				if (drop != null) this.entityDropItem(drop, 0.4F);
				total++;
			}
		}
	}

	public void onLivingUpdate()
	{
		if (!this.worldObj.isRemote && TragicNewConfig.allowCorruption && this.isPotionActive(TragicPotions.Corruption)) this.removePotionEffect(TragicPotions.Corruption.id);
		super.onLivingUpdate();
		if (this.getAttackTarget() != null && this.getAttackTarget().isDead) this.setAttackTarget(null);
		if (this.worldObj.difficultySetting == EnumDifficulty.EASY) this.setDead();
	}

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

	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (TragicNewConfig.allowStun && this.isPotionActive(TragicPotions.Stun)) return false;
		return super.attackEntityAsMob(par1Entity);
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		if (this.worldObj.isRemote) return false;

		if (par1DamageSource.getEntity() != null)
		{
			if (par1DamageSource.getEntity() instanceof EntityPlayer && par2 >= 30)
			{
				EntityPlayer player = (EntityPlayer) par1DamageSource.getEntity();

				if (!player.capabilities.isCreativeMode)
				{
					par2 = MathHelper.clamp_float(par2, 0.0F, 30.0F);
				}
				else if (player.getCurrentEquippedItem() != null)
				{
					boolean flag = player.getCurrentEquippedItem().getItem() == TragicItems.BowOfJustice || player.getCurrentEquippedItem().getItem() == TragicItems.SwordOfJustice;
					if (!flag) return false;
				}
			}
			
			if (rand.nextBoolean() && this.getAttackTarget() != null && par1DamageSource.getEntity() instanceof EntityLivingBase) this.setAttackTarget((EntityLivingBase) par1DamageSource.getEntity());
		}
		return super.attackEntityFrom(par1DamageSource, par2);
	}

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
}
