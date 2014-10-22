package tragicneko.tragicmc.network;

import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.properties.PropertyDoom;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageHandlerDoom implements IMessageHandler<MessageDoom, IMessage> {

	@Override
	public MessageDoom onMessage(MessageDoom message, MessageContext ctx) {			
		PropertyDoom doom = PropertyDoom.get(TragicMC.proxy.getPlayerFromMessageCtx(ctx));
		if (doom != null) doom.loadNBTData(message.tag);
		return null;
	}

}