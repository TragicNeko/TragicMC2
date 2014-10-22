package tragicneko.tragicmc.network;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import tragicneko.tragicmc.TragicMC;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageHandlerAttack implements IMessageHandler<MessageAttack, IMessage>{

	@Override
	public IMessage onMessage(MessageAttack message, MessageContext ctx) {
		EntityPlayer player = MinecraftServer.getServer().isDedicatedServer() ? TragicMC.proxy.getPlayerFromMessageCtx(ctx) : ctx.getServerHandler().playerEntity;
		if (player != null && player instanceof EntityPlayerMP)
		{
			Entity entity = MinecraftServer.getServer().getEntityWorld().getEntityByID(message.entityID);
			if (entity != null && !entity.isDead) ((EntityPlayerMP) player).attackTargetEntityWithCurrentItem(entity);
		}
		return null;
	}

}
