#!/bin/bash

kotlinc Jalali.kt Utils.kt Date.kt -d jalali.jar || exit 1
kotlin -classpath jalali.jar JalaliKt

