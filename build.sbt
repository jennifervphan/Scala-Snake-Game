name := "scala_js_webapp"

version := "0.1"

scalaVersion := "2.13.2"

enablePlugins(ScalaJSPlugin)

// This is an application with a main method
scalaJSUseMainModuleInitializer := true
mainClass := Some("game.MainGame")

//Scala JS
libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "1.0.0"

//uTest
libraryDependencies += "com.lihaoyi" %%% "utest" % "0.7.4" % "test"
testFrameworks += new TestFramework("utest.runner.Framework")

jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv()
