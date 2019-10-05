#!/bin/bash

kotlinc Gregorian.kt Utils.kt -d gregorian.jar || exit 1
kotlin -classpath gregorian.jar GregorianKt

