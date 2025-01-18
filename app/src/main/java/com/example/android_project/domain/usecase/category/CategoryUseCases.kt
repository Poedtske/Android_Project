package com.example.android_project.domain.usecase.category

data class CategoryUseCases (
    val getCategories: GetCategoriesUseCase,
    val getCategory: GetCategoryUseCase,
    val upsertCategory: UpsertCategoryUseCase,
    val deleteCategory: DeleteCategoryUseCase
)