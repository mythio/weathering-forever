```
                      __  __              _                   ____                              
 _      _____  ____ _/ /_/ /_  ___  _____(_)___  ____ _      / __/___  ________ _   _____  _____
| | /| / / _ \/ __ `/ __/ __ \/ _ \/ ___/ / __ \/ __ `/_____/ /_/ __ \/ ___/ _ \ | / / _ \/ ___/
| |/ |/ /  __/ /_/ / /_/ / / /  __/ /  / / / / / /_/ /_____/ __/ /_/ / /  /  __/ |/ /  __/ /    
|__/|__/\___/\__,_/\__/_/ /_/\___/_/  /_/_/ /_/\__, /     /_/  \____/_/   \___/|___/\___/_/     
                                              /____/                                            
```
[wip]: Weather application which caches weather forecast daily (Automatically!)

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/c78b8c8f27c9401980e505185cd0f884)](https://www.codacy.com/app/mythio/weathering-forever?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=mythio/weathering-forever&amp;utm_campaign=Badge_Grade)

* Current status:
<p align="center">
<img src="https://github.com/mythio/weathering-forever/blob/master/res/weather.png" width="200"> <img src="https://github.com/mythio/weathering-forever/blob/master/res/weather_refresh.png" width="200"> <img src="https://github.com/mythio/weathering-forever/blob/master/res/recent_search.png" width="200"> <img src="https://github.com/mythio/weathering-forever/blob/master/res/search_results.png" width="200"> <img src="https://github.com/mythio/weathering-forever/blob/master/res/settings.png" width="200"> <img src="https://github.com/mythio/weathering-forever/blob/master/res/weather_widget_small.png" width="200"> <img src="https://github.com/mythio/weathering-forever/blob/master/res/weather_widget_large.png" width="200">
</p>

### File structure
```
.
├── AndroidManifest.xml
├── ic_launcher-web.png
├── java
│   └── com
│       └── mythio
│           └── weather
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
│               │   ├── adapter
│               │   │   ├── DataBindingViewHolder.kt
│               │   │   ├── RecentLocationAdapter.kt
│               │   │   └── SearchLocationAdapter.kt
│               │   ├── SearchActivity.kt
│               │   ├── SettingsActivity.kt
│               │   └── WeatherActivity.kt
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
│               ├── widget
│               │   ├── ForecastWidget.kt
│               │   └── WeatherWidget.kt
│               └── work
│                   └── RefreshDataWorker.kt
└── res
    ├── drawable
    │   ├── ic_close.xml
    │   ├── ic_cloud_lightning.xml
    │   ├── ic_cloud_rain.xml
    │   ├── ic_cloud_snow.xml
    │   ├── ic_cloud.xml
    │   ├── ic_delete.xml
    │   ├── ic_humidity.xml
    │   ├── ic_launcher_foreground.xml
    │   ├── ic_map_pin.xml
    │   ├── ic_precipation.xml
    │   ├── ic_refresh.xml
    │   ├── ic_settings.xml
    │   ├── ic_sun.xml
    │   ├── ic_uv.xml
    │   └── ic_wind.xml
    ├── drawable-nodpi
    ├── font
    │   └── quicksand_medium.ttf
    ├── layout
    │   ├── activity_search.xml
    │   ├── activity_settings.xml
    │   ├── activity_weather.xml
    │   ├── item_forecast.xml
    │   ├── item_recent.xml
    │   ├── item_search.xml
    │   ├── widget_forecast.xml
    │   └── widget_weather.xml
    ├── mipmap-anydpi
    ├── mipmap-anydpi-v26
    │   ├── ic_launcher_round.xml
    │   └── ic_launcher.xml
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
    │   ├── ic_launcher_background.xml
    │   ├── strings.xml
    │   └── styles.xml
    ├── values-night
    │   ├── colors.xml
    │   └── styles.xml
    └── xml
        ├── forecast_widget_info.xml
        ├── preferences.xml
        └── weather_widget_info.xml
34 directories, 87 files
```
