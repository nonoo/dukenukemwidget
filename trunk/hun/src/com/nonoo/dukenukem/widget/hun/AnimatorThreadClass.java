package com.nonoo.dukenukem.widget.hun;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.appwidget.AppWidgetManager;
import android.widget.RemoteViews;

public class AnimatorThreadClass extends Thread {

	public List<Integer> appWidgetIds;
	protected long startTime = System.currentTimeMillis();
	
	public void init( int appWidgetId ) {
		this.appWidgetIds = new ArrayList<Integer>();
		this.appWidgetIds.add( appWidgetId );
	}

	public void updateAppWidgets( RemoteViews views, AppWidgetManager appWidgetManager ) {
		for( Iterator<Integer> it = appWidgetIds.iterator(); it.hasNext(); ) {
			int appWidgetId = it.next();
			appWidgetManager.updateAppWidget( appWidgetId, views );
		}
	}

}
