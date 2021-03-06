__CreateJSPath = function (js) {
    var scripts = document.getElementsByTagName("script");
    var path = "";
    for (var i = 0, l = scripts.length; i < l; i++) {
        var src = scripts[i].src;
        if (src.indexOf(js) != -1) {
            var ss = src.split(js);
            path = ss[0];
            break;
        }
    }
    var href = location.href;
    href = href.split("#")[0];
    href = href.split("?")[0];
    var ss = href.split("/");
    ss.length = ss.length - 1;
    href = ss.join("/");
    if (path.indexOf("https:") == -1 && path.indexOf("http:") == -1 && path.indexOf("file:") == -1 && path.indexOf("\/") != 0) {
        path = href + "/" + path;
    }
    return path;
};

var bootPATH = __CreateJSPath("fastDev-import.js");
var fastDevPath = bootPATH;

//fastui
document.write("<link href='"+fastDevPath+"themes/default/css/fastui-default.1.5.1.6.css' rel='stylesheet' type='text/css' ><\/link>");
document.write("<script src='"+fastDevPath+"fastui-1.5.1.10.beta.js'><\/script>");
document.write("<script src='"+fastDevPath+"compile.js'><\/script>");
//validate Session Expire and other
document.write("<script src='"+fastDevPath+"common.js'><\/script>");
