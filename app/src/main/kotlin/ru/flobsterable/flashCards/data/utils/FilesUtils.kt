package ru.flobsterable.flashCards.data.utils

import java.io.BufferedInputStream
import java.io.File
import java.util.zip.ZipFile

private const val BUFFER_SIZE = 1024

fun unzip(zipFile: File, targetPath: String): List<String> {
    val zip = ZipFile(zipFile, 1)
    val enumeration = zip.entries()
    val dirList: MutableList<String> = mutableListOf()
    while (enumeration.hasMoreElements()) {
        val entry = enumeration.nextElement()
        val destFilePath = File(targetPath, entry.name)
        destFilePath.parentFile?.mkdirs()

        if (entry.isDirectory) {
            dirList.add(entry.toString())
            continue
        }

        val bufferedIs = BufferedInputStream(zip.getInputStream(entry))

        bufferedIs.use {
            destFilePath.outputStream().buffered(BUFFER_SIZE).use { bos ->
                bufferedIs.copyTo(bos)
            }
        }
    }
    return dirList
}

fun findFileByExtension(file: File, extension: String): File? {
    file.walk().forEach { subFile ->
        if (subFile.extension == extension) {
            return subFile
        }
    }
    return null
}
