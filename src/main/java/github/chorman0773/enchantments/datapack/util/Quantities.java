package github.chorman0773.enchantments.datapack.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import github.chorman0773.gac14.enchantment.core.enchantment.Gac14Enchantment;
import net.minecraft.command.arguments.EntitySelector;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.util.ResourceLocation;

public interface Quantities {
	public static DoubleBiFunction<ItemStack,EntityLivingBase> constant(double val){
		return (t,u)->val;
	}
	
	public static DoubleBiFunction<ItemStack,EntityLivingBase> level(ResourceLocation name){
		return (i,u)->Gac14Enchantment.getEnchantmentLevel(i, name.toString());
	}
	
	public static DoubleBiFunction<ItemStack,EntityLivingBase> multiply(DoubleBiFunction<ItemStack,EntityLivingBase> f1,DoubleBiFunction<ItemStack,EntityLivingBase> f2){
		return DoubleBiFunction.join(f1, f2, (a,b)->a*b);
	}
 
	public static DoubleBiFunction<ItemStack,EntityLivingBase> multiply(List<DoubleBiFunction<ItemStack,EntityLivingBase>> functions){
		return functions.parallelStream().reduce((t, u)->1.0,Quantities::multiply, Quantities::multiply);
	}
	
	public static DoubleBiFunction<ItemStack,EntityLivingBase> add(DoubleBiFunction<ItemStack,EntityLivingBase> f1,DoubleBiFunction<ItemStack,EntityLivingBase> f2){
		return DoubleBiFunction.join(f1, f2, (a,b)->a+b);
	}
 
	public static DoubleBiFunction<ItemStack,EntityLivingBase> add(List<DoubleBiFunction<ItemStack,EntityLivingBase>> functions){
		return functions.parallelStream().reduce((t, u)->1.0,Quantities::add, Quantities::add);
	}
	
	public static DoubleBiFunction<ItemStack,EntityLivingBase> negate(DoubleBiFunction<ItemStack,EntityLivingBase> input){
		return input.andThen(d->-d);
	}
	
	public static DoubleBiFunction<ItemStack,EntityLivingBase> divide(DoubleBiFunction<ItemStack,EntityLivingBase> dvs,DoubleBiFunction<ItemStack,EntityLivingBase> dvd){
		return DoubleBiFunction.join(dvd, dvs, (a,b)->a/b);
	}
	
	public static DoubleBiFunction<ItemStack,EntityLivingBase> random(){
		return (t,u)->Math.random();
	}
	public static DoubleBiFunction<ItemStack,EntityLivingBase> random(double min,double max){
		assert min<max;
		double rMax = max-min;
		return random().andThen(d->rMax*d+min);
	}
	
	public static DoubleBiFunction<ItemStack,EntityLivingBase> modified(ResourceLocation which,double base,double modifier){
		return level(which).andThen(d->modifier*d+base);
	}
	
	public static DoubleBiFunction<ItemStack,EntityLivingBase> parse(ResourceLocation targetEnch,JsonElement e){
		if(e.isJsonPrimitive())
			return constant(e.getAsDouble());
		else {
			JsonObject o = e.getAsJsonObject();
			if(o.has("function"))
				switch(o.get("function").getAsString()) {
				case "constant":
					return constant(o.get("value").getAsDouble());
				case "level":
					return level(targetEnch);
				case "modified":
					return modified(targetEnch,o.get("base").getAsDouble(),o.get("modifier").getAsDouble());
				case "random":
					if(o.has("min")||o.has("max"))
						return random(o.get("min").getAsDouble(),o.get("max").getAsDouble());
					else
						return random();
				case "add":
				{
					JsonArray operands = o.getAsJsonArray("operands");
					List<DoubleBiFunction<ItemStack,EntityLivingBase>> ops = new ArrayList<>();
					for(JsonElement e1:operands)
						ops.add(parse(targetEnch,e1));
					return add(ops);
				}
				case "multiply":
				{
					JsonArray operands = o.getAsJsonArray("operands");
					List<DoubleBiFunction<ItemStack,EntityLivingBase>> ops = new ArrayList<>();
					for(JsonElement e1:operands)
						ops.add(parse(targetEnch,e1));
					return multiply(ops);
				}
				case "negate":
					return negate(parse(targetEnch,o.get("operand")));
				case "divide":
					return divide(parse(targetEnch,o.get("dividend")),parse(targetEnch,o.get("divisor")));
				default:
					throw new IllegalArgumentException("I don't know what this is");
				}
			return modified(targetEnch,o.get("base").getAsDouble(),o.get("modifier").getAsDouble());
		}
	}
	
}
