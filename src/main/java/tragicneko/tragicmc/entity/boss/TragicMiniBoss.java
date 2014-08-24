package tragicneko.tragicmc.entity.boss;

import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import tragicneko.tragicmc.entity.mob.TragicMob;
import tragicneko.tragicmc.main.TragicItems;

public abstract class TragicMiniBoss extends TragicMob
{
	public TragicMiniBoss(World par1World) {
		super(par1World);
		this.isChangeable = false;
		this.isCorrupted = true;
		this.isCorruptible = false;
		this.canCorrupt = true;
	}
	
	public void onDeath(DamageSource par1)
	{
		super.onDeath(par1);

		int id = 0;

		if (this instanceof EntityJarra)
		{
			id = 6;
		}
		else if (this instanceof EntityKragul)
		{
			id = 7;
		}
		else if (this instanceof EntityMagmox)
		{
			id = 8;
		}
		else if (this instanceof EntityMegaCryse)
		{
			id = 9;
		}
		else if (this instanceof EntityStinKing)
		{
			id = 10;
		}
		else if (this instanceof EntityStinQueen)
		{
			id = 11;
		}
		else if (this instanceof EntityGreaterStin)
		{
			id = 12;
		}

		if (!this.worldObj.isRemote && id != 0) this.entityDropItem(new ItemStack(TragicItems.MobStatue, 1, id), (0.25F * rand.nextFloat()) - (rand.nextFloat() * 0.25F));
	}

	@Override
	public void onCorruption() {
	}

	@Override
	public void onChange(World world, TragicMob entity, TragicMiniBoss boss,
			double par1, double par2, double par3) {
	}

	@Override
	public void onPurify() {
		
	}

}
