function signOut(){
    window.close();
    return true;
}
var dayarray=new Array("Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday");
var montharray=new Array("JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC");

function getTheDate(){
    var mydate=new Date()
    var year=mydate.getFullYear()     
    var month=mydate.getMonth()
    var daym=mydate.getDate()
    if (daym<10) daym="0"+daym
    var hours=mydate.getHours()
    var minutes=mydate.getMinutes()
    var seconds=mydate.getSeconds()
    var tz=Math.round(mydate.getTimezoneOffset()/60)

    var tmZone=8;
    tz+=tmZone;
    var hourss=hours+tz;
    hourss=hourss>=24?(hourss-24):hourss;
    hourss=hourss<=0?(hourss+24):hourss;

    if (hourss==24) hourss=0

    var dn="AM"
      
    if (hours>=12)
    {
        dn="PM"
        hours-=12
    }
    if (hours==0) hours=12

    if (hourss>=12)
    {
        dns="PM"
        hourss-=12
    }

    if (hourss==0) hourss=12
    if (minutes<=9) minutes="0"+minutes
    if (seconds<=9) seconds="0"+seconds
     
    var cdate1=daym+" "+montharray[month]+" "+year+",  "+hours+":"+minutes+":"+seconds+" "+dn;

    if (document.getElementById) {
        document.getElementById("clock").innerHTML=cdate1;
    }  
    else if (document.all) {
        document.all.clock.style.position='relative';
        document.all.clock.innerHTML=cdate1;
    }
    else if (document.layers) {
        document.clock.visibility='show'
        document.clock.document.open();
        document.clock.document.write('<center>'+cdate1+'</center>');
        document.clock.document.close();
    }
    
    else document.write('<center>'+cdate1+'</center>')  
}
setInterval("getTheDate()",1000);
