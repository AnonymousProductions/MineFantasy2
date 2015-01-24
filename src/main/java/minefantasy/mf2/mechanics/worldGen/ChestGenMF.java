package minefantasy.mf2.mechanics.worldGen;

import minefantasy.mf2.item.list.ToolListMF;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

public class ChestGenMF
{
	public void init()
	{
		addLoot(ChestGenHooks.STRONGHOLD_CORRIDOR, ToolListMF.bandage_tough, 1, 5, 5);
		
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH, ToolListMF.swords[2], 1, 1, 5);
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH, ToolListMF.waraxes[2], 1, 1, 5);
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH, ToolListMF.maces[2], 1, 1, 5);
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH, ToolListMF.daggers[2], 1, 1, 5);
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH, ToolListMF.greatswords[2], 1, 1, 2);
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH, ToolListMF.battleaxes[2], 1, 1, 2);
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH, ToolListMF.warhammers[2], 1, 1, 2);
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH, ToolListMF.katanas[2], 1, 1, 2);
		
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH, ToolListMF.picks[2], 1, 1, 7);
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH, ToolListMF.axes[2], 1, 1, 7);
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH, ToolListMF.spades[2], 1, 1, 7);
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH, ToolListMF.hoes[2], 1, 1, 7);
	}
	private static void addLoot(String type, Item item, int min, int max, int chance)
	{
		addLoot(type, item, 1, min, max, chance);
	}
	private static void addLoot(String type, Item item, int stack, int min, int max, int chance)
	{
		ChestGenHooks.addItem(type, new WeightedRandomChestContent(item, stack, min, max, chance));
	}
	private static void addLoot(String type, ItemStack item, int min, int max, int chance)
	{
		ChestGenHooks.addItem(type, new WeightedRandomChestContent(item, min, max, chance));
	}
}
