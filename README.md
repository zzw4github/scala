# scala
https://www.coursera.org/learn/progfun1/lecture/7xLKH/tools-setup-for-linux

###scala sublime build
'''
{
    "cmd": ["scalac", "-d", "classes", "$file", "&", "scala", "-cp", "classes", "$file_base_name"],
    "selector": ["source.scala"],
    "shell": "true"
}
'''


http://ensime.github.io/editors/


先说说协变和逆变（实际上还有非变）。协变和逆变主要是用来解决参数化类型的泛化问题。由于参数化类型的参数（参数类型）是可变的，当两个参数化类型的参数是继承关系（可泛化），那被参数化的类型是否也可以泛化呢？Java中这种情况下是不可泛化的，然而Scala提供了三个选择，即协变、逆变和非变。下面说一下三种情况的含义，首先假设有参数化特征Queue，那它可以有如下三种定义。   
1. trait Queue[T] {} 
这是非变情况。这种情况下，当类型S是类型A的子类型，则Queue[S]不可认为是Queue[A]的子类型或父类型，这种情况是和Java一样的。 

2. trait Queue[+T] {} 
这是协变情况。这种情况下，当类型S是类型A的子类型，则Queue[S]也可以认为是Queue[A}的子类型，即Queue[S]可以泛化为Queue[A]。也就是被参数化类型的泛化方向与参数类型的方向是一致的，所以称为协变。 

3. trait Queue[-T] {} 
这是逆变情况。这种情况下，当类型S是类型A的子类型，则Queue[A]反过来可以认为是Queue[S}的子类型。也就是被参数化类型的泛化方向与参数类型的方向是相反的，所以称为逆变。
