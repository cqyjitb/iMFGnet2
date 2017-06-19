/**
 * Created by Rebs on 2017/6/13.
 */

/**
 * using for formatting the Date
 * @param fmt
 * @returns {*}
 */
Date.prototype.format = function(fmt) {
    var o = {
        "M+" : this.getMonth()+1,                 //月份
        "d+" : this.getDate(),                    //日
        "h+" : this.getHours(),                   //小时
        "m+" : this.getMinutes(),                 //分
        "s+" : this.getSeconds(),                 //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S"  : this.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt)) {
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }
    for(var k in o) {
        if(new RegExp("("+ k +")").test(fmt)){
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
    return fmt;
}

/**
 * using for setting cookie
 * @param name
 * @param value
 */
/*function setCookie(name,value) {
    document.cookie = name + "=" + value;
}*/
function setCookie(name,value)
{
    var Days = 30;
    var exp = new Date();
    exp.setTime(exp.getTime() + Days*24*60*60*1000);
    document.cookie = name + "="+ value + ";expires=" + exp.toGMTString()+"domain=1;path=/";
}

/**
 * using for getting cookie
 * @param name
 * @returns {*}
 */
/*function getCookie(name) {

    var value = null;

    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg)){
        value = arr[2];
    }
    return value;
}*/
function getCookie(name)
{
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg))
        return arr[2];
    else
        return null;
}

/**
 * using for deleting cookie
 * @param name
 * @returns {*}
 */
/*function delCookie(name) {
    var cval = getCookie(name);
    if(cval!=null){
        document.cookie = name + "=" + cval;
    }
}*/
function delCookie(name)
{
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval=getCookie(name);
    if(cval!=null)
        document.cookie= name + "="+cval+";expires="+exp.toGMTString()+"domain=1;path=/";
}

/**
 * using for getting the date of the same day
 * @returns {*}
 */
function getTodayDate() {
    return new Date().format("yyyy-MM-dd");
}

/**
 * using for getting the date of yesterday
 * @returns {*}
 */
function getYesterdayDate() {
    var yesterday = new Date();
    yesterday.setDate(yesterday.getDate()-1);
    return yesterday.format("yyyy-MM-dd");
}

/**
 * using for appending the mask Dialog
 */
function appendMaskDialog() {
    var oMaskD=document.createElement("div");
    oMaskD.id="mask";
    oMaskD.style.height="320px";
    oMaskD.style.width="240px";
    document.body.appendChild(oMaskD);
}

/**
 * using for removing the mask Dialog
 */
function removeMaskDialog() {
    var oMaskD=document.getElementById("mask");
    document.body.removeChild(oMaskD);
}

/**
 * using for translating Code
 */
function transCode(value) {
    return value;
}