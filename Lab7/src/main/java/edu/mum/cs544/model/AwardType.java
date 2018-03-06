package edu.mum.cs544.model;

import java.util.HashMap;
import java.util.Map;

public enum AwardType {
	MEDAL(1), TROPHY(2), NONE(0);
	
	private static Map<Integer, AwardType> map = new HashMap<>();

    static {
        for (AwardType e : AwardType.values()) {
            map.put(e.getValue(), e);
        }
    }
	
	private final int value;
    
	private AwardType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    public static AwardType valueOf(int i) {
        return map.get(i);
    }
}
