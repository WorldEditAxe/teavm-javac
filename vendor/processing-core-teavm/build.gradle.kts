plugins {
    id("java")
}

repositories {
    mavenCentral()
    maven { url = uri("https://jogamp.org/deployment/maven") }
}

sourceSets{
    main{
        java{
            srcDirs("src")
            include(
                "processing/core/*.java",
                "processing/data/*.java",
                "processing/event/*.java",
                "processing/opengl/PGL.java",
                "processing/opengl/PShader.java",
                "processing/platform/core/*.java",
                "processing/platform/teavm/*.java"
            )
            exclude("**/*.jnilib")
        }
        resources{
            srcDirs("src")
            exclude("**/*.java")
        }
    }
    test{
        java{
            srcDirs("test")
        }
    }
}

dependencies {
    implementation(files("library/jogl-all.jar", "library/gluegen-rt.jar"))
    compileOnly("org.teavm:teavm-jso:0.14.1")
    compileOnly("org.teavm:teavm-jso-apis:0.14.1")
    compileOnly("org.teavm:teavm-interop:0.14.1")

    testImplementation("junit:junit:4.13.2")
}


tasks.test {
    useJUnit()
}
tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    archiveFileName.set("processing-core-teavm.jar")
    destinationDirectory.set(layout.buildDirectory)
}
tasks.compileJava{
    options.encoding = "UTF-8"
}
tasks.javadoc{
    options.encoding = "UTF-8"
}
