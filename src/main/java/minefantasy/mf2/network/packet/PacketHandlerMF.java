package minefantasy.mf2.network.packet;

import java.util.Hashtable;
import java.util.Map;

import minefantasy.mf2.MineFantasyII;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;

public class PacketHandlerMF
{
    public Map<String, PacketMF> packetList = new Hashtable<String, PacketMF>();
    public Map<String, FMLEventChannel> channels = new Hashtable<String, FMLEventChannel>();

    public PacketHandlerMF() 
    {
        packetList.put(StaminaPacket.packetName, new StaminaPacket());
        packetList.put(ParryPacket.packetName, new ParryPacket());
        packetList.put(HitSoundPacket.packetName, new HitSoundPacket());
        packetList.put(AnvilPacket.packetName, new AnvilPacket());
        packetList.put(CarpenterPacket.packetName, new CarpenterPacket());
        packetList.put(KnowledgePacket.packetName, new KnowledgePacket());
        packetList.put(ResearchRequest.packetName, new ResearchRequest());
    }

    @SubscribeEvent
    public void onServerPacket(FMLNetworkEvent.ServerCustomPacketEvent event) 
    {
        packetList.get(event.packet.channel()).process(event.packet.payload(), ((NetHandlerPlayServer)event.handler).playerEntity);
    }

    @SubscribeEvent
    public void onClientPacket(FMLNetworkEvent.ClientCustomPacketEvent event)
    {
        packetList.get(event.packet.channel()).process(event.packet.payload(), MineFantasyII.proxy.getClientPlayer());
    }

    public void sendPacketToPlayer(FMLProxyPacket packet, EntityPlayerMP player)
    {
        channels.get(packet.channel()).sendTo(packet, player);
    }

    public void sendPacketToServer(FMLProxyPacket packet)
    {
        channels.get(packet.channel()).sendToServer(packet);
    }

    public void sendPacketAround(Entity entity, double range, FMLProxyPacket packet)
    {
        channels.get(packet.channel()).sendToAllAround(packet, new NetworkRegistry.TargetPoint(entity.dimension, entity.posX, entity.posY, entity.posZ, range));
    }

    public void sendPacketToAll(FMLProxyPacket packet)
    {
        channels.get(packet.channel()).sendToAll(packet);
    }
}
