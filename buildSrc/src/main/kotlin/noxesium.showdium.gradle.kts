plugins {
    `java-library`
}

configurations {
    register("commonJava") {
        isCanBeResolved = true
    }
}

dependencies {
    api(project(":showdium:showdium-common"))
    "commonJava"(project(":showdium:showdium-common", "commonJava"))
}

tasks {
    named<JavaCompile>("compileJava").configure {
        dependsOn(configurations.getByName("commonJava"))
        source(configurations.getByName("commonJava"))
    }
    named<Jar>("sourcesJar").configure {
        dependsOn(configurations.getByName("commonJava"))
        from(configurations.getByName("commonJava"))
    }
}