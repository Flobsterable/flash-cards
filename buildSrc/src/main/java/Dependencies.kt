object Dependencies {

    object Compose {
        const val version = "1.4.0-alpha02"

        const val ui = "androidx.compose.ui:ui:$version"
        const val material = "androidx.compose.material3:material3:1.0.1"
        const val preview = "androidx.compose.ui:ui-tooling-preview:$version"

        const val composeJUnit = "androidx.compose.ui:ui-test-junit4:$version"
        const val composeUITooling = "androidx.compose.ui:ui-tooling:$version"
        const val composeManifest = "androidx.compose.ui:ui-test-manifest:$version"
        const val twyper = "com.github.theapache64:twyper:0.0.4"
        const val livedata = "androidx.compose.runtime:runtime-livedata:$version"
    }

    object Android {
        const val coreKtx = "androidx.core:core-ktx:1.9.0"
    }

    object Lifecycle {
        const val lifecycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:2.5.1"
        const val activityCompose = "androidx.activity:activity-compose:1.6.1"
    }

    object Test {
        const val jUnit = "junit:junit:4.13.2"
        const val androidJUnit = "androidx.test.ext:junit:1.1.5"
        const val espresso = "androidx.test.espresso:espresso-core:3.5.1"
    }

    object Detekt {
        const val detekt = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.22.0-RC2"
    }

    object Hilt {
        private const val version = "2.44"
        const val hilt = "com.google.dagger:hilt-android:$version"
        const val compiler = "com.google.dagger:hilt-android-compiler:$version"
        const val navigation = "androidx.hilt:hilt-navigation-compose:1.0.0"
        const val worker = "androidx.hilt:hilt-work:1.0.0"
    }

    object Navigation {
        private const val version = "2.5.3"
        const val navigation = "androidx.navigation:navigation-compose:$version"
    }

    object Room {
        private const val version = "2.4.3"
        const val room = "androidx.room:room-runtime:$version"
        const val annotationProcessor = "androidx.room:room-compiler:$version"
        const val kapt = "androidx.room:room-compiler:$version"
        const val ktx = "androidx.room:room-ktx:$version"
    }

    object Worker {
        const val worker = "androidx.work:work-runtime-ktx:2.7.1"
    }
}
