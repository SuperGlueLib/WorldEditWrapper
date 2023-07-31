plugins {
    id("me.superpenguin.libraries.java-conventions")
}

group = "me.superpenguin.libraries"
version = "1.0.0"

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "me.superpenguin.superglue"
            artifactId = "worldeditwrapper-java"
            version = "1.0.0"

            from(components["java"])
        }
    }
}