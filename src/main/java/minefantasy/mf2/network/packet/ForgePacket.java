package minefantasy.mf2.network.packet;

import io.netty.buffer.ByteBuf;
import minefantasy.mf2.block.tileentity.TileEntityForge;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.ByteBufUtils;

public class ForgePacket extends PacketMF
{
	public static final String packetName = "MF2_ForgePacket";
	private int[] coords = new int[3];
	private float[] fuels = new float[2];

	public ForgePacket(TileEntityForge tile)
	{
		coords = new int[]{tile.xCoord, tile.yCoord, tile.zCoord};
		fuels = new float[]{tile.fuel, tile.maxFuel};
		if(fuels[0] > fuels[1])
		{
			fuels[0] = fuels[1];
		}
	}

	public ForgePacket() {
	}

	@Override
	public void process(ByteBuf packet, EntityPlayer player) 
	{
        coords = new int[]{packet.readInt(), packet.readInt(), packet.readInt()};
        TileEntity entity = player.worldObj.getTileEntity(coords[0], coords[1], coords[2]);
        
        if(entity != null && entity instanceof TileEntityForge)
        {
	        fuels[0] = packet.readFloat();
	        fuels[1] = packet.readFloat();
	        
	        TileEntityForge tile = (TileEntityForge)entity;
	        tile.fuel = fuels[0];
	        tile.maxFuel = fuels[1];
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
		for(int a = 0; a < coords.length; a++)
		{
			packet.writeInt(coords[a]);
		}
		packet.writeFloat(fuels[0]);
		packet.writeFloat(fuels[1]);
	}
}
