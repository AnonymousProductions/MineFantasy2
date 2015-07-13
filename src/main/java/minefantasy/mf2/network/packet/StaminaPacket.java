package minefantasy.mf2.network.packet;

import io.netty.buffer.ByteBuf;
import minefantasy.mf2.api.stamina.StaminaBar;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.ByteBufUtils;

public class StaminaPacket extends PacketMF
{
	public static final String packetName = "MF2_Staminabar";
	private float[] value;
	private String username;

	public StaminaPacket(float[]value, EntityPlayer user)
	{
		this.value = value;
		this.username = user.getCommandSenderName();
	}

	public StaminaPacket() {
	}

	@Override
	public void process(ByteBuf packet, EntityPlayer player) 
	{
        value = new float[]{StaminaBar.getDefaultMax(player), StaminaBar.getDefaultMax(player), 0, 0F};
        value[0] = packet.readFloat();
        value[1] = packet.readFloat();
        value[2] = packet.readFloat();
        value[3] = packet.readFloat();
        username = ByteBufUtils.readUTF8String(packet);
        
        if (username != null) 
        {
            EntityPlayer entity = player.worldObj .getPlayerEntityByName(username);
            if(entity != null)
            {
	            StaminaBar.setStaminaValue(entity, value[0]);
	            StaminaBar.setMaxStamina(entity, value[1]);
	            StaminaBar.setFlashTime(entity, value[2]);
	            StaminaBar.setBonusStamina(entity, value[3]);
            }
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
		for(float variable : value)
		{
			packet.writeFloat(variable);
		}
        ByteBufUtils.writeUTF8String(packet, username);
	}
}
