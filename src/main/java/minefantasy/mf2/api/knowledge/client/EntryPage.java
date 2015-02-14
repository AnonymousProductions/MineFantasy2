package minefantasy.mf2.api.knowledge.client;

import net.minecraft.client.gui.GuiScreen;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class EntryPage
{
	public abstract void render(GuiScreen parent, int x, int y, float f, int posX, int posY);
	public abstract void preRender(GuiScreen parent, int x, int y, float f, int posX, int posY);

}
