package week3

class overrides {

}

abstract  class Base {
  def foo = 1
  def bar: Int
}

class Sub extends Base {
  override def foo = 2 //is mandatory if override
  def bar = 3
}
