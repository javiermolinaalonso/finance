package com.phoboss.finance.strategy;

import com.phoboss.finance.entities.Value;
import com.phoboss.finance.enumerations.Actions;

public interface Strategy {

	public Actions evaluate(Value value);
	
}
