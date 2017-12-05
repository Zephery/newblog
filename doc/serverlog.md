# 2017-09-23
服务器被黑，进程占用率高达99%，查看了一下，是/usr/sbin/bashd的原因，怎么删也删不掉，使用ps查看：  
![](http://image.wenzhihuai.com/images/553ac1ca20170923033013.png)  
stratum+tcp://get.bi-chi.com:3333 -u 47EAoaBc5TWDZKVaAYvQ7Y4ZfoJMFathAR882gabJ43wHEfxEp81vfJ3J3j6FQGJxJNQTAwvmJYS2Ei8dbkKcwfPFst8FhG   

使用top查看：  
![](http://image.wenzhihuai.com/images/20170923033252.png)

<div align="center">

![](http://image.wenzhihuai.com/images/20170923034427.png)

</div>

<div align="center">

![](http://image.wenzhihuai.com/images/20170923040503.png)

</div>

启动iptables,参考[http://www.setphp.com/981.html](http://www.setphp.com/981.html)
[http://www.setphp.com/981.html](http://www.setphp.com/981.html)

iptables -A INPUT -s xmr.crypto-pool.fr -j DROP
iptables -A OUTPUT -d xmr.crypto-pool.fr -j DROP


# 2017-10-02 再次遭到挖矿攻击

# 2017-11-20
19号添加mongodb之后，20号重启了服务器，但是忘记启动mongodb，导致后台一直在重连mongodb，也就导致了服务访问超级超级慢，记住要启动所需要的组件。

# 2017-12-03

<div align="center">

![](http://image.wenzhihuai.com/images/20171203010136.png)

</div>

# 2017-12-05 wipefs
<div align="center">

![](http://image.wenzhihuai.com/images/20171205091617.png)

</div>


