package tragicneko.tragicmc.network;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class MessageGui implements IMessage {

	public int id;

	public MessageGui(){}

	public MessageGui(int id)
	{
		this.id = id;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		id = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(id);
	}
}
