package edu.mum.coffee.domain;

import java.util.HashMap;
import java.util.Map;

public enum ProductType {
	BREAKFAST(1),LUNCH(3),DINNER(5);
	
	private static Map<Integer, ProductType> map = new HashMap<>();

    static {
        for (ProductType e : ProductType.values()) {
            map.put(e.getValue(), e);
        }
    }
	
	private final int value;
    
	private ProductType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    public static ProductType valueOf(int i) {
        return map.get(i);
    }
}
