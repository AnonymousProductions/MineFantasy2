package minefantasy.mf2.network.packet;

import io.netty.buffer.ByteBuf;
import minefantasy.mf2.block.tileentity.TileEntityBombBench;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.ByteBufUtils;

public class BombBenchPacket extends PacketMF
{
	public static final String packetName = "MF2_BombBenchPkt";
	private int[] coords = new int[3];
	private int progress;
	private boolean hasRecipe;
	
	public BombBenchPacket(TileEntityBombBench tile)
	{
		coords = new int[]{tile.xCoord, tile.yCoord, tile.zCoord};
		progress = (int)tile.progress;
		hasRecipe = tile.hasRecipe;
	}

	public BombBenchPacket() {
	}

	@Override
	public void process(ByteBuf packet, EntityPlayer player) 
	{
        coords = new int[]{packet.readInt(), packet.readInt(), packet.readInt()};
        TileEntity entity = player.worldObj.getTileEntity(coords[0], coords[1], coords[2]);
        int newProgress = packet.readInt();
        boolean newRecipe = packet.readBoolean();
        
        if(entity != null && entity instanceof TileEntityBombBench)
        {
	        TileEntityBombBench tile = (TileEntityBombBench)entity;
	        tile.progress = newProgress;
	        tile.hasRecipe = newRecipe;
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
		packet.writeInt(progress);
		packet.writeBoolean(hasRecipe);
	}
}
