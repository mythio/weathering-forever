package com.mythio.weather.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
import com.mythio.weather.R
import com.mythio.weather.db.AppDatabase
import com.mythio.weather.repository.WeatherRepository
import com.mythio.weather.utils.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ForecastWidget : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        Log.d("TAG_TAG_TAG", "update Forecast")

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

            Log.d("TAG_TAG_TAG", "update Forecast 2")
            val sharedPref =
                context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

            val location = sharedPref.getString(SHARED_PREF_KEY_LOCATION, DEFAULT_LOCATION)!!
            val unit = sharedPref.getInt(SHARED_PREF_KEY_UNIT, DEFAULT_UNIT)

            val db = AppDatabase.getInstance(context.applicationContext).weatherDao()
            val repository = WeatherRepository(db)
            val views = RemoteViews(context.packageName, R.layout.widget_forecast)

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

                val forecast = when (unit) {
                    UNIT_METRIC -> {
                        db.getForecastMetricAsync()
                    }
                    UNIT_IMPERIAL -> {
                        db.getForecastImperialAsync()
                    }
                    else -> null
                }!!

                views.setTextViewText(R.id.widget_tv_temperature, "${weather.temperature}\u00B0")
                views.setImageViewResource(
                    R.id.widget_iv_condition_1,
                    codeIconRes(forecast[0].conditionCode)
                )
                views.setImageViewResource(
                    R.id.widget_iv_condition_2,
                    codeIconRes(forecast[1].conditionCode)
                )
                views.setImageViewResource(
                    R.id.widget_iv_condition_3,
                    codeIconRes(forecast[2].conditionCode)
                )
                views.setImageViewResource(
                    R.id.widget_iv_condition_4,
                    codeIconRes(forecast[3].conditionCode)
                )
                views.setImageViewResource(
                    R.id.widget_img_condition,
                    codeIconRes(weather.conditionCode)
                )
                views.setTextViewText(R.id.widget_tv_location, location)
                appWidgetManager.updateAppWidget(appWidgetId, views)
            }

            val intentUpdate = Intent(context, ForecastWidget::class.java)
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
