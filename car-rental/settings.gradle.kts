plugins {
    id("com.gradle.enterprise").version("3.15.1") // Sync with `build-logic-commons/build-platform/build.gradle.kts`
    id("io.github.gradle.gradle-enterprise-conventions-plugin").version("0.7.6")
    id("org.gradle.toolchains.foojay-resolver-convention") version("0.7.0")
}

rootProject.name = "car-rental"
