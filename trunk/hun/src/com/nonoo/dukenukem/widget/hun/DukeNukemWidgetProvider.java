package com.nonoo.dukenukem.widget.hun;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

public class DukeNukemWidgetProvider extends AppWidgetProvider {
	@Override
	public void onDisabled(Context context) {
		android.os.Process.killProcess( android.os.Process.myPid() );
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.main);

		for (int appWidgetId : appWidgetIds) {
			PendingIntent pendingIntent = makePendingIntent(context, appWidgetId);
			remoteViews.setOnClickPendingIntent(R.id.button, pendingIntent);
			AppWidgetManager.getInstance(context).updateAppWidget( appWidgetId, remoteViews );
		}
	}

    private PendingIntent makePendingIntent( Context context, int appWidgetId ) {
    	Intent widgetUpdate = new Intent();
    	widgetUpdate.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
    	widgetUpdate.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, new int[] { appWidgetId });

        // make this pending intent unique by adding a scheme to it
        widgetUpdate.setData(Uri.withAppendedPath(Uri.parse("DukeNukemWidgetHUNScheme://widget/id/"), String.valueOf(appWidgetId)));
        return PendingIntent.getBroadcast(context, 0, widgetUpdate, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}