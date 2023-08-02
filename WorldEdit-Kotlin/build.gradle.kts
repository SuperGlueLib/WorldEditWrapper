plugins {
    id("me.superpenguin.libraries.kotlin-conventions")
}

group = "me.superpenguin.superglue"
version = "1.0.0"

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "me.superpenguin.superglue"
            artifactId = "worldeditwrapper-kotlin"
            version = "1.0.0"

            from(components["java"])
        }
    }
}