plugins  {
    id("me.superpenguin.libraries.java-conventions")
    kotlin("jvm")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.9.0")
}

kotlin {
    jvmToolchain {
        java.sourceCompatibility = JavaVersion.VERSION_17
        java.targetCompatibility = JavaVersion.VERSION_17
    }
}


