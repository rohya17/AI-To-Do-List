package com.rohya.TodoList.utils;

import java.time.LocalDate;

import org.springframework.util.StringUtils;

public class Utils {

	public static boolean isValidString(String name) {
		return StringUtils.hasLength(name) && !name.isBlank() ;
	}

	public static boolean isValidDate(LocalDate deadline) {
		return deadline.isAfter(LocalDate.now());
	}

}
