package minefantasy.mf2.api.knowledge.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;

public class EntryPageText extends EntryPage
{
	private Minecraft mc = Minecraft.getMinecraft();
	private String[] paragraphs;
	
	public EntryPageText(String... paragraphs)
	{
		this.paragraphs = paragraphs;
	}
	@Override
	public void render(GuiScreen parent, int x, int y, float f, int posX, int posY)
	{
		String text = "";
		for(String s: paragraphs)
		{
			text += StatCollector.translateToLocal(s);
			text += "\n\n";
		}
		mc.fontRenderer.drawSplitString(text, posX+14, posY+15, 117, 0);
	}
	@Override
	public void preRender(GuiScreen parent, int x, int y, float f, int posX, int posY)
	{
	}

}
