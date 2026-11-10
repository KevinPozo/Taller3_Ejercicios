plugins {
    id("java")
}

group = "web"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

repositories {
    mavenCentral();
}
val lanchain4jVersion = "1.23.0"
dependencies{
    implementation("dev.langchain4j:langchain4j:$lanchain4jVersion")
    implementation("dev.langchain4j:langchain4j-embeddings:${lanchain4jVersion}-beta33")
}