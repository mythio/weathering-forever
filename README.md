# weathering-forever
[wip]: Weather application which caches weather forecast daily (Automatically!)

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/c78b8c8f27c9401980e505185cd0f884)](https://www.codacy.com/app/mythio/weathering-forever?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=mythio/weathering-forever&amp;utm_campaign=Badge_Grade)

* Current status:
<p align="center">
<img src="https://github.com/mythio/weathering-forever/blob/master/res/weather.png" width="200"> <img src="https://github.com/mythio/weathering-forever/blob/master/res/weather_refresh.png" width="200"> <img src="https://github.com/mythio/weathering-forever/blob/master/res/recent_search.png" width="200"> <img src="https://github.com/mythio/weathering-forever/blob/master/res/search_results.png" width="200"> <img src="https://github.com/mythio/weathering-forever/blob/master/res/settings.png" width="200">
</p>

### File structure
```
.
├── AndroidManifest.xml
├── java
│   └── com
│       └── mythio
│           └── weather
│               ├── adapter
│               │   ├── DataBindingViewHolder.kt
│               │   ├── RecentLocationAdapter.kt
│               │   └── SearchLocationAdapter.kt
│               ├── db
│               │   ├── AppDatabase.kt
│               │   └── dao
│               │       ├── LocationDao.kt
│               │       └── WeatherDao.kt
│               ├── model
│               │   ├── domain
│               │   │   ├── CurrentWeather.kt
│               │   │   └── ForecastWeather.kt
│               │   └── entity
│               │       ├── Condition.kt
│               │       ├── current
│               │       │   ├── CurrentImperial.kt
│               │       │   ├── Current.kt
│               │       │   └── CurrentMetric.kt
│               │       ├── CurrentW.kt
│               │       ├── Day.kt
│               │       ├── forecast
│               │       │   ├── ForecastImperial.kt
│               │       │   ├── Forecast.kt
│               │       │   └── ForecastMetric.kt
│               │       ├── ForecastW.kt
│               │       └── Location.kt
│               ├── network
│               │   ├── response
│               │   │   ├── ForecastResponse.kt
│               │   │   └── WeatherResponse.kt
│               │   └── WeatherApiService.kt
│               ├── repository
│               │   ├── SearchRepository.kt
│               │   └── WeatherRepository.kt
│               ├── ui
│               │   ├── SearchActivity.kt
│               │   ├── SettingsActivity.kt
│               │   ├── WeatherActivity.kt
│               │   └── WeatherWidget.kt
│               ├── utils
│               │   ├── ActivityViewModelLazy.kt
│               │   ├── BindingAdapter.kt
│               │   ├── Constants.kt
│               │   ├── Converters.kt
│               │   └── InjectorUtils.kt
│               ├── viewmodels
│               │   ├── SearchViewModel.kt
│               │   └── WeatherViewModel.kt
│               ├── WeatherApplication.kt
│               └── work
│                   └── RefreshDataWorker.kt
└── res
    ├── drawable
    │   ├── bg_button_round.xml
    │   ├── ic_close.xml
    │   ├── ic_cloud_drizzle.xml
    │   ├── ic_cloud_lightning.xml
    │   ├── ic_cloud_rain.xml
    │   ├── ic_cloud_snow.xml
    │   ├── ic_cloud.xml
    │   ├── ic_delete.xml
    │   ├── ic_humidity.xml
    │   ├── ic_map_pin.xml
    │   ├── ic_precipation.xml
    │   ├── ic_refresh.xml
    │   ├── ic_search.xml
    │   ├── ic_settings.xml
    │   ├── ic_sun.xml
    │   ├── ic_uv.xml
    │   └── ic_wind.xml
    ├── drawable-nodpi
    │   └── example_appwidget_preview.png
    ├── font
    │   └── quicksand_medium.ttf
    ├── layout
    │   ├── activity_search.xml
    │   ├── activity_weather.xml
    │   ├── item_forecast.xml
    │   ├── item_recent.xml
    │   ├── item_search.xml
    │   ├── settings_activity.xml
    │   └── weather_widget.xml
    ├── mipmap-anydpi
    ├── mipmap-hdpi
    │   ├── ic_launcher.png
    │   └── ic_launcher_round.png
    ├── mipmap-mdpi
    │   ├── ic_launcher.png
    │   └── ic_launcher_round.png
    ├── mipmap-xhdpi
    │   ├── ic_launcher.png
    │   └── ic_launcher_round.png
    ├── mipmap-xxhdpi
    │   ├── ic_launcher.png
    │   └── ic_launcher_round.png
    ├── mipmap-xxxhdpi
    │   ├── ic_launcher.png
    │   └── ic_launcher_round.png
    ├── values
    │   ├── arrays.xml
    │   ├── colors.xml
    │   ├── dimens.xml
    │   ├── strings.xml
    │   └── styles.xml
    ├── values-night
    │   ├── colors.xml
    │   ├── dimens.xml
    │   ├── strings.xml
    │   └── styles.xml
    ├── values-v14
    │   └── dimens.xml
    └── xml
        ├── preferences.xml
        └── weather_widget_info.xml

33 directories, 87 files
```