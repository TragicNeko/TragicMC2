package tragicneko.tragicmc.network;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class MessageFlight implements IMessage {

	public int flightEnabled;
	
	public MessageFlight() {}
	
	public MessageFlight(boolean enabled)
	{
		this.flightEnabled = enabled ? 1 : 0;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.flightEnabled = ByteBufUtils.readVarInt(buf, 2);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeVarInt(buf, this.flightEnabled, 2);
	}

}
