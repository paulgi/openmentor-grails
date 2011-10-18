package uk.org.openmentor.domain

import java.util.List;

import org.codehaus.groovy.grails.commons.ConfigurationHolder;

class Category {
	static List<String> getCategories() {
		return ConfigurationHolder.config.openmentor.categories as List<String>
	}

	static List<String> getBands() {
		return ConfigurationHolder.config.openmentor.bands as List<String>
	}
}