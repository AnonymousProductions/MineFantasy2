package minefantasy.mf2.network.packet;

import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.Iterator;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.knowledge.InformationBase;
import minefantasy.mf2.api.knowledge.InformationList;
import minefantasy.mf2.api.knowledge.ResearchLogic;
import minefantasy.mf2.util.MFLogUtil;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.ByteBufUtils;

public class KnowledgePacket extends PacketMF
{
	public static final String packetName = "MF2_KnowledgeSync";
	private EntityPlayer user;
	private String username;

	public KnowledgePacket(EntityPlayer user)
	{
		this.username = user.getCommandSenderName();
		this.user = user;
	}

	public KnowledgePacket() {
	}

	@Override
	public void process(ByteBuf packet, EntityPlayer player) 
	{
		int lvl = packet.readInt();
		
		int size = InformationList.knowledgeList.size();
		ArrayList<InformationBase> completed = new ArrayList<InformationBase>();
		
		for(int a = 0; a < size; a++)
		{
			boolean unlocked = packet.readBoolean();
			
			if(unlocked)
			{
				InformationBase base = InformationList.knowledgeList.get(a);
				if(base != null)
				{
					completed.add(base);
				}
			}
		}
		int pts = packet.readInt();
		username = ByteBufUtils.readUTF8String(packet);
		if (username != null) 
        {
            EntityPlayer entity = player.worldObj .getPlayerEntityByName(username);
            if(entity != null)
            {
            	MFLogUtil.logDebug("KnowledgeSync Complete: " + completed.size() + " Unlocked");
            	Iterator researches = completed.iterator();
            	while(researches.hasNext())
            	{
            		InformationBase base = (InformationBase) researches.next();
            		ResearchLogic.tryUnlock(player, base);
            	}
            	entity.experienceLevel = lvl;
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
		packet.writeInt(user.experienceLevel);
		int size = InformationList.knowledgeList.size();
		
		for(int a = 0; a < size; a++)
		{
			InformationBase base = InformationList.knowledgeList.get(a);
			packet.writeBoolean(ResearchLogic.hasInfoUnlocked(user, base));
		}
		packet.writeInt(ResearchLogic.getKnowledgePoints(user));
        ByteBufUtils.writeUTF8String(packet, username);
	}
}
