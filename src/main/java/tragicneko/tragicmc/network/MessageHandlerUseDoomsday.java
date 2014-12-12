package tragicneko.tragicmc.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.TragicNewConfig;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.doomsday.Doomsday.EnumDoomType;
import tragicneko.tragicmc.items.armor.TragicArmor;
import tragicneko.tragicmc.items.weapons.ItemJack;
import tragicneko.tragicmc.items.weapons.ItemScythe;
import tragicneko.tragicmc.items.weapons.ItemShield;
import tragicneko.tragicmc.items.weapons.TragicWeapon;
import tragicneko.tragicmc.items.weapons.WeaponCelestialLongbow;
import tragicneko.tragicmc.items.weapons.WeaponHuntersBow;
import tragicneko.tragicmc.properties.PropertyDoom;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageHandlerUseDoomsday implements IMessageHandler<MessageUseDoomsday, IMessage> {

	@Override
	public MessageDoom onMessage(MessageUseDoomsday message, MessageContext ctx) {
		EntityPlayer player = MinecraftServer.getServer().isDedicatedServer() ? TragicMC.proxy.getPlayerFromMessageCtx(ctx) : ctx.getServerHandler().playerEntity;

		if (player == null) return null;
		PropertyDoom doom = PropertyDoom.get(player);

		if (doom == null) return null;

		ItemStack stack = message.stack;
		Doomsday doomsday = null;
		boolean flag = false;

		if (stack != null)
		{
			flag = true;
			if (stack.getItem() instanceof WeaponHuntersBow)
			{
				doomsday = ((WeaponHuntersBow)stack.getItem()).doomsday;
			}
			else if (stack.getItem() instanceof WeaponCelestialLongbow)
			{
				doomsday = ((WeaponCelestialLongbow)stack.getItem()).doomsday;
			}
			else if (stack.getItem() instanceof TragicWeapon)
			{
				TragicWeapon weapon = (TragicWeapon) stack.getItem();
				doomsday = !player.isSneaking() && weapon.getSecondaryDoomsday() != null ? weapon.getSecondaryDoomsday() : weapon.getDoomsday();
			}
			else if (stack.getItem() instanceof ItemShield)
			{
				doomsday = ((ItemShield)stack.getItem()).doomsday;
			}
			else if (stack.getItem() instanceof ItemScythe)
			{
				doomsday = ((ItemScythe)stack.getItem()).doomsday;
			}
			else if (stack.getItem() instanceof ItemJack)
			{
				doomsday = ((ItemJack)stack.getItem()).doomsday;
			}
		}
		else
		{
			boolean flag2 = false;
			Doomsday[] doomsdays = new Doomsday[4];

			for (int i = 0; i < 4; i++)
			{
				stack = player.getCurrentArmor(i);

				if (stack != null && stack.getItem() instanceof TragicArmor)
				{
					if (i == 0)
					{
						doomsday = ((TragicArmor)stack.getItem()).doomsday;
					}

					doomsdays[i] = ((TragicArmor)stack.getItem()).doomsday;
				}
			}

			for (int i = 0; i < 4; i++)
			{
				if (doomsdays[i] == null)
				{
					flag2 = false;
				}
				else if (doomsdays[i] == doomsday)
				{
					flag2 = true;
				}
				else
				{
					flag2 = false;
				}
			}

			if (!flag2) return null;
		}

		if (doomsday == null) return null;

		if (doomsday.getDoomsdayType() == EnumDoomType.INFLUENCE && !TragicNewConfig.allowInfluenceDoomsday ||
				doomsday.getDoomsdayType() == EnumDoomType.CRISIS && !TragicNewConfig.allowCrisisDoomsday ||
				doomsday.getDoomsdayType() == EnumDoomType.OVERFLOW && !TragicNewConfig.allowOverflowDoomsday ||
				doomsday.getDoomsdayType() == EnumDoomType.WORLDSHAPER && !TragicNewConfig.allowWorldShaperDoomsday ||
				doomsday.getDoomsdayType() == EnumDoomType.COMBINATION && !TragicNewConfig.allowCombinationDoomsday)
		{
			player.addChatComponentMessage(new ChatComponentText("The Doomsday of that type is disabled, enable in config."));
			return null;
		}
		
		if (!TragicNewConfig.doomsdayAllow[doomsday.doomID])
		{
			player.addChatComponentMessage(new ChatComponentText("That particular Doomsday is disabled, enable in config."));
			return null;
		}
		
		TragicMC.logInfo("Doomsday is " + doomsday);

		doomsday.activateDoomsday(doom);

		return null;
	}

}
