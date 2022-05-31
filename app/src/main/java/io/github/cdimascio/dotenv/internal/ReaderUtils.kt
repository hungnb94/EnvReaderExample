package io.github.cdimascio.dotenv.internal

import java.io.Reader

object ReaderUtils {
    @JvmStatic
    fun readLines(reader: Reader): List<String> {
        return reader.readLines()
    }
}