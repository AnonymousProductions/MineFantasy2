package minefantasy.mf2.api.knowledge.client;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class EntryPage
{
	public static final int universalBookImageWidth = 146;
	public static final int universalBookImageHeight = 180;
	
	public abstract void render(GuiScreen parent, int x, int y, float f, int posX, int posY);
	public abstract void preRender(GuiScreen parent, int x, int y, float f, int posX, int posY);

}
