val droolsVersion = "6.5.0.Final"
resolvers += "JBoss public" at "http://repository.jboss.org/nexus/content/groups/public/"

lazy val root = (project in file(".")).
  settings(
    name := "strudel",
    version := "1.0",
    scalaVersion := "2.10.6",
    libraryDependencies += "org.apache.spark" %% "spark-core" % "1.5.0",
    libraryDependencies += "org.apache.spark" %% "spark-streaming" % "1.5.0",
    libraryDependencies += "org.json4s" %% "json4s-ext" % "3.2.11",
    libraryDependencies += "org.json4s" %% "json4s-jackson" % "3.2.11",
    libraryDependencies += "org.scalaj" %% "scalaj-http" % "2.3.0",
    libraryDependencies ++= {
        "org.kie" % "kie-api" % droolsVersion ::
        List("drools-compiler", "drools-core", "drools-jsr94", "drools-decisiontables", "knowledge-api").map("org.drools" % _ % droolsVersion)
    }
  )
