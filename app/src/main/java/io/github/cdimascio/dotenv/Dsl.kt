/**
 * Copyright (c) Carmine DiMascio 2017 - 2021
 * License: MIT
 */
package io.github.cdimascio.dotenv

import java.io.InputStream

/**
 * Configure dotenv
 */
fun dotenv(block: Configuration.() -> Unit = {}): Dotenv {
    val config = Configuration()
    block(config)
    val dotenv = Dotenv.configure()
//    dotenv.directory(config.directory)
//    dotenv.filename(config.filename)
    dotenv.inputStream(config.inputStream)
    if (config.ignoreIfMalformed) dotenv.ignoreIfMalformed()
    if (config.ignoreIfMissing) dotenv.ignoreIfMissing()
    if (config.systemProperties) dotenv.systemProperties()
    return dotenv.load()
}

/**
 * The dotenv configuration
 */
class Configuration {
    /**
     * Set the directory containing the env file
     */
    private var directory: String = "./"
    /**
     * Sets the name of the env. The default is env
     */
    private var filename: String = "env"

    var inputStream: InputStream? = null
    /**
     * Do not throw an exception when env is malformed
     */
    var ignoreIfMalformed = false
    /**
     * Do not throw an exception when env is missing
     */
    var ignoreIfMissing = false

    /**
     * Set env vars into System properties. Enables fetch them via e.g. System.getProperty(...)
     */
    var systemProperties = false
}
