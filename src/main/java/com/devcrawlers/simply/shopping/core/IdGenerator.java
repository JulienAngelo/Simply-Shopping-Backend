package com.devcrawlers.simply.shopping.core;

import java.util.List;

public class IdGenerator {
	
	public static long generateRefNos(List<Long> arrayList) {

		long id;
		long next = arrayList.size();
		next++;
		id = next;
		if (arrayList.contains(id)) {
			next++;
			id = next;
		}
		return id;
	}

}
