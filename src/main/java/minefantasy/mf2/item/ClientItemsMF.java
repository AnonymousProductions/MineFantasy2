package minefantasy.mf2.item;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class ClientItemsMF
{

	public static boolean showSpecials(ItemStack item, EntityPlayer user, List list, boolean fullInfo)
	{
		if(GameSettings.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindSneak))
    	{
			return true;
    	}
    	else
    	{
    		String keyname = Keyboard.getKeyName(Minecraft.getMinecraft().gameSettings.keyBindSneak.getKeyCode());
    		list.add("Hold [" + EnumChatFormatting.AQUA + keyname.toUpperCase() + EnumChatFormatting.GRAY + "] for more information.");
    		return false;
    	}
	}

}
