# xyzPlayJava

## 并发学习

并发是同一时间对应多件事情的能力。
并行是同一时间同时做多件事情的能力。

## 并行架构

### 线程与锁

内存可见性
Java内存模 型定义了何时一个线程对内存的修改对另一个线程可见。
双重检查锁模式
死锁与活锁
可中断锁
交替锁
原子变量
volatile(保证变量不被乱序执行)
ForkJoinPool与线程池
work-stealing
阿姆达尔定律
Java Concurrency in Practice\[Goe06\]

使用规则：
访问共享变量时按需同步
按照全局的固定顺序来获取多把锁
持有锁时避免调用外星方法


### 函数式编程

### 分离标识与状态

### Actor

http://akka.io


### 通信顺序进程 CSP

### 数据及并行

### Lambda架构



