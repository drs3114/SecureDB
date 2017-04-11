import com.github.play2war.plugin._

name := """SecureDBApp"""

version := "1.0-SNAPSHOT"

Play2WarPlugin.play2WarSettings

Play2WarKeys.servletVersion := "3.1"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.8"

libraryDependencies += jdbc

libraryDependencies += javaJdbc

libraryDependencies += evolutions

libraryDependencies += filters

libraryDependencies += javaJpa

libraryDependencies += "org.mockito" % "mockito-core" % "2.1.0"

libraryDependencies += javaWs % "test"

libraryDependencies += "org.hibernate" % "hibernate-core" % "5.2.5.Final"