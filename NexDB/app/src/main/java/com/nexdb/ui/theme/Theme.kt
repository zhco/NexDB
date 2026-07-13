package com.nexdb.ui.theme
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
private val LightColors=lightColorScheme(primary=Primary,secondary=Secondary,background=Background,surface=Surface,error=Error,onPrimary=OnPrimary,onBackground=OnBackground,onSurface=OnSurface)
private val DarkColors=darkColorScheme(primary=Primary,secondary=Secondary,background=DarkBackground,surface=DarkSurface,error=Error,onPrimary=OnPrimary,onBackground=DarkOnBackground,onSurface=DarkOnSurface)
@Composable fun NexDBTheme(darkTheme:Boolean=isSystemInDarkTheme(),content:@Composable ()->Unit){MaterialTheme(colorScheme=if(darkTheme)DarkColors else LightColors,content=content)}
