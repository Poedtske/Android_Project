package com.example.android_project.domain.usecase.category

import com.example.android_project.api.CategoryApiService
import com.example.android_project.classes.CategoryVM
import com.example.android_project.utils.Exception


class UpsertCategoryUseCase(private val categoryApiService: CategoryApiService) {
    @Throws(Exception::class)
    suspend operator fun invoke(category: CategoryVM){
        if(category.name.isEmpty()){
            throw Exception("Name of food is empty")
        }else if(category.img.isEmpty()){
            throw Exception("Img is empty")
        }

        if(category.id==-1){
            categoryApiService.createCategory(category.toEntity())
        }else{
            categoryApiService.updateCategory(category.toEntity())
        }

    }
}