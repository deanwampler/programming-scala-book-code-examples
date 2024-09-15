package progscala3.typesystem.intersectionunion

object IntersectionUnion:

    trait M:
        def m(s: String): String = s
    trait T1 extends M:
        override def m(s: String): String = s"[ ${super.m(s)} ]"
    trait T2 extends M:
        override def m(s: String): String = s"( ${super.m(s)} )"
    trait T3 extends M:
        override def m(s: String): String = s"| ${super.m(s)} |"
    open class C extends M:
        override def m(s: String): String = s"{ ${super.m(s)} }"

    val c123 = new C with T1 with T2 with T3
    val c321 = new C with T3 with T2 with T1

    def checkM(): Unit =
        val m: M = new C
        assert(m.m("hello") == "{ hello }", m.m("hello"))

        assert(c123.m("hello") == "| ( [ { hello } ] ) |")
        assert(c321.m("hello") == "[ ( | { hello } | ) ]")

    def checkIntersectionCommutativity(): Unit =
        val ct1t2t3_c123: C & T1 & T2 & T3 = c123
        val t1ct2t3_c123: T1 & C & T2 & T3 = c123
        val t1t2ct3_c123: T1 & T2 & C & T3 = c123
        val t1t2t3c_c123: T1 & T2 & T3 & C = c123

        val ct3t2t1_c123: C & T3 & T2 & T1 = c123
        val t3ct2t1_c123: T3 & C & T2 & T1 = c123
        val t3t2ct1_c123: T3 & T2 & C & T1 = c123
        val t3t2t1c_c123: T3 & T2 & T1 & C = c123

        val ct1t2t3_c321: C & T1 & T2 & T3 = c321
        val t1ct2t3_c321: T1 & C & T2 & T3 = c321
        val t1t2ct3_c321: T1 & T2 & C & T3 = c321
        val t1t2t3c_c321: T1 & T2 & T3 & C = c321

        val ct3t2t1_c321: C & T3 & T2 & T1 = c321
        val t3ct2t1_c321: T3 & C & T2 & T1 = c321
        val t3t2ct1_c321: T3 & T2 & C & T1 = c321
        val t3t2t1c_c321: T3 & T2 & T1 & C = c321

    def checkIntersectionSubtyping(): Unit =
        val t1a: T1 = c123
        val t2a: T2 = c123
        val t3a: T3 = c123
        val c2a: C  = c123

        val t123: T1 & T2 & T3 = c123
        val ct1: C & T1 = c123
        val ct2: C & T2 = c123
        val ct3: C & T3 = c123

    def checkIntersectionFunctionUsage(): Unit =
        def f(t123: T1 & T2 & T3): String = t123.m("hello!")
        val list123: Seq[T1 & T2 & T3] = Seq(c123, c321)
        assert(list123.map(f) == List("| ( [ { hello! } ] ) |", "[ ( | { hello! } | ) ]"))

    def checkIntersectionCovariance(): Unit =
        val listt1t2t3: Seq[T1 & T2 & T3] = Seq(c123, c321)
        val list1: Seq[T1] = listt1t2t3
        val list2: Seq[T2] = listt1t2t3
        val list3: Seq[T3] = listt1t2t3
        val list123: Seq[T1] & Seq[T2] & Seq[T3] = listt1t2t3

        // f(list1.head)   // ERROR: "Found T1, Required T1 & T2 & T3"
        // f(list2.head)   // ERROR: "Found T2, Required T1 & T2 & T3"

    case class Bad(message: String)
    case class Good(i: Int)

    def checkUnionGoodBad(): Unit =
        val error = Bad("Failed!")
        val result = Good(0)

        val seq1 = Seq(error, result)   // Inferred type: Seq[T1nyRef] or Seq[Object]!
        val seq: Seq[Good | Bad] = Seq(error, result)

        def work(i: Int): Good | Bad =
        if i > 0 then Bad(s"$i must be <= 0") else Good(i)

        def process(result: Good | Bad): String = result match
        case Bad(message) => message
        case Good(value) => s"Success! value = $value"

        val results = Seq(0, 1).map(work)
        val strings = results.map(process)
        println(s"results = ${results.mkString(", ")}, strings = ${strings.mkString(", ")}")
    
    def checkUnionLaws(): Unit =
        summon[(T1 & (T2 | T3)) =:= ((T1 & T2) | (T1 & T3))]
        summon[(T1 | (T2 & T3)) =:= ((T1 | T2) & (T1 | T3))]

        val x1:  T1 & (T2 | T3)        = new T1 with T2 {}
        val x2:  T1 & (T2 | T3)        = new T1 with T3 {}
        val x3:  T1 & (T2 | T3)        = new T1 with T2 with T3 {}
        val x4:  (T1 & T2) | (T1 & T3) = new T1 with T2 {}
        val x5:  (T1 & T2) | (T1 & T3) = new T1 with T3 {}
        val x6:  (T1 & T2) | (T1 & T3) = new T1 with T2 with T3 {}

        val x7:  T1 | (T2 & T3)        = new T1 {}
        val x8:  T1 | (T2 & T3)        = new T2 with T3 {}
        val x9:  T1 | (T2 & T3)        = new T1 with T2 with T3 {}
        val x10: (T1 | T2) & (T1 | T3) = new T1 {}
        val x11: (T1 | T2) & (T1 | T3) = new T2 with T3 {}
        val x12: (T1 | T2) & (T1 | T3) = new T1 with T2 with T3 {}

    def checkUnionCovariance(): Unit =  
        val seqT1s: Seq[T1] = Seq(new T1 {})
        val seqT2s: Seq[T2] = Seq(new T2 {})
        val seqT3s: Seq[T3] = Seq(new T3 {})
        val seqT1T2T3s1: Seq[T1 | T2 | T3] = seqT1s
        val seqT1T2T3s2: Seq[T1 | T2 | T3] = seqT2s
        val seqT1T2T3s3: Seq[T1 | T2 | T3] = seqT3s

        val tT1T2T3s: Seq[T1 | T2 | T3] = Seq(new T1 {}, new T2 {}, new T3 {})
        // val tT1s: Seq[T1] = tT1T2T3s        // ERROR
        // val tT2s: Seq[T2] = tT1T2T3s        // ERROR
        // val tT3s: Seq[T3] = tT1T2T3s        // ERROR

    def checkUnionContravariantFunctions(): Unit = 
        val fT1T2T31: (T1 | T2 | T3) => String = _ match
            case t1: T1 => "T1"
            case t2: T2 => "T2"
            case t3: T3 => "T3"
        val fT1T2T32: (T1 => String) & (T2 => String) & (T3 => String) = fT1T2T31

        val seqT1T2T3s: Seq[T1 | T2 | T3] = Seq(new T1 {}, new T2 {}, new T3 {})
        seqT1T2T3s.map(fT1T2T31)
        seqT1T2T3s.map(fT1T2T32)
        seqT1T2T3s.map((x: AnyRef) => s"<$x>")

    def main(args: Array[String]): Unit =
        checkM()
        checkIntersectionCommutativity()
        checkIntersectionSubtyping()
        checkIntersectionFunctionUsage()
        checkIntersectionCovariance()
        checkUnionGoodBad()
        checkUnionLaws()
        checkUnionCovariance()
        checkUnionContravariantFunctions()