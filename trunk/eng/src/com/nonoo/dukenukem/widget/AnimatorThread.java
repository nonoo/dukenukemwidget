package com.nonoo.dukenukem.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.widget.RemoteViews;

public class AnimatorThread extends Thread {
	private final int ANIMATIONWAIT = 100;
	
	private int appWidgetId = 0;
	private Context context;
	private AppWidgetManager appWidgetManager;

	AnimatorThread(Context context, int appWidgetId) {
		this.context = context;
		this.appWidgetId = appWidgetId;
		appWidgetManager = AppWidgetManager.getInstance(context);
	}

	@Override
	public void run() {
		while( true ) {
			// this is needed here: http://groups.google.com/group/android-developers/browse_thread/thread/a90e12579c0beee3?pli=1
			RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.main);

			try {
				sleep( ANIMATIONWAIT );
			} catch (Exception e) {
				break;
			}

			remoteViews.setImageViewResource(R.id.button, R.drawable.a002);
			appWidgetManager.updateAppWidget( appWidgetId, remoteViews );

			try {
				sleep( ANIMATIONWAIT );
			} catch (Exception e) {
				break;
			}

			remoteViews.setImageViewResource(R.id.button, R.drawable.a003);
			appWidgetManager.updateAppWidget( appWidgetId, remoteViews );

			try {
				sleep( ANIMATIONWAIT );
			} catch (Exception e) {
				break;
			}

			remoteViews.setImageViewResource(R.id.button, R.drawable.a004);
			AppWidgetManager.getInstance(context).updateAppWidget( appWidgetId, remoteViews );
		}
	}

}
