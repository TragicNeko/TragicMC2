package tragicneko.tragicmc.entity.boss;

import java.util.UUID;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.main.TragicBlocks;
import tragicneko.tragicmc.main.TragicItems;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.util.EntityDropHelper;

public class TragicBoss extends EntityMob implements IBossDisplayData
{
	/**
	 * Time since being hit last by a player or a mob owned by a player
	 */
	protected int playerHitTicks;

	private static UUID victoryHealthUUID = UUID.fromString("7752a001-bd03-4e39-b24f-47f87f3738f7");
	private static AttributeModifier victoryHealthBuff = new AttributeModifier(victoryHealthUUID, "bossVictoryHealthBuff", 10.0, 0);

	private static ItemStack[] luxuryDrops = new ItemStack[] {new ItemStack(Items.diamond, TragicMC.rand.nextInt(4) + 1), new ItemStack(Items.emerald, TragicMC.rand.nextInt(4) + 1),
		new ItemStack(Items.iron_ingot, TragicMC.rand.nextInt(4) + 1), new ItemStack(Items.gold_ingot, TragicMC.rand.nextInt(4) + 1), new ItemStack(Items.diamond, TragicMC.rand.nextInt(4) + 1, TragicMC.rand.nextInt(2)),
		new ItemStack(TragicItems.Ruby, TragicMC.rand.nextInt(4) + 1), new ItemStack(TragicItems.Sapphire, TragicMC.rand.nextInt(4) + 1),
		new ItemStack(TragicItems.Tungsten, TragicMC.rand.nextInt(4) + 1), new ItemStack(TragicItems.RedMercury, TragicMC.rand.nextInt(4) + 1),
		new ItemStack(TragicBlocks.StorageBlock, TragicMC.rand.nextInt(2) + 1, TragicMC.rand.nextInt(5)), new ItemStack(Blocks.gold_block, TragicMC.rand.nextInt(2) + 1),
		new ItemStack(Blocks.diamond_block, TragicMC.rand.nextInt(2) + 1), new ItemStack(Blocks.iron_block, TragicMC.rand.nextInt(2) + 1),
		new ItemStack(TragicItems.AmuletRelease, 1), new ItemStack(TragicItems.AwakeningStone, 1), new ItemStack(TragicItems.CooldownDefuse, 1 + TragicMC.rand.nextInt(4)),
		new ItemStack(TragicItems.DoomConsume, 1)};

	public boolean isCorruptible = false;
	public static Entity currentTarget;

	public TragicBoss(World par1World) {
		super(par1World);
		this.experienceValue = 100;
	}

	protected boolean getCorruptible()
	{
		return this.isCorruptible;
	}

	public void onDeath(DamageSource par1)
	{
		if (this.worldObj.isRemote || !this.worldObj.getGameRules().getGameRuleBooleanValue("doMobLoot")) return;

		if (TragicNewConfig.allowExtraBossLoot)
		{
			int amount = rand.nextInt(20);

			if (amount < 10) amount = 10;

			for (int i = 0; i < amount; i++)
			{
				if (rand.nextBoolean()) this.entityDropItem(luxuryDrops[rand.nextInt(luxuryDrops.length)], 0.4F);
			}
		}

		super.onDeath(par1);
	}

	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		if (this.getAttackTarget() != null && this.getAttackTarget().isDead) this.setAttackTarget(null);
		if (this.worldObj.difficultySetting == EnumDifficulty.EASY) this.setDead();
		if (this.playerHitTicks > 0 && !this.worldObj.isRemote) this.playerHitTicks--;
	}

	public void despawnEntity(){}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		if (this.worldObj.isRemote) return false;

		if (par1DamageSource.getEntity() != null)
		{
			if (par1DamageSource.getEntity() instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer) par1DamageSource.getEntity();

				if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ItemSword)
				{
					ItemSword weapon = (ItemSword) player.getCurrentEquippedItem().getItem();
					ToolMaterial material = Item.ToolMaterial.valueOf(weapon.getToolMaterialName());
					float prevDamage = par2;
					float damage = material.getDamageVsEntity() + 4.0F;

					if (damage > 25 && !this.worldObj.isRemote && weapon != TragicItems.SwordOfJustice)
					{
						par2 /= 10.0F;

						if (damage > 250)
						{
							par2 /= 10.0F;

							if (damage > 2500)
							{
								par2 /= 10.0F;
							}
						}					
					}
				}
			}
		}
		return super.attackEntityFrom(par1DamageSource, par2);
	}

	public int getMaxSpawnedInChunk()
	{
		return 1;
	}

}
