package minefantasy.mf2.api.crafting.anvil;

/**
 * 
 * @author AnonymousProductions
 * This interface is used by the anvil in MineFantasy so 
 * it can be referred to without importing the mod
 * 
 */
public interface IAnvil {
	void setForgeTime(int i);
	void setHammerUsed(int i);
	void setRequiredAnvil(int i);
	void setHotOutput(boolean i);
	void setToolType(String toolType);
}
