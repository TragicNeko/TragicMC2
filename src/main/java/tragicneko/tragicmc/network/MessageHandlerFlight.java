package tragicneko.tragicmc.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageHandlerFlight implements IMessageHandler<MessageFlight, IMessage>{

	@Override
	public IMessage onMessage(MessageFlight message, MessageContext ctx) {
		TragicConfig.allowFlight = message.flightEnabled == 1;
		if (!TragicConfig.allowFlight)
		{
			EntityPlayer player = TragicMC.proxy.getPlayerFromMessageCtx(ctx);
			if (player != null) player.addChatMessage(new ChatComponentText("Flight potion effect is disabled due to the server not allowing it. Change the option in your server.properties file if you want it enabled."));
		}
		return null;
	}

}
