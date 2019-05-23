package github.chorman0773.enchantments.datapack.enchantment;

import java.time.Instant;

import github.chorman0773.gac14.enchantment.core.enchantment.Gac14Enchantment;
import net.minecraft.resources.IResource;
import net.minecraft.util.ResourceLocation;

public class CacheableDatapackEnchantmentObject {
	private Gac14Enchantment ench;
	private ResourceLocation name;
	
	private Instant lastUpdateTime;
	
	
	public CacheableDatapackEnchantmentObject(ResourceLocation loc) {
		// TODO Auto-generated constructor stub
	}
	
	public void reload(IResource res) {
		
	}

}
