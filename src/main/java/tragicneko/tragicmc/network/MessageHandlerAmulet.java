package tragicneko.tragicmc.network;

import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.properties.PropertyAmulets;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageHandlerAmulet implements IMessageHandler<MessageAmulet, IMessage> {

	@Override
	public MessageAmulet onMessage(MessageAmulet message, MessageContext ctx) {
		PropertyAmulets amulets = PropertyAmulets.get(TragicMC.proxy.getPlayerFromMessageCtx(ctx));
		if (amulets != null)
		{
			amulets.loadNBTData(message.tag);
		}
		return null;
	}

}