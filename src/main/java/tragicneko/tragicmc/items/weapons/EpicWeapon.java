package tragicneko.tragicmc.items.weapons;

import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.management.ItemInWorldManager;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicEnchantments;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.doomsday.Doomsday;

public class EpicWeapon extends TragicWeapon {

	public EpicWeapon(Doomsday dday) {
		super(TragicItems.toolEpic, dday);
		this.uncommonEnchants = new Enchantment[] {Enchantment.unbreaking, TragicEnchantments.Reach};
		this.uncommonLevels = new int[] {3, 3};
	}
}
