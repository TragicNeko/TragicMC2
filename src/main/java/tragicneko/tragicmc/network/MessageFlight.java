package tragicneko.tragicmc.network;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class MessageFlight implements IMessage {

	public byte flightEnabled;

	public MessageFlight() {}

	public MessageFlight(boolean enabled)
	{
		this.flightEnabled = (byte) (enabled ? 1 : 0);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.flightEnabled = buf.readByte();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeByte(this.flightEnabled);
	}

}
