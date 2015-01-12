package tragicneko.tragicmc.network;

import net.minecraft.entity.Entity;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class MessageAttack implements IMessage {

	public int entityID;

	public MessageAttack(){}

	public MessageAttack(Entity entity)
	{
		this.entityID = entity.getEntityId();
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.entityID = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.entityID);
	}

}
