package com.mythio.weather.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.mythio.weather.R
import com.mythio.weather.db.AppDatabase
import com.mythio.weather.repository.WeatherRepository
import com.mythio.weather.utils.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherWidget : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(
                context,
                appWidgetManager,
                appWidgetId
            )
        }
    }

    override fun onEnabled(context: Context) {}

    override fun onDisabled(context: Context) {}

    companion object {

        internal fun updateAppWidget(
            context: Context, appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {
            val sharedPref =
                context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

            val location = sharedPref.getString(SHARED_PREF_KEY_LOCATION, DEFAULT_LOCATION)!!
            val unit = sharedPref.getInt(SHARED_PREF_KEY_UNIT, DEFAULT_UNIT)!!

            val db = AppDatabase.getInstance(context.applicationContext).weatherDao()
            val repository = WeatherRepository(db)
            val views = RemoteViews(context.packageName, R.layout.widget_weather)

            CoroutineScope(Dispatchers.IO).launch {
                repository.getWeather(location)

                val weather = when (unit) {
                    UNIT_METRIC -> {
                        db.getCurrentWeatherMetricAsync()
                    }
                    UNIT_IMPERIAL -> {
                        db.getCurrentWeatherImperialAsync()
                    }
                    else -> null
                }!!

                val locationTokens = location.split(',')
                var locationShort = ""

                for (i in locationTokens.indices - 1) {
                    locationShort += locationTokens[i]
                    if (i != locationTokens.size - 1) {
                        locationShort += ", "
                    }
                }

                views.setTextViewText(R.id.widget_tv_temperature, "${weather.temperature}\u00B0")
                views.setImageViewResource(
                    R.id.widget_img_condition,
                    codeIconRes(weather.conditionCode)
                )

                views.setTextViewText(R.id.widget_tv_location, locationShort)
                appWidgetManager.updateAppWidget(appWidgetId, views)
            }

            val intentUpdate = Intent(context, WeatherWidget::class.java)
            intentUpdate.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
            val idArray = intArrayOf(appWidgetId)
            intentUpdate.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, idArray)
            val pendingUpdate = PendingIntent.getBroadcast(
                context,
                appWidgetId, intentUpdate, PendingIntent.FLAG_UPDATE_CURRENT
            )
            views.setOnClickPendingIntent(R.id.widget_layout, pendingUpdate)
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}
