// src/main/scala/progscala2/metaprogramming/match-types.sc
// NOTE: This example is actually nonsense. I don't know what I was thinking.
// implicitly(seq.head) just returns seq.head and that call to `getClass` does
// the real work I was intending `ClassTag` to do. So, ignore this and look at
// mkArray.sc in this package instead, which is far better.
import scala.reflect.ClassTag

def useClassTag[T : ClassTag](seq: Seq[T]): String = seq match { // <1>
  case Nil => "Nothing"
  case head +: _ => implicitly(seq.head).getClass.toString           // <2>
}

def check(seq: Seq[_]): String =                                     // <3>
  s"Seq: ${useClassTag(seq)}"

Seq(Seq(5.5,5.6,5.7), Seq("a", "b"),                                 // <4>
    Seq(1, "two", 3.14), Nil) foreach {
  case seq: Seq[_] => println("%20s:  %s".format(seq, check(seq)))
  case x           => println("%20s:  %s".format(x, "unknown!"))
}
