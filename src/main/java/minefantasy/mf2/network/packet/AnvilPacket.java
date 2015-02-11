package minefantasy.mf2.network.packet;

import io.netty.buffer.ByteBuf;
import minefantasy.mf2.block.tileentity.TileEntityAnvilMF;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.ByteBufUtils;

public class AnvilPacket extends PacketMF
{
	public static final String packetName = "MF2_AnvilPacket";
	private int[] coords = new int[3];
	private String resultName;
	private String toolNeeded;
	private String research;
	private float[] progress = new float[2];
	private int[] tiers = new int[2];

	public AnvilPacket(TileEntityAnvilMF tile)
	{
		coords = new int[]{tile.xCoord, tile.yCoord, tile.zCoord};
		resultName = tile.getResultName();
		toolNeeded = tile.getToolNeeded();
		research = tile.getResearchNeeded();
		progress = new float[]{tile.progress, tile.progressMax};
		tiers = new int[]{tile.getToolTierNeeded(), tile.getAnvilTierNeeded()};
		if(progress[1] <= 0)
		{
			progress[1] = 0;
		}
	}

	public AnvilPacket() {
	}

	@Override
	public void process(ByteBuf packet, EntityPlayer player) 
	{
        coords = new int[]{packet.readInt(), packet.readInt(), packet.readInt()};
        TileEntity entity = player.worldObj.getTileEntity(coords[0], coords[1], coords[2]);
        
        if(entity != null && entity instanceof TileEntityAnvilMF)
        {
	        progress[0] = packet.readFloat();
	        progress[1] = packet.readFloat();
	        tiers[0] = packet.readInt();
	        tiers[1] = packet.readInt();
	        resultName = ByteBufUtils.readUTF8String(packet);
	        toolNeeded = ByteBufUtils.readUTF8String(packet);
	        research = ByteBufUtils.readUTF8String(packet);
	        
	        TileEntityAnvilMF anvil = (TileEntityAnvilMF)entity;
	        anvil.resName = resultName;
	        anvil.setToolType(toolNeeded);
	        anvil.progress = progress[0];
	        anvil.progressMax = progress[1];
	        anvil.setHammerUsed(tiers[0]);
	        anvil.setRequiredAnvil(tiers[1]);
	        anvil.setResearch(research);
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
		packet.writeFloat(progress[0]);
		packet.writeFloat(progress[1]);
		packet.writeInt(tiers[0]);
		packet.writeInt(tiers[1]);
		ByteBufUtils.writeUTF8String(packet, resultName);
		ByteBufUtils.writeUTF8String(packet, toolNeeded);
		ByteBufUtils.writeUTF8String(packet, research);
	}
}
