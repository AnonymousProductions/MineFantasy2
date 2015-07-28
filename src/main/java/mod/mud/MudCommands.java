package mod.mud;

import java.util.List;

import mod.mud.gui.GuiChangelogDownload;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;

public class MudCommands extends CommandBase{

    @Override
    public String getName() {
        return "mud";
    }
    
    @Override
    public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr,BlockPos pos) {
        return getListOfStringsMatchingLastWord(par2ArrayOfStr, getName());
    }
    
    @Override
    public boolean canCommandSenderUse(ICommandSender par1ICommandSender){
        return true;
    }

    @Override
    public String getCommandUsage(ICommandSender icommandsender) {
        return null;
    }

    @Override
    public void execute(ICommandSender icommandsender, String[] astring) {
        Minecraft.getMinecraft().displayGuiScreen(new GuiChangelogDownload(Minecraft.getMinecraft().currentScreen));
    }

    @Override
    public int compareTo(Object o) {
        return this.compareTo((ICommand)o);
    }
}
