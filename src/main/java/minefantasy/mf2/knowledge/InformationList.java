package minefantasy.mf2.knowledge;

import java.util.ArrayList;
import java.util.List;

import minefantasy.mf2.item.list.ComponentListMF;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.JsonSerializableSet;

public class InformationList
{
	public static InformationPage smithing = new InformationPage("infoPage.smithing").registerInfoPage();
	
    /** Is the smallest column used to display a achievement on the GUI. */
    public static int minDisplayColumn;
    /** Is the smallest row used to display a achievement on the GUI. */
    public static int minDisplayRow;
    /** Is the biggest column used to display a achievement on the GUI. */
    public static int maxDisplayColumn;
    /** Is the biggest row used to display a achievement on the GUI. */
    public static int maxDisplayRow;
    /** Holds a list of all registered achievements. */
    public static List achievementList = new ArrayList();
    /** Is the 'open inventory' achievement. */
    public static InformationBase gettingStarted = (new InformationBase("knowledge.gettingStarted", "gettingStarted", 	0, 0, Items.book, (InformationBase)null)).initIndependentStat().registerStat().setUnlocked();
    public static InformationBase crucible = (new InformationBase("knowledge.crucible", "crucible", 					2, 2, Blocks.furnace, gettingStarted)).registerStat().setPage(smithing).setUnlocked().setSpecial();
    
    public static InformationBase smeltCopper = (new InformationBase("knowledge.smeltCopper", "smeltCopper",  			0, 0, ComponentListMF.ingots[0], (InformationBase)null)).registerStat().setPage(smithing).setUnlocked();
    public static InformationBase smeltIron = (new InformationBase("knowledge.smeltIron", "smeltIron",  				2, 1, Items.iron_ingot, (InformationBase)null)).registerStat().setPage(smithing).setUnlocked();
    public static InformationBase smeltBronze = (new InformationBase("knowledge.smeltBronze", "smeltBronze",  			0, 2, ComponentListMF.ingots[2], crucible)).registerStat().setPage(smithing);
    
    public static InformationBase blastfurn = (new InformationBase("knowledge.blastfurn", "blastfurn",  				3, 4, Blocks.furnace, crucible)).registerStat().setPage(smithing).setSpecial();
    public static InformationBase smeltSteel = (new InformationBase("knowledge.smeltSteel", "smeltSteel",  				5, 4, ComponentListMF.ingots[4], blastfurn)).registerStat().setPage(smithing);
    public static InformationBase encrusted = (new InformationBase("knowledge.encrusted", "encrusted",  				6, 3, ComponentListMF.diamond_shards, smeltSteel)).registerStat().setPage(smithing);
    
    public static InformationBase crucible2 = (new InformationBase("knowledge.crucible2", "crucible2",  					6, 6, Blocks.furnace, smeltSteel)).registerStat().setPage(smithing).setSpecial();
    public static InformationBase smeltBlackSteel = (new InformationBase("knowledge.smeltBlackSteel", "smeltBlackSteel",	5, 7, ComponentListMF.ingots[7], crucible2)).registerStat().setPage(smithing);
    public static InformationBase smeltDragonforge = (new InformationBase("knowledge.smeltDragonforge", "smeltDragonforge",	4, 8, ComponentListMF.ingots[8], smeltBlackSteel)).registerStat().setPage(smithing);
    
    public static InformationBase crucible3 = (new InformationBase("knowledge.crucible3", "crucible3",  					8, 6, Blocks.furnace, crucible2)).registerStat().setPage(smithing).setSpecial();
    public static InformationBase smeltRedSteel = (new InformationBase("knowledge.smeltRedSteel", "smeltRedSteel", 			7, 5, ComponentListMF.ingots[12], crucible3)).registerStat().setPage(smithing);
    public static InformationBase smeltBlueSteel = (new InformationBase("knowledge.smeltBlueSteel", "smeltBlueSteel", 		9, 5, ComponentListMF.ingots[14], crucible3)).registerStat().setPage(smithing);
    
    public static InformationBase mythic = (new InformationBase("knowledge.mythic", "mythic",  								10, 9, Blocks.furnace, crucible3)).registerStat().setPage(smithing).setSpecial();
    public static InformationBase smeltMithril = (new InformationBase("knowledge.smeltMithril", "smeltMithril", 			12, 8,  ComponentListMF.ingots[16], mythic)).registerStat().setPage(smithing);
    public static InformationBase smeltAdamant = (new InformationBase("knowledge.smeltAdamant", "smeltAdamant", 			12, 10, ComponentListMF.ingots[15], mythic)).registerStat().setPage(smithing);
    
    /**
     * A stub functions called to make the static initializer for this class run.
     */
    public static void init() {}
}