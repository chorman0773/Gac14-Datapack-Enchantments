package github.chorman0773.enchantments.datapack.util;

import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;

public interface DoubleBiFunction<T, U> {
	public double applyAsDouble(T t,U u);
	
	public default DoubleBiFunction<T,U> andThen(DoubleUnaryOperator op){
		return (t,u)->op.applyAsDouble(DoubleBiFunction.this.applyAsDouble(t,u));
	}
	
	public static <T,U> DoubleBiFunction<T,U> join(DoubleBiFunction<? super T,? super U> f1,DoubleBiFunction<? super T,? super U> f2,DoubleBinaryOperator joinOp){
		return (t,u)->joinOp.applyAsDouble(f1.applyAsDouble(t, u), f2.applyAsDouble(t, u));
	}
}
