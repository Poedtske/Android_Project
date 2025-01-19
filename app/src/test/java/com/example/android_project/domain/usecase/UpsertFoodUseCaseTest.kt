package com.example.android_project.domain.usecase

import com.example.android_project.classes.Course
import com.example.android_project.classes.FoodCategory
import com.example.android_project.domain.model.FoodItem
import com.example.android_project.domain.usecase.food.UpsertFoodUseCase
import com.example.android_project.utils.Exception
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class UpsertFoodUseCaseTest {

    lateinit var upsertFoodUseCase: UpsertFoodUseCase
    val mockDatabase= MockDatabase()

    @Before
    fun setUp() {
        upsertFoodUseCase = UpsertFoodUseCase(mockDatabase)
    }

    @Test
    fun `Food is add if fields are valid`() {
        runBlocking {
            upsertFoodUseCase(FoodItem(1,"Spaghetti","placeholder",12.5,FoodCategory.PASTA,Course.MAIN,true))
            val foods= mockDatabase.getFood().first()
            assertEquals(1,foods.size)
        }
    }

    @Test(expected = Exception::class)
    fun `Food should not be added because invallid name`() {
        runBlocking {
            upsertFoodUseCase(FoodItem(1,"","placeholder",12.5,FoodCategory.PASTA,Course.MAIN,true))
        }
    }

    @Test(expected = Exception::class)
    fun `Food should not be added because invallid price`() {
        runBlocking {
            upsertFoodUseCase(FoodItem(1,"Spaghetti","placeholder",0.0,FoodCategory.PASTA,Course.MAIN,true))
        }
    }

    @Test(expected = Exception::class)
    fun `Food should not be added because invallid price and name`() {
        runBlocking {
            upsertFoodUseCase(FoodItem(1,"","placeholder",0.0,FoodCategory.PASTA,Course.MAIN,true))
        }
    }
}