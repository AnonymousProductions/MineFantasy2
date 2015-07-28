package minefantasy.mf2.network.packet;

import io.netty.buffer.ByteBuf;
import minefantasy.mf2.block.tileentity.TileEntityChimney;
import minefantasy.mf2.util.MFLogUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;

public class ChimneyPacket extends PacketMF
{
	public static final String packetName = "MF2_ChimneyPacket";
	private int[] coords = new int[3];
	private int block, meta;

	public ChimneyPacket(TileEntityChimney tile)
	{
		coords = new int[]{tile.getPos().getX(), tile.getPos().getY(), tile.getPos().getZ()};
		block = Block.getIdFromBlock(tile.maskBlock);
		meta = tile.getBlockMetadata();
	}

	public ChimneyPacket() {
	}

	@Override
	public void process(ByteBuf packet, EntityPlayer player) 
	{
        coords = new int[]{packet.readInt(), packet.readInt(), packet.readInt()};
        TileEntity entity = player.worldObj.getTileEntity(new BlockPos(coords[0], coords[1], coords[2]));
        
        if(entity != null && entity instanceof TileEntityChimney)
        {
        	TileEntityChimney tile = (TileEntityChimney)entity;
        	
        	int blockID = packet.readInt();
        	int blockMetadata = packet.readInt();
        	if(Block.getIdFromBlock(tile.maskBlock) != blockID || tile.maskMeta != blockMetadata)
        	{
        		MFLogUtil.logDebug("Chimney Texture Change Found");
        	}
    		tile.setBlock(blockID, blockMetadata);
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
		packet.writeInt(block);
		packet.writeInt(meta);
	}
}
