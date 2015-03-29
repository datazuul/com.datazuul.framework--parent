package com.datazuul.framework.webapp.wicket.markup.html.navigation.paging;

import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.html.navigation.paging.IPagingLabelProvider;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;

public class DatazuulPagingNavigator extends PagingNavigator {
	private static final long serialVersionUID = 1L;

	public DatazuulPagingNavigator(final String id, final IPageable pageable) {
		super(id, pageable);
	}

	public DatazuulPagingNavigator(final String id, final IPageable pageable, final IPagingLabelProvider labelProvider) {
		super(id, pageable, labelProvider);
	}
}
