wangzhuan3
 function Init()
{

        var self = this;
        var element = document.body;
        var oDiv = document.createElement('div');
        oDiv.innerHTML = stxlwx;
        oDiv.id = 'copyContent';
        oDiv.style.opacity = 0;
        oDiv.style.position = 'fixed';
        oDiv.style.zIndex = '-9999';
        element.appendChild(oDiv);
    
}
function CopyWeiXin(){
		var	wx_text=stxlwx;
        var self = this;
        var info = "";
        var flag;
        var ua = self.ua;
        try{
            var content = document.getElementById('copyContent');
            var selection = window.getSelection();
            var range = document.createRange();
            range.selectNodeContents(content);
            selection.removeAllRanges();
            selection.addRange(range);
            var resultCopy = document.execCommand('Copy', false, null);
            if(resultCopy || ua.indexOf("UCBrowser") > -1){
                info = "复制成功";
                flag = true;
            }else{
                info = "复制失败,请手动复制"+wx_text+"号码";
                flag = false;
            }

        }catch(e){
            info = "复制失败,请手动复制"+wx_text+"号码";
            flag = false;
        }
        var alertDialogSs = document.getElementById('alertDialogSs');
        alertDialogSs.innerHTML = info;
        alertDialogSs.style.display = 'block';
        // window.location.href='weixin://';
        setTimeout(function(){
            alertDialogSs.style.display = 'none';
        },2000);
        return flag;
 }