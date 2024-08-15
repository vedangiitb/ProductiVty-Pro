package com.example.productivtypro.HomePage

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.productivtypro.Analytics.AnalyticsPage
import com.example.productivtypro.Analytics.FocusAnalytics
import com.example.productivtypro.Analytics.HabitAnalytics
import com.example.productivtypro.Analytics.TaskAnalytics
import com.example.productivtypro.DayPlanner.DayPlanner
import com.example.productivtypro.FocusMode.FocusSession
import com.example.productivtypro.Habits.AddNewHabits
import com.example.productivtypro.Habits.HabitPage
import com.example.productivtypro.Meditate.MeditatePage
import com.example.productivtypro.User.AccountSettings
import com.example.productivtypro.Settings.FocusSettings
import com.example.productivtypro.Settings.SettingsPage
import com.example.productivtypro.Task.Planned


enum class HomePage{
    Home,
    FocusSession,
    Planned,
    Habit,
    Analytics,
    Settings,
    FocusSettings,
    AccountSettings,
    addHabitPage,
    Meditate,
    DayPlanner,
    TaskAnalytics,
    FocusAnalytics,
    HabitAnalytics
}

@Composable
fun ProductiVtyPro(navController: NavHostController = rememberNavController()){
    NavHost(navController = navController, startDestination = HomePage.Home.name){
            composable(route= HomePage.Home.name){
                HomeScreen(navController)
            }

            composable(route= HomePage.FocusSession.name){
                FocusSession(navController)
            }
            composable(route= HomePage.Habit.name){
                HabitPage(navController)
            }
            composable(route= HomePage.Analytics.name){
                AnalyticsPage(navController)
            }
            composable(route= HomePage.AccountSettings.name){
                AccountSettings(navController)
            }
            composable(route= HomePage.Planned.name){
                Planned()
            }
            composable(route= HomePage.FocusSettings.name){
                FocusSettings()
            }
            composable(route = HomePage.Settings.name){
                SettingsPage()
            }
            composable(route = HomePage.addHabitPage.name){
                AddNewHabits(navController)
            }
            composable(route = HomePage.Meditate.name) {
                MeditatePage()
            }
            composable(route = HomePage.DayPlanner.name){
                DayPlanner()
            }

            composable(route = HomePage.TaskAnalytics.name){
                TaskAnalytics()
            }

            composable(route = HomePage.FocusAnalytics.name){
                FocusAnalytics()
            }

            composable(route = HomePage.HabitAnalytics.name){
                HabitAnalytics()
            }

        }
    }