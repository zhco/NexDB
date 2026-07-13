package com.nexdb.ui.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.*
import com.nexdb.ui.screens.*

@Composable fun NavGraph(navController:NavHostController){
NavHost(navController=navController,startDestination=Screen.ConnectionList.route){
composable(Screen.ConnectionList.route){ConnectionListScreen(navController=navController)}
composable(Screen.ConnectionEdit.route,arguments=listOf(navArgument("connectionId"){type=NavType.LongType})){be->ConnectionEditScreen(navController=navController,connectionId=be.arguments?.getLong("connectionId")?:-1)}
composable(Screen.SQLEditor.route,arguments=listOf(navArgument("connectionId"){type=NavType.LongType})){be->SQLEditorScreen(navController=navController,connectionId=be.arguments?.getLong("connectionId")?:0)}
composable(Screen.DataBrowser.route,arguments=listOf(navArgument("connectionId"){type=NavType.LongType},navArgument("database"){type=NavType.StringType},navArgument("table"){type=NavType.StringType})){be->DataBrowserScreen(navController=navController,connectionId=be.arguments?.getLong("connectionId")?:0,database=be.arguments?.getString("database")?:"",table=be.arguments?.getString("table")?:"")}
composable(Screen.TableStructure.route,arguments=listOf(navArgument("connectionId"){type=NavType.LongType},navArgument("database"){type=NavType.StringType},navArgument("table"){type=NavType.StringType})){be->TableStructureScreen(navController=navController,connectionId=be.arguments?.getLong("connectionId")?:0,database=be.arguments?.getString("database")?:"",table=be.arguments?.getString("table")?:"")}
composable(Screen.Export.route,arguments=listOf(navArgument("connectionId"){type=NavType.LongType})){be->ExportScreen(navController=navController,connectionId=be.arguments?.getLong("connectionId")?:0)}
composable(Screen.Chart.route,arguments=listOf(navArgument("connectionId"){type=NavType.LongType},navArgument("database"){type=NavType.StringType},navArgument("table"){type=NavType.StringType})){be->ChartScreen(navController=navController,connectionId=be.arguments?.getLong("connectionId")?:0,database=be.arguments?.getString("database")?:"",table=be.arguments?.getString("table")?:"")}
composable(Screen.AIAssistant.route,arguments=listOf(navArgument("connectionId"){type=NavType.LongType})){be->AIAssistantScreen(navController=navController,connectionId=be.arguments?.getLong("connectionId")?:0)}
}}
