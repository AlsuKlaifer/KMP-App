package com.example.newsapp.di

import org.koin.core.module.Module

//хз можно сделать вот так для вью моделей, но разница будет лишь в том что по разному создаются вью модельки
//но имеет ли это смысл?
expect fun viewModelModule() : Module