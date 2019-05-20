package github.chorman0773.enchantments.datapack.util;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import github.chorman0773.gac14.enchantment.core.enchantment.Gac14Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;

public interface Conditions {
	public static BiPredicate<ItemStack,EntityLivingBase> conjunction(List<BiPredicate<ItemStack,EntityLivingBase>> chained){
		return chained.stream().reduce((t, u)->true,BiPredicate::and);
	}
	public static BiPredicate<ItemStack,EntityLivingBase> disjunction(List<BiPredicate<ItemStack,EntityLivingBase>> chained){
		return chained.stream().reduce((t, u)->false,BiPredicate::or);
	}
	
	public static BiPredicate<ItemStack,EntityLivingBase> randomChance(double threshold){
		return (t,u)->Math.random()<threshold;
	}
	
	public static BiPredicate<ItemStack,EntityLivingBase> firstTrigger(ResourceLocation targetEnch){
		return (t,e)->Gac14Enchantment.isFirstTrigger(targetEnch, e);
	}
	
	public static BiPredicate<ItemStack,EntityLivingBase> biomePredicate(Predicate<Biome> condition){
		return (t,e)->condition.test(e.getEntityWorld().getBiome(e.getPosition()));
	}
	
	public static BiPredicate<ItemStack,EntityLivingBase> entityType(Predicate<EntityType<?>> condition){
		return (t,e)->condition.test(e.getType());
	}
	
}
