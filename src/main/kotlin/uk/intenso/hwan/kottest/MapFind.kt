package uk.intenso.hwan.kottest

import org.slf4j.LoggerFactory
import uk.intenso.hwan.cols.HwColUtils

class MapFind {
        private val log = LoggerFactory.getLogger(MapFind::class.java)

    fun findString(map: AnyMap, vararg keys: String?): String? {
        val result = findDeep(map, *keys)
        return if (result is String) {
            result
        } else {
            throw RuntimeException("Found Unexpected Type " + result.javaClass.simpleName)
        }
    }

    fun findList(map: AnyMap, vararg keys: String?): List<*>? {
        val result = findDeep(map, *keys)
        return if (result is List<*>) {
            result
        } else {
            throw RuntimeException("Found Unexpected Type " + result.javaClass.simpleName)
        }
    }

    private tailrec fun findDeep(map: AnyMap, vararg keys: String?): Any {
        if (keys == null || keys.size == 0) {
            log.debug("No more keys -  returning existing value")
            return map
        }
        val results = map[keys[0]]
        return when (results) {
            null -> {
                log.debug("Nothing found with key {}, returning map%n", keys[0])
                println("Returning Map")
                map
            }
            is List<*> -> {
                log.debug("Found list with Key {} - returning %n", keys[0])
                results
            }
            is Int -> {
                log.debug("Found int with Key {} - returning %n", keys[0])
                results
            }
            is Double -> {
                log.debug("Found double with Key {} - returning %n", keys[0])
                results
            }
            is String -> {
                log.debug("Found String with Key {} - returning %n", keys[0])
                results
            }
            is Map<*, *> -> {
                log.debug("Found Child Map with Key {}}%n", keys[0])
                findDeep(results, *HwColUtils.removeFirst(*keys))
            }
            else -> {
                throw RuntimeException("Found value of class " + results.javaClass.simpleName + " Don't know what to do yet...")
            }
        }
    }
}