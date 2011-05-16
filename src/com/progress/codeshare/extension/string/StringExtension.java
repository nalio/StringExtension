package com.progress.codeshare.extension.string;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.saxon.expr.XPathContext;

public final class StringExtension {

	private static final String DEFAULT_PADDING_NUMBER = "0";

	private static final String DEFAULT_PADDING_CHARACTER = " ";

	public Object trim(final XPathContext ctx, final String tValue) {
		Object result = null;
		result = (String) tValue.trim();
		return result;
	}

	public Object dateCurrent(final XPathContext ctx, String format) {
		Object result = null;
		Date data = new Date();
		Format formato = new SimpleDateFormat(format);
		result = formato.format(data);
		return result;
	}

	public Object paddingCharacter(final XPathContext ctx, String value,
			int length, String character) {
		return paddingCharacter(ctx, value, length, character,
				DEFAULT_PADDING_CHARACTER);
	}

	public Object paddingCharacter(final XPathContext ctx, String value,
			int length, String character, String valueDefault) {

		StringBuffer result = new StringBuffer();

		if (value == null) {
			value = valueDefault;
		}

		if (value.trim().length() == 0) {
			value = valueDefault;
		}

		result.append(value);
		for (int i = 0; i < length - value.length(); i++) {
			result.append(character);
		}

		return result.toString().substring(0, length);

	}

	public Object paddingNumber(final XPathContext ctx, String value, int length, int precision,
			String character) {
		return paddingNumber(ctx, value, length, precision, character,
				DEFAULT_PADDING_NUMBER);
	}

	public Object paddingNumber(final XPathContext ctx, String value, int length, int precision,
			String character, String valueDefault) {

		StringBuffer result = new StringBuffer();
		int operator = (int) Math.pow(10, precision);

		if (value == null) {
			value = valueDefault;
		}

		if (value.trim().length() == 0) {
			value = valueDefault;
		}

		double parseDouble = Math.floor(Double.parseDouble(value) * operator);

		value = String.valueOf(Math.round(parseDouble));

		for (int i = 0; i < length - value.length(); i++) {
			result.append(character);
		}

		result.append(value);

		int size = length - result.toString().length();
		if (size < 0 ) size = 0 ;
				
		return result.toString().substring(
				Math.abs(size), length);
	}

}