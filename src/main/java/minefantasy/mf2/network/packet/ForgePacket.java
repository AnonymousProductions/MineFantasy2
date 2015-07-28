package minefantasy.mf2.network.packet;

import io.netty.buffer.ByteBuf;
import minefantasy.mf2.block.tileentity.TileEntityForge;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;

public class ForgePacket extends PacketMF
{
	public static final String packetName = "MF2_ForgePacket";
	private int[] coords = new int[3];
	private float[] fuels = new float[2];
	private float[] temps = new float[2];

	public ForgePacket(TileEntityForge tile)
	{
		coords = new int[]{tile.getPos().getX(), tile.getPos().getY(), tile.getPos().getZ()};
		fuels = new float[]{tile.fuel, tile.maxFuel};
		if(fuels[0] > fuels[1])
		{
			fuels[0] = fuels[1];
		}
		temps = new float[]{tile.temperature, tile.fuelTemperature};
	}

	public ForgePacket() {
	}

	@Override
	public void process(ByteBuf packet, EntityPlayer player) 
	{
        coords = new int[]{packet.readInt(), packet.readInt(), packet.readInt()};
        TileEntity entity = player.worldObj.getTileEntity(new BlockPos(coords[0], coords[1], coords[2]));
        
        if(entity != null && entity instanceof TileEntityForge)
        {
	        fuels[0] = packet.readFloat();
	        fuels[1] = packet.readFloat();
	        temps[0] = packet.readFloat();
	        temps[1] = packet.readFloat();
	        
	        TileEntityForge tile = (TileEntityForge)entity;
	        tile.fuel = fuels[0];
	        tile.maxFuel = fuels[1];
	        tile.temperature = temps[0];
	        tile.fuelTemperature = temps[1];
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
		packet.writeFloat(temps[0]);
		packet.writeFloat(temps[1]);
	}
}
