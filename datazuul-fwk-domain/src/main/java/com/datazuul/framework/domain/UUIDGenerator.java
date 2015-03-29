package com.datazuul.framework.domain;

import java.util.UUID;

public class UUIDGenerator {
	public static String createId() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
}
