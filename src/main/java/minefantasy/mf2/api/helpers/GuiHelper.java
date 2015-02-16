package minefantasy.mf2.api.helpers;

import minefantasy.mf2.api.helpers.TextureHelperMF;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class GuiHelper
{
	private static final Minecraft mc = Minecraft.getMinecraft();
	public static void renderToolIcon(Gui screen, String toolType, int tier, int x, int y)
	{
		renderToolIcon(screen, toolType, tier, x, y, false);
	}
	public static void renderToolIcon(Gui screen, String toolType, int tier, int x, int y, boolean outline)
	{
		mc.getTextureManager().bindTexture(TextureHelperMF.getResource("textures/gui/icons.png"));
        int[] icon = getToolTypeIcon(toolType);
        screen.drawTexturedModalRect(x, y, outline ? 20 : 0, 0, 20, 20);
        screen.drawTexturedModalRect(x, y, icon[0], icon[1]+20, 20, 20);
        if(tier > -1)
        mc.fontRenderer.drawStringWithShadow(""+tier, x + 4, y + 10, 16777215);
	}
	public static int[] getToolTypeIcon(String s)
	{
		int width = 20;
		int height = 20;
		if(s.equalsIgnoreCase("hands"))
		{
			return new int[]{0, 0};
		}
		if(s.equalsIgnoreCase("knife"))
		{
			return new int[]{width*1, height*0};
		}
		if(s.equalsIgnoreCase("saw"))
		{
			return new int[]{width*2, height*0};
		}
		if(s.equalsIgnoreCase("mallet"))
		{
			return new int[]{width*3, height*0};
		}
		if(s.equalsIgnoreCase("needle"))
		{
			return new int[]{width*4, height*0};
		}
		if(s.equalsIgnoreCase("hammer"))
		{
			return new int[]{width*5, height*0};
		}
		if(s.equalsIgnoreCase("hvyHammer"))
		{
			return new int[]{width*6, height*0};
		}
		if(s.equalsIgnoreCase("spoon"))
		{
			return new int[]{width*7, height*0};
		}
		if(s.equalsIgnoreCase("shears"))
		{
			return new int[]{width*8, height*0};
		}
		
		if(s.equalsIgnoreCase("anvil"))
		{
			return new int[]{0, height*2};
		}
		if(s.equalsIgnoreCase("carpenter"))
		{
			return new int[]{width*1, height*2};
		}
		return new int[]{0, 0};
	}

}
