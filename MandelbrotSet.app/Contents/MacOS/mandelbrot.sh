#!/bin/sh
JAR_DIR=`dirname $0`
java -Xdock:name="Mandelbrot Set in Java" -Xdock:icon=$JAR_DIR/../Resources/mandelbrot.icns -jar $JAR_DIR/mandelbrot.jar
