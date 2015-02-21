package minefantasy.mf2.network.packet;

import io.netty.buffer.ByteBuf;
import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.knowledge.InformationBase;
import minefantasy.mf2.api.knowledge.InformationList;
import minefantasy.mf2.api.knowledge.ResearchLogic;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.ByteBufUtils;

public class ResearchRequest extends PacketMF
{
	public static final String packetName = "MF2_RequestResearch";
	private EntityPlayer user;
	private int researchID;
	private String username;

	public ResearchRequest(EntityPlayer user, int id)
	{
		this.researchID = id;
		this.username = user.getCommandSenderName();
		this.user = user;
	}

	public ResearchRequest() {
	}

	@Override
	public void process(ByteBuf packet, EntityPlayer player) 
	{
		researchID = packet.readInt();
		username = ByteBufUtils.readUTF8String(packet);
		
		if (username != null) 
        {
            EntityPlayer entity = player.worldObj .getPlayerEntityByName(username);
            InformationBase research = InformationList.knowledgeList.get(researchID);
            if(entity != null && research != null)
            {
            	int points = ResearchLogic.getKnowledgePoints(entity);
            	int cost = ResearchLogic.getCost(player, research);
            	if(!player.worldObj.isRemote && (MineFantasyII.isDebug() || points >= cost))
            	{
	            	if(research.trigger(player, true))
	            	{
	            		if(!MineFantasyII.isDebug())
	            		ResearchLogic.modifyKnowledgePoints(entity, -cost);
	            		ResearchLogic.syncData(entity);
	            	}
            	}
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
		packet.writeInt(researchID);
        ByteBufUtils.writeUTF8String(packet, username);
	}
}
