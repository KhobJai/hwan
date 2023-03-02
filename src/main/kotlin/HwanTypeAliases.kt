import kotlin.collections.LinkedHashMap as HashMap1

typealias MuteMap<K,V> = HashMap1<K,V>
typealias DeepMap = Map<String,Map<String,Any>>
typealias AnyMap = Map<*,*>
typealias NotFoundInMppException = RuntimeException
typealias Pred = () -> Boolean
