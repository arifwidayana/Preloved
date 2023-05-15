import java.io.File
import java.io.FileInputStream
import java.util.*

object SigningHelper {
    // KEY PROPERTIES
    private const val KEY_PROPERTIES = "key.properties"

    const val RELEASE_STORE_FILE = "RELEASE_STORE_FILE"
//    const val RELEASE_STORE_FILE = "preloved.jks"
    const val RELEASE_STORE_PASSWORD = "RELEASE_STORE_PASSWORD"
    const val RELEASE_KEY_ALIAS = "RELEASE_KEY_ALIAS"
    const val RELEASE_KEY_PASSWORD = "RELEASE_KEY_PASSWORD"

    private val keyProperties by lazy {
        Properties().apply { load(FileInputStream(File(KEY_PROPERTIES))) }
    }

    fun getValue(key: String): String {
        return keyProperties.getProperty(key)
    }
}