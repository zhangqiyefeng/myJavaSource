## GC和堆内存

```
1.机器物理内存>=1g时,JVM默认的最大堆内存是机器物理内存的四分之一,
  机器物理内存<1g时,JVM默认的最大堆内存是机器物理内存的二分之一;
  
2.指定最大堆内存越小,发生Full GC的频率越大,说明堆内存不够用时会频繁执行Full GC来释放内存;
  当指定最大堆内存非常小时,不仅会频繁执行Full GC,还可能回收不了大的对象,会大概率造成堆内存溢出错误;

3.java8默认的GC策略是并行GC;

4.串行化的GC是单线程的,效率较低;

5.young->old 默认晋升阈值为15次,打开自适应参数时,就不一定了,JVM会根据自己的算法动态调整各种容量的大小和其他一些参数;
  
6.加大执行最大堆内存,会减少GC发生次数,但每次GC处理的数据量会大很多,造成每次GC暂停时间加长;

7.删除-Xms初始堆内存大小后,一开始设置的大小不会很大,单位时间内导致更快地发生Full GC;
  执行完Full GC后,JVM会扩大堆内存容量大小,经过N次扩大会达到设置的最大堆内存;

8.CMS GC只针对于Old区的回收;

9.G1 GC是比较复杂的GC,在CMS之上进行的改进,堆内存4G以上,强烈推荐使用,能够有效降低每次GC暂停时间,但会降低单位时间吞吐量;

10.正常系统:分配速率较低 ~~ 回收速率 -> 健康;
   内存泄露:分配速率较高 持续大于 回收速率 -> OOM;
   性能劣化:分配速率较高 ~~ 回收速率 -> 亚健康;

11.过早提升:对象存活时间还不够长就被晋升到了老年代;
   MajorGC不是为了频繁GC而设计,如果MajorGC还要处理生命短暂的对象,导致GC暂停时间过长,严重影响系统吞吐量;

等待补充...
```
