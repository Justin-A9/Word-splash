package com.example.squash.data

import com.example.squash.datasource.SubCategories

class DataSource {

    fun loadMedicineOccupation() : ArrayList<SubCategories> {
        return arrayListOf(
            SubCategories("Nursing"),
            SubCategories("Doctor"),
            SubCategories("Medicine")

        )
    }

    fun loadSportOccupation() : ArrayList<SubCategories> {
        return arrayListOf(
            SubCategories("Soccer"),
            SubCategories("BasketBall"),
            SubCategories("Rugby")

        )
    }

    fun loadCountriesOccupation() : ArrayList<SubCategories> {
        return arrayListOf(
            SubCategories("Nigeria"),
            SubCategories("England"),
            SubCategories("America")

        )
    }

    fun loadFoodOccupation() : ArrayList<SubCategories> {
        return arrayListOf(
            SubCategories("Pastries"),
            SubCategories("Pastries"),
            SubCategories("Swallow"),
            SubCategories("Pastries"),

            SubCategories("Swallow")

        )
    }
}