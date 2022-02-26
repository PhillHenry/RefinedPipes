# An introduction to general refined types

All maths functions have a domain - that is, a class from which the argument is taken.
Let's take a simple function, the inverse function on real numbers. We'll call it```inverse```. We can define it as:

`inverse(x) = 1 / x where x ∈ ℝ ; x ≠ 0`

Let's attempt to write some Scala code to represent this:

```scala mdoc
def inverse(x: Double): Double = 1d / x
```

Now, let's call it:

```scala mdoc
val x = inverse(2.0)
```

Great! OK, let's be naughty:

```scala mdoc
inverse(0.0)
```

Well, that wasn't very clever. We've hit a [mathematical singularity](https://en.wikipedia.org/wiki/Singularity_(mathematics)).
The function is simply not defined at `x=0`. 

**Note that this is a problem at runtime.** You simple can't do anything with an `Infinity`.

To be fair, the `where` clause in the original equation did warn us.
So, how can we express this limitation in computer code?
This is where refined types come in. 
Let's write the same function this time refining the argument:

```scala mdoc
import eu.timepit.refined.api.Refined
import eu.timepit.refined.numeric._

type NonZeroDouble = Validated[GreaterThanDouble[0d] And LowerThanDouble[0d]]

def inverse_refined(x: NonZeroDouble): Double = 1d / x.asInstanceOf[Double]
```

The happy path is as before:

```scala mdoc
inverse_refined(2.0: NonZeroDouble)
```

But now, our efforts at being naughty are caught at *compile time*:

```scala mdoc:crash
inverse_refined(0.0)
```