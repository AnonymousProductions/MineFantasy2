package minefantasy.mf2.network.packet;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import minefantasy.mf2.api.stamina.StaminaBar;
import minefantasy.mf2.mechanics.CombatMechanics;
import mods.battlegear2.api.core.IBattlePlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.WorldServer;

public class ParryPacket extends PacketMF
{
	public static final String packetName = "MF2_ParryPacket";
	private int value;
	private String username;

	public ParryPacket(int value, EntityPlayer user)
	{
		this.value = value;
		this.username = user.getCommandSenderName();
	}

	public ParryPacket() {
	}

	@Override
	public void process(ByteBuf packet, EntityPlayer player) 
	{
        value = packet.readInt();
        username = ByteBufUtils.readUTF8String(packet);
        
        if (username != null) 
        {
            EntityPlayer entity = player.worldObj .getPlayerEntityByName(username);
            if(entity != null)
            CombatMechanics.setParryCooldown(entity, value);
        }
	}

	@Override
	public String getChannel()
	{
		return packetName;
	}

	@Override
	public void write(ByteBuf packet) 
	{
		packet.writeInt(value);
        ByteBufUtils.writeUTF8String(packet, username);
	}
}
