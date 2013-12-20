// code-examples/ToolsLibs/aspectj/LogComplex.aj

package toollibs.aspectj;

public aspect LogComplex {
  public pointcut newInstances(double real, double imag): 
    execution(Complex.new(..)) && args(real, imag);
    
  public pointcut plusInvocations(Complex self, Complex other): 
    execution(Complex Complex.$plus(Complex)) && this(self) && args(other);

  before(double real, double imag): newInstances(real, imag) {
    System.out.println("new Complex(" + real + "," + imag + ") called.");
  }
  
  before(Complex self, Complex other): plusInvocations(self, other) {
    System.out.println("Calling " + self + ".+(" + other + ")");
  }
  
  after(Complex self, Complex other) returning(Complex c): 
    plusInvocations(self, other) {
    System.out.println("Complex.+ returned " + c);
  }
}