plugins {
    java
    `maven-publish`
}

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://maven.enginehub.org/repo/")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.17.1-R0.1-SNAPSHOT")
    implementation("com.sk89q.worldedit:worldedit-core:7.2.9")
    implementation("com.sk89q.worldedit:worldedit-bukkit:7.2.9")
}

java {
    val javaVer = 16
    val javaVersion = JavaVersion.toVersion(javaVer)
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
    if (JavaVersion.current() != javaVersion) {
        toolchain.languageVersion.set(JavaLanguageVersion.of(javaVer))
    }
}

