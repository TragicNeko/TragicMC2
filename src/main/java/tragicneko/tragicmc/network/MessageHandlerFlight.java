package tragicneko.tragicmc.network;

import tragicneko.tragicmc.TragicConfig;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageHandlerFlight implements IMessageHandler<MessageFlight, IMessage>{

	@Override
	public IMessage onMessage(MessageFlight message, MessageContext ctx) {
		TragicConfig.allowFlight = message.flightEnabled == 1;
		return null;
	}

}
