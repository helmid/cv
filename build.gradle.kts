import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id(Plugin.Name.Spring.boot) version Version.Plugin.Spring.boot
	id(Plugin.Name.Spring.dependecyManagement) version Version.Plugin.Spring.dependencyManagement
	kotlin(Plugin.Name.Kotlin.jvm) version Version.Plugin.Kotlin.jvm
	kotlin(Plugin.Name.Kotlin.spring) version Version.Plugin.Kotlin.spring
	kotlin(Plugin.Name.Kotlin.serialization) version Version.Plugin.Kotlin.serialization
}

group = App.group
version = App.version

java.sourceCompatibility = Java.javaVersion

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation(Dependency.Spring.web)
	implementation(Dependency.Spring.security)
	implementation(Dependency.Spring.thymeleaf)
	implementation(Dependency.Spring.mail)
	implementation(Dependency.jackson)
	implementation(Dependency.Kotlin.reflect)
	implementation(Dependency.Kotlin.jdk)
	compileOnly(Dependency.lombok)
	annotationProcessor(Dependency.lombok)
	testImplementation(Dependency.Test.Spring.web)
	testImplementation(Dependency.Test.Spring.security)
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = Java.freeCompilerArgs
		jvmTarget = Java.jvmTarget
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}