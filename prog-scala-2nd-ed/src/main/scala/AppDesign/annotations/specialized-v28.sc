// code-examples/AppDesign/annotations/specialized-v28-script.scala
class MyStack[@specialized T](val list: List[T]) {
    def push(t: T) = new MyStack(t :: list)
    def peek = list.head
    def pop = new MyStack(list.tail)
    
    override def toString = list.toString
}

val intList = new MyStack(List(1,2,3))
println(intList)
println(intList.peek)
println(intList.pop)