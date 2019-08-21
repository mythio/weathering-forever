package com.mythio.weather.ui

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.util.Log
import android.widget.RemoteViews
import com.mythio.weather.R
import com.mythio.weather.db.AppDatabase
import kotlinx.coroutines.*

/**
 * Implementation of App Widget functionality.
 */
class WeatherWidget : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    companion object {

        internal fun updateAppWidget(
            context: Context, appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {
            val db = AppDatabase.getInstance(context.applicationContext).weatherDao()
            val views = RemoteViews(context.packageName, R.layout.weather_widget)
            GlobalScope.launch {
                val weather = db.getCurrentWeatherMetricAsync()
                Log.d("TAG_TAG_TAG", "weather: " + weather.temperature);
                withContext(Dispatchers.Main) {
                    Log.d("TAG_TAG_TAG", "weather: " + weather.temperature);
                    views.setTextViewText(R.id.tv_counter, " asd " + weather.temperature)
                }
            }
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}
