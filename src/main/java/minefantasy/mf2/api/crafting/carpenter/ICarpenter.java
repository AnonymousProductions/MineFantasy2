package minefantasy.mf2.api.crafting.carpenter;

/**
 * 
 * @author AnonymousProductions
 * This interface is used by the anvil in MineFantasy so 
 * it can be referred to without importing the mod
 * 
 */
public interface ICarpenter {
	void setForgeTime(int i);
	void setToolTier(int i);
	void setRequiredCarpenter(int i);
	void setHotOutput(boolean i);
	void setToolType(String toolType);
	void setCraftingSound(String trade);
}
