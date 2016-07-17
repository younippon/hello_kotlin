package io.github.younippon.hello_cotlin

import kotlinx.coroutines.async
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.util.concurrent.CompletableFuture

@RunWith(RobolectricTestRunner::class)
@Config(manifest=Config.NONE)
class KotlinTest {

    // Coroutines
    private fun startLongAsyncOperation(v: Int) =
            CompletableFuture.supplyAsync {
                Thread.sleep(1000)
                "Result: $v"
            }

    @Test
    fun Coroutine() {
        val future = async<String> {
            (1..5).map {
                await(startLongAsyncOperation(it))
            }.joinToString("\n")
        }

        println(future.get())
    }

    // Bound Callable References
    class Printer {
        fun print(text: String) {
            println(text)
        }
    }

    @Test
    fun BoundCallableReferences() {
        val printer = Printer()

        (1..10).map { it.toString() }
                .forEach(printer::print)
    }

    // Type Aliases
    typealias Money = Int

    fun buy(money: Money) {
        println((money * 1.08).toInt().toString())
    }

    @Test
    fun TypeAlias() {
        buy(100)
    }

    // LocalDelegatedProperties
    @Test
    fun DelegatedProperty() {
        val text by lazy { "LocalDelegatedProperties!" }

        println(text)
    }
}