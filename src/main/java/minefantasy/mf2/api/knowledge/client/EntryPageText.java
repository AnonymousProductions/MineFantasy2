package minefantasy.mf2.api.knowledge.client;

import minefantasy.mf2.api.armour.ArmourDesign;
import minefantasy.mf2.api.armour.CustomArmourEntry;
import minefantasy.mf2.util.MFLogUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.Item;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

public class EntryPageText extends EntryPage
{
	private Minecraft mc = Minecraft.getMinecraft();
	private String paragraph;
	
	public EntryPageText(String paragraph)
	{
		this.paragraph = paragraph;
	}
	@Override
	public void render(GuiScreen parent, int x, int y, float f, int posX, int posY)
	{
		String local = StatCollector.translateToLocal(paragraph);
		String text = "";
		String temp = "";
		boolean prefix = false;
		for(int a = 0; a < local.length(); a++)
		{
			char c = local.charAt(a);
			if(a == local.length()-1)
			{
				text = text + temp + c;
				temp = "";
			}
			else if(prefix)
			{
				if(c == "h".charAt(0))
				{
					text = text + temp + EnumChatFormatting.DARK_BLUE + EnumChatFormatting.BOLD;
					temp = "";
				}
				else if(c == "d".charAt(0))
				{
					text = text + temp + EnumChatFormatting.DARK_RED;
					temp = "";
				}
				else if(c == "y".charAt(0))
				{
					text = text + temp + EnumChatFormatting.GOLD;
					temp = "";
				}
				else if(c == "u".charAt(0))
				{
					text = text + temp + EnumChatFormatting.UNDERLINE;
					temp = "";
				}
				else if(c == "r".charAt(0))
				{
					text = text + temp + EnumChatFormatting.RESET + EnumChatFormatting.BLACK;
					temp = "";
				}
				prefix = false;
			}
			else if(c == "^".charAt(0))
			{
				text = text + temp + "\n\n";
				temp = "";
			}
			else if(c == "$".charAt(0))
			{
				prefix = true;
			}
			else
			{
				temp = temp + c;
			}
		}
		mc.fontRenderer.drawSplitString(text, posX+14, posY+15, 117, 0);
	}
	@Override
	public void preRender(GuiScreen parent, int x, int y, float f, int posX, int posY)
	{
	}
}
