(function (){
    //一、定义构造函数
    let ajax=function (){}
    //二、在构造函数的原型对象上添加函数(这样的函数在基于构造函数构建的js对象中共享)
    ajax.prototype={
        doAjaxGet:function (url,params,callback){
            //1.创建xhr对象(Ajax技术应用的入口)
            let xhr = new XMLHttpRequest();
            //2.设置状态监听(不是必须的,但是假如要获取服务端响应的结果并进行处理就要进行状态监听)
            xhr.onreadystatechange=function (){
                if(xhr.readyState==4 && xhr.status==200){
                    callback(xhr.responseText);
                }
            }
            //3.建立Get连接(get请求传参，要把参数拼接到url中)
            xhr.open("GET",`${url}?${params}`,true);
            //4.发送异步请求
            xhr.send(null);
        },
        doAjaxPost:function (url,params,callback){
            let xhr=new XMLHttpRequest();
            xhr.onreadystatechange=function(){
                if(xhr.readyState==4 && xhr.status==200){
                    callback(xhr.responseText);
                }
            }
            xhr.open("POST",url,true);
            //post请求假如需要向服务端传递参数,则必须在open之后设置请求头
            xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
            xhr.send(params);
        }
    }
    //三、基于ajax构造函数构建ajax对象，并将对象赋值给一个全局变量
    window.Ajax=new ajax();
})()
