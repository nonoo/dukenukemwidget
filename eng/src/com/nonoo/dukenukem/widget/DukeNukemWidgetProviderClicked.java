package com.nonoo.dukenukem.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;

public class DukeNukemWidgetProviderClicked extends DukeNukemWidgetProvider {

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds);

		for (int appWidgetId : appWidgetIds) {
			Intent i = new Intent(context, DukeNukemWidgetService.class);
			i.putExtra("appWidgetId", appWidgetId);
			context.startService(i);
		}
	}

}
