package minefantasy.mf2.api.helpers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;

public class ClientTickHandler {

	public static int pageFlipTicks = 0;
	public static int ticksInGame = 0;
	public static float partialTicks = 0;

	@SubscribeEvent
	public void renderTickStart(RenderTickEvent event)
	{
		if(event.phase == Phase.START)
			partialTicks = event.renderTickTime;
	}

	@SubscribeEvent
	public void clientTickEnd(ClientTickEvent event) 
	{
		if(event.phase == Phase.END) 
		{
			GuiScreen gui = Minecraft.getMinecraft().currentScreen;
			if(gui == null || !gui.doesGuiPauseGame()) 
			{
				ticksInGame++;
			}
		}
	}

	public static void notifyPageChange() {
		if(pageFlipTicks == 0)
			pageFlipTicks = 5;
	}

}
