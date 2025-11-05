rootProject.name = "showdium"

pluginManagement {
    repositories {
        maven("https://maven.fabricmc.net/")
        gradlePluginPortal()
    }
}

fun includeGroup(
    group: String,
    vararg modules: String,
) = modules.forEach {
    val first = "$group:$it"
    val second = first.replace(":", "-")
    include(first)
    project(":$first").name = second
}

includeGroup(
    "showdium",
    "common",
    "fabric",
    "paper",
)