package com.example.exam_helper

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class ExamHelperApplication

fun main(args: Array<String>) {
    runApplication<ExamHelperApplication>(*args)
}