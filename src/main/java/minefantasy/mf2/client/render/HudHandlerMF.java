package minefantasy.mf2.client.render;

import org.lwjgl.input.Mouse;

import minefantasy.mf2.item.archery.ItemBowMF;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class HudHandlerMF 
{
	private MineFantasyHUD inGameGUI = new MineFantasyHUD();
	
	@SubscribeEvent
	public void postRenderOverlay(RenderGameOverlayEvent.Post event) 
	{
		if (event.type == RenderGameOverlayEvent.ElementType.HOTBAR)
		{
			inGameGUI.renderGameOverlay(event.partialTicks, Mouse.getX(), Mouse.getY());
		}
	}
	
	@SubscribeEvent
    public void onBowFOV(FOVUpdateEvent event)
	{
        ItemStack stack = event.entity.getItemInUse();
        if (stack != null && stack.getItem() instanceof ItemBowMF)
        {
            int i = event.entity.getItemInUseDuration();
            float f1 = i / 20.0F;
            if (f1 > 1.0F)
            {
                f1 = 1.0F;
            }
            else{
                f1 *= f1;
            }
            event.newfov *= 1.0F - f1 * 0.15F;
        }
    }
}
