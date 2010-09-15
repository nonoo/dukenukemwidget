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
		android.os.Process.killProcess(android.os.Process.myPid());
		super.onDisabled(context);
	}
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // Perform this loop procedure for each App Widget that belongs to this provider
        for (int i=0; i < appWidgetIds.length; i++) {
            int appWidgetId = appWidgetIds[i];

            // Create an Intent to launch activity
            Intent intent = new Intent(context, DukeNukemWidgetService.class);
            intent.putExtra( AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId );
            Uri data = Uri.withAppendedPath( Uri.parse("countdownwidget://widget/id/#update" + appWidgetId), String.valueOf( appWidgetId ) );
            intent.setData(data);
            intent.setAction( "update" );
            PendingIntent pendingIntent = PendingIntent.getService( context, 0, intent, 0 );

            // Get the layout for the App Widget and attach an on-click listener to the button
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.main);
            views.setOnClickPendingIntent( R.id.button, pendingIntent );

            // Tell the AppWidgetManager to perform an update on the current App Widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }

		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}

}